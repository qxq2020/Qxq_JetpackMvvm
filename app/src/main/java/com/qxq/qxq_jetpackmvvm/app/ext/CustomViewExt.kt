package com.qxq.qxq_jetpackmvvm.app.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * @author: qs
 * @date: 2022/5/14
 * @Description:
 */


/**
 * 隐藏软键盘
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}