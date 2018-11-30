package com.example.buddah.visiontesting

import java.lang.Thread.sleep

class TestResults (input: TestContainer) {
    //need to move the getting points/updating results to this class.
    val TestContainer = input
    var resultsMap = HashMap<Pair<Int,Int>, Boolean>()
    var valuesMap = HashMap<Pair<Int,Int>, Int>()
    var pointList = mutableListOf<Pair<Int,Int>>()
    var liarPoints = 0
    val ListLeft : MutableList<Pair<Int,Int>> = input.getPointListLeft()
    var LeftValueMap : MutableMap<Pair<Int,Int>, Int> = input.getValueListLeft()
    val ListRight : MutableList<Pair<Int,Int>> = input.getPointListRight()
    var RightValueMap : MutableMap<Pair<Int,Int>, Int> = input.getValueListRight()
    var LeftCheck = mutableMapOf<Pair<Int,Int>, Int>()
    var RightCheck = mutableMapOf<Pair<Int,Int>, Int>()
    var currentPair : Pair<Int,Int> = Pair(0,0)

    init {
        LeftValueMap = buildValueMap(0)
        RightValueMap = buildValueMap(1)
        LeftCheck = buildValueMap(2)
        RightCheck = buildValueMap(3)
    }

    //if person does sees the point, we decrease until they can't see it anymore, then increase
    //  until they see it again -> case 1.
    //If person does not see it, we increase brightness until they can see it. Then we decrease until
    //  they can't see it anymore -> case 2.
    //Person does not see it at all we start, and make it until max brightness.



    fun getNextPointLeft(): Pair<Int,Int>? {//should return Pair<Pair<Int,Int>, Int> where second
                                            //int is brightness value?
        var temp = TestContainer.getNextPointLeft()

        return temp

    }

    fun getNextPointRight(): Pair<Int,Int>? {

        return TestContainer.getNextPointRight()
    }

    fun addPoint(input: Pair<Int,Int>){
        pointList.add(input)
        println(pointList)
    }

    fun addResult(pointInput: Pair<Int, Int>, resultInput: Boolean){
        resultsMap[pointInput] = resultInput
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
            return LeftValueMap[input]!!.toFloat()
        else
            return RightValueMap[input]!!.toFloat()
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
    // **** note ****
    //get liarPoints exists as part of the data class.
    //get pointList exists as part of the data class.
    //get resultsMap exists as part of the data class.



}