import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val n = 3
    val start = 1
    val end = 3

    val roads = arrayOf(
        intArrayOf(1, 2, 2),
        intArrayOf(3, 2, 3)
    )
    val traps = intArrayOf(2)

    val answer = EscapeMaze().solution(n, start, end, roads, traps)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}