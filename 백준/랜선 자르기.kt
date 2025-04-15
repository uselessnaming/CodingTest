package 백준

// 이분 탐색의 기초

class CuttingLAN {
    val nums = mutableListOf<Int>()
    fun run(){
        val (k, n) = readln().split(" ").map{it.toInt()}
        repeat(k){ nums.add(readln().toInt()) }

        var start = 0L
        var end = (Int.MAX_VALUE).toLong()

        while(start < end){
            var mid = (start+end)/2+1

            val cnt = getLANs(mid)

            if (cnt >= n){
                start = mid
            } else {
                end = mid-1
            }
            println("start: $start / end: $end")
        }
        println(start)
    }

    fun getLANs(length: Long): Int = nums.map{ (it.toLong() / length).toInt() }.sum()
}