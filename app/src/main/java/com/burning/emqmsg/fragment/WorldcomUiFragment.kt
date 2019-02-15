package com.burning.emqmsg.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.MainActivity
import com.burning.emqmsg.adapter.WorldUiAdapter
import com.burning.emqmsg.view.SSRlayoutListener
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.LoginUserPo
import kotlinx.android.synthetic.main.frend_com_fragment.*


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
class WorldcomUiFragment : BaseFragment() {
    var resuPo: LoginUserPo? = null
    override fun initData() {
        frend_recyler.layoutManager = LinearLayoutManager(activity)
        val activity = activity as MainActivity
        resuPo = activity.realm.where(LoginUserPo::class.java).equalTo("userid", UserInfo.userid).findFirstAsync()
        resuPo?.addChangeListener<LoginUserPo> {
            if (frend_recyler.adapter != null) {
                frend_recyler.adapter.notifyDataSetChanged()
            } else {
                hideloading()
                frend_recyler.adapter = WorldUiAdapter(activity, it.diaryPos)
            }
        }
        swipe_refresh.apply {
            setOnPullRefreshListener(object : SSRlayoutListener {
                override fun onRefresh() {
                    swipe_refresh.apply {
                        postDelayed({ isRefreshing = false }, 1000)
                    }
                }
            })
            setDefaultCircleProgressColor(Color.parseColor("#ff0000"))
            //   setFooterView(View.inflate(activity, R.layout.item_imagview, null))
            // 设置默认圆形进度条颜色
//            setDefaultCircleBackgroundColor
//            设置默认圆形背景色
//            setDefaultCircleShadowColor
        }

    }

    override fun initViewOnlayout(): Int = R.layout.frend_com_fragment
    override fun onDestroy() {
        super.onDestroy()
        resuPo?.removeAllChangeListeners()
    }
}