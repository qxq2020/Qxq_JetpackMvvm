package com.qxq.qxq_jetpackmvvm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.qxq.qxq_jetpackmvvm.R
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
class WelcomeActivity : BaseActivity<BaseViewModel, ActivityWelcomeBinding>(),
    View.OnClickListener {

    private var resList = listOf<Int>(R.drawable.qxq, R.drawable.qxq, R.drawable.qxq)

    override fun initView(savedInstanceState: Bundle?) {
        //防止出现按Home键回到桌面时，再次点击重新进入该界面bug
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return
        }
        mDatabind.click = this
        if (CacheUtil.isFirst()) {
            //是第一次打开App 显示引导页
            mDatabind.bannerView.setAdapter(WelcomeBannerAdapter());
            mDatabind.bannerView.apply {
                setAdapter(WelcomeBannerAdapter())
                setLifecycleRegistry(lifecycle)
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        if (position == resList.size - 1) {
                            mDatabind.welcomeJoin.visibility = View.VISIBLE
                        } else {
                            mDatabind.welcomeJoin.visibility = View.GONE
                        }
                    }
                })
                create(resList)
            }
        } else {
            //不是第一次打开App 0.3秒后自动跳转到主页
            mDatabind.bannerView.postDelayed({
                toMain()
            }, 300)
        }
    }

    override fun onClick(v: View?) {
        toMain()
    }

    private fun toMain() {
        CacheUtil.setFirst(false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        //带点渐变动画
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}