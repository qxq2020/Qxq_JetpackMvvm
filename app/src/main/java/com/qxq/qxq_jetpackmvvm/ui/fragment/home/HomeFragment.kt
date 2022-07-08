package com.qxq.qxq_jetpackmvvm.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.kingja.loadsir.core.LoadService
import com.qxq.qxq_jetpackmvvm.R
import com.qxq.qxq_jetpackmvvm.app.base.BaseFragment
import com.qxq.qxq_jetpackmvvm.app.ext.init
import com.qxq.qxq_jetpackmvvm.app.ext.loadServiceInit
import com.qxq.qxq_jetpackmvvm.app.ext.showLoading
import com.qxq.qxq_jetpackmvvm.databinding.FragmentHomeBinding
import com.qxq.qxq_jetpackmvvm.viewmodel.request.RequestHomeViewModel
import com.qxq.qxq_jetpackmvvm.viewmodel.state.HomeViewModel
import kotlinx.android.synthetic.main.include_toolbar.view.*
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction

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
            requestHomeViewModel.getBannerData()
            requestHomeViewModel.getHomeData(true)
        }
        //初始化
        mDatabind.includeToolbar.toolbar.run {
            init("首页")
            inflateMenu(R.menu.home_menu)
            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.home_search->{
                        nav().navigateAction(R.id.action_mainfragment_to_searchFragment)
                    }
                }
                true
            }
        }


    }

}