package com.qxq.qxq_jetpackmvvm.ui.fragment.home

import android.os.Bundle
import com.kingja.loadsir.core.LoadService
import com.qxq.qxq_jetpackmvvm.app.base.BaseFragment
import com.qxq.qxq_jetpackmvvm.app.ext.loadServiceInit
import com.qxq.qxq_jetpackmvvm.app.ext.showLoading
import com.qxq.qxq_jetpackmvvm.databinding.FragmentHomeBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 *  name： qs
 *  date： 2022/5/20 10:26
 *  desc： 主页
 */
class HomeFragment : BaseFragment<BaseViewModel, FragmentHomeBinding>() {

    //界面状态管理者
    private lateinit var loadsir:LoadService<Any>



    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = loadServiceInit(mDatabind.includeList.includeRecyclerview.swipeRefresh){
            //点击重试时触发的操作
            loadsir.showLoading()


        }



    }

}