package com.qxq.qxq_jetpackmvvm.app

import com.qxq.qxq_jetpackmvvm.app.event.AppViewModel
import com.qxq.qxq_jetpackmvvm.app.event.EventViewModel
import com.tencent.mmkv.MMKV
import me.hgj.jetpackmvvm.base.BaseApp

/**
 *  name： qs
 *  date： 2022/4/18 19:19
 *  desc： 主Application
 */
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


    }


}