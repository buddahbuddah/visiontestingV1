package com.example.buddah.visiontesting

import android.graphics.Canvas
import android.os.Handler
import android.view.SurfaceHolder
import com.example.buddah.visiontesting.Timer.DelayCallback
import android.R.attr.delay





//Created by arjun on 26/12/17
//modified by lempia 22/7/18

class VisionThread(private val surfaceHolder: SurfaceHolder, private val visionView: VisionView) : Thread() {
    private var running: Boolean = false
    private val targetFPS = 50 // frames per second, the rate at which you would like to refresh the Canvas
    private var locations = LocationResults()

    init {

        locations.printList()
        var ListPoints= locations.getPointList()
        println(ListPoints)

    }

    fun setRunning(isRunning: Boolean) {
        this.running = isRunning
    }

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        val targetTime = (1000 / .25).toLong()//changes every four seconds
        val targetUpdateTime = 3

        while (running) {
            startTime = System.nanoTime()
            canvas = null

            try {
                // locking the canvas allows us to draw on to it
                // this is the important part
                    canvas = this.surfaceHolder.lockCanvas()
                    synchronized(surfaceHolder) {

                        this.visionView.update()
                        this.visionView.draw(canvas!!, "left")
                    }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            try {
                sleep(waitTime)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private var canvas: Canvas? = null
    }

}