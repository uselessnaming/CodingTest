package 프로그래머스

class ExpressionalBinaryTree {
    fun solution(numbers: LongArray): IntArray {
        val answer = ArrayList<Int>()

        for (number in numbers) {
            val bin = number.toString(2)
            var n = 2
            while (n - 1 < bin.length) n *= 2

            val res = ZERO.toString().repeat(n - 1 - bin.length) + bin
            if (isPossible(res, 0, res.length)) answer.add(1)
            else answer.add(0)
        }

        return answer.toIntArray()
    }

    private fun isPossible(bin: String, startIdx: Int, endIdx: Int): Boolean {
        if (endIdx - startIdx == 1) return true

        val mid = (startIdx + endIdx) / 2

        if (bin[mid] == ZERO) {
            for (i in startIdx until endIdx) if (bin[i] == ONE) return false

            return true
        }

        return isPossible(bin, startIdx, mid) && isPossible(bin, mid + 1, endIdx)
    }

    companion object {
        const val ZERO = '0'
        const val ONE = '1'
    }
}