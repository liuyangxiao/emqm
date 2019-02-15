package com.burning.emqmsg.fragment

import android.support.design.widget.TabLayout
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.CommunityAdapter
import com.burning.realmdatalibrary.po.LoginUserPo
import kotlinx.android.synthetic.main.fragmeng_comdia.*

/**
 * Created by burning on 2018/10/26.
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
class CommunityFragment : BaseFragment() {
    var resuPo: LoginUserPo? = null
    override fun initData() {
        //  tv_title.text = "圈子"
        // comfragment_content.setPadding(comfragment_content.left,  BaseActivity.actionBarHeight, comfragment_content.right, comfragment_content.bottom)


        community_vp.adapter = CommunityAdapter(fragmentManager!!)
        viewPagerIndicator.setViewPager(community_vp)
        tabLayout.setupWithViewPager(community_vp)
        tabLayout.tabMode = TabLayout.MODE_FIXED
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setSelectedTabIndicatorHeight(0)
        hideloading()
        add_comdia.setOnClickListener {


        }
    }

    override fun initViewOnlayout(): Int = R.layout.fragmeng_comdia
    override fun onDestroy() {
        super.onDestroy()
        resuPo?.removeAllChangeListeners()
    }
}