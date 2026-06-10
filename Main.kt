import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val n = 7
    val wires = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 7),
        intArrayOf(3, 7),
        intArrayOf(3, 4),
        intArrayOf(4, 5),
        intArrayOf(6, 7),
    )

    val answer = DivideElectricNet().solution(n, wires)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}