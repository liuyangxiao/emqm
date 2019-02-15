package com.burning.emqmsg.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.UserinfoActivity
import com.burning.emqmsg.glidehelp.MyTransform
import com.burning.realmdatalibrary.po.DiaryPo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_worldui.view.*


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
class WorldUiAdapter(context: Context, data: MutableList<DiaryPo>) : BaseAdapter<DiaryPo>(context, data) {
    override fun onSetData(itemview: View, h: DiaryPo, position: Int) {
        var data = ArrayList<String>()
        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lv2cW2AVUvpAAAhrr-Mr6w145.jpg")
        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lv2cW2AVUvpAAAhrr-Mr6w145.jpg")
        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lxlGdGAKAGOAAHyC7o7ckQ665.png")
        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lv2cW2AVUvpAAAhrr-Mr6w145.jpg")
        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lv2cW2AVUvpAAAhrr-Mr6w145.jpg")
        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lxlGdGAKAGOAAHyC7o7ckQ665.png")
        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lv2cW2AVUvpAAAhrr-Mr6w145.jpg")
        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lv2cW2AVUvpAAAhrr-Mr6w145.jpg")
        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lxlGdGAKAGOAAHyC7o7ckQ665.png")
//        data.add("http://47.105.169.72/image/M00/00/00/rB-U8lv2cW2AVUvpAAAhrr-Mr6w145.jpg")
        Glide.with(context).load("group:${Gson().toJson(data)}").into(itemview.item_word_images)
        Glide.with(context).load(R.mipmap.ccatsfas).apply(MyTransform.getCircleCrop()).into(itemview.msg_item_user_icon)
    }

    override fun onBindOnclic(itemview: View, position: Int) {
        super.onBindOnclic(itemview, position)
        itemview.msg_item_user_icon.setOnClickListener {
            var intent = Intent(context, UserinfoActivity::class.java)
            intent.putExtra(UserinfoActivity.USER_ID, data[position].uid)
            context.startActivity(intent)
        }
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_worldui

}
