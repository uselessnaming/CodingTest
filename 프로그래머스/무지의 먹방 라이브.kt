package 프로그래머스

class StreamingToEat {
    fun solution(food_times: IntArray, k: Long): Int {
        val size = food_times.size
        val sorted = food_times.sorted()
        var eaten = 0L
        var time = 0L

        for(i in sorted.indices) {
            if (time + (size - i) * (sorted[i] - eaten) > k) {
                val moves = (k - time) % (size - i)
                var tmp = 0L
                for( j in food_times.indices) {
                    if (food_times[j] - eaten <= 0) continue
                    if (tmp++ >= moves) {
                        return j + 1
                    }
                }
            }
            time += (size - i) * (sorted[i] - eaten)
            eaten = sorted[i].toLong()
        }

        return -1
    }
}