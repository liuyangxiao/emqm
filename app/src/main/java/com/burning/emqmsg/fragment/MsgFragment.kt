package com.burning.emqmsg.fragment

import android.support.v7.widget.LinearLayoutManager
import com.burning.emqlibrary.bean.FragmentMsgBean
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.MsgAdapter
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
class MsgFragment : BaseFragment() {

    override fun initViewOnlayout(): Int = R.layout.fragmeng_frend

    override fun initData() {
        tv_title.text = "消息"
        frend_recyler.layoutManager = LinearLayoutManager(activity)
        var data = ArrayList<FragmentMsgBean>()
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        data.add(FragmentMsgBean())
        frend_recyler.adapter = MsgAdapter(activity!!, data)

    }


}