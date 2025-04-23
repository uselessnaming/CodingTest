package 백준

import kotlin.math.min

// 핵심
// 마지막 아이가 타게 되는 놀이기구의 번호를 출력 -> x분에 몇 번째부터 몇 번째 아이가 놀이기구를 타는가?

class AmusementPark{
    lateinit var amusements: List<Int>
    var target = 0L
    fun run(){
        val (n, m) = readln().split(" ").map{ it.toInt() }
        target = n.toLong()
        amusements = readln().split(" ").map{it.toInt()}

        if (n <= m){
            println(n)
            return
        }

        var left = 0L
        var right = 2000000000L * 30L
        var time = right

        while(left <= right){
            val mid = (left + right) / 2

            val children = getN(mid)
            if (children >= n){
                time = min(mid, time)
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        println(getAnswer(time))
    }

    fun getN(mid : Long): Long = amusements.map{
        mid / it
    }.sum() + amusements.size.toLong()

    fun getAnswer(time: Long): Long{
        var cur = amusements.size.toLong()
        for(i in 0 until amusements.size){
            cur += ((time - 1) / amusements[i])
        }
        for(i in 0 until amusements.size){
            if (time % amusements[i].toLong() == 0L){
                cur++
            }
            if (cur == target) return (i+1).toLong()
        }
        return 0
    }
}