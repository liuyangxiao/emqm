package com.burning.emqmsg.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqlibrary.bean.FragmentMsgBean
import com.burning.emqmsg.R
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
class FrendFragmentAdapter(context: Context, data: MutableList<String>) : BaseAdapter<String>(context, data) {
    override fun onSetData(itemview: View, h: String, position: Int) {
        //itemview.item_group_name.text = "==========" + h + "====" + position
        itemview.item_frend_recyler_view.layoutManager = LinearLayoutManager(context)
        var images = ArrayList<FragmentMsgBean>()
        images.add(FragmentMsgBean())
        images.add(FragmentMsgBean())
        images.add(FragmentMsgBean())
        itemview.item_frend_recyler_view.adapter = MsgAdapter(context, images)
        setChecked(itemview, btns.contains(position))
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_frend_fragment
    var btns = HashSet<Int>()
    fun setChecked(itemview: View, boolean: Boolean) {
        itemview.item_frend_recyler_view.visibility = if (boolean) {
            Glide.with(context).load(R.mipmap.nsr).into(itemview.frend_item_check_goup)
            View.VISIBLE
        } else {
            Glide.with(context).load(R.mipmap.nss).into(itemview.frend_item_check_goup)
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