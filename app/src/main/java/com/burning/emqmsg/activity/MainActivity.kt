package com.burning.emqmsg.activity

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.widget.Toast
import com.burning.emqlibrary.emqNet.EmqClientImp
import com.burning.emqlibrary.emqNet.TopicHelp
import com.burning.emqmsg.R
import com.burning.emqmsg.fragment.CommunityFragment
import com.burning.emqmsg.fragment.FrendFragment
import com.burning.emqmsg.fragment.MsgFragment
import com.burning.emqmsg.fragment.UserinfoFragment
import com.burning.emqmsg.utils.LiveDataBus
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
                "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE")
    }

    override fun getActivityLayout(): Int = R.layout.activity_main
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {


                Toast.makeText(this, " Key:" + LiveDataBus.get().bus.size, Toast.LENGTH_SHORT).show()
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
    // private var contentfragment: Fragment? = null
    var USER_TAG = "u_1"
    var MSG_TAG = "m_1"
    var FED_TAG = "f_1"
    var COM_TAG = "c_1"
    private var CONTENT_FRAGMENT_TAG = "NO"
    fun replaceFragmenByTag(tag: String) {
        if (CONTENT_FRAGMENT_TAG == tag) {
            return
        }
        val beginTransaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentByTag(CONTENT_FRAGMENT_TAG)?.apply {
            beginTransaction.hide(this)
        }
        supportFragmentManager.findFragmentByTag(tag).apply {
            if (this == null) {
                var newTagfragment = when (tag) {
                    USER_TAG -> {
                        UserinfoFragment()
                    }
                    MSG_TAG -> {
                        MsgFragment()
                    }
                    FED_TAG -> {
                        FrendFragment()
                    }
                    COM_TAG -> {
                        CommunityFragment()
                    }
                    else -> {
                        FrendFragment()
                    }
                }
                beginTransaction.add(R.id.activity_main_fragment, newTagfragment, tag)
                beginTransaction.show(newTagfragment).commit()
            } else {
                beginTransaction.show(this).commit()
            }
            CONTENT_FRAGMENT_TAG = tag
        }
    }

}

