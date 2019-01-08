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
import java.awt.font.NumericShaper
import java.io.File
import kotlin.math.absoluteValue


class TestView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {

    private val thread: TestThread
    private val clickPlayer = MediaPlayer.create(context, R.raw.click)
    private val TestContainer = TestContainer()
    private var Spot = Spot(BitmapFactory.decodeResource(resources, R.drawable.whitecircle10pxffffff))
    private val Center = Spot(BitmapFactory.decodeResource(resources, R.drawable.redcircle10px))
    private val background = Spot(BitmapFactory.decodeResource(resources, R.drawable.backgroundddddd))
    private var TestResults = TestResults(TestContainer)
    private var NumberList : Array<Int>
    private var Side : Boolean
    private var threadControl : Boolean
    private var resultsBoolean : Boolean
    private var pause: Boolean
    private var pauseCheck: Int
    private var finalPlacebo: Int

    init {
        ChooseSpot()
        NumberList = ChooseNumbers()
        Side = false
        threadControl = false
        resultsBoolean = false
        holder.addCallback(this)
        pause = false
        pauseCheck = 0
        finalPlacebo = 0
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
    fun ChooseNumbers() : Array<Int> {
        //builds array of pointers for correct number values
        val fIn = context.openFileInput("configure.txt")
        var fileContentsInt = fIn.read().absoluteValue
        fIn.close()
        println("The value of the configure file inside of Numbers is:" +fileContentsInt)
        if (fileContentsInt >= 3)
            fileContentsInt = 3

        val result = arrayOf(
                _one[fileContentsInt],
                _two[fileContentsInt],
                _three[fileContentsInt],
                _four[fileContentsInt],
                _five[fileContentsInt],
                _six[fileContentsInt],
                _seven[fileContentsInt],
                _eight[fileContentsInt],
                _nine[fileContentsInt],
                _ten[fileContentsInt],
                _eleven[fileContentsInt],
                _twelve[fileContentsInt],
                _thirteen[fileContentsInt],
                _fourteen[fileContentsInt],
                _fifteen[fileContentsInt],
                _sixteen[fileContentsInt],
                _seventeen[fileContentsInt],
                _eighteen[fileContentsInt],
                _nineteen[fileContentsInt],
                _twenty[fileContentsInt],
                _twentyone[fileContentsInt],
                _twentytwo[fileContentsInt],
                _twentythree[fileContentsInt],
                _twentyfour[fileContentsInt],
                _twentyfive[fileContentsInt])

        return result
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
        if (TestContainer.checkLeft() && pauseCheck == 0) {
            pause = true
        }
        if (TestContainer.checkLeft() && TestContainer.checkRight()) {
            this.saveToCache()
            this.readFromCache()
            thread.tellThreadDone()
            finalPlacebo = TestResults.getControl()
            resultsBoolean = true
        }
        //draw results
        if (resultsBoolean == true) {
            print(finalPlacebo)
            //display all results.
            var results: HashMap<Pair<Int, Int>, Int> = TestResults.getValues()
            var resultkeys = results.keys
            for (entry in resultkeys) {
                if (results[entry] != 1) {
                    val tempSpot = Spot(
                            BitmapFactory.decodeResource(
                                    resources, NumberList[results[entry]!!.absoluteValue - 1]))
                    tempSpot.draw(canvas, entry)
                    //Spot.drawBrightness(canvas, entry, TestResults.getBrightness(entry)!!.toFloat())

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

        if (pause == true && pauseCheck == 0){
            pause = false
            pauseCheck++
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


    companion object {

        val _one = arrayListOf(
                R.drawable.one10, R.drawable.one15, R.drawable.one20, R.drawable.one25)
        val _two = arrayListOf(
                R.drawable.two10, R.drawable.two15, R.drawable.two20, R.drawable.two25)
        val _three = arrayListOf(
                R.drawable.three10, R.drawable.three15, R.drawable.three20, R.drawable.three25)
        val _four = arrayListOf(
                R.drawable.four10, R.drawable.four15, R.drawable.four20, R.drawable.four25)
        val _five = arrayListOf(
                R.drawable.five10, R.drawable.five15, R.drawable.five20, R.drawable.five25)
        val _six = arrayListOf(
                R.drawable.six10, R.drawable.six15, R.drawable.six20, R.drawable.six25)
        val _seven = arrayListOf(
                R.drawable.seven10, R.drawable.seven15, R.drawable.seven20, R.drawable. seven25)
        val _eight = arrayListOf(
                R.drawable.eight10, R.drawable.eight15, R.drawable.eight20, R.drawable.eight25)
        val _nine = arrayListOf(
                R.drawable.nine10, R.drawable.nine15, R.drawable.nine20, R.drawable.nine25)
        val _ten = arrayListOf(
                R.drawable.ten10, R.drawable.ten15, R.drawable.ten20, R.drawable.ten25)
        val _eleven = arrayListOf(
                R.drawable.eleven10, R.drawable.eleven15, R.drawable.eleven20, R.drawable.eleven25)
        val _twelve = arrayListOf(
                R.drawable.twelve10, R.drawable.twelve15, R.drawable.twelve20, R.drawable.twelve25)
        val _thirteen = arrayListOf(
                R.drawable.thirteen10, R.drawable.thirteen15, R.drawable.thirteen20, R.drawable.thirteen25)
        val _fourteen = arrayListOf(
                R.drawable.fourteen10, R.drawable.fourteen15, R.drawable.fourteen20, R.drawable.fourteen25)
        val _fifteen = arrayListOf(
                R.drawable.fifteen10, R.drawable.fifteen15, R.drawable.fifteen20, R.drawable.fifteen25)
        val _sixteen = arrayListOf(
                R.drawable.sixteen10, R.drawable.sixteen15, R.drawable.sixteen20, R.drawable.sixteen25)
        val _seventeen = arrayListOf(
                R.drawable.seventeen10, R.drawable.seven15, R.drawable.seventeen20, R.drawable.seventeen25)
        val _eighteen = arrayListOf(
                R.drawable.eighteen10, R.drawable.eighteen15, R.drawable.eighteen20, R.drawable.eighteen25)
        val _nineteen = arrayListOf(
                R.drawable.nineteen10, R.drawable.nineteen15, R.drawable.nineteen20, R.drawable.nineteen25)
        val _twenty = arrayListOf(
                R.drawable.twenty10, R.drawable.twenty15, R.drawable.twenty20, R.drawable.twenty25)
        val _twentyone = arrayListOf(
                R.drawable.twentyone10, R.drawable.twentyone15, R.drawable.twentyone20, R.drawable.twentyone25)
        val _twentytwo = arrayListOf(
                R.drawable.twentytwo10, R.drawable.twentytwo15, R.drawable.twentytwo20, R.drawable.twentytwo25)
        val _twentythree = arrayListOf(
                R.drawable.twentythree10, R.drawable.twentythree15, R.drawable.twentythree20, R.drawable.twentythree25)
        val _twentyfour = arrayListOf(
                R.drawable.twentyfour10, R.drawable.twentyfour15, R.drawable.twentyfour20, R.drawable.twentyfour25)
        val _twentyfive = arrayListOf(
                R.drawable.twentyfive10, R.drawable.twentyfive15, R.drawable.twentyfive20, R.drawable.twentyfive25)
    }

}
