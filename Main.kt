import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val n = 3
    val count = 1

    val arr1 = arrayOf(
        intArrayOf(1, 4),
        intArrayOf(3, 2),
        intArrayOf(4, 1),
    )
    val arr2 = arrayOf(
        intArrayOf(3, 3),
        intArrayOf(3, 3),
    )

    val answer = MatrixMultiplication().solution(arr1, arr2)
    println(answer.map { it.toList() })

//    // 백준
//    StartAndLink().apply { run() }
}