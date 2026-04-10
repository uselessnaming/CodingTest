package 프로그래머스

// 전형적인 DP 형태의 문제
// 다만 해당 문제와 같이 좌 * 우의 형태로 나타나며 분할로써 해결하는 문제는 카탈란 수를 적용할 수 있음

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