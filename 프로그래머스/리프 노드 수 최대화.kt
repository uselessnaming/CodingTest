package 프로그래머스

class MaxOfLeafNode {
    fun solution(dist_limit: Int, split_limit: Int): Int {
        var answer = 1L

        var pow3 = 1L

        for (y in 0..60) {
            if (pow3 > split_limit) break
            if (y > dist_limit) break

            var pow2 = 1L
            for (x in 0..60) {
                if (x + y > dist_limit) break
                if (pow2 * pow3 > split_limit) break

                val leaf = 1 + x + 2 * y
                answer = maxOf(answer, leaf.toLong())

                pow2 *= 2
            }

            pow3 *= 3
        }

        return answer.toInt()
    }
}