package 백준

import kotlin.math.max

// 집 간 거리 / c 를 활용해서 거리를 구하고 공유기 설치할 수 있는 최대 값을 구함

class InstallRouter(){
    val houses = mutableListOf<Long>()
    fun run(){
        val (n, c) = readln().split(" ").map{ it.toInt() }
        repeat(n){ houses.add(readln().toLong()) }
        houses.sort()

        var start = 1L
        var end = houses[houses.lastIndex] - houses[0]
        var maxDis = 0L

        while(start <= end){
            val targetDis = (start + end) / 2

            var installableCnt = 1
            var prev = houses[0]

            for(i in 1 until n){
                if (houses[i] - prev >= targetDis){
                    installableCnt++
                    prev = houses[i]
                }
            }

            if (installableCnt >= c){
                start = targetDis + 1
                maxDis = max(maxDis, targetDis)
            } else {
                end = targetDis - 1
            }
        }
        println(maxDis)
    }
}