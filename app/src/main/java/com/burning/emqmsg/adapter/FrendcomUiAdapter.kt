package com.burning.emqmsg.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.UserinfoActivity
import com.burning.emqmsg.activity.ViewPagerActivity
import com.burning.emqmsg.glidehelp.MyTransform
import com.burning.emqmsg.utils.ImageConfig
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.httpservice.impl.DiaryApimpl
import com.burning.realmdatalibrary.httpservice.requbean.DiaryComment
import com.burning.realmdatalibrary.po.DiaryPo
import com.burning.realmdatalibrary.po.UserPo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_com_item.view.*
import java.util.*
import kotlin.collections.ArrayList

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
        var user = baseActivity.realm.where(UserPo::class.java).equalTo("id", h.uid).findFirst()
        itemview.com_item_user_name.text = "用户名:${user.username}"
        itemview.com_item_user_messages.text = "id==${h.id}内容 :${h.contens}"
        itemview.edi_linea.visibility = View.GONE
        Glide.with(baseActivity).load(ImageConfig.Image_path + user.icon).apply(MyTransform.getCircleCrop()).into(itemview.com_item_user_icon)
        Calendar.getInstance().apply {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = h.createTime
            itemview.com_item_user_msgtime.text = "${calendar.get(Calendar.YEAR)}年" +
                    "${calendar.get(Calendar.MONTH)}月" +
                    "${calendar.get(Calendar.YEAR)}" +
                    "日${calendar.get(Calendar.HOUR)}时${calendar.get(Calendar.MINUTE)}分"
        }
        var images = ArrayList<String>()
        //val fromJson = Gson().fromJson<List<String>>(h.icons, object : TypeToken<List<String>>() {}.type)
        //var getdata = GsonUtils().getdata<ArrayList<String>>(h.icons)
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
        Glide.with(baseActivity).load(images[0]).into(itemview.com_item_recycler_images)
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
        itemview.com_item_recycler_usrmasg.setOnClickListener {
            itemview.edi_linea.visibility = View.VISIBLE
        }
        itemview.senm_icon.setOnClickListener {

            if (itemview.send_message.text.isEmpty()) {
                Toast.makeText(context, "内容不可未空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var msg = DiaryComment()
            msg.uid = UserInfo.userid
            msg.content = itemview.send_message.text.toString()
            msg.diaryID = data[position].id
            itemview.edi_linea.visibility = View.GONE
            itemview.send_message.text.clear()
            diaryApimpl.descantMessage(msg) { i: Int, s: String, s1: String ->
                if (i != 200) {
                    Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show()
                } else {
                    itemview.com_item_recycler_comforid_conent
                            .postDelayed({
                                itemview.com_item_recycler_comforid_conent.adapter.notifyDataSetChanged()
                            }, 1000)
                }
            }
        }
        itemview.com_item_recycler_images.setOnClickListener {
            var intent = Intent(context, ViewPagerActivity::class.java)
            intent.putExtra(ViewPagerActivity.PAGE_VIEW, Gson().fromJson<ArrayList<String>>(data[position].icons, object : TypeToken<ArrayList<String>>() {}.type))
            context.startActivity(intent)
            // PhotoView.getImageViewInfo()
        }

    }

    var diaryApimpl = DiaryApimpl()
    override fun getItemViewType(position: Int): Int = R.layout.fragment_com_item

}

