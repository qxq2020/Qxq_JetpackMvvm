package com.qxq.qxq_jetpackmvvm.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.qxq.qxq_jetpackmvvm.app.network.apiService
import com.qxq.qxq_jetpackmvvm.app.network.stateCallback.ListDataUiState
import com.qxq.qxq_jetpackmvvm.data.repository.request.httpRequestCoroutine
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.demo.data.model.bean.AriticleResponse
import me.hgj.jetpackmvvm.demo.data.model.bean.BannerResponse
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 *  name： qs
 *  date： 2022/6/1 17:59
 *  desc：有两种回调方式：
 * 1.首页文章列表 将返回的数据放在ViewModel中过滤包装给activity/fragment去使用
 * 2.首页轮播图 将返回的数据直接给activity/fragment去处理使用
 * 可以根据个人理解与喜好使用(建议 简单的不需要做数据过滤包装的能直接用返回数据的可以直接用2   复杂的需要自己封装一下让使用变的更方便的可以使用1  )
 */
class RequestHomeViewModel : BaseViewModel() {

    //页码 首页数据页码从0开始
    var pageNo = 0

    //首页文章列表数据
    var homeDataState: MutableLiveData<ListDataUiState<AriticleResponse>> = MutableLiveData()

    //首页轮播图数据
    var bannerData: MutableLiveData<ResultState<ArrayList<BannerResponse>>> = MutableLiveData()

    /**
     * 获取首页文章列表数据
     * @param isRefresh 是否是刷新，即第一页
     */
    fun getHomeData(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0
        }
        request({ httpRequestCoroutine.getHomeData(pageNo) }, {
            pageNo++
            val listDataUiState = ListDataUiState(
                isSuccess = true,
                isRefresh = isRefresh,
                isEmpty = it.isEmpty(),
                hasMore = it.hasMore(),
                isFirstEmpty = isRefresh && it.isEmpty(),
                listData = it.datas
            )
            homeDataState.value = listDataUiState
        }, {
            val listDataUiState = ListDataUiState(
                isSuccess = true,
                errMessage = it.errorMsg,
                isRefresh = isRefresh,
                listData = arrayListOf<AriticleResponse>()
            )
            homeDataState.value = listDataUiState
        })
    }

    /**
     * 获取轮播图数据
     */
    fun getBannerData() {
        request({ apiService.getBanner() }, bannerData)
    }

}