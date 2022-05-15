package com.qxq.qxq_jetpackmvvm.app.weight.banner

/**
 * 作者　: qs
 * 时间　: 2022/5/15
 * 描述　:
 */

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qxq.qxq_jetpackmvvm.R
import com.zhpan.bannerview.BaseViewHolder

class WelcomeBannerViewHolder(view: View) : BaseViewHolder<String>(view) {
    override fun bindData(data: String?, position: Int, pageSize: Int) {
       /* val ivbanner = findView<ImageView>(R.id.iv_banner)
        ivbanner.setImageResource(data)*/
        val textView = findView<TextView>(R.id.banner_text)
        textView.text = data
    }

}
