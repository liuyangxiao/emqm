package com.burning.emqmsg.activity

import com.burning.emqmsg.R
import kotlinx.android.synthetic.main.back_title.*

class SetUserInfoActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_set_user_info

    companion object {
        var SET_USER = "USER_INFO"
    }

    override fun init() {
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)
        val intExtra = intent.getIntExtra(SET_USER, 0)
        tv_title.text = when (intExtra) {
            1 -> "修改性别"
            2 -> "修改年龄"
            3 -> "修改昵称"
            4 -> "修改签名"
            else -> {
                finish()
                "--"
            }
        }


    }

}
