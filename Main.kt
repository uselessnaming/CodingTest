import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
//    val key = arrayOf(
//        intArrayOf(0, 0, 0),
//        intArrayOf(1, 0, 0),
//        intArrayOf(0, 1, 1),
//    )
//    val lock = arrayOf(
//        intArrayOf(1, 1, 1),
//        intArrayOf(1, 1, 0),
//        intArrayOf(1, 0, 1),
//    )
    val key = arrayOf(
        intArrayOf(1,0),
        intArrayOf(0,0)
    )

    val lock = arrayOf(
        intArrayOf(0,0),
        intArrayOf(1,1)
    )

    val answer = LockAndKey().solution(key, lock)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}