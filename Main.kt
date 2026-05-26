import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val n = 4
    val edges = arrayOf(
        intArrayOf(1,2),
        intArrayOf(2,3),
        intArrayOf(3,4)
    )
//    val edges = arrayOf(
//        intArrayOf(1,5),
//        intArrayOf(2,5),
//        intArrayOf(3,5),
//        intArrayOf(4,5)
//    )

    val answer = TreeTrio().solution(n, edges)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}