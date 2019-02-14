package com.burning.emqmsg.activity

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.burning.emqmsg.R
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.httpservice.impl.UserApimpl
import com.burning.realmdatalibrary.httpservice.requbean.LoginBean
import com.burning.realmdatalibrary.po.LoginsPo
import com.burning.realmdatalibrary.po.UserPo
import com.burning.realmdatalibrary.redao.RxReamlUtils
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity() {

    override fun getActivityLayout(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        val findFirst = realm.where(UserPo::class.java).equalTo("id", 222).findFirst()
        Logger.d("====findFirst==$findFirst")
        //  login_content.setPadding(login_content.left, login_content.top + actionBarHeight, login_content.right, login_content.bottom)
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })
        showProgress(false)
        email_sign_in_button.setOnClickListener { attemptLogin() }
        signup_tv.setOnClickListener {
            startActivityForResult(Intent(this@LoginActivity, SignupActivity::class.java), 300)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 300 && resultCode == 200) {
            var loginBean = LoginBean()
            loginBean.loginname = data?.getStringExtra("loginname")
            loginBean.password = data?.getStringExtra("password")
            login(loginBean)
        }

    }

    private fun attemptLogin() {
        showProgress(true)
        var loginBean = LoginBean()
        loginBean.loginname = email.text.toString()
        loginBean.password = password.text.toString()
        login(loginBean)

    }

    private fun login(loginBean: LoginBean) {
        UserApimpl().login(loginBean) { code, msg, _ ->
            if (code == 200) {
                startMyActivity(MainActivity::class.java)
                showProgress(false)
                RxReamlUtils.updata {
                    //登入成功 记录标识
                    var findFirst = it.where(LoginsPo::class.java).equalTo("userid", UserInfo.userid).findFirst()
                    if (findFirst == null) {
                        findFirst = it.createObject(LoginsPo::class.java, UserInfo.userid)
                    }
                    findFirst.status = 100
                }
                finish()
            } else {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                showProgress(false)
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        if (show) {
            login_loading.show()
        } else {
            login_loading.hide()
        }
    }

    override fun onResume() {
        super.onResume()
        particleView.resume()
    }

    override fun onPause() {
        super.onPause()
        particleView.pause()
    }

}
