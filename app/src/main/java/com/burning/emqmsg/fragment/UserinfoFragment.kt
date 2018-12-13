package com.burning.emqmsg.fragment

import com.burning.emqmsg.R
import com.burning.realmdatalibrary.po.UserPo
import io.realm.Realm
import kotlinx.android.synthetic.main.back_title.*
import kotlinx.android.synthetic.main.fragment_userinfo.*

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
class UserinfoFragment : BaseFragment() {
    override fun initViewOnlayout(): Int = R.layout.fragment_userinfo

    override fun initData() {
        tv_title.text = "User"
        user_fragment_usericon.setOnClickListener {
            var tt = "开始"
            val defaultInstance = Realm.getDefaultInstance()
            for (userPo in defaultInstance.where(UserPo::class.java).findAll()) {
                val id = userPo.id
                tt = "{$tt}id={$id}---username-${userPo.username}--\n"
            }
            // defaultInstance.beginTransaction()
            test_tv.text = tt
        }
    }


}

