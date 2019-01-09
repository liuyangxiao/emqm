package com.burning.emqmsg.fragment

import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.MainActivity
import com.burning.emqmsg.adapter.MsgAdapter
import com.burning.realmdatalibrary.po.UserPo
import io.realm.RealmResults
import kotlinx.android.synthetic.main.back_title.*
import kotlinx.android.synthetic.main.fragmeng_frend.*

/**
 * Created by burning on 2018/10/23.
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
class MsgFragment : BaseFragment() {

    override fun initViewOnlayout(): Int = R.layout.fragmeng_msg
    var result: RealmResults<UserPo>? = null
    override fun initData() {
        tv_title.text = "消息"
        frend_recyler.layoutManager = LinearLayoutManager(activity)
        var mac = activity as MainActivity
        result = mac.realm.where(UserPo::class.java).findAllAsync()
        result?.addChangeListener { results ->

            if (frend_recyler.adapter != null) {
                frend_recyler.adapter.notifyDataSetChanged()
            } else {
                hideloading()
                frend_recyler.adapter = MsgAdapter(activity!!, results)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        result?.removeAllChangeListeners()
    }

}