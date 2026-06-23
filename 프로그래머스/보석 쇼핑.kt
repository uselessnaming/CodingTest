package 프로그래머스

class GemShopping {
    private var GEM_SIZE = 0

    fun solution(gems: Array<String>): IntArray {
        val answer = intArrayOf(1, Int.MAX_VALUE)
        GEM_SIZE = gems.distinct().size
        val gemSet = mutableSetOf<String>()
        val gemCnt = gems.distinct().associateWith { 0 }.toMutableMap()

        var left = 0
        var right = 0
        var len = Int.MAX_VALUE

        while (true) {
            if (right == gems.size && gemSet.size != GEM_SIZE) break
            while (gemSet.size != GEM_SIZE) {
                if (right >= gems.size) break

                val rightGem = gems[right]
                gemCnt[rightGem] = gemCnt[rightGem]!! + 1
                gemSet.add(rightGem)
                right++
            }
            while (gemSet.size == GEM_SIZE) {
                if (right - left < len) {
                    len = right- left
                    answer[0] = left + 1
                    answer[1] = right
                }

                val leftGem = gems[left]
                gemCnt[leftGem] = gemCnt[leftGem]!! - 1

                if (gemCnt[leftGem]!! == 0) {
                    gemSet.remove(leftGem)
                }

                left++
            }
        }

        return answer
    }
}