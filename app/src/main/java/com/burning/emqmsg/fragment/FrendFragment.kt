package com.burning.emqmsg.fragment

import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.FrendFragmentAdapter
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.LoginUserPo
import io.realm.Realm
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
    override fun initData() {
        tv_title.text = "好友"
        frend_recyler.layoutManager = LinearLayoutManager(activity)

        Realm.getDefaultInstance().where(LoginUserPo::class.java).equalTo("userid", UserInfo.userid).findFirstAsync()
                .addChangeListener<LoginUserPo> {
                    if (frend_recyler.adapter == null)
                        frend_recyler.adapter = FrendFragmentAdapter(this.activity!!, it.groupPos!!)
                    else
                        frend_recyler.adapter.notifyDataSetChanged()
                }

    }

}