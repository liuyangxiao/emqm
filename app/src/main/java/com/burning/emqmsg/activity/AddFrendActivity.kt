package com.burning.emqmsg.activity

import com.burning.emqmsg.R
import kotlinx.android.synthetic.main.back_title.*

class AddFrendActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_add_frend2
    override fun init() {
        tv_title.text = "好友添加"

    }
}
