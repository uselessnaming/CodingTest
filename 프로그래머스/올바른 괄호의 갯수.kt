package 프로그래머스

class NumberOfBrackets {
    fun solution(n: Int): Int {
        val dp1 = IntArray(n + 1)
        val dp2 = IntArray(n + 2)
        dp1[0] = 1

        (1..n).forEach {
            dp2[it] = dp1[it - 1]
            dp1[it] = (0..it).sumOf { k -> dp1[it - k] * dp2[k]}
        }
        return dp1.last()
    }
}