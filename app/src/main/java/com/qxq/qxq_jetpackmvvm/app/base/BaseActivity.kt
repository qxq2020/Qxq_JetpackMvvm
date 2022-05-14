package com.qxq.qxq_jetpackmvvm.app.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.qxq.qxq_jetpackmvvm.app.ext.dismissDialogExt
import com.qxq.qxq_jetpackmvvm.app.ext.showLoadingExt
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 *  name： qs
 *  date： 2022/5/11 13:57
 *  desc： 你项目中的Activity基类，在这里实现显示弹窗，吐司，还有加入自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmActivity例如
 * abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    abstract override fun initView(savedInstanceState: Bundle?)

    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    override fun dismissLoading() {
        dismissDialogExt()
    }

    /**
     * 创建liveData观察者
     */
    override fun createObserver() {
    }
}