import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val n = 4
    val costs = arrayOf(
        intArrayOf(0,1,1),
        intArrayOf(0,2,2),
        intArrayOf(1,2,5),
        intArrayOf(1,3,1),
        intArrayOf(2,3,8),
    )

    val answer = ConnectIsland().solution(n, costs)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}