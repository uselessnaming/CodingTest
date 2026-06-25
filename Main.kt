import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val n = 5
    val roads = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 4),
        intArrayOf(2, 4),
        intArrayOf(2, 5),
        intArrayOf(4, 5)
    )
    val sources = intArrayOf(1, 3, 5)
    val destination = 5

    val answer = ReturnArmy().solution(n, roads, sources, destination)
    println(answer.toList())

//    // 백준
//    StartAndLink().apply { run() }
}