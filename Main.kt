import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    val players = intArrayOf(0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5)
    val m = 3
    val k = 5

    val answer = CountCreateServer().solution(players, m, k)
    println(answer)
}