import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val n = 7
    val infection = 6
//    val edges = arrayOf(
//        intArrayOf(1, 2, 1),
//        intArrayOf(1, 3, 1),
//        intArrayOf(1, 4, 3),
//        intArrayOf(1, 5, 2),
//        intArrayOf(5, 6, 1),
//        intArrayOf(5, 7, 1),
//        intArrayOf(2, 8, 3),
//        intArrayOf(2, 9, 2),
//        intArrayOf(9, 10, 1),
//    )
    val edges = arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(1,4,3),
        intArrayOf(4,5,1),
        intArrayOf(5,6,1),
        intArrayOf(3,6,2),
        intArrayOf(3,7,2),
    )
    val k = 3

    val answer = VirusPipe().solution(n, infection, edges, k)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}