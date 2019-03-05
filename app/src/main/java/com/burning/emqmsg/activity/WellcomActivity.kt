package com.burning.emqmsg.activity

import android.content.Intent
import android.os.Handler
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.LoginsPo
import kotlinx.android.synthetic.main.activity_wellcom.*

class WellcomActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_wellcom

    override fun init() {
        Glide.with(this)
                .load(R.mipmap.mq_cc_1)
                // .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(wellcom_iv)
        val findFirst = realm.where(LoginsPo::class.java).equalTo("status", 100).findFirst()
        if (findFirst != null) {
            UserInfo.userid = findFirst.userid
        }
        Handler().postDelayed({
            if (UserInfo.userid != null && UserInfo.userid != 0L) {
                startActivity(Intent(this@WellcomActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@WellcomActivity, LoginActivity::class.java))
            }
            finish()
        }, 3000)
    }
}
