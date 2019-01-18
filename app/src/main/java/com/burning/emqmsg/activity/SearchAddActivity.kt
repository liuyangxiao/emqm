package com.burning.emqmsg.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.SearchUsersAdapter
import com.burning.realmdatalibrary.httpservice.impl.UserApimpl
import kotlinx.android.synthetic.main.activity_add_frend.*
import kotlinx.android.synthetic.main.back_title.*

class SearchAddActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_add_frend
    var api = UserApimpl()
    override fun init() {
        tv_title.text = "查找"
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)
        rv_searchs.apply {
            layoutManager = LinearLayoutManager(this@SearchAddActivity)
            adapter = SearchUsersAdapter(this@SearchAddActivity, ArrayList())
        }
        search_text.setOnClickListener {
            if (TextUtils.isEmpty(edt_search.text))
                return@setOnClickListener
            api.searchFrend(edt_search.text.toString()) { code, _, data ->
                if (code == 200 && data != null && data.size != 0) {
                    val baseAdapter = rv_searchs.adapter as SearchUsersAdapter
                    baseAdapter.updataAdapter(data)
                }
            }
        }
    }


}
