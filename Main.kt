import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val stones = intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1)
    val k = 3
//    val stones = intArrayOf(2,2,2)
//    val k = 3

    val answer = CrossingBridge().solution(stones, k)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}