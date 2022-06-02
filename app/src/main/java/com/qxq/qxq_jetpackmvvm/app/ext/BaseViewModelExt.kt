package com.qxq.qxq_jetpackmvvm.app.ext

import androidx.lifecycle.MutableLiveData
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.network.BaseResponse
import me.hgj.jetpackmvvm.state.ResultState

/**
 *  name： qs
 *  date： 2022/6/1 18:19
 *  desc： BaseViewModel请求协程封装
 */


/**
 * net request 不校验请求结果数据是否是成功
 * @param block 请求体方法
 * @param resultState 请求回调的ResultState数据
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.request(block: suspend () -> BaseResponse<T>,
                              resultState: MutableLiveData<ResultState<T>>,
                              isShowDialog: Boolean = false, loadingMessage: String = "请求网络中..."){

}