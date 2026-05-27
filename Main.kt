import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val a = 10
    val b = 10
    val g = intArrayOf(70, 70, 0)
    val s = intArrayOf(0, 0, 500)
    val w = intArrayOf(100, 100, 2)
    val t = intArrayOf(4, 8, 1)

    val answer = CarryGoldAndSilver().solution(a, b, g, s, w, t)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}