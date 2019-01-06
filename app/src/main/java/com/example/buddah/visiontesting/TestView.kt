package com.example.buddah.visiontesting

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.SurfaceHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


class TestView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {

    private val thread: TestThread
    private val clickPlayer = MediaPlayer.create(context, R.raw.click)
    private val TestContainer = TestContainer()
    private var Spot = Spot(BitmapFactory.decodeResource(resources, R.drawable.whitecircle10pxffffff))
    private val Center = Spot(BitmapFactory.decodeResource(resources, R.drawable.redcircle10px))
    private val background = Spot(BitmapFactory.decodeResource(resources, R.drawable.backgroundddddd))
    private var TestResults = TestResults(TestContainer)
    private var Side : Boolean
    private var threadControl : Boolean
    private var resultsBoolean : Boolean
    private var pause: Boolean
    private var pauseChecker: Int
    private var finalTestCount: Int

    init {
        ChooseSpot()
        Side = false
        threadControl = false
        resultsBoolean = false
        holder.addCallback(this)
        pause = false
        pauseChecker = 0
        finalTestCount = 0
        thread = TestThread(holder, this)
    }

    fun ChooseSpot(){
        val mImageIds = arrayOf(R.drawable.transcircle10ffffff,
                R.drawable.transcircle15ffffff,
                R.drawable.transcircle20ffffff,
                R.drawable.transcircle24ffffff,
                R.drawable.transcircle30ffffff,
                R.drawable.transcircle35ffffff,
                R.drawable.transcircle40ffffff,
                R.drawable.transcircle45ffffff)
        val fIn = context.openFileInput("configure.txt")
        val fileContentsInt = fIn.read()
        fIn.close()
        println("The value of configure file is: " + fileContentsInt)
        Spot = Spot(BitmapFactory.decodeResource(resources, mImageIds[fileContentsInt]))


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
        //check if time to display results.
        if (TestContainer.checkLeft() && pauseChecker == 0) {
            pause = true
        }
        if (TestContainer.checkLeft() && TestContainer.checkRight()) {
            this.saveToCache()
            this.readFromCache()
            thread.tellThreadDone()
            finalTestCount = TestResults.getControl()
            resultsBoolean = true
        }
        //draw results
        if (resultsBoolean == true) {
            print(finalTestCount)
            //display all results.
            var results: HashMap<Pair<Int, Int>, Int> = TestResults.getValues()
            var resultkeys = results.keys
            for (entry in resultkeys) {
                if (results[entry] != 1) {
                    Spot.drawBrightness(canvas, entry, TestResults.getBrightness(entry)!!.toFloat())

                }
            }
        }
        if (pause != true && resultsBoolean == false){
            //Control Check
            if (TestContainer.checkTheOdds()) {
                clickPlayer.start()
                threadControl = true
                if (!Side) {
                    Center.draw(canvas, TestContainer.getCenterLeft())
                } else if (!resultsBoolean)
                    Center.draw(canvas, TestContainer.getCenterRight())
            } else {
                //Left / Right Check
                clickPlayer.start()
                threadControl = false
                if (!Side) {
                    Center.draw(canvas, TestContainer.getCenterLeft())
                    //If Left is Empty, switch to right side
                    if (!TestContainer.checkLeft()) {
                        val tempPair = TestResults.getNextPointLeft()!!
                        Spot.drawBrightness(canvas, tempPair,
                                TestResults.getBrightness(tempPair)!!.toFloat())
                    }
                    else
                        Side = true

                } else if (!TestContainer.checkRight()) {
                    Center.draw(canvas, TestContainer.getCenterRight())
                    if (!TestContainer.checkRight()) {
                        val tempPair = TestResults.getNextPointRight()!!
                        Spot.drawBrightness(canvas, tempPair,
                                TestResults.getBrightness(tempPair)!!.toFloat())
                    }
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (pause == true && pauseChecker == 0){
            pause = false
            pauseChecker++
        }

        if (!threadControl)
            thread.updateCurrentBoolean()
        else
            thread.changeBooleanToNull()

        return true
    }

    fun saveToCache(){

        val gson = Gson()
        val toSave = gson.toJson(TestResults.getValues())

        //writes .txt to cache file in the cache.
        context.openFileOutput("temp.txt", Context.MODE_PRIVATE).use {
            it.write(toSave.toByteArray())
        }
    }

    fun readFromCache(){

        val gson = Gson()

        val file = File(context.filesDir, "temp.txt")
        val fileContents : String = file.readText()
        println("**********************************************")
        println(fileContents)
        println("**********************************************")
        val finalMap : Map<Pair<Int,Int>, Int> = gson.fromJson(fileContents,
                object : TypeToken<Map<Any,Any>>(){}.type)

        println(finalMap)

    }



    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
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
    fun update() {
    }
}
