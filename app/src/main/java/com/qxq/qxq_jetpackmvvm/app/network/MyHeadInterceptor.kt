package com.qxq.qxq_jetpackmvvm.app.network

import com.qxq.qxq_jetpackmvvm.app.util.CacheUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 *  name： qs
 *  date： 2022/7/4 10:32
 *  desc： 自定义头部参数拦截器，传入heads
 */
class MyHeadInterceptor :Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        newBuilder.addHeader("token", "token123456").build()
        newBuilder.addHeader("device", "Android").build()
        newBuilder.addHeader("isLogin", CacheUtil.isLogin().toString()).build()
        return chain.proceed(newBuilder.build())
    }

}