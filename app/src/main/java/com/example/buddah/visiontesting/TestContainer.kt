package com.example.buddah.visiontesting

import android.content.res.Resources
import java.util.*

//helper function for randomly pulling points from list.
fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive+1)-start) + start

//calculates testing points dynamically based on phone resource.

class TestContainer {

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private val xListLeft = mutableListOf<Int>()
    private val xListRight = mutableListOf<Int>()
    private val yListLeft = mutableListOf<Int>()

    private val ListLeft : MutableList<Pair<Int,Int>>
    private val ListRight : MutableList<Pair<Int,Int>>

    private var ControlCount : Int
    var currentPair : Pair<Int,Int>

    init {
        BuildLists()
        ListLeft = getPointListLeft()
        ListRight = getPointListRight()
        ControlCount = 0
        currentPair = Pair(0,0)
    }

    fun getNextPointLeft(): Pair<Int,Int>? {

        if (!ListLeft.isEmpty()) {
            val RandomIndex = (0 until ListLeft.size).random()
            val result = ListLeft[RandomIndex]
            ListLeft.removeAt(RandomIndex)
            currentPair = result
            return result
        }
        else return null

    }

    fun getNextPointRight(): Pair<Int,Int>? {
        if (!ListRight.isEmpty()) {
            val RandomIndex = (0 until ListRight.size).random()
            val result = ListRight[RandomIndex]
            ListRight.removeAt(RandomIndex)
            currentPair = result
            return result
        }
        else return null
    }
    fun checkRight(): Boolean {
        return ListRight.isEmpty()
    }
    fun checkLeft(): Boolean {
        return ListLeft.isEmpty()
    }

    fun getCenterLeft(): Pair<Int,Int> {
        val leftX = screenWidth/4
        val leftY = screenHeight/2

        return Pair(leftX, leftY)
    }
    fun getCenter(): Pair<Int,Int> {
        val X = 0
        val Y = 0

        return Pair(X,Y)
    }

    fun getCenterRight(): Pair<Int,Int>{
        val rightX = screenWidth/4 + screenWidth/2
        val rightY = screenHeight/2

        return Pair(rightX, rightY)
    }

    // if true, skip the check
    fun checkTheOdds(): Boolean {

        val chooseRandom = (1 until 5).random()
        //logic for control phases. Stops after 15 counts, might be less.
        return (chooseRandom % 4 == 0 && ControlCount < 15)


    }


    fun printList(){
        println(xListLeft)
        println(yListLeft)
    }

    fun getPointListLeft(): MutableList<Pair<Int,Int>>{
        val returnList = mutableListOf<Pair<Int, Int>>()

        var selector = 0
        for (x in xListLeft){
            returnList.add(Pair(x, yListLeft.get(selector)))
            selector ++
        }
        return returnList

    }

    fun getPointListRight(): MutableList<Pair<Int,Int>>{
        val returnList = mutableListOf<Pair<Int,Int>>()

        var selector = 0
        for (x in xListRight){
            returnList.add(Pair(x, yListLeft.get(selector)))
            selector ++
        }
        return returnList
    }

    fun ClosedRange<Int>.random() =
            Random().nextInt((endInclusive+1)-start) + start



    private fun BuildLists() {
        var x = 0
        var xMiddle = Resources.getSystem().displayMetrics.widthPixels / 2
        var yMiddle = Resources.getSystem().displayMetrics.heightPixels / 2
        var xMiddleLeft = Resources.getSystem().displayMetrics.widthPixels / 4
        var xMiddleRight = Resources.getSystem().displayMetrics.widthPixels / 4 + xMiddle

        //first row
        xListLeft.add(xMiddleLeft / 2)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft / 2 + xMiddleLeft)
        //second row
        xListLeft.add(xMiddleLeft / 5)
        xListLeft.add(xMiddle - xMiddleLeft / 5)//using relative middle
        //third row
        xListLeft.add(xMiddleLeft - (4 * xMiddleLeft / 6))
        xListLeft.add(xMiddleLeft - (2 * xMiddleLeft / 6))
        xListLeft.add(xMiddleLeft + (2 * xMiddleLeft / 6))
        xListLeft.add(xMiddleLeft + (4 * xMiddleLeft / 6))
        //fourth row
        xListLeft.add(xMiddleLeft - (xMiddleLeft / 12))
        xListLeft.add(xMiddleLeft + (xMiddleLeft / 12))
        //fifth row
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12 * 11)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 2)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12 * 11)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 2)
        //sixth row
        xListLeft.add(xMiddleLeft - xMiddleLeft / 3 * 2)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 3)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 3)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 3 * 2)
        //seventh row
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12)
        //eight row
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12 * 11)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12 * 8)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12 * 3)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12 * 3)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12 * 8)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12 * 11)
        //this is the middle. Now we just repeat the whole thing backward.
        // ninth row
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12 * 11)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12 * 8)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12 * 3)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12 * 3)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12 * 8)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12 * 11)
        //tenth row
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12)
        //eleventh row
        xListLeft.add(xMiddleLeft - xMiddleLeft / 3 * 2)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 3)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 3)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 3 * 2)
        //twelfth row
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12 * 11)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 2)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12 * 11)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 2)
        //thirteeth row
        xListLeft.add(xMiddleLeft - (xMiddleLeft / 12))
        xListLeft.add(xMiddleLeft + (xMiddleLeft / 12))
        //fourteeth row
        xListLeft.add(xMiddleLeft - (4 * xMiddleLeft / 6))
        xListLeft.add(xMiddleLeft - (2 * xMiddleLeft / 6))
        xListLeft.add(xMiddleLeft + (2 * xMiddleLeft / 6))
        xListLeft.add(xMiddleLeft + (4 * xMiddleLeft / 6))
        //fifteenth row
        xListLeft.add(xMiddleLeft / 5)
        xListLeft.add(xMiddle - xMiddleLeft / 5)//using relative middle
        //sixteenth row
        xListLeft.add(xMiddleLeft / 2)
        xListLeft.add(xMiddleLeft - xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft + xMiddleLeft / 12)
        xListLeft.add(xMiddleLeft / 2 + xMiddleLeft)

        //first row y
        var counter = 0
        var numberInRow = 4
        while (counter < numberInRow) {
            yListLeft.add(yMiddle + yMiddle / 12 * 11)
            counter++
        }
        //second row y
        counter = 0
        numberInRow = 2
        while (counter < numberInRow) {
            yListLeft.add(yMiddle + yMiddle / 6 * 5)
            counter++
        }
        //third row y
        counter = 0
        numberInRow = 4
        while (counter < numberInRow) {
            yListLeft.add(yMiddle + yMiddle / 3 * 2)
            counter++
        }
        //fourth row y
        counter = 0
        numberInRow = 2
        while (counter < numberInRow) {
            yListLeft.add(yMiddle + yMiddle / 12 * 7)
            counter++
        }
        //fifth row y
        counter = 0
        numberInRow = 4
        while (counter < numberInRow) {
            yListLeft.add(yMiddle + yMiddle / 2)
            counter++
        }
        //sixth row y
        counter = 0
        numberInRow = 4
        while (counter < numberInRow) {
            yListLeft.add(yMiddle + yMiddle / 3)
            counter++
        }
        //seventh row y
        counter = 0
        numberInRow = 2
        while (counter < numberInRow) {
            yListLeft.add(yMiddle + yMiddle / 12 * 3)
            counter++
        }
        //eighth row y
        counter = 0
        numberInRow = 8
        while (counter < numberInRow) {
            yListLeft.add(yMiddle + yMiddle / 12)
            counter++
        }
        //ninth row y (mirrors eighth)
        counter = 0
        numberInRow = 8
        while (counter < numberInRow) {
            yListLeft.add(yMiddle - yMiddle / 12)
            counter++
        }
        //tenth row y (mirrors seventh)
        counter = 0
        numberInRow = 2
        while (counter < numberInRow) {
            yListLeft.add(yMiddle - yMiddle / 12 * 3)
            counter++
        }
        //eleventh row (mirrors sixth)
        counter = 0
        numberInRow = 4
        while (counter < numberInRow) {
            yListLeft.add(yMiddle - yMiddle / 3)
            counter++
        }
        //twelfth row (mirrors fifth)
        counter = 0
        numberInRow = 4
        while (counter < numberInRow) {
            yListLeft.add(yMiddle - yMiddle / 2)
            counter++
        }
        //thirteenth row (mirrors fourth)
        counter = 0
        numberInRow = 2
        while (counter < numberInRow) {
            yListLeft.add(yMiddle - yMiddle / 12 * 7)
            counter++
        }
        //fourteenth row (mirrors third)
        counter = 0
        numberInRow = 4
        while (counter < numberInRow) {
            yListLeft.add(yMiddle - yMiddle / 3 * 2)
            counter++
        }
        //fifteenth row (mirrors second)
        counter = 0
        numberInRow = 2
        while (counter < numberInRow) {
            yListLeft.add(yMiddle - yMiddle / 6 * 5)
            counter++
        }
        //sixteenth row (mirrors first)
        counter = 0
        numberInRow = 4
        while (counter < numberInRow) {
            yListLeft.add(yMiddle - yMiddle / 12 * 11)
            counter++
        }

        //xListRight mirrors the left with the center variable changed.
        for (x in xListLeft) {
            xListRight.add(x + xMiddle)
        }
    }

}