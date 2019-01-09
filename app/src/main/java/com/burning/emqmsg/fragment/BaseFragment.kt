package com.burning.emqmsg.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.burning.emqmsg.R
import kotlinx.android.synthetic.main.base_fragment.*

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
abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("========onCreate======" + javaClass.name)
    }

    abstract fun initData()
    // abstract fun initView(): View
    abstract fun initViewOnlayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("========onCreateView======" + javaClass.name)
        val inflate = inflater.inflate(R.layout.base_fragment, null)
        val viewGroup = inflate as ViewGroup
        viewGroup.addView(View.inflate(activity, initViewOnlayout(), null), 0)
        return inflate//super.onCreateView(inflater, container, savedI)nstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("========onView===Created======" + javaClass.name)
        showloading()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    fun showloading() {
        fragment_loading.show()
    }

    fun hideloading() {
        fragment_loading.hide()
    }

    override fun onAttach(context: Context?) {
        println("========onAttach======" + javaClass.name)
        super.onAttach(context)
    }

//    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
//        println("========onCreateAnimation======" + javaClass.name)
//        return super.onCreateAnimation(transit, enter, nextAnim)
    //=onView===Created 之后
//    }

    override fun onStart() {
        println("========onStart======" + javaClass.name)
        super.onStart()
    }

    override fun onPause() {
        println("========onPause======" + javaClass.name)
        super.onPause()
    }

    override fun onResume() {
        println("========onResume======" + javaClass.name)
        super.onResume()
    }

    override fun onStop() {
        println("========onStop======" + javaClass.name)
        super.onStop()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        println("========setUserVisibleHint======" + javaClass.name + "====={$isVisibleToUser}==")
        super.setUserVisibleHint(isVisibleToUser)
    }

}