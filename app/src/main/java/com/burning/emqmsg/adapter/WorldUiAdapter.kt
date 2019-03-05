package com.burning.emqmsg.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.MainActivity
import com.burning.emqmsg.activity.UserinfoActivity
import com.burning.emqmsg.glidehelp.MyTransform
import com.burning.emqmsg.utils.ImageConfig
import com.burning.realmdatalibrary.po.DiaryPo
import com.burning.realmdatalibrary.po.UserPo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        var images = ArrayList<String>()
        var getdata: ArrayList<String>? = null
        try {
            getdata = Gson().fromJson<ArrayList<String>>(h.icons, object : TypeToken<ArrayList<String>>() {}.type)
        } catch (e: Exception) {
        }
        getdata?.forEach {
            images.add(ImageConfig.Image_path + it)
        }
        if (images.size == 0) {
            images.add("http://47.105.169.72/image/M00/00/00/rB-U8lxrrceAbZ8EAAsr_QUb9Io069.jpg")
        }
        Glide.with(context).load(images[0]).into(itemview.item_word_images)
        val activity = context as MainActivity
        var user = activity.realm.where(UserPo::class.java).equalTo("id", h.uid).findFirst()
        itemview.msg_item_user_name.text = user.username
        itemview.item_word_title.text = "图片=${h.icons}-" +
                "\n 内容: ${h.contens}"
        Glide.with(activity).load(ImageConfig.Image_path + user.icon).apply(MyTransform.getCircleCrop()).into(itemview.msg_item_user_icon)
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
