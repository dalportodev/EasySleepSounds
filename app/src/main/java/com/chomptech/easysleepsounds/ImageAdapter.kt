package com.chomptech.easysleepsounds

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView


/**
 * Created by wp7ch on 1/24/2018.
 */
class ImageAdapter(private val mContext: Context) : BaseAdapter() {



    // references to our images
    private val mThumbIds = arrayOf<Int>(R.drawable.rain, R.drawable.thunder, R.drawable.highway, R.drawable.crickets, R.drawable.stop)

    override fun getCount(): Int {
        return mThumbIds.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = ImageView(mContext)
            imageView.setLayoutParams(AbsListView.LayoutParams(280, 280))
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = (convertView as ImageView?)!!
        }

        imageView.setImageResource(mThumbIds[position])
        return imageView
    }


}