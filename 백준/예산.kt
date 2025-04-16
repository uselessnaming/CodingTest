package 백준

import kotlin.math.max

class Budget{
    lateinit var areas: List<Int>
    fun run(){
        val n = readln().toInt()
        areas = readln().split(" ").map{it.toInt()}
        val m = readln().toInt()

        var min = 1
        var max = areas.max()
        var ans = 0

        while(min <= max){
            val limit = (min + max) / 2

            val budget = areas.map{ if(it > limit) limit else it }.sum()
            println("budget : $budget")

            if (budget > m){
                max = limit - 1
            } else {
                ans = max(ans, limit)
                min = limit + 1
            }
            println("min $min / max $max")
        }
        if (areas.max() < ans){
            println(areas.max())
        } else {
            println(ans)
        }
    }
}
