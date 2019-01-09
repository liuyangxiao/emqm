package com.burning.emqmsg.activity

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.burning.emqmsg.R
import com.burning.emqmsg.service.Mqservices
import com.burning.realmdatalibrary.httpservice.impl.UserApimpl
import com.burning.realmdatalibrary.httpservice.requbean.LoginBean
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity() {

    override fun getActivityLayout(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })
        email_sign_in_button.setOnClickListener { attemptLogin() }

        val intent = Intent(this, Mqservices::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }


    }

    private fun attemptLogin() {
        showProgress(true)
        var loginBean = LoginBean()
        loginBean.loginname = email.text.toString()
        loginBean.password = password.text.toString()

        UserApimpl().login(loginBean) { code, msg, _ ->
            if (code == 200) {
                startMyActivity(MainActivity::class.java)
                showProgress(false)
                //    startService(Intent(this, RtService::class.java))
                //  finish()
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


}
