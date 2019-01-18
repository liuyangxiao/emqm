package com.burning.emqmsg.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.MainActivity
import com.burning.emqmsg.adapter.MsgAdapter
import com.burning.emqmsg.view.SSRlayoutListener
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.MesgWinPo
import io.realm.RealmResults
import kotlinx.android.synthetic.main.back_title.*
import kotlinx.android.synthetic.main.fragmeng_msg.*


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
class MsgFragment : BaseFragment() {

    override fun initViewOnlayout(): Int = R.layout.fragmeng_msg
    var result: RealmResults<MesgWinPo>? = null
    override fun initData() {
        fragment_back_title.setPadding(fragment_back_title.left, fragment_back_title.top + BaseActivity.actionBarHeight, fragment_back_title.right, fragment_back_title.bottom)

        tv_title.text = "消息"
        frend_recyler.layoutManager = LinearLayoutManager(activity)
        var mac = activity as MainActivity

        result = mac.realm.where(MesgWinPo::class.java).equalTo("userid", UserInfo.userid).findAllAsync()
        result?.addChangeListener { results ->
            if (frend_recyler.adapter != null) {
                frend_recyler.adapter.notifyItemChanged(0, results.size)
            } else {
                hideloading()
                frend_recyler.adapter = MsgAdapter(activity!!, results)
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
           // setHeaderViewBackgroundColor(R.color.login_btn_color)
            //setDefaultCircleBackgroundColor(R.color.dark_color)
            // 设置默认圆形进度条颜色
//            setDefaultCircleBackgroundColor
//            设置默认圆形背景色
//            setDefaultCircleShadowColor
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        result?.removeAllChangeListeners()
    }

}