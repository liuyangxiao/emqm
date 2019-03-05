package com.burning.emqmsg.activity

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bm.library.PhotoView
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.utils.ImageConfig
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : BaseActivity() {
    companion object {
        var PAGE_VIEW = "PAGEICONS"
    }

    override fun init() {
        var stringArrayListExtra = intent.getStringArrayListExtra(PAGE_VIEW)
        if (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) {
            finish()
            return
        }
        pager.pageMargin = ((resources.displayMetrics.density * 15).toInt())
        pager.adapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj
            override fun getCount(): Int = stringArrayListExtra.size
            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val view = PhotoView(this@ViewPagerActivity)
                view.enable()
                view.scaleType = ImageView.ScaleType.FIT_CENTER
                Glide.with(this@ViewPagerActivity).load(ImageConfig.Image_path + stringArrayListExtra[position]).into(view)
                //view.setImageResource(imgsId[position]);
                container.addView(view)
                return view
            }

            override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
                container.removeView(obj as View)
            }
        }
    }

    override fun getActivityLayout(): Int = R.layout.activity_view_pager


}
