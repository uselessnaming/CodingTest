package 프로그래머스

class SteppingStone {
    private lateinit var diffArr: IntArray

    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        var start = 1
        var end = distance
        var answer = 0
        rocks.sort()

        diffArr = IntArray(rocks.size + 1)
        diffArr[0] = rocks[0]
        diffArr[rocks.size] = distance - rocks[rocks.size - 1]
        for (i in 0 until rocks.size - 1) {
            diffArr[i + 1] = rocks[i + 1] - rocks[i]
        }

        while(start <= end) {
            println("start : $start / end : $end")
            val mid = (start + end) / 2
            var cur = 0
            var removed = 0

            for (i in diffArr.indices) {
                cur += diffArr[i]
                if (cur < mid) removed++
                else cur = 0
            }
            println("removed : $removed")

            if (removed > n) {
                end = mid - 1
            } else {
                answer = mid
                start = mid + 1
            }

            println("answer : $answer")
        }

        return answer
    }
}