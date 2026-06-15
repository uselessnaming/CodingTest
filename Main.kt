import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val arr = intArrayOf(
        49134,
        86806,
        94548,
        88849,
        95022,
        28334,
        16637,
        79487,
        23773,
        7314,
        47370,
        50269,
        36573,
        9415,
        44674,
        28096
    )
    val l = 61242L
    val r = 88535L

    val answer = SquareArray().solution(arr, l, r)
    println(answer.toList())

//    // 백준
//    StartAndLink().apply { run() }
}