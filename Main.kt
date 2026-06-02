import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val rc = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8),
        intArrayOf(9, 10, 11, 12)
    )
    val operations = arrayOf("ShiftRow", "Rotate", "ShiftRow", "Rotate")
    val answer = MatrixAndArray().solution(rc, operations)
    print(answer.toList().map { it.toList() })

//    // 백준
//    StartAndLink().apply { run() }
}