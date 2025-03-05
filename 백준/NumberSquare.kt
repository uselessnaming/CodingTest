package 백준

import kotlin.math.min

// 백준 숫자 정사각형

class NumberSquare {

    val map = mutableListOf<String>()
    var answer = 1

    fun run(){
        val (n, m) = readln().split(" ").map{it.toInt()}

        repeat(n){ map.add(readln()) }
        getSquare(n,m)

        println(answer)
    }

    fun getSquare(_n: Int, _m: Int){
        val n = min(_n, _m)
        outer@for(i in n downTo 2){
            for(x in 0.._n-i){
                for(y in 0.._m-i){
                    if(map[x][y] == map[x][y+i-1]
                        && map[x+i-1][y] == map[x+i-1][y+i-1]
                        && map[x][y+i-1] == map[x+i-1][y]){
                        answer = i*i
                        break@outer
                    }
                }
            }
        }
    }
}