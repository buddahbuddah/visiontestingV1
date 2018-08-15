package com.example.buddah.visiontesting

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*


class VisionView(context: Context, attributes: AttributeSet)
    : SurfaceView(context, attributes),
        SurfaceHolder.Callback {

    private val thread:  VisionThread
    private var centerSprite: centerSprite? = null
    private var variableSprite: variableSprite? = null
    private var locations = LocationResults()
    private var clickPlayer = MediaPlayer.create(context, R.raw.click)
    var ListPointsLeftList: List<Pair<Int,Int>>?
    var ListPointsLeft : MutableList<Pair<Int,Int>>
    var ListPointsRightList: List<Pair<Int,Int>>?
    var ListPointsRight : MutableList<Pair<Int,Int>>
    private var visionResults = VisionResults()
    var currentPair: Pair<Int,Int> = Pair(0,0)
    //for checking blank vision screens
    var controlCount : Int
    //for right/left selection. default false = left, function to change it to true for second half of test
    var leftOrRight : Boolean
    var controlBoolean : Boolean


    init {
        //why is list conversion so painful?
        ListPointsLeftList = locations.getPointListLeft()
        var tempLeft = ListPointsLeftList!!.toTypedArray()
        ListPointsLeft = tempLeft.toMutableList()
        ListPointsRightList = locations.getPointListRight()
        var tempRight = ListPointsRightList!!.toTypedArray()
        ListPointsRight = tempRight.toMutableList()
        //locations.printList()
        //add callback
        holder.addCallback(this)
        thread = VisionThread(holder, this)
        controlCount = 0
        leftOrRight = false
        controlBoolean = false

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while(retry){
            try{
                thread.setRunning(false)
                thread.join()
            } catch (e:Exception) {
                e.printStackTrace()
            }
            retry = false
        }

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        //objects
        centerSprite = centerSprite(BitmapFactory.decodeResource(resources, R.drawable.center))
        variableSprite = variableSprite(BitmapFactory.decodeResource(resources, R.drawable.test), 10, 10)


        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    fun update() {
        //does nothing at all
        centerSprite!!.update()

    }
    fun updateResults(isSeen: Boolean){
        visionResults.addPoint(currentPair)
        visionResults.addResult(currentPair, isSeen)
    }

    fun incrementFailCounter(){
        visionResults.incrementControl()
        println(visionResults.liarPoints)
        println("liar!")
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)

        clickPlayer.start()
///*

        //take this random mod 4 to get ranges between 11 and 23 dead draws on average
        val chooseRandom = (1 until 5).random()

        //logic for control phases. Stops after 15 counts, might be less.
        if (chooseRandom % 4 == 0 && controlCount < 15){
            //increment control count
            //skip draw phase
            controlCount ++
            controlBoolean = true
            if (leftOrRight == false)
                centerSprite!!.drawLeft(canvas)
            else
                centerSprite!!.drawRight(canvas)
        }
        else if (ListPointsLeft!!.isNotEmpty() && leftOrRight == false){
            controlBoolean = false
            centerSprite!!.drawLeft(canvas)
            val toTake = (0 until ListPointsLeft!!.size).random()
            val toPlug = ListPointsLeft!![toTake]
            currentPair = toPlug
            variableSprite!!.draw(canvas, toPlug)
            ListPointsLeft.remove(toPlug)
        }

        else if (ListPointsRight!!.isNotEmpty() && leftOrRight == true){
            controlBoolean = false
            centerSprite!!.drawRight(canvas)
            val toTake = (0 until ListPointsRight!!.size).random()
            val toPlug = ListPointsRight!![toTake]
            currentPair = toPlug
            variableSprite!!.draw(canvas, toPlug)
            ListPointsRight.remove(toPlug)
        }

        //else go to final screen/inbetween screen depending on leftOrRight variable

//*/

            //go to final screen and pass through the visionresults.

        //loop used for testing also will be used for
        /*
        for (x in ListPointsLeft!!){
            variableSprite!!.draw(canvas, x)
            currentPair = x
        }
        for (y in ListPointsRight!!){
            variableSprite!!.draw(canvas, y)
            currentPair = y
        }
        */
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (controlBoolean == false)
            thread.updateCurrentBoolean()
        else
            thread.changeBooleanToNull()

        println("Touched!")

        return true
    }

    fun setUpForRightTest(){
        controlCount = 0
        leftOrRight = true
    }

}
//helper function for randomly pulling points from list.
fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive+1)-start) + start