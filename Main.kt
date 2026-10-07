import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    val n = 12
    val weak = intArrayOf(1, 5, 6, 10)
    val dist = intArrayOf(1, 2, 3, 4)

    val answer = CheckWallSecond().solution(n, weak, dist)
    println(answer)
}