package 프로그래머스

class DeliveryAndPickupSecond {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer = 0L
        var d = 0
        var p = 0

        for (i in n - 1 downTo 0) {
            d += deliveries[i]
            p += pickups[i]

            val need = maxOf(d, p)
            if (need > 0) {
                val trips = (need + cap - 1) / cap
                answer += trips.toLong() * (i + 1) * 2
                d -= trips * cap
                p -= trips * cap
            }
        }

        return answer
    }
}