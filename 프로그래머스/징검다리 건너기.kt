package 프로그래머스

class CrossingBridge {
    fun solution(stones: IntArray, k: Int): Int {
        var top = 200_000_000
        var bottom = 0
        var middle = 0

        while(bottom <= top) {
            middle = (top + bottom) / 2

            if (isAvailable(middle, stones, k)) {
                bottom = middle + 1
            } else {
                top = middle - 1
            }
        }

        return bottom
    }

    private fun isAvailable(value: Int, stones: IntArray, k: Int): Boolean {
        var step = 0

        stones.forEach { stone ->
            val endStone = stone - value
            if (endStone <= 0) {
                step++

                if (step >= k) return false
            } else {
                step = 0
            }
        }

        return true
    }
}