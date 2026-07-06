import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val n = 5
    val results = arrayOf(
        intArrayOf(4, 3),
        intArrayOf(4, 2),
        intArrayOf(3, 2),
        intArrayOf(1, 2),
        intArrayOf(2, 5),
    )
    val answer = Order().solution(n, results)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}