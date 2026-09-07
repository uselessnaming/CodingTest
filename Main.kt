import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val points = arrayOf(
        intArrayOf(3, 2),
        intArrayOf(6, 4),
        intArrayOf(4, 7),
        intArrayOf(1, 4),
    )
    val routes = arrayOf(
        intArrayOf(4, 2),
        intArrayOf(1, 3),
        intArrayOf(4, 2),
        intArrayOf(4, 3),
    )
    val key = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(0, 0)
    )

    val lock = arrayOf(
        intArrayOf(0, 0),
        intArrayOf(1, 1)
    )

    val answer = FindCollisionRiskSecond().solution(points, routes)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}