package com.burning.emqmsg.activity

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.burning.emqlibrary.emqNet.EmqClientImp
import com.burning.emqlibrary.emqNet.TopicHelp
import com.burning.emqmsg.R
import com.burning.emqmsg.fragment.CommunityFragment
import com.burning.emqmsg.fragment.FrendFragment
import com.burning.emqmsg.fragment.MsgFragment
import com.burning.emqmsg.fragment.UserinfoFragment
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.LoginUserPo
import com.burning.realmdatalibrary.po.LoginsPo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun init() {
        var tops = HashSet<String>()
        if (UserInfo.userid == 0L || UserInfo.userid == null) {
            val findFirst = realm.where(LoginsPo::class.java).equalTo("status", 100).findFirst()
            UserInfo.userid = findFirst.userid
        }
        if (UserInfo.userid == 0L || UserInfo.userid == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        tops.add(TopicHelp.baseToppic + UserInfo.userid)//自己
        tops.add(TopicHelp.serviceToppic + UserInfo.userid)//系统
        var groups = realm.where(LoginUserPo::class.java).equalTo("userid", UserInfo.userid).findFirst().groupPos.where().equalTo("type", 2).findAll()
        if (groups != null && groups.isNotEmpty())
            for (groupPo in groups) {
                tops.add(TopicHelp.baseGroupToppic + groupPo.id)//群组
            }
        EmqClientImp.instance().addtopicks(tops)
        navigation.itemIconTintList = null
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_notifications
        PermissionActivity.startActivityForResult(this, 400,
                "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.READ_EXTERNAL_STORAGE", "android.permission.FOREGROUND_SERVICE", "android.permission.READ_PROFILE")
    }

    override fun getActivityLayout(): Int = R.layout.activity_main


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragmenByTag(MSG_TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                replaceFragmenByTag(FED_TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                replaceFragmenByTag(COM_TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user -> {
                replaceFragmenByTag(USER_TAG)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private var contentfragment: Fragment? = null
    var USER_TAG = "u_1"
    var MSG_TAG = "m_1"
    var FED_TAG = "f_1"
    var COM_TAG = "c_1"

    fun replaceFragmenByTag(tag: String) {

        val beginTransaction = supportFragmentManager.beginTransaction()
        if (contentfragment != null)
            beginTransaction.hide(contentfragment!!)
        contentfragment = supportFragmentManager.findFragmentByTag(tag)
        if (contentfragment == null) {
            when (tag) {
                USER_TAG -> {
                    contentfragment = UserinfoFragment()
                }
                MSG_TAG -> {
                    contentfragment = MsgFragment()
                }
                FED_TAG -> {
                    contentfragment = FrendFragment()
                }
                COM_TAG -> {
                    contentfragment = CommunityFragment()
                }
            }
            beginTransaction.add(R.id.activity_main_fragment, contentfragment!!, tag)
        }
        beginTransaction.show(contentfragment!!).commit()
    }

}