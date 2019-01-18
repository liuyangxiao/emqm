package com.burning.emqmsg.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.MsgActivity
import com.burning.emqmsg.glidehelp.MyTransform
import com.burning.realmdatalibrary.po.GroupPo
import kotlinx.android.synthetic.main.item_group.view.*

/**
 * Created by burning on 2018/10/30.
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
class GroupImageAdapter(context: Context, data: MutableList<GroupPo>) : BaseAdapter<GroupPo>(context, data) {
    override fun onSetData(itemview: View, h: GroupPo, position: Int) {
        Glide.with(context).load("aaaxxxxx").apply(MyTransform.getRequestOptions(10)).into(itemview.item_group_icon)
        itemview.item_group_name.text = h.content + "群ID:" + h.id
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_group
    override fun onBindOnclic(itemview: View, position: Int) {
        super.onBindOnclic(itemview, position)
        itemview.setOnClickListener {
            var intent = Intent(context, MsgActivity::class.java)
            intent.putExtra(MsgActivity.GROUP_ID, data[position].id)
            context.startActivity(intent)
        }
    }
}