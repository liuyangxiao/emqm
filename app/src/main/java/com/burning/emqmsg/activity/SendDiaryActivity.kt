package com.burning.emqmsg.activity

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.BaseAdapter
import com.burning.emqmsg.adapter.ImageAdapter
import com.burning.emqmsg.utils.DiarySend
import com.burning.emqmsg.utils.DiarySendBean
import com.burning.emqmsg.utils.MoveItemCallBack
import com.burning.realmdatalibrary.UserInfo
import kotlinx.android.synthetic.main.activity_send_diary.*
import kotlinx.android.synthetic.main.back_title.*
import java.io.File


class SendDiaryActivity : BaseActivity() {
    override fun init() {
        tv_title.text = "发表活动"
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)
        activity_send_recyview.layoutManager = GridLayoutManager(this, 3)
        var data = ArrayList<String>()
        data.add("M00/00/00/rB-U8lxrrceAbZ8EAAsr_QUb9Io069.jpg")
        data.add("M00/00/00/rB-U8lxrrceAbZ8EAAsr_QUb9Io069.jpg")
        data.add("M00/00/00/rB-U8lxrrceAbZ8EAAsr_QUb9Io069.jpg")
        data.add("M00/00/00/rB-U8lxrrceAbZ8EAAsr_QUb9Io069.jpg")
        data.add("M00/00/00/rB-U8lxrrceAbZ8EAAsr_QUb9Io069.jpg")
        data.add("M00/00/00/rB-U8lxrrceAbZ8EAAsr_QUb9Io069.jpg")


        var imageAdapter = ImageAdapter(this, data)
        activity_send_recyview.adapter = imageAdapter

        var mItemTouchHelper = ItemTouchHelper(MoveItemCallBack())
        mItemTouchHelper.attachToRecyclerView(activity_send_recyview)
        //  activity_send_recyview.set

        imageAdapter.setOnitemlongclic(object : BaseAdapter.Onitemlongclic {
            override fun onitemViewHolder(tviewHolder: BaseAdapter.TviewHolder) {
                if (tviewHolder.layoutPosition != 0)
                    mItemTouchHelper.startDrag(tviewHolder)

            }
        })
        //activity_send_recyview.


        iv_right.text = "发布"
        btn_back.text = "取消"
        btn_back.setOnClickListener {
            finish()
        }
        iv_right.setOnClickListener {
            if (activity_send_edt.text == null) {
                Toast.makeText(this@SendDiaryActivity, "空内容", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var sendpo = DiarySendBean()
            sendpo.uid = UserInfo.userid
            sendpo.content = activity_send_edt.text.toString()
            sendpo.icons = icons
            application
            DiarySend.sendMessage(applicationContext, sendpo)
            finish()
        }

    }

    var icons = ArrayList<File>()
    override fun getActivityLayout(): Int = R.layout.activity_send_diary


}
