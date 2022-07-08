package com.qxq.qxq_jetpackmvvm.data.repository.request

import com.qxq.qxq_jetpackmvvm.app.network.ApiService
import com.qxq.qxq_jetpackmvvm.app.network.apiService
import com.qxq.qxq_jetpackmvvm.app.util.CacheUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import me.hgj.jetpackmvvm.demo.data.model.bean.ApiPagerResponse
import me.hgj.jetpackmvvm.demo.data.model.bean.ApiResponse
import me.hgj.jetpackmvvm.demo.data.model.bean.AriticleResponse
import me.hgj.jetpackmvvm.demo.data.model.bean.UserInfo
import me.hgj.jetpackmvvm.network.AppException
import me.hgj.jetpackmvvm.network.BaseResponse

/**
 *  name： qs
 *  date： 2022/6/8 19:13
 *  desc： 处理协程的请求类
 */

val httpRequestCoroutine: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManger()
}

class HttpRequestManger {

    /**
     * 获取首页文章数据
     */
    suspend fun getHomeData(
        pagNo: Int): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        //同时异步请求2个接口，请求完成后合并数据
        return withContext(Dispatchers.IO) {
            val listData = async { apiService.getAritrilList(pagNo) }
            //如果App配置打开了首页请求置顶文章，且是第一页
            if (CacheUtil.isNeedTop() && pagNo == 0) {
                val top = async { apiService.getTopAritrilList() }
                listData.await().data.datas.addAll(0, top.await().data)
                listData.await()
            } else {
                listData.await()
            }
        }
    }

    /**
     * 注册并登陆
     */
    suspend fun register(user: String, password: String): BaseResponse<UserInfo> {
        val register = apiService.register(user, password, password)
        //判断注册结果 注册成功，调用登录接口
        if (register.isSucces()) {
            return apiService.login(user, password)
        } else {
            //抛出错误异常·
            throw AppException(register.errorCode, register.errorMsg)
        }
    }

    /**
     * 获取项目标题数据
     */
    suspend fun getProjectData(page: Int, cid: Int,
                               isNew: Boolean): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        if (isNew) {
            return apiService.getProjecNewData(page)
        } else {
            return apiService.getProjecDataByType(page, cid)
        }
    }


}