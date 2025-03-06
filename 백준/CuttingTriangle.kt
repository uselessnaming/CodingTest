package 백준

import kotlin.math.abs

// 백준 1198번 삼각형으로 자르기

class CuttingTriangle {
    val dots = mutableListOf<List<Int>>()
    val combi = mutableListOf<List<List<Int>>>()
    var result = 0.toDouble()
    fun run(){
        val N = readln().toInt()
        repeat(N){ dots.add(readln().split(" ").map{it.toInt()}) }

        getAllCombi()
        for(c in combi){ getArea(c[0], c[1], c[2]) }

        println(result)
    }

    fun getArea(x: List<Int>, y: List<Int>, z: List<Int>){
        val area = abs(x[0]*(y[1] - z[1]) + y[0]*(z[1] - x[1]) + z[0]*(x[1] - y[1])) * 0.5
        if (area > result){ result = area }
    }

    fun getAllCombi(){
        for(x in 0 until dots.size){
            for(y in x+1 until dots.size){
                for(z in y+1 until dots.size){
                    combi.add(listOf(dots[x], dots[y], dots[z]))
                }
            }
        }
    }
}