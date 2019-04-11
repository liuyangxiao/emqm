package com.burning.emqmsg.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.TextUtils
import android.widget.Toast
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.BaseAdapter
import com.burning.emqmsg.adapter.ImageAdapter
import com.burning.emqmsg.utils.*
import com.burning.realmdatalibrary.UserInfo
import com.orhanobut.logger.Logger
import com.zhihu.matisse.Matisse
import kotlinx.android.synthetic.main.activity_send_diary.*
import kotlinx.android.synthetic.main.back_title.*
import java.io.File


class SendDiaryActivity : BaseActivity() {
    var dataicons = ArrayList<Uri>()
    override fun init() {
        tv_title.text = "发表活动"
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)
        activity_send_recyview.layoutManager = GridLayoutManager(this, 3)
        var imageAdapter = ImageAdapter(this, dataicons)
        activity_send_recyview.adapter = imageAdapter
        var mItemTouchHelper = ItemTouchHelper(MoveItemCallBack())
        mItemTouchHelper.attachToRecyclerView(activity_send_recyview)
        //  activity_send_recyview.set

        imageAdapter.run {
            setOnitemlongclic(object : BaseAdapter.Onitemlongclic {
                override fun onitemViewHolder(tviewHolder: BaseAdapter.TviewHolder) {
                    Logger.d("========onitemViewHolder==============" + tviewHolder.layoutPosition)
                    Logger.d("========onitemViewHolder==============" + tviewHolder.adapterPosition)
                    if (tviewHolder.layoutPosition != dataicons.size)
                        mItemTouchHelper.startDrag(tviewHolder)

                }
            })
            setOnitemclic(object : BaseAdapter.Onitemlongclic {
                override fun onitemViewHolder(tviewHolder: BaseAdapter.TviewHolder) {
                    if (tviewHolder.adapterPosition == dataicons.size) {
                        //
                        MatUtils.getIcon(this@SendDiaryActivity, 9)
                    }
                }

            })
        }
        iv_right.text = "发布"
        btn_back.text = "取消"
        btn_back.setOnClickListener {
            finish()
        }
        iv_right.setOnClickListener {
            if (activity_send_edt.text == null || TextUtils.isEmpty(activity_send_edt.text)) {
                Toast.makeText(this@SendDiaryActivity, "空内容", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var sendpo = DiarySendBean()
            sendpo.uid = UserInfo.userid
            sendpo.content = activity_send_edt.text.toString()
            dataicons.forEach {
                icons.add(UriUtils.getRealFilePath(it, this@SendDiaryActivity))
            }
            sendpo.icons = icons
            DiarySend.sendMessage(applicationContext, sendpo)
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MatUtils.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            dataicons.addAll(Matisse.obtainResult(data))
            activity_send_recyview.adapter?.notifyDataSetChanged()
        }

    }

    var icons = ArrayList<File>()
    override fun getActivityLayout(): Int = R.layout.activity_send_diary


}
