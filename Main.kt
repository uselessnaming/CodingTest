import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val board = arrayOf(
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(0, 1, 0, 1, 1),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 0)
    )
    val answer = MoveBlock().solution(board)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}