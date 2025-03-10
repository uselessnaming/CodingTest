package 백준

import kotlin.math.abs

class RepresentativeN {
    lateinit var numbers : List<Int>

    fun run(){
        val n = readln().toInt()
        numbers = readln().split(" ").map{it.toInt()}.sorted()

        var result = Int.MAX_VALUE
        var befNum = numbers[0]
        var answer = 0

        for(target in numbers){
            var cur = 0
            for(number in numbers){
                cur += abs(target - number)
            }
            if (cur < result) {
                result = cur
                answer = target
            } 
        }

        println(answer)
    }
}