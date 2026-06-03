import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val sales = intArrayOf(14, 17, 15, 18, 19, 14, 13, 16, 28, 17)
    val links = arrayOf(
        intArrayOf(10, 8),
        intArrayOf(1, 9),
        intArrayOf(9, 7),
        intArrayOf(5, 4),
        intArrayOf(1, 5),
        intArrayOf(5, 10),
        intArrayOf(10, 6),
        intArrayOf(1, 3),
        intArrayOf(10, 2),
    )
    val answer = MinimizeSales().solution(sales, links)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}