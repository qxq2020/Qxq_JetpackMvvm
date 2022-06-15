package com.qxq.qxq_jetpackmvvm.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.kingja.loadsir.core.LoadService
import com.qxq.qxq_jetpackmvvm.app.base.BaseFragment
import com.qxq.qxq_jetpackmvvm.app.ext.loadServiceInit
import com.qxq.qxq_jetpackmvvm.app.ext.showLoading
import com.qxq.qxq_jetpackmvvm.databinding.FragmentHomeBinding
import com.qxq.qxq_jetpackmvvm.viewmodel.request.RequestHomeViewModel
import com.qxq.qxq_jetpackmvvm.viewmodel.state.HomeViewModel
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 *  name： qs
 *  date： 2022/5/20 10:26
 *  desc： 主页
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    private val requestHomeViewModel: RequestHomeViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = loadServiceInit(mDatabind.includeList.includeRecyclerview.swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showLoading()
            requestHomeViewModel.bannerData

        }


    }

}