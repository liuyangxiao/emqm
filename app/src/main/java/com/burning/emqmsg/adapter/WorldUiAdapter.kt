package com.burning.emqmsg.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.glidehelp.MyTransform
import com.burning.realmdatalibrary.po.DiaryPo
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
        Glide.with(context).load("aaxxxx").into(itemview.item_word_images)
        Glide.with(context).load(R.mipmap.ccatsfas).apply(MyTransform.getCircleCrop()).into(itemview.msg_item_user_icon)
    }


    override fun getItemViewType(position: Int): Int = R.layout.item_worldui

}
