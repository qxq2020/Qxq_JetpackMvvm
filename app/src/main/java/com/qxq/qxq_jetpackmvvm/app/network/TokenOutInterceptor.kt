package com.qxq.qxq_jetpackmvvm.app.network

import android.content.Intent
import com.google.gson.Gson
import com.qxq.qxq_jetpackmvvm.ui.activity.TestActivity
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.demo.data.model.bean.ApiResponse
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

/**
 *  name： qs
 *  date： 2022/7/5 18:58
 *  desc： token过期拦截器
 */
class TokenOutInterceptor : Interceptor {
    val gson: Gson by lazy { Gson() }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return if (response.body() != null && response.body()!!.contentType() != null) {
            val mediaType = response.body()!!.contentType();
            val string = response.body()!!.string()
            val responseBody = ResponseBody.create(mediaType, string)
            val apiResponse = gson.fromJson(string, ApiResponse::class.java)
            if (apiResponse.errorCode == 9999) {
                appContext.startActivity(Intent(appContext, TestActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }
            response.newBuilder().body(responseBody).build()
        } else {
            response
        }
    }
}