package com.burning.emqmsg.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.UserinfoActivity
import com.burning.emqmsg.glidehelp.MyTransform
import com.burning.mybaselibrary.DpPxTransformUtil
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
class MsgActivityAdapter(context: Context, data: MutableList<String>) : BaseAdapter<String>(context, data) {
    override fun onSetData(itemview: View, h: String, position: Int) {
        Glide.with(context).load(R.mipmap.ccatsfas).apply(MyTransform.getCircleCrop()).into(itemview.item_msg_activity_usericon)
        var texttest = "a"
        for (int in 1..position) {
            texttest = "xasfsafsffsfafsafsafa${position}sfafasf$texttest"
        }
        itemview.item_msg_activity_message.text = texttest
        val measureText = itemview.item_msg_activity_message.paint.measureText(texttest)
        val dip2px = DpPxTransformUtil.dip2px(context, 240f)
        if (measureText > dip2px)
            itemview.item_msg_activity_message.width = dip2px
        itemview.item_msg_activity_usericon.setOnClickListener {
            val baseActivity = context as BaseActivity
            baseActivity.startMyActivity(UserinfoActivity().javaClass)
        }

    }


    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0)
            return R.layout.activity_msg_item
        else
            return R.layout.activity_msg_item_me
    }

}