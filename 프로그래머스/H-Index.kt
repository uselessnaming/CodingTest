package 프로그래머스

class HIndex {
    fun solution(citations: IntArray): Int {
        val sorted = citations.sorted()
        val n = sorted.size

        for (i in sorted.indices) {
            val h = n - i

            if (sorted[i] >= h) {
                return h
            }
        }

        return 0
    }
}