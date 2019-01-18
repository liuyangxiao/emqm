package com.burning.emqmsg.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.UserinfoActivity
import com.burning.realmdatalibrary.po.UserPo
import kotlinx.android.synthetic.main.fragment_msg_item.view.*

/**
 * Created by burning on 2018/10/26.
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * -------------------------//┏┓　　　┏┓
 * -------------------------//┏┛┻━━━┛┻┓
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┃　　　━　　　┃
 * -------------------------//┃　┳┛　┗┳　┃
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┃　　　┻　　　┃
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┗━┓　　　┏━┛
 * -------------------------//┃　　　┃  神兽保佑
 * -------------------------//┃　　　┃  代码无BUG！
 * -------------------------//┃　　　┗━━━┓
 * -------------------------//┃　　　　　　　┣┓
 * -------------------------//┃　　　　　　　┏┛
 * -------------------------//┗┓┓┏━┳┓┏┛
 * -------------------------// ┃┫┫　┃┫┫
 * -------------------------// ┗┻┛　┗┻┛
 */
class SearchUsersAdapter(context: Context, data: MutableList<UserPo>) : BaseAdapter<UserPo>(context, data) {
    override fun onSetData(itemview: View, h: UserPo, position: Int) {
        val baseActivity = context as BaseActivity
        itemview.msg_item_user_name.text = " ${h.username}"
        itemview.msg_item_user_msg.text = "私语ID :${h.setID}"
        var options = RequestOptions().placeholder(R.mipmap.a111)                //加载成功之前占位图
                .error(R.mipmap.ccatsfas)                    //加载错误之后的错误图
                .fitCenter()
                .centerCrop()
        Glide.with(baseActivity).load(h.icon).apply(options).into(itemview.msg_item_user_icon)
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_searchfrend
    override fun onBindOnclic(itemview: View, position: Int) {
        itemview.setOnClickListener {
            val baseActivity = context as BaseActivity
            var intent = Intent(context, UserinfoActivity::class.java)
            intent.putExtra(UserinfoActivity.USER_ID, data[position].id)
            baseActivity.startMyActivity(intent)
        }
    }
}
