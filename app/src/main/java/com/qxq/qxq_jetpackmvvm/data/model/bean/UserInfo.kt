package com.qxq.qxq_jetpackmvvm.data.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  name： qs
 *  date： 2022/4/20 19:42
 *  desc： 账户信息
 */

@Parcelize
data class UserInfo(
    var admin: Boolean = false,
    var chapterTops: List<String> = listOf(),
    var collectIds: MutableList<String> = mutableListOf(),
    var email: String = "",
    var icon: String = "",
    var id: String = "",
    var nickname: String = "",
    var password: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = ""
) : Parcelable
