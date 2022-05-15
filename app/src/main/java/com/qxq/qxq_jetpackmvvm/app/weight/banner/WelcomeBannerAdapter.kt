package com.qxq.qxq_jetpackmvvm.app.weight.banner

import android.view.View
import com.qxq.qxq_jetpackmvvm.R
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * @author: qs
 * @date: 2022/5/15
 * @Description: 欢迎界面适配器
 */
class WelcomeBannerAdapter : BaseBannerAdapter<String, WelcomeBannerViewHolder>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_itemwelcome
    }

    override fun createViewHolder(itemView: View, viewType: Int): WelcomeBannerViewHolder {
        return WelcomeBannerViewHolder(itemView)
    }

    override fun onBind(
        holder: WelcomeBannerViewHolder?,
        data: String?,
        position: Int,
        pageSize: Int
    ) {
        holder?.bindData(data, position, pageSize);
    }

}