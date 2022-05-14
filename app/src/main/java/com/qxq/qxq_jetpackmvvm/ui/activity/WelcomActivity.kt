package com.qxq.qxq_jetpackmvvm.ui.activity

import android.os.Bundle
import android.view.View
import com.qxq.qxq_jetpackmvvm.app.base.BaseActivity
import com.qxq.qxq_jetpackmvvm.databinding.ActivityWelcomeBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 *  name： qs
 *  date： 2022/5/10 13:55
 *  desc： 欢迎界面
 */
class WelcomActivity :BaseActivity<BaseViewModel,ActivityWelcomeBinding>(), View.OnClickListener {

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.setClick(this)

    }

    override fun onClick(v: View?) {


    }


}