package com.qxq.qxq_jetpackmvvm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.qxq.qxq_jetpackmvvm.app.base.BaseActivity
import com.qxq.qxq_jetpackmvvm.app.util.CacheUtil
import com.qxq.qxq_jetpackmvvm.app.weight.banner.WelcomeBannerAdapter
import com.qxq.qxq_jetpackmvvm.databinding.ActivityWelcomeBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 *  name： qs
 *  date： 2022/5/10 13:55
 *  desc： 欢迎界面
 */
class WelcomActivity : BaseActivity<BaseViewModel, ActivityWelcomeBinding>(), View.OnClickListener {

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.setClick(this)
        //防止出现按Home键回到桌面时，再次点击重新进入该界面bug
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return
        }
        if (CacheUtil.isFirst()) {

            mDatabind.bannerView.setAdapter(WelcomeBannerAdapter());
            mDatabind.bannerView.apply {
                setAdapter(WelcomeBannerAdapter())
                setLifecycleRegistry(lifecycle)


            }

        } else {


        }

    }

    override fun onClick(v: View?) {


    }


}