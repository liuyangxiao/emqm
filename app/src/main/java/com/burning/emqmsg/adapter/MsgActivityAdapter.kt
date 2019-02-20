package com.burning.emqmsg.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.UserinfoActivity
import com.burning.emqmsg.glidehelp.MyTransform
import com.burning.emqmsg.utils.ImageConfig
import com.burning.mybaselibrary.DpPxTransformUtil
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.MessagePo
import com.burning.realmdatalibrary.po.UserPo
import kotlinx.android.synthetic.main.activity_msg_item.view.*

/**
 * Created by burning on 2018/10/29.
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
class MsgActivityAdapter(context: Context, data: MutableList<MessagePo>) : BaseAdapter<MessagePo>(context, data) {
    override fun onSetData(itemview: View, h: MessagePo, position: Int) {
        val baseActivity = context as BaseActivity
        var user = baseActivity.realm.where(UserPo::class.java).equalTo("id", h.clientId).findFirst()
        itemview.item_msg_activity_username.text = user.username

        Glide.with(baseActivity).load(ImageConfig.Image_path +user.icon).apply(MyTransform.getCircleCrop()).into(itemview.item_msg_activity_usericon)
        itemview.item_msg_activity_message.text = h.content
        val measureText = itemview.item_msg_activity_message.paint.measureText(h.content)
        val dip2px = DpPxTransformUtil.dip2px(context, 240f)
        if (measureText > dip2px)
            itemview.item_msg_activity_message.width = dip2px

        if (h.status == 0) {
            itemview.msg_send_status.visibility = View.GONE
        } else {
            itemview.msg_send_status.visibility = View.VISIBLE
            if (h.status == 1) {
                itemview.msg_send_status.text = "发送中"
            } else if (h.status == 2) {
                itemview.msg_send_status.text = "发送失败"
            }
        }

    }

    override fun onBindOnclic(itemview: View, position: Int) {
        super.onBindOnclic(itemview, position)
        itemview.item_msg_activity_usericon.setOnClickListener {
            var intent = Intent(context, UserinfoActivity::class.java)
            intent.putExtra(UserinfoActivity.USER_ID, data[position].clientId)
            context.startActivity(intent)
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (data[position].clientId != UserInfo.userid)
            return R.layout.activity_msg_item
        else
            return R.layout.activity_msg_item_me
    }

}