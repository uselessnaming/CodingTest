package 프로그래머스

class ExpressionalBinaryTree {
    fun solution(numbers: LongArray): IntArray {
        val answer = ArrayList<Int>()

        for (number in numbers) {
            val bin = number.toString(2)
            var n = 2
            while (n - 1 < bin.length) n *= 2

            val res = "0".repeat(n - 1 - bin.length) + bin
            if (isPossible(res)) answer.add(1)
            else answer.add(0)
        }

        return answer.toIntArray()
    }

    private fun isPossible(bin: String): Boolean {
        if (bin.length == 1) return true

        val mid = bin.length / 2
        val left = bin.take(mid)
        val right = bin.takeLast(mid)

        if (bin[mid] == '0') {
            if (bin.any { it == '1' }) return false

            return true
        }

        return isPossible(left) && isPossible(right)
    }
}