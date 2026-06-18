import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val cost = arrayOf(
        intArrayOf(160, 140, 120, 110, 60),
        intArrayOf(290, 270, 260, 120, 10),
        intArrayOf(160, 130, 120, 60, 20),
        intArrayOf(160, 120, 80, 70, 20),
        intArrayOf(110, 70, 60, 30, 20)
    )
    val hint = arrayOf(
        intArrayOf(40, 2, 3),
        intArrayOf(40, 5, 3),
        intArrayOf(20, 5, 4),
        intArrayOf(50, 5, 5)
    )

    val answer = HintStage().solution(cost, hint)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}