package com.burning.emqmsg.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.NewFrendAdapter
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.httpservice.impl.FrendApimpl
import com.zyao89.view.zloading.ZLoadingDialog
import com.zyao89.view.zloading.Z_TYPE
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.back_title.*

/**
 * Created by burning on 2019/1/10.
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
-------------------------//┏┓　　　┏┓
-------------------------//┏┛┻━━━┛┻┓
-------------------------//┃　　　　　　　┃ 　
-------------------------//┃　　　━　　　┃
-------------------------//┃　┳┛　┗┳　┃
-------------------------//┃　　　　　　　┃
-------------------------//┃　　　┻　　　┃
-------------------------//┃　　　　　　　┃
-------------------------//┗━┓　　　┏━┛
-------------------------//┃　　　┃  神兽保佑　　　　　　　　
-------------------------//┃　　　┃  代码无BUG！
-------------------------//┃　　　┗━━━┓
-------------------------//┃　　　　　　　┣┓
-------------------------//┃　　　　　　　┏┛
-------------------------//┗┓┓┏━┳┓┏┛
-------------------------// ┃┫┫　┃┫┫
-------------------------// ┗┻┛　┗┻┛
 */
class NewFrendActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_group
    var api = FrendApimpl()
    override fun init() {
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)
        tv_title.text = "好友请求"
        activity_group_rv.layoutManager = LinearLayoutManager(this)

        val dialog = ZLoadingDialog(this)
        dialog.setLoadingBuilder(Z_TYPE.SNAKE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLUE)//颜色
                .setHintText("Loading...")
                .show()
        api.getAddlist(UserInfo.userid) { code, msg, data ->
            dialog.dismiss()
            if (data != null && !data.isEmpty())
                activity_group_rv.adapter = NewFrendAdapter(this, data)
            else {

            }
        }
    }

}