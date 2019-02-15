package com.burning.emqmsg.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.MsgActivity
import com.burning.realmdatalibrary.po.GroupPo
import com.burning.realmdatalibrary.po.MesgWinPo
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
class MsgAdapter(context: Context, data: MutableList<MesgWinPo>) : BaseAdapter<MesgWinPo>(context, data) {
    override fun onSetData(itemview: View, h: MesgWinPo, position: Int) {
        val baseActivity = context as BaseActivity
        var options = RequestOptions().placeholder(R.mipmap.a111)                //加载成功之前占位图
                .error(R.mipmap.ccatsfas)                    //加载错误之后的错误图
                .fitCenter()
                .centerCrop()
        var name = if (h.type == 1) {
            val findFirst = baseActivity.realm.where(UserPo::class.java).equalTo("id", h.msgid).findFirst();
            Glide.with(baseActivity).load(findFirst.icon).apply(options).into(itemview.msg_item_user_icon)
            findFirst.username + "用户id=" + findFirst.id
        } else {
            var group = baseActivity.realm.where(GroupPo::class.java).equalTo("id", h.msgid).findFirst()
            Glide.with(baseActivity).load("xxxxx").apply(options).into(itemview.msg_item_user_icon)
            group.content + "群id=" + group.id
        }
        itemview.msg_item_user_name.text = name


    }

    override fun getItemViewType(position: Int): Int = R.layout.fragment_msg_item
    override fun onBindOnclic(itemview: View, position: Int) {
        itemview.setOnClickListener {
            val baseActivity = context as BaseActivity
            var intent = Intent(context, MsgActivity::class.java)
            if (data[position].type == 1) {
                intent.putExtra(MsgActivity.USER_ID, data[position].msgid)
            } else {
                intent.putExtra(MsgActivity.GROUP_ID, data[position].msgid)
            }
            baseActivity.startMyActivity(intent)
        }

        itemview.setOnLongClickListener {

            return@setOnLongClickListener true
        }

    }
}
