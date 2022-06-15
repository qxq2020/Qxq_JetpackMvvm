package com.qxq.qxq_jetpackmvvm.data.repository.request

import me.hgj.jetpackmvvm.demo.data.model.bean.ApiPagerResponse
import me.hgj.jetpackmvvm.demo.data.model.bean.ApiResponse
import me.hgj.jetpackmvvm.demo.data.model.bean.AriticleResponse

/**
 *  name： qs
 *  date： 2022/6/8 19:13
 *  desc： 处理协程的请求类
 */




class HttpRequestManger {

    /**
     * 获取首页文章数据
     */
    suspend fun getHomeData(page:Int): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        //同时异步请求2个接口，请求完成后合并数据
    }




}