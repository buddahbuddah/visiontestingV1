package com.example.buddah.visiontesting

import android.graphics.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Bitmap




class Spot(var image: Bitmap) {

    val original : Bitmap

    init {
        original = image

    }

    fun draw(canvas: Canvas, Point: Pair<Int,Int>){

        canvas.drawBitmap(original, Point.first.toFloat(), Point.second.toFloat(), null)

    }

    fun drawBrightness(canvas: Canvas, Point: Pair<Int,Int>, brightness : Float) {
        var i = 1
        var colorFilter = changeBitmapBrightness(original, i.toFloat(), brightness)

        canvas.drawBitmap(original, Point.first.toFloat(),
                Point.second.toFloat(),
                colorFilter)
    }

    fun changeBitmapBrightness(bmp: Bitmap, contrast: Float, brightness: Float): Paint {

        val cm = ColorMatrix(floatArrayOf(contrast, 0f, 0f, 0f, brightness,
                0f, contrast, 0f, 0f, brightness,
                0f, 0f, contrast, 0f, brightness,
                0f, 0f, 0f, 1f, 0f))

        val ret = Bitmap.createBitmap(bmp.width, bmp.height, bmp.config)
        val paint = Paint()
        paint.setColorFilter(ColorMatrixColorFilter(cm))

        return paint
    }

}
