package com.example.buddah.visiontesting

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*


class VisionView(context: Context, attributes: AttributeSet)
    : SurfaceView(context, attributes),
        SurfaceHolder.Callback {

    private val thread:  VisionThread
    //instantiates the center focal point
    private var centerSprite: centerSprite? = null
    //instantiates the testing node
    private var variableSprite: variableSprite? = null
    private var locations = LocationResults()
    //used to store the list of testing points to pull from randomly.
    var ListPoints: List<Pair<Int,Int>>?

    init {

        //not yet dynamically building a specific subset. Building the entire subset.
        ListPoints = locations.getPointList()
        //locations.printList()
        //add callback
        holder.addCallback(this)
        thread = VisionThread(holder, this)

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
        //thread is running on separate thread, can do timing updates based on this
       // myTimer.schedule(centerSprite!!.update(), 4000)
        centerSprite!!.update()

    }

    fun draw(canvas: Canvas, orientation: String){
        super.draw(canvas)
        //implement left vs right, possibly left/right with different centers
        centerSprite!!.drawLeft(canvas)
        centerSprite!!.drawRight(canvas)
        //editing variablesprite to draw at a random point.
        /*
        val toPlug = ListPoints!![(0 until ListPoints!!.size).random()]
        variableSprite!!.draw(canvas, toPlug)
        */
        for (x in ListPoints!!){
            variableSprite!!.draw(canvas, x)
        }
    }
}
//helper function for randomly pulling points from list.
fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive+1)-start) + start