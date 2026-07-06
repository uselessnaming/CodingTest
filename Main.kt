import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
//    val scores = arrayOf(
//        intArrayOf(2, 2),
//        intArrayOf(1, 4),
//        intArrayOf(3, 2),
//        intArrayOf(3, 2),
//        intArrayOf(2, 1),
//    )
    val scores = arrayOf(
        intArrayOf(2, 2),
        intArrayOf(3, 1),
        intArrayOf(1, 3)
    )
    val answer = HREvaluationDepartment().solution(scores)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}