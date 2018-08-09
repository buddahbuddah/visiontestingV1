package com.example.buddah.visiontesting

import android.content.res.Resources

//calculates testing points dynamically based on phone resource.



class LocationResults {

    val xListLeft = mutableListOf<Int>()
    val xListRight = mutableListOf<Int>()
    val yListLeft = mutableListOf<Int>()


    init {
        var x = 0
        var xMiddle = Resources.getSystem().displayMetrics.widthPixels/2
        var yMiddle = Resources.getSystem().displayMetrics.heightPixels/2
        var xMiddleLeft = Resources.getSystem().displayMetrics.widthPixels/4
        var xMiddleRight = Resources.getSystem().displayMetrics.widthPixels/4 + xMiddle

        //first row
        xListLeft.add(xMiddleLeft/2)
        xListLeft.add(xMiddleLeft - xMiddleLeft/12)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12)
        xListLeft.add(xMiddleLeft/2 + xMiddleLeft)
        //second row
        xListLeft.add(xMiddleLeft/5)
        xListLeft.add(xMiddle - xMiddleLeft/5)//using relative middle
        //third row
        xListLeft.add(xMiddleLeft - (4* xMiddleLeft/6)) //check this thing
        xListLeft.add(xMiddleLeft - (2* xMiddleLeft/6)) //check this thing
        xListLeft.add(xMiddleLeft + (2* xMiddleLeft/6)) //check this thing
        xListLeft.add(xMiddleLeft + (4* xMiddleLeft/6)) //check this thing
        //fourth row
        xListLeft.add(xMiddleLeft - (xMiddleLeft/12))
        xListLeft.add(xMiddleLeft + (xMiddleLeft/12))
        //fifth row
        xListLeft.add(xMiddleLeft - xMiddleLeft/12 * 11)
        xListLeft.add(xMiddleLeft - xMiddleLeft/2)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12 * 11)
        xListLeft.add(xMiddleLeft + xMiddleLeft/2)
        //sixth row
        xListLeft.add(xMiddleLeft - xMiddleLeft/3 * 2)
        xListLeft.add(xMiddleLeft - xMiddleLeft/3)
        xListLeft.add(xMiddleLeft + xMiddleLeft/3)
        xListLeft.add(xMiddleLeft - xMiddleLeft/3 * 2)
        //seventh row
        xListLeft.add(xMiddleLeft - xMiddleLeft/12)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12)
        //eight row
        xListLeft.add(xMiddleLeft - xMiddleLeft/12 *11)
        xListLeft.add(xMiddleLeft - xMiddleLeft/12 *8)
        xListLeft.add(xMiddleLeft - xMiddleLeft/12 *3)
        xListLeft.add(xMiddleLeft - xMiddleLeft/12)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12 *3)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12 *8)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12 *11)
        //this is the middle. Now we just repeat the whole thing backward.
        // ninth row
        xListLeft.add(xMiddleLeft - xMiddleLeft/12 *11)
        xListLeft.add(xMiddleLeft - xMiddleLeft/12 *8)
        xListLeft.add(xMiddleLeft - xMiddleLeft/12 *3)
        xListLeft.add(xMiddleLeft - xMiddleLeft/12)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12 *3)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12 *8)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12 *11)
        //tenth row
        xListLeft.add(xMiddleLeft - xMiddleLeft/12)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12)
        //eleventh row
        xListLeft.add(xMiddleLeft - xMiddleLeft/3 * 2)
        xListLeft.add(xMiddleLeft - xMiddleLeft/3)
        xListLeft.add(xMiddleLeft + xMiddleLeft/3)
        xListLeft.add(xMiddleLeft - xMiddleLeft/3 * 2)
        //twelfth row
        xListLeft.add(xMiddleLeft - xMiddleLeft/12 * 11)
        xListLeft.add(xMiddleLeft - xMiddleLeft/2)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12 * 11)
        xListLeft.add(xMiddleLeft + xMiddleLeft/2)
        //thirteeth row
        xListLeft.add(xMiddleLeft - (xMiddleLeft/12))
        xListLeft.add(xMiddleLeft + (xMiddleLeft/12))
        //fourteeth row
        xListLeft.add(xMiddleLeft - (4* xMiddleLeft/6))
        xListLeft.add(xMiddleLeft - (2* xMiddleLeft/6))
        xListLeft.add(xMiddleLeft + (2* xMiddleLeft/6))
        xListLeft.add(xMiddleLeft + (4* xMiddleLeft/6))
        //fifteenth row
        xListLeft.add(xMiddleLeft/5)
        xListLeft.add(xMiddle - xMiddleLeft/5)//using relative middle
        //sixteenth row
        xListLeft.add(xMiddleLeft/2)
        xListLeft.add(xMiddleLeft - xMiddleLeft/12)
        xListLeft.add(xMiddleLeft + xMiddleLeft/12)
        xListLeft.add(xMiddleLeft/2 + xMiddleLeft)

        //first row y
        var counter = 0
        var numberInRow = 4
        while (counter < numberInRow){
            yListLeft.add(yMiddle + yMiddle/12 *11)
            counter ++
        }
        //second row y
        counter = 0
        numberInRow = 2
        while (counter < numberInRow){
            yListLeft.add(yMiddle + yMiddle/6 * 5)
            counter ++
        }
        //third row y
        counter = 0
        numberInRow = 4
        while (counter < numberInRow){
            yListLeft.add(yMiddle + yMiddle/3 * 2)
            counter ++
        }
        //fourth row y
        counter = 0
        numberInRow = 2
        while (counter < numberInRow){
            yListLeft.add(yMiddle + yMiddle/12 * 7)
            counter ++
        }
        //fifth row y
        counter = 0
        numberInRow = 4
        while (counter < numberInRow){
            yListLeft.add(yMiddle + yMiddle/2)
            counter ++
        }
        //sixth row y
        counter = 0
        numberInRow = 4
        while (counter < numberInRow){
            yListLeft.add(yMiddle + yMiddle/3)
            counter ++
        }
        //seventh row y
        counter = 0
        numberInRow = 2
        while (counter < numberInRow){
            yListLeft.add(yMiddle + yMiddle/12 * 3)
            counter ++
        }
        //eighth row y
        counter = 0
        numberInRow = 8
        while (counter < numberInRow){
            yListLeft.add(yMiddle + yMiddle/12)
            counter ++
        }
        //ninth row y (mirrors eighth)
        counter = 0
        numberInRow = 8
        while (counter < numberInRow){
            yListLeft.add(yMiddle - yMiddle/12)
            counter ++
        }
        //tenth row y (mirrors seventh)
        counter = 0
        numberInRow = 2
        while (counter < numberInRow){
            yListLeft.add(yMiddle - yMiddle/12 * 3)
            counter ++
        }
        //eleventh row (mirrors sixth)
        counter = 0
        numberInRow = 4
        while (counter < numberInRow){
            yListLeft.add(yMiddle - yMiddle/3)
            counter ++
        }
        //twelfth row (mirrors fifth)
        counter = 0
        numberInRow = 4
        while (counter < numberInRow){
            yListLeft.add(yMiddle - yMiddle/2)
            counter ++
        }
        //thirteenth row (mirrors fourth)
        counter = 0
        numberInRow = 2
        while (counter < numberInRow){
            yListLeft.add(yMiddle - yMiddle/12 * 7)
            counter ++
        }
        //fourteenth row (mirrors third)
        counter = 0
        numberInRow = 4
        while (counter < numberInRow){
            yListLeft.add(yMiddle - yMiddle/3 * 2)
            counter ++
        }
        //fifteenth row (mirrors second)
        counter = 0
        numberInRow = 2
        while (counter < numberInRow){
            yListLeft.add(yMiddle - yMiddle/6 * 5)
            counter ++
        }
        //sixteenth row (mirrors first)
        counter = 0
        numberInRow = 4
        while (counter < numberInRow){
            yListLeft.add(yMiddle - yMiddle/12 *11)
            counter ++
        }

        //xListRight mirrors the left with the center variable changed.
        for (x in xListLeft){
            xListRight.add(x + xMiddle)
        }

    }



    fun printList(){
        println(xListLeft)
        println(yListLeft)
    }

    fun getPointList(): List<Pair<Int,Int>>?{
        val returnList = mutableListOf<Pair<Int, Int>>()

        var selector = 0
        for (x in xListLeft){
            returnList.add(Pair(x, yListLeft.get(selector)))
            selector ++
        }
        selector = 0
        for (x in xListRight){
            returnList.add(Pair(x, yListLeft.get(selector)))
            selector ++
        }

        return returnList

    }

}