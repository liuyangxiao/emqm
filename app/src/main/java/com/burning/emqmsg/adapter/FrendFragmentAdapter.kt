package com.burning.emqmsg.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.burning.emqmsg.R
import com.burning.realmdatalibrary.po.GroupPo
import kotlinx.android.synthetic.main.item_frend_fragment.view.*

/**
 * Created by burning on 2018/10/24.
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
class FrendFragmentAdapter(context: Context, data: MutableList<GroupPo>) : BaseAdapter<GroupPo>(context, data) {
    override fun onSetData(itemview: View, h: GroupPo, position: Int) {
        //itemview.item_group_name.text = "==========" + h + "====" + position
        itemview.item_frend_recyler_view.layoutManager = LinearLayoutManager(context)
        itemview.item_group_name.text = " ${h.content}" + if (h.type == 1) {
            "好友分组"
        } else {
            "群--"
        }
        itemview.item_frend_recyler_view.adapter = UsersAdapter(context, h.userlis)
        setChecked(itemview, btns.contains(position))
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_frend_fragment
    var btns = HashSet<Int>()
    fun setChecked(itemview: View, boolean: Boolean) {
        itemview.item_frend_recyler_view.visibility = if (boolean) {
            // Glide.with(context).load(R.mipmap.nsr).into(itemview.frend_item_check_goup)
            itemview.frend_item_check_goup.setImageResource(R.mipmap.nsr)
            View.VISIBLE
        } else {
            itemview.frend_item_check_goup.setImageResource(R.mipmap.nss)
            //    Glide.with(context).load().into(itemview.frend_item_check_goup)
            View.GONE
        }
    }

    override fun onBindOnclic(itemview: View, h: Int) {
        itemview.setOnClickListener {
            if (btns.contains(h)) {
                btns.remove(h)
            } else {
                btns.add(h)
            }
            setChecked(itemview, btns.contains(h))
        }
    }
}