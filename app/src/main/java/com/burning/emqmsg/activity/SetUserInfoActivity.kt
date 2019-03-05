package com.burning.emqmsg.activity

import android.content.Intent
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.method.NumberKeyListener
import android.widget.Toast
import com.burning.emqmsg.R
import kotlinx.android.synthetic.main.activity_set_user_info.*
import kotlinx.android.synthetic.main.back_title.*

class SetUserInfoActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_set_user_info

    companion object {
        var SET_USER = "USER_INFO"
    }

    override fun init() {
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)
        val intExtra = intent.getIntExtra(SET_USER, 0)
        iv_right.text = "保存"
        btn_back.text = "取消"
        btn_back.setOnClickListener {
            finish()
        }

        iv_right.setOnClickListener {

            if (activity_setuser.text == null || TextUtils.isEmpty(activity_setuser.text)) {
                Toast.makeText(this@SetUserInfoActivity, "空内容", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var intent = Intent()
            intent.putExtra(UserinfoActivity.SET_USER_STRING, intExtra)//返回-
            intent.putExtra(SET_USER, activity_setuser.text.toString())//返回-
            setResult(RESULT_OK, intent)
            finish()
        }
        tv_title.text = when (intExtra) {
            1 -> {
                activity_setuser.hint = Editable.Factory.getInstance().newEditable("性别")
                activity_tinput.hint = Editable.Factory.getInstance().newEditable("性别")
                "修改性别"
            }
            2 -> {
                activity_setuser.hint = Editable.Factory.getInstance().newEditable("年龄")
                activity_tinput.hint = Editable.Factory.getInstance().newEditable("年龄")
                activity_setuser.inputType = InputType.TYPE_CLASS_PHONE
                val numberKeyListener = object : NumberKeyListener() {
                    override fun getInputType(): Int {
                        return android.text.InputType.TYPE_CLASS_PHONE;
                    }

                    override fun getAcceptedChars(): CharArray {
                        return charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
                    }

                }
                "修改年龄"
            }
            3 -> {
                activity_setuser.hint = Editable.Factory.getInstance().newEditable("昵称")
                activity_tinput.hint = Editable.Factory.getInstance().newEditable("昵称")
                "修改昵称"
            }
            4 -> {
                activity_setuser.hint = Editable.Factory.getInstance().newEditable("签名")
                activity_tinput.hint = Editable.Factory.getInstance().newEditable("签名")
                "修改签名"
            }
            else -> {
                finish()
                "--"
            }
        }

    }

}
