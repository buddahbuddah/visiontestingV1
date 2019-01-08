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
    private val LeftValueMap : MutableMap<Pair<Int,Int>, Int>
    private val ListRight : MutableList<Pair<Int,Int>>
    private val RightValueMap : MutableMap<Pair<Int,Int>, Int>

    private var ControlCount : Int
    var currentPair : Pair<Int,Int>

    init {
        BuildLists()
        ListLeft = getPointListLeft()
        LeftValueMap = getValueListLeft()
        ListRight = getPointListRight()
        RightValueMap = getValueListRight()
        ControlCount = 0
        currentPair = Pair(0,0)
    }

    fun removeLeft(input: Pair<Int,Int>){
        println("Removing left from inside test container")
        println("New List: ")
        println(ListLeft)
        ListLeft.remove(input)
    }
    fun removeRight(input: Pair<Int,Int>){
        ListRight.remove(input)
    }

    fun getNextPointLeft(): Pair<Int,Int>? {

        if (!ListLeft.isEmpty()) {
            val RandomIndex = (0 until ListLeft.size).random()
            val result = ListLeft[RandomIndex]
            currentPair = result
            return result
        }
        else return null
    }

    fun getNextPointRight(): Pair<Int,Int>? {
        if (!ListRight.isEmpty()) {
            val RandomIndex = (0 until ListRight.size).random()
            val result = ListRight[RandomIndex]
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

    fun getPointListLeft(): MutableList<Pair<Int,Int>>{
        val returnList = mutableListOf<Pair<Int, Int>>()

        var selector = 0
        var i = 0
        for (x in xListLeft) {
            if (i < 1) {
                returnList.add(Pair(x, yListLeft.get(selector)))
                selector++
                i++
            }
        }
        return returnList

    }

    fun getValueListLeft(): MutableMap<Pair<Int,Int>, Int> {

        val returnMap = mutableMapOf<Pair<Int,Int>, Int>()
        var selector = 0
        var i = 0
        for (x in xListLeft){
            if (i < 1) {
                val tempPair = Pair(x, yListLeft.get(selector))
                returnMap[tempPair] = 20
                selector++
                i++
            }
        }
        return returnMap
    }

    fun getPointListRight(): MutableList<Pair<Int,Int>>{
        val returnList = mutableListOf<Pair<Int,Int>>()

        var selector = 0
        var i = 0
        for (x in xListRight){
            if (i < 1) {
                returnList.add(Pair(x, yListLeft.get(selector)))
                selector++
                i++
            }
        }
        return returnList
    }

    fun getValueListRight(): MutableMap<Pair<Int,Int>, Int> {

        val returnMap = mutableMapOf<Pair<Int,Int>, Int>()
        var selector = 0
        var i = 0
        for (x in xListRight){
            if (i < 1) {
                val tempPair = Pair(x, yListLeft.get(selector))
                returnMap[tempPair] = 20
                selector++
                i++
            }
        }
        return returnMap
    }
    fun ClosedRange<Int>.random() =
            Random().nextInt((endInclusive+1)-start) + start



    private fun BuildLists() {
        var x = 0
        var xMiddle = Resources.getSystem().displayMetrics.widthPixels / 2
        var yMiddle = Resources.getSystem().displayMetrics.heightPixels / 2
        var xMiddleLeft = Resources.getSystem().displayMetrics.widthPixels / 4
        var xMiddleRight = Resources.getSystem().displayMetrics.widthPixels / 4+ xMiddle

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


    //        objects
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