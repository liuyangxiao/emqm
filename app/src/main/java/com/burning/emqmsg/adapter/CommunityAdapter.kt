package com.burning.emqmsg.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.burning.emqmsg.R
import com.burning.realmdatalibrary.po.DiaryPo
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
class CommunityAdapter(context: Context, data: MutableList<DiaryPo>) : BaseAdapter<DiaryPo>(context, data) {
    override fun onSetData(itemview: View, h: DiaryPo, position: Int) {
        itemview.com_item_user_name.text = "${h.uid} 这个二比ID"
        itemview.com_item_user_messages.text = "飞洒是否会四u发货哦啊师傅i是更符合双方还将分别世界富豪榜上的开发商电话方便的是风水宝地福克斯的肌肤的数据库备份第三方控件不是防护技术的反抗军的说法的讲课风格独守空房但是发动快速减肥华盛顿发生对抗肌肤士大夫但是"
        Calendar.getInstance().apply {
            itemview.com_item_user_msgtime.text = "${get(Calendar.YEAR)}年${get(Calendar.MONTH)}月${get(Calendar.DAY_OF_YEAR)}"
        }
        var images = ArrayList<String>()
        val nextInt = h.icons.length
        for (i in 1..nextInt) {
            images.add("====")
        }
        var spancount = if (images.size > 4) {
            3
        } else {
            2
        }
        itemview.com_item_recycler_images.apply {
            layoutManager = GridLayoutManager(context, spancount)
            if (adapter == null) {
                adapter = ImageAdapter(context, images)
            } else {
                val imageAdapter = adapter as ImageAdapter
                imageAdapter.updataAdapter(images)
            }
            visibility = if (gonesDesc.contains(position)) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
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

    var gonesDesc = HashSet<Int>()
    override fun onBindOnclic(itemview: View, position: Int) {
        itemview.com_item_user_name.setOnClickListener {
            if (gonesDesc.contains(position)) {
                itemview.com_item_recycler_images.visibility = View.VISIBLE
                gonesDesc.remove(position)
            } else {
                gonesDesc.add(position)
                itemview.com_item_recycler_images.visibility = View.GONE
            }
        }

    }

    override fun getItemViewType(position: Int): Int = R.layout.fragment_com_item

}
