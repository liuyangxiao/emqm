package com.burning.emqmsg.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.*
import com.burning.emqmsg.adapter.FrendFragmentAdapter
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.GroupPo
import com.burning.realmdatalibrary.po.LoginUserPo
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
class FrendFragment : BaseFragment() {
    override fun initViewOnlayout(): Int = R.layout.fragmeng_frend
    var results: RealmResults<GroupPo>? = null
    override fun initData() {
        fragment_back_title.setPadding(fragment_back_title.left, fragment_back_title.top + BaseActivity.actionBarHeight, fragment_back_title.right, fragment_back_title.bottom)
        tv_title.text = "好友"
        frend_recyler.layoutManager = LinearLayoutManager(activity)
        val activity = activity as MainActivity
        results = activity.realm.where(LoginUserPo::class.java).equalTo("userid", UserInfo.userid).findFirst().groupPos.where().equalTo("type", 1).findAllAsync()
        results?.addChangeListener { results ->
            if (frend_recyler.adapter != null) {
                frend_recyler.adapter.notifyItemRangeChanged(0, results.size)
            } else {
                hideloading()
                frend_recyler.adapter = FrendFragmentAdapter(activity, results)
            }
        }
        fragment_frend_group_text.setOnClickListener {
            startActivity(Intent(activity, GroupActivity::class.java))
        }
        fragment_addfrend_tv.setOnClickListener {
            startActivity(Intent(activity, SearchAddActivity::class.java))
        }
        fragment_newfrend_tv.setOnClickListener {
            startActivity(Intent(activity, NewFrendActivity::class.java))
        }
    }

    override fun onDestroy() {
        results?.removeAllChangeListeners()
        super.onDestroy()
    }
}