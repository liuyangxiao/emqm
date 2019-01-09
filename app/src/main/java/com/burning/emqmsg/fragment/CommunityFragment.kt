package com.burning.emqmsg.fragment

import android.support.design.widget.TabLayout
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.MainActivity
import com.burning.emqmsg.adapter.CommunityAdapter
import com.burning.realmdatalibrary.po.LoginUserPo
import kotlinx.android.synthetic.main.back_title.*
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
        tv_title.text = "圈子"
        //    community_vp.layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        val activity = activity as MainActivity
        var data = ArrayList<String>()
        community_vp.adapter = CommunityAdapter(fragmentManager!!)
        //   viewPagerIndicator.
        viewPagerIndicator.setViewPager(community_vp)
        //com_vp_tablelayout.
        tabLayout.setupWithViewPager(community_vp)
        tabLayout.tabMode = TabLayout.MODE_FIXED
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setSelectedTabIndicatorHeight(0)
        hideloading()
    }

    override fun initViewOnlayout(): Int = R.layout.fragmeng_comdia
    override fun onDestroy() {
        super.onDestroy()
        resuPo?.removeAllChangeListeners()
    }
}