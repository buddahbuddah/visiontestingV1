package com.example.buddah.visiontesting

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas


class variableSprite(private val image: Bitmap, x: Long, y: Long) {

    private var x: Long = x
    private var y: Long = y
    private val w: Int
    private val h: Int
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        w = image.width
        h = image.height
    }
    //draws red sprite with pair of x y coordinates.
    fun draw(canvas: Canvas, points : Pair<Int, Int>) {

        canvas.drawBitmap(image, points.first.toFloat(), points.second.toFloat(), null)
    }


}
