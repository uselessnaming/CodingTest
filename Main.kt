import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
//    val board = arrayOf(
//        intArrayOf(0,0,0),
//        intArrayOf(0,0,0),
//        intArrayOf(0,0,0),
//    )
    val board = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,1),
        intArrayOf(0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,1,0,0),
        intArrayOf(0,0,0,0,1,0,0,0),
        intArrayOf(0,0,0,1,0,0,0,1),
        intArrayOf(0,0,1,0,0,0,1,0),
        intArrayOf(0,1,0,0,0,1,0,0),
        intArrayOf(1,0,0,0,0,0,0,0)
    )

    val answer = BuildRoad().solution(board)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}