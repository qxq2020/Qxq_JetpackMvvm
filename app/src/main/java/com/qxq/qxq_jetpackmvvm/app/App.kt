package com.qxq.qxq_jetpackmvvm.app

import androidx.multidex.MultiDex
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.qxq.qxq_jetpackmvvm.app.event.AppViewModel
import com.qxq.qxq_jetpackmvvm.app.event.EventViewModel
import com.tencent.mmkv.MMKV
import me.hgj.jetpackmvvm.base.BaseApp
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.EmptyCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.ErrorCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.LoadingCallback

/**
 *  name： qs
 *  date： 2022/4/18 19:19
 *  desc： 主Application
 */
//Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
val appViewModel: AppViewModel by lazy { App.appViewModelInstance }
//Application全局的ViewModel，用于发送全局通知操作
val eventViewModel:EventViewModel by lazy { App.eventViewModelInstance }

class App : BaseApp() {

    companion object {
        lateinit var instance: App
        lateinit var eventViewModelInstance: EventViewModel
        lateinit var appViewModelInstance: AppViewModel
    }


    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(filesDir.absolutePath + "/mmkv")
        instance = this
        eventViewModelInstance = getAppViewModelProvider().get(EventViewModel::class.java)
        appViewModelInstance = getAppViewModelProvider().get(AppViewModel::class.java)
        MultiDex.install(this)
        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())
            .addCallback(ErrorCallback())
            .addCallback(EmptyCallback())
            .setDefaultCallback(SuccessCallback::class.java)
            .commit()

    }


}