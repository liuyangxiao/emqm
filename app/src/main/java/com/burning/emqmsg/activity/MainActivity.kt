package com.burning.emqmsg.activity

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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun init() {
        var tops = HashSet<String>()
        tops.add(TopicHelp.baseGroupToppic + UserInfo.userid)//自己
        tops.add(TopicHelp.serviceToppic + UserInfo.userid)//系统
        //群组 待添加
        EmqClientImp.instance().addtopicks(tops)

        navigation.itemIconTintList = null

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_notifications

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
            beginTransaction.hide(contentfragment)
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
            beginTransaction.add(R.id.activity_main_fragment, contentfragment, tag)
        }
        beginTransaction.show(contentfragment).commit()
    }

}