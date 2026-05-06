import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val n = 2
    val m = 2
    val x = 0
    val y = 0
    val queries = arrayOf(
        intArrayOf(2,1),
        intArrayOf(0,1),
        intArrayOf(1,1),
        intArrayOf(0,1),
        intArrayOf(2,1)
    )
    val answer = MoveBallSimulation().solution(n, m, x, y, queries)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}