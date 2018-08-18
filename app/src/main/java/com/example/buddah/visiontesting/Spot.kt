package com.example.buddah.visiontesting

import android.graphics.Bitmap
import android.graphics.Canvas


class Spot(var image: Bitmap) {


    fun draw(canvas: Canvas, Point: Pair<Int,Int>) {
        canvas.drawBitmap(image, Point.first.toFloat(),
                Point.second.toFloat(),
                null)
    }

    //no need for update yet
    fun update() {

    }

}
