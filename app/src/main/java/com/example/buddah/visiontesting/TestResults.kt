package com.example.buddah.visiontesting

import java.lang.Thread.sleep

class TestResults (input: TestContainer) {
    //need to move the getting points/updating results to this class.
    val TestContainer = input
    var resultsMap = HashMap<Pair<Int,Int>, Boolean>()
    var ValuesMap = HashMap<Pair<Int,Int>, Int>()
    var pointList = mutableListOf<Pair<Int,Int>>()
    var liarPoints = 0
    val ListLeft : MutableList<Pair<Int,Int>> = input.getPointListLeft()
    var LeftValue : MutableMap<Pair<Int,Int>, Int> = input.getValueListLeft()
    val ListRight : MutableList<Pair<Int,Int>> = input.getPointListRight()
    var RightValue : MutableMap<Pair<Int,Int>, Int> = input.getValueListRight()
    var LeftMode = mutableMapOf<Pair<Int,Int>, Int>()
    var RightMode = mutableMapOf<Pair<Int,Int>, Int>()
    var currentPair : Pair<Int,Int> = Pair(0,0)

    init {
        LeftValue = buildValueMap(0)
        RightValue = buildValueMap(1)
        LeftMode = buildValueMap(2)
        RightMode = buildValueMap(3)
    }

    fun getNextPointLeft(): Pair<Int,Int>? {

        var temp = TestContainer.getNextPointLeft()
        currentPair = temp!!

        while (LeftMode[temp] == 4){
            ValuesMap[temp!!] = LeftValue[temp]!!
            TestContainer.removeLeft(temp!!)
            temp = TestContainer.getNextPointLeft()
            currentPair = temp!!

        }
        return temp
    }

    fun getNextPointRight(): Pair<Int,Int>? {

        var temp = TestContainer.getNextPointRight()
        currentPair = temp!!

        while (RightMode[temp] == 4){
            ValuesMap[temp!!] = LeftValue[temp]!!
            TestContainer.removeRight(temp!!)
            temp = TestContainer.getNextPointRight()
            currentPair = temp!!

        }
        return temp
    }

    fun addPoint(input: Pair<Int,Int>){
        pointList.add(input)
        println(pointList)
    }

    fun addResult(input: Pair<Int, Int>, result: Boolean){

        if (input in ListLeft){
            println("Input in ListLeft")
            println(LeftMode[input])

            if (LeftMode[input] == 0){
                if (result == true){
                    println("changing to 1")
                    LeftMode[input] = 1
                    //decrease brightness
                    var temp = ChangeBrightness(LeftValue[input]!!, true)
                    LeftValue[input] = temp
                }else{
                    println("changing to 2")
                    LeftMode[input] = 2
                    //increase brightness
                    var temp = ChangeBrightness(LeftValue[input]!!, false)
                    LeftValue[input] = temp
                }
            }
            if (LeftMode[input] == 1){
                if (result == false){
                    println("changing to four")
                    LeftMode[input] = 4
                    //increase brightness
                    //var temp = ChangeBrightness(LeftValue[input]!!, false)
                    //LeftValue[input] = temp
                } else {
                    //decrease brightness
                    var temp = ChangeBrightness(LeftValue[input]!!, true)
                    LeftValue[input] = temp
                }
            }
            if (LeftMode[input] == 2){
                if (result == true){
                    //ready for result storage.
                    println("changing to 3")
                    LeftMode[input] = 3
                }else{
                    //increase brightness
                    var temp = ChangeBrightness(LeftValue[input]!!, false)
                    LeftValue[input] = temp
                }
            }
            if (LeftMode[input] == 3){
                if (result == false){
                    //ready for result storage.
                    print("changing to 4")
                    LeftMode[input] = 4
                }else{
                    //decrease brightness.
                    var temp = ChangeBrightness(LeftValue[input]!!, true)
                    LeftValue[input] = temp
                }

            }
            if (LeftMode[input] == 4){
                //add result to result value map. final value is stored.
                ValuesMap[input] = LeftValue[input]!!
                println("Values Map Stored: ")
                println(ValuesMap[input])
                println(input)
            }

        }
        if (input in ListRight){
            println("Input in ListLeft")
            println(RightMode[input])

            if (RightMode[input] == 0){
                if (result == true){
                    println("changing to 1")
                    RightMode[input] = 1
                    //decrease brightness
                    var temp = ChangeBrightness(RightValue[input]!!, true)
                    RightValue[input] = temp
                }else{
                    println("changing to 2")
                    RightMode[input] = 2
                    //increase brightness
                    var temp = ChangeBrightness(RightValue[input]!!, false)
                    RightValue[input] = temp
                }
            }
            if (RightMode[input] == 1){
                if (result == false){
                    println("changing to four")
                    RightMode[input] = 4
                    //increase brightness
                    //var temp = ChangeBrightness(LeftValue[input]!!, false)
                    //LeftValue[input] = temp
                } else {
                    //decrease brightness
                    var temp = ChangeBrightness(RightValue[input]!!, true)
                    RightValue[input] = temp
                }
            }
            if (RightMode[input] == 2){
                if (result == true){
                    //ready for result storage.
                    println("changing to 3")
                    RightMode[input] = 3
                }else{
                    //increase brightness
                    var temp = ChangeBrightness(RightValue[input]!!, false)
                    RightValue[input] = temp
                }
            }
            if (RightMode[input] == 3){
                if (result == false){
                    //ready for result storage.
                    println("changing to 4")
                    RightMode[input] = 4
                }else{
                    //decrease brightness.
                    var temp = ChangeBrightness(RightValue[input]!!, true)
                    RightValue[input] = temp
                }

            }
            if (RightMode[input] == 4){
                //add result to result value map. final value is stored.
                ValuesMap[input] = RightValue[input]!!
                println("Values Map Stored: ")
                println(ValuesMap[input])
                println(input)
            }

        }

        resultsMap[input] = result;
        println(resultsMap)
    }

    fun getResults(): HashMap<Pair<Int,Int>,Boolean>{
        return resultsMap
    }

    fun incrementControl(){
        liarPoints ++
    }

    fun getControl(): Int {
        return liarPoints
    }

    fun getBrightness(input: Pair<Int,Int>) : Float?{

        if (input in ListLeft)
            return LeftValue[input]!!.toFloat()
        else
            return RightValue[input]!!.toFloat()
    }

    fun buildValueMap (input : Int) : MutableMap<Pair<Int,Int>, Int> {

        //builds helper lists based on input. See init for values meanings

        var temp : MutableList<Pair<Int,Int>>
        val result = mutableMapOf<Pair<Int,Int>,Int>()
        if (input == 0) {
            temp = ListLeft
            for (pair in temp) {
                result[pair] = -8
            }
        }
        else if (input == 1) {
            temp = ListRight
            for (pair in temp) {
                result[pair] = -8
            }
        }
        else if (input == 2) {
            temp = ListLeft
            for (pair in temp) {
                result[pair] = 0
            }
        }
        else if (input == 3) {
            temp = ListRight
            for (pair in temp) {
                result[pair] = 0
            }
        }
        println(result)
        return result
    }


    fun ChangeBrightness(brightness: Int, decreasing: Boolean): Int{

        var result = 0;
        //range from lowest brightness to highest. -25, -24, -23, -22, -21 etc.
        if (decreasing){
            result = brightness -4;
        }
        if (!decreasing){
            result = brightness +2
        }
        if (result < -25)
            result = -25
        if (result > 0)
            result = 0

        return result


    }

}