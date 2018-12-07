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
            /* val messBean = MessBean()
             messBean.apply {
                 uuid = UUID.randomUUID().toString()
                 code = 100
                 content = "testxxxx"
                 clientId = 2222
             }
             EmqClientImp.instance().sendMessage(1111, messBean, null)*/
//            val findFirst = Realm.getDefaultInstance().where(LoginUserPo::class.java).findFirst();
//            val userid = findFirst.userid
//            var a = findFirst.groupPos[0].remarks
//            tv_title.text = "xxxx{$userid}----{$a}"
            var tt = "开始"
            for (userPo in Realm.getDefaultInstance().where(UserPo::class.java).findAll()) {
                val id = userPo.id
                tt = "{$tt}id={$id}---username-${userPo.username}--\n"
            }
            test_tv.text = tt
        }
    }


}

