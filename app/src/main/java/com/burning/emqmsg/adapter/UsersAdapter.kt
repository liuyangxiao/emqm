package com.burning.emqmsg.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.MsgActivity
import com.burning.emqmsg.utils.ImageConfig
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
class UsersAdapter(context: Context, data: MutableList<UserPo>) : BaseAdapter<UserPo>(context, data) {
    override fun onSetData(itemview: View, h: UserPo, position: Int) {
        val baseActivity = context as BaseActivity
        itemview.msg_item_user_name.text = " ${h.username}"
        var options = RequestOptions().placeholder(R.mipmap.a111)                //加载成功之前占位图
                .error(R.mipmap.ccatsfas)                    //加载错误之后的错误图
                .fitCenter()
                .centerCrop()
        Glide.with(baseActivity).load(ImageConfig.Image_path +h.icon).apply(options).into(itemview.msg_item_user_icon)
    }

    override fun getItemViewType(position: Int): Int = R.layout.fragment_msg_item
    override fun onBindOnclic(itemview: View, position: Int) {
        itemview.setOnClickListener {
            val baseActivity = context as BaseActivity
            var intent = Intent(context, MsgActivity::class.java)
            intent.putExtra(MsgActivity.USER_ID, data[position].id)
            baseActivity.startMyActivity(intent)
        }
    }
}
