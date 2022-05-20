package com.qxq.qxq_jetpackmvvm.ui.fragment

import android.os.Bundle
import com.qxq.qxq_jetpackmvvm.R
import com.qxq.qxq_jetpackmvvm.app.appViewModel
import com.qxq.qxq_jetpackmvvm.app.base.BaseFragment
import com.qxq.qxq_jetpackmvvm.app.ext.init
import com.qxq.qxq_jetpackmvvm.app.ext.initMain
import com.qxq.qxq_jetpackmvvm.app.ext.interceptLongClick
import com.qxq.qxq_jetpackmvvm.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 *  name： qs
 *  date： 2022/5/20 9:39
 *  desc： 项目主页Fragment
 */
class MainFragment : BaseFragment<BaseViewModel, FragmentMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        //初始化viewpager2
        mainViewpager.initMain(this)
        mainBottom.init {
            when (it) {
                R.id.menu_main -> mainViewpager.setCurrentItem(0, false)
                R.id.menu_project -> mainViewpager.setCurrentItem(1, false)
                R.id.menu_system -> mainViewpager.setCurrentItem(2, false)
                R.id.menu_public -> mainViewpager.setCurrentItem(3, false)
                R.id.menu_me -> mainViewpager.setCurrentItem(4, false)
            }
        }
        mainBottom.interceptLongClick(
            R.id.menu_main,
            R.id.menu_project,
            R.id.menu_system,
            R.id.menu_public,
            R.id.menu_me
        )
    }

    override fun createObserver() {
        appViewModel.appColor.observeInFragment(this, {

        })
    }

}