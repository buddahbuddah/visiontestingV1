package com.example.buddah.visiontesting

class TestResults () {

    var resultsMap = HashMap<Pair<Int,Int>, Boolean>()
    var pointList = mutableListOf<Pair<Int,Int>>()
    var liarPoints = 0


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

    // **** note ****
    //get liarPoints exists as part of the data class.
    //get pointList exists as part of the data class.
    //get resultsMap exists as part of the data class.



}