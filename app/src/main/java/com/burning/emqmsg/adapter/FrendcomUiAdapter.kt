package com.burning.emqmsg.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.UserinfoActivity
import com.burning.realmdatalibrary.po.DiaryPo
import com.burning.realmdatalibrary.po.UserPo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_com_item.view.*
import java.util.*

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
class FrendcomUiAdapter(context: Context, data: MutableList<DiaryPo>) : BaseAdapter<DiaryPo>(context, data) {
    override fun onSetData(itemview: View, h: DiaryPo, position: Int) {
        val baseActivity = context as BaseActivity
        var username = baseActivity.realm.where(UserPo::class.java).equalTo("id", 2).findFirst().username
        itemview.com_item_user_name.text = "用户名:$username"
        itemview.com_item_user_messages.text = "内容 :${h.contens}"
        Calendar.getInstance().apply {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = h.createTime
            itemview.com_item_user_msgtime.text = "${calendar.get(Calendar.YEAR)}年" +
                    "${calendar.get(Calendar.MONTH)}月" +
                    "${calendar.get(Calendar.YEAR)}" +
                    "日${calendar.get(Calendar.HOUR)}时${calendar.get(Calendar.MINUTE)}分"
        }
        var images = ArrayList<String>()
        var nextInt = h.icons.length
        if (nextInt > 9) {
            nextInt = 9
        }
        for (i in 1..nextInt) {
            images.add("http://47.105.169.72/image/M00/00/00/rB-U8lv2cW2AVUvpAAAhrr-Mr6w145.jpg")
        }
        Glide.with(baseActivity).load("group:${Gson().toJson(images)}").into(itemview.com_item_recycler_images)
//
//        itemview.com_item_recycler_images.apply {
//            layoutManager = GridLayoutManager(context, spancount)
//            if (adapter == null) {
//                adapter = ImageAdapter(context, images)
//            } else {
//                val imageAdapter = adapter as ImageAdapter
//                imageAdapter.updataAdapter(images)
//            }
//
//        }
        itemview.com_item_recycler_comforid_conent.apply {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(context)
                adapter = ComDiscussionAdapter(context, h.diaryDescs)
            } else {
                val comDiscussionAdapter = adapter as ComDiscussionAdapter
                comDiscussionAdapter.updataAdapter(h.diaryDescs)
            }
        }

    }

    override fun onBindOnclic(itemview: View, position: Int) {
        itemview.com_item_user_icon.setOnClickListener {
            var intent = Intent(context, UserinfoActivity::class.java)
            intent.putExtra(UserinfoActivity.USER_ID, data[position].uid)
            context.startActivity(intent)
        }
        itemview.com_item_user_name.setOnClickListener {
            var intent = Intent(context, UserinfoActivity::class.java)
            intent.putExtra(UserinfoActivity.USER_ID, data[position].uid)
            context.startActivity(intent)
        }

    }

    override fun getItemViewType(position: Int): Int = R.layout.fragment_com_item

}
