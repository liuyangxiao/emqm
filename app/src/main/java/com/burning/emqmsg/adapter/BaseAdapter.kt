package com.burning.emqmsg.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by burning on 2018/10/23.
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * -------------------------//┏┓　　　┏┓
 * -------------------------//┏┛┻━━━┛┻┓
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┃　　　━　　　┃
 * -------------------------//┃　┳┛　┗┳　┃
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┃　　　┻　　　┃
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┗━┓　　　┏━┛
 * -------------------------//┃　　　┃  神兽保佑
 * -------------------------//┃　　　┃  代码无BUG！
 * -------------------------//┃　　　┗━━━┓
 * -------------------------//┃　　　　　　　┣┓
 * -------------------------//┃　　　　　　　┏┛
 * -------------------------//┗┓┓┏━┳┓┏┛
 * -------------------------// ┃┫┫　┃┫┫
 * -------------------------// ┗┻┛　┗┻┛
 */
abstract class BaseAdapter<H>(var context: Context, var data: MutableList<H>) : RecyclerView.Adapter<BaseAdapter.TviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TviewHolder {
        return TviewHolder(View.inflate(context, viewType, null))
    }

    open fun onBindOnclic(itemview: View, position: Int) {
    }

    override fun onBindViewHolder(holder: TviewHolder, position: Int) {
        onSetData(holder.itemView, data[position], position)
        onBindOnclic(holder.itemView, position)

    }

    fun updataAdapter(list: MutableList<H>) {
        /*  data.clear()
          if (list.isNotEmpty())
              data.addAll(list)*/
        if (data != list) {
            data = list
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = if (data == null) {
        0
    } else {
        data.size
    }

    abstract fun onSetData(itemview: View, h: H, position: Int)
    abstract override fun getItemViewType(position: Int): Int

    class TviewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

}

