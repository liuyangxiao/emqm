package com.burning.emqmsg.adapter

import android.content.Context
import android.net.Uri
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.glidehelp.MyTransform
import kotlinx.android.synthetic.main.item_imagview.view.*

/**
 * Created by burning on 2018/10/30.
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
class ImageAdapter(context: Context, data: MutableList<Uri>) : BaseAdapter<Uri>(context, data) {
    override fun onSetData(itemview: View, h: Uri, position: Int) {
        if (position == data.size) {
            //添加--
            //   Glide.with(context.applicationContext).load()./*apply(MyTransform.getRequestOptions(10)).*/into(itemview.item_imageview_image)
            itemview.item_imageview_image.setImageResource(R.mipmap.xiangce)
        } else {
            Glide.with(context).load(h).apply(MyTransform.getRequestOptions(10)).into(itemview.item_imageview_image)
            //itemview.item_imageview_image.setImageURI(h)

        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_imagview


}