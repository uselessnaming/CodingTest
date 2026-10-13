import 프로그래머스.*

fun main() {
    val info = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(2, 1),
    )
    val n = 1
    val m = 7

    val answer = PerfectCrimeSecond().solution(info, n, m)
    println(answer)
}