package com.burning.emqmsg.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.R.id.item_msg_activity_username
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.UserinfoActivity
import com.burning.emqmsg.glidehelp.MyTransform
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
        Glide.with(context).load(R.mipmap.ccatsfas).apply(MyTransform.getCircleCrop()).into(itemview.item_msg_activity_usericon)
        val baseActivity = context as BaseActivity
        itemview.item_msg_activity_username.text = baseActivity.realm.where(UserPo::class.java).equalTo("id", h.clientId).findFirst().username
        itemview.item_msg_activity_message.text = h.content
        val measureText = itemview.item_msg_activity_message.paint.measureText(h.content)
        val dip2px = DpPxTransformUtil.dip2px(context, 240f)
        if (measureText > dip2px)
            itemview.item_msg_activity_message.width = dip2px
        itemview.item_msg_activity_usericon.setOnClickListener {
            val baseActivity = context as BaseActivity
            baseActivity.startMyActivity(UserinfoActivity::class.java)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (data[position].clientId != UserInfo.userid)
            return R.layout.activity_msg_item
        else
            return R.layout.activity_msg_item_me
    }

}