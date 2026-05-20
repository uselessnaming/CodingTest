import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val n = 9

    val path = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, 3),
        intArrayOf(0, 7),
        intArrayOf(8, 1),
        intArrayOf(3, 6),
        intArrayOf(1, 2),
        intArrayOf(4, 7),
        intArrayOf(7, 5),
    )

    val order = arrayOf(
        intArrayOf(4, 1),
        intArrayOf(8, 7),
        intArrayOf(6, 5),
    )

    val answer = CaveExploration().solution(n, path, order)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}