package com.qxq.qxq_jetpackmvvm.app.ext

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.qxq.qxq_jetpackmvvm.R
import com.qxq.qxq_jetpackmvvm.app.util.SettingUtil
import com.qxq.qxq_jetpackmvvm.ui.fragment.home.HomeFragment
import com.qxq.qxq_jetpackmvvm.ui.fragment.me.MeFragment
import com.qxq.qxq_jetpackmvvm.ui.fragment.project.ProjectFragment
import com.qxq.qxq_jetpackmvvm.ui.fragment.publicNumber.PublicNumberFragment
import com.qxq.qxq_jetpackmvvm.ui.fragment.tree.TreeArrFragment
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.EmptyCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.ErrorCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.LoadingCallback

/**
 * @author: qs
 * @date: 2022/5/14
 * @Description:项目中自定义类的拓展函数
 */
fun LoadService<*>.setErrorText(message: String) {
    if (message.isNotEmpty()) {
        setCallBack(ErrorCallback::class.java) { context, view ->
            view.findViewById<TextView>(R.id.error_text).text = message
        }
    }
}

/**
 * 设置错误布局
 * @param message 错误布局显示的提示内容
 */
fun LoadService<*>.showError(message: String = "") {
    this.setErrorText(message)
    this.showCallback(ErrorCallback::class.java)
}

/**
 * 设置空布局
 */
fun LoadService<*>.showEmpty() {
    this.showCallback(EmptyCallback::class.java)
}

/**
 * 设置加载中
 */
fun LoadService<*>.showLoading() {
    this.showCallback(LoadingCallback::class.java)
}


fun loadServiceInit(view: View, callback: () -> Unit): LoadService<Any> {
    val loadSir = LoadSir.getDefault().register(view) {
        callback.invoke()
    }
    loadSir.showSuccess()
    SettingUtil.setLoadingColor(SettingUtil.getColor(appContext), loadSir)
    return loadSir
}


/**
 * 隐藏软键盘
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}

/**
 * 初始化主界面
 */
fun ViewPager2.initMain(fragment: Fragment): ViewPager2 {
    //是否可滑动
    this.isUserInputEnabled = false
    this.offscreenPageLimit = 5
    //设置适配器
    adapter = object : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 5
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> {
                    return HomeFragment()
                }
                1 -> {
                    return ProjectFragment()
                }
                2 -> {
                    return TreeArrFragment()
                }
                3 -> {
                    return PublicNumberFragment()
                }
                4 -> {
                    return MeFragment()
                }
                else -> {
                    return HomeFragment()
                }
            }
        }
    }
    return this
}

/**
 * 初始化BottomNavigationViewEx
 */
fun BottomNavigationViewEx.init(
    navigationItemSelectedAction: (Int) -> Unit): BottomNavigationViewEx {

    enableAnimation(true)
    enableShiftingMode(false)
    enableItemShiftingMode(true)
    itemIconTintList = SettingUtil.getColorStateList(SettingUtil.getColor(appContext))
    itemTextColor = SettingUtil.getColorStateList(appContext)
    setTextSize(12f)
    setOnNavigationItemSelectedListener {
        navigationItemSelectedAction.invoke(it.itemId)
        true
    }
    return this
}

/**
 * 拦截BottomNavigation长按事件 防止长按时出现Toast
 * @receiver BottomNavigationViewEx
 * @param ids IntArray
 */
fun BottomNavigationViewEx.interceptLongClick(vararg ids: Int) {
    val bottomNavigationMenuView: ViewGroup = (this.getChildAt(0) as ViewGroup)
    for (index in ids.indices) {
        bottomNavigationMenuView.getChildAt(index).findViewById<View>(ids[index])
            .setOnLongClickListener { true }
    }

}

/**
 * 根据控件的类型设置主题，注意，控件具有优先级， 基本类型的控件建议放到最后，像 Textview，FragmentLayout，不然会出现问题，
 * 列如下面的BottomNavigationViewEx他的顶级父控件为FragmentLayout，如果先 is Fragmentlayout判断在 is BottomNavigationViewEx上面
 * 那么就会直接去执行 is FragmentLayout的代码块 跳过 is BottomNavigationViewEx的代码块了
 */
fun setUiTheme(color: Int, vararg anyList: Any?) {
    anyList?.forEach {
        it?.let {
            when (it) {
                is LoadService<*> -> SettingUtil.setLoadingColor(color, it as LoadService<Any>)
                is SwipeRefreshLayout -> it.setColorSchemeColors(color)
                is BottomNavigationViewEx -> {
                    it.itemIconTintList = SettingUtil.getColorStateList(color)
                    it.itemTextColor = SettingUtil.getColorStateList(color)
                }
                is Toolbar -> it.setBackgroundColor(color)
                is TextView -> it.setTextColor(color)
                is LinearLayout -> it.setBackgroundColor(color)
                is ConstraintLayout -> it.setBackgroundColor(color)
                is FrameLayout -> it.setBackgroundColor(color)
            }
        }
    }
}




