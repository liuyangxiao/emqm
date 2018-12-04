package com.burning.emqmsg.fragment

import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.FrendFragmentAdapter
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
        tv_title.text="好友"
        frend_recyler.layoutManager = LinearLayoutManager(activity)
        var data = ArrayList<String>()
        data.add("1")
        data.add("2")
        data.add("3")
        data.add("4")
        data.add("5")
        data.add("6")
        data.add("7")
        data.add("9")
        data.add("10")
        data.add("11")
        data.add("12")
        data.add("13")
        data.add("14")
        data.add("15")
        data.add("16")
        data.add("17")
        frend_recyler.adapter = FrendFragmentAdapter(this!!.activity!!, data)
    }

}