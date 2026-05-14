import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val board = arrayOf(
        intArrayOf(1,0,0,3),
        intArrayOf(2,0,0,0),
        intArrayOf(0,0,0,2),
        intArrayOf(3,0,1,0)
    )
    val r = 1
    val c = 0

    val answer = MatchingCard().solution(board, r, c)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}