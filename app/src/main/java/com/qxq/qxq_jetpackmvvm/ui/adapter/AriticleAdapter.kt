package com.qxq.qxq_jetpackmvvm.ui.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qxq.qxq_jetpackmvvm.R
import com.qxq.qxq_jetpackmvvm.app.ext.setAdapterAnimation
import com.qxq.qxq_jetpackmvvm.app.ext.util.toHtml
import com.qxq.qxq_jetpackmvvm.app.util.SettingUtil
import com.qxq.qxq_jetpackmvvm.app.weight.customview.CollectView
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.demo.data.model.bean.AriticleResponse
import me.hgj.jetpackmvvm.ext.util.toHtml

/**
 *  name： qs
 *  date： 2022/7/13 18:02
 *  desc： 主页adapter
 */
class AriticleAdapter(data: MutableList<AriticleResponse>?) :
    BaseDelegateMultiAdapter<AriticleResponse, BaseViewHolder>(data) {
    private val Ariticle = 1//文章类型
    private val Project = 2//项目类型 本来打算不区分文章和项目布局用统一布局的，但是布局完以后发现差异化蛮大的，所以还是分开吧
    private var showTag = false//是否展示标签 tag 一般主页才用的到

    constructor(data: MutableList<AriticleResponse>?, showTag: Boolean) : this(data) {
        this.showTag = showTag
    }

    init {
        setAdapterAnimation(SettingUtil.getListMode())
        // 第一步，设置代理
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<AriticleResponse>() {
            override fun getItemType(data: List<AriticleResponse>, position: Int): Int {
                //根据是否有图片 判断为文章还是项目，好像有点low的感觉。。。我看实体类好像没有相关的字段，就用了这个，也有可能是我没发现
                return if (TextUtils.isEmpty(data[position].envelopePic)) Ariticle else Project
            }
        })
        // 第二步，绑定 item 类型
        getMultiTypeDelegate()?.apply {
            addItemType(Ariticle, R.layout.item_ariticle)
            addItemType(Ariticle, R.layout.item_project)
        }
    }

    override fun convert(holder: BaseViewHolder, item: AriticleResponse) {
        when (holder.itemViewType) {
            Ariticle -> {
                //文章布局的赋值
                item.run {
                    holder.setText(R.id.item_home_author,if(author.isNotEmpty()) author else shareUser)
                    holder.setText(R.id.item_home_content,title.toHtml())
                    holder.setText(R.id.item_home_type2, "$superChapterName·$chapterName".toHtml())
                    holder.setText(R.id.item_home_date, niceDate)
                    holder.getView<CollectView>(R.id.item_home_collect).isChecked = collect
                    if (showTag) {
                        //展示标签
                        holder.setGone(R.id.item_home_new, !fresh)
                        holder.setGone(R.id.item_home_top, type != 1)
                        if (tags.isNotEmpty()) {
                            holder.setGone(R.id.item_home_type1, false)
                            holder.setText(R.id.item_home_type1, tags[0].name)
                        } else {
                            holder.setGone(R.id.item_home_type1, true)
                        }
                    } else {
                        //隐藏所有标签
                        holder.setGone(R.id.item_home_top, true)
                        holder.setGone(R.id.item_home_type1, true)
                        holder.setGone(R.id.item_home_new, true)
                    }
                }
                holder.getView<CollectView>(R.id.item_home_collect)
                    .setOnCollectViewClickListener(object : CollectView.OnCollectViewClickListener {
                        override fun onClick(v: CollectView) {
                            collectAction.invoke(item, v, helper.adapterPosition)
                        }
                    })
            }
            Project -> {


            }
        }
    }

}