package com.burning.emqmsg.activity

import android.content.Intent
import android.widget.Toast
import com.burning.emqmsg.R
import com.burning.realmdatalibrary.httpservice.impl.UserApimpl
import com.burning.realmdatalibrary.httpservice.requbean.LoginBean
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.back_title.*

class SignupActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_signup

    override fun init() {
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)
        tv_title.text = "注册"
        login_loading.hide()
        email_sign_in_button.setOnClickListener {
            var loginBean = LoginBean()
            loginBean.loginname = email.text.toString()
            loginBean.password = password.text.toString()
            UserApimpl().signup(loginBean) { code, msg, _ ->
                if (code == 100) {
                    Toast.makeText(this@SignupActivity, "注册失败$msg", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent()
                    intent.putExtra("loginname", loginBean.loginname)
                    intent.putExtra("password", loginBean.password)
                    setResult(200, intent)
                    finish()
                }
            }

        }
    }

}
