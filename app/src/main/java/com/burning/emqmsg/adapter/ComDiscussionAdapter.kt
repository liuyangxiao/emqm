package com.burning.emqmsg.adapter

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.style.ForegroundColorSpan
import android.view.View
import com.burning.emqmsg.R
import com.burning.realmdatalibrary.po.DiaryDescPo
import kotlinx.android.synthetic.main.comdiscussion_item.view.*


/**
 * Created by burning on 2018/11/29.
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
class ComDiscussionAdapter(context: Context, data: MutableList<DiaryDescPo>) : BaseAdapter<DiaryDescPo>(context, data) {
    override fun onSetData(itemview: View, h: DiaryDescPo, position: Int) {
        val spannable = SpannableStringBuilder("王二小 : ${h.userid}cc ${h.content}")
        spannable.setSpan(ForegroundColorSpan(Color.RED), 0, 4, SPAN_EXCLUSIVE_EXCLUSIVE)
        itemview.item_condis_text.text = spannable
    }

    override fun getItemViewType(position: Int): Int = R.layout.comdiscussion_item


}