package com.example.buddah.visiontesting

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.SurfaceHolder



class TestView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: TestThread
    private val clickPlayer = MediaPlayer.create(context, R.raw.click)
    private val TestContainer = TestContainer()
    private val Spot = Spot(BitmapFactory.decodeResource(resources, R.drawable.transcircle25ffffff))
    private val Center = Spot(BitmapFactory.decodeResource(resources, R.drawable.redcircle25px))
    private val background = Spot(BitmapFactory.decodeResource(resources, R.drawable.backgroundddddd))
    private var TestResults = TestResults()
    //private val Center = Spot()
    //private val Background = Spot()
    private var Side : Boolean
    private var threadControl : Boolean
    private var resultsBoolean : Boolean


    init {
        //println("got to init")
        Side = false
        threadControl = false
        resultsBoolean = false
        // add callback
        holder.addCallback(this)

        // instantiate the test thread
        thread = TestThread(holder, this)
    }


    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {

        // start the game thread
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }
    }

    //maybe put background stuff here? it's called anyway
    fun update() {

    }
    fun updateResults(verdict: Boolean){
        TestResults.addPoint(TestContainer.currentPair)
        TestResults.addResult(TestContainer.currentPair, verdict)
    }

    fun incrementFailCounter(){
        TestResults.incrementControl()
        println("Control Checked: ${TestResults.liarPoints}")
    }

    //does not need to be this complex
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        background.draw(canvas, TestContainer.getCenter())
        clickPlayer.start()
        //check if time to display results.
        if (TestContainer.checkLeft() && TestContainer.checkRight()){
            resultsBoolean = true
        }
        //draw results
        if (resultsBoolean == true){
            //display all results.
            var results : HashMap<Pair<Int,Int>,Boolean> = TestResults.getResults()
            var resultkeys = results.keys
            for (entry in resultkeys){
                if (results[entry] == false)
                    Spot.draw(canvas, entry)
            }
        }
        //Control Check
        if (TestContainer.checkTheOdds()) {
            threadControl = true
            if (!Side) {
                Center.draw(canvas, TestContainer.getCenterLeft())
            }
            else
                Center.draw(canvas, TestContainer.getCenterRight())
        }

        else {
            //Left / Right Check
            threadControl = false
            if (!Side) {
                Center.draw(canvas, TestContainer.getCenterLeft())
                //If Left is Empty, switch to right side
                if (!TestContainer.checkLeft())
                    Spot.draw(canvas, TestContainer.getNextPointLeft()!!)
                else
                    Side = true
                //maybe display something inbetween tests
            }
            else {
                Center.draw(canvas, TestContainer.getCenterRight())
                if (!TestContainer.checkRight())
                    Spot.draw(canvas, TestContainer.getNextPointRight()!!)
                //else
                //start new activity
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!threadControl)
            thread.updateCurrentBoolean()
        else
            thread.changeBooleanToNull()

        return true
    }
}
