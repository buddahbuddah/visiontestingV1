package com.example.buddah.visiontesting

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas


class centerSprite(var image: Bitmap) {
    var leftX: Int = 0
    var leftY: Int = 0
    var rightX: Int = 0
    var rightY: Int = 0
    var w: Int = 0
    var h: Int = 0

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        w = image.width
        h = image.height

        leftX = screenWidth/4
        leftY = screenHeight/2
        rightX = screenWidth/4 + screenWidth/2
        rightY = screenHeight/2
    }

    //
    fun drawLeft(canvas: Canvas) {
        canvas.drawBitmap(image, leftX.toFloat(), leftY.toFloat(), null)
    }

    fun drawRight(canvas: Canvas) {
        canvas.drawBitmap(image, rightX.toFloat(), rightY.toFloat(), null)
    }

    fun update() {
        // val randomNum = ThreadLocalRandom.current().nextInt(1, 5)
/*
        if (x > screenWidth - image.width || x < image.width) {
            xVelocity = xVelocity * -1
        }
        if (y > screenHeight - image.height || y < image.height) {
            yVelocity = yVelocity * -1
        }

        x += (xVelocity)
        y += (yVelocity)
*/
    }

}