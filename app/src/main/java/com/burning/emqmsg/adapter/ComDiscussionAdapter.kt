package com.burning.emqmsg.adapter

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.style.ForegroundColorSpan
import android.view.View
import com.burning.emqmsg.R
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
class ComDiscussionAdapter(context: Context, data: MutableList<String>) : BaseAdapter<String>(context, data) {
    override fun onSetData(itemview: View, h: String, position: Int) {
        val spannable = SpannableStringBuilder("王二小 : 你是大傻叉")
        spannable.setSpan(ForegroundColorSpan(Color.RED),0,4,SPAN_EXCLUSIVE_EXCLUSIVE)
        itemview.item_condis_text.text =spannable
    }

    override fun getItemViewType(position: Int): Int = R.layout.comdiscussion_item


}