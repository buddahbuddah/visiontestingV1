package com.example.buddah.visiontesting

import android.graphics.Canvas
import android.view.SurfaceHolder


class TestThread(private val surfaceHolder: SurfaceHolder, private val TestView: TestView) : Thread() {
    private var running: Boolean = false
    private var buttonCheck : Boolean? = false
    private var done : Boolean = false


    private val targetFPS = 4 // frames per second, the rate at which you would like to refresh the Canvas

    fun setRunning(isRunning: Boolean) {
        this.running = isRunning
    }

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        val targetTime = (500 / targetFPS).toLong()
        buttonCheck = false

        while (running) {
            startTime = System.nanoTime()
            canvas = null

            try {
                // locking the canvas allows us to draw on to it
                canvas = this.surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    this.TestView.update()
                    this.TestView.draw(canvas!!)

                    if (buttonCheck == null) {
                        if (!done)
                            TestView.incrementFailCounter()
                        buttonCheck = false
                    }
                    if (buttonCheck == false) {
                        TestView.updateResults(false)
                        buttonCheck = false
                    }
                    if (buttonCheck == true) {
                        TestView.updateResults(true)
                        buttonCheck = false
                    }

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

    fun updateCurrentBoolean(){
        buttonCheck = true
    }

    fun changeBooleanToNull(){
        buttonCheck = null
    }

    fun tellThreadDone(){
        done = true
    }

}