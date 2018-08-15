package com.example.buddah.visiontesting

class VisionResults () {
    //stores points/results in hashmap
    var resultsMap = HashMap<Pair<Int,Int>, Boolean>()
    //used for saving points after they are removed from the built list in VisionView
    //allows us to have a full list of points.
    var pointList = mutableListOf<Pair<Int,Int>>()


    fun addPoint(input: Pair<Int,Int>){
        pointList.add(input)
        println(pointList)
    }

    fun addResult(pointInput: Pair<Int, Int>, resultInput: Boolean){
        resultsMap[pointInput] = resultInput
        println(resultsMap)
    }



}