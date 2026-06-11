import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val n = 3

    val answer = TopOfHanoi().solution(n)
    println(answer.map { it.toList() })

//    // 백준
//    StartAndLink().apply { run() }
}