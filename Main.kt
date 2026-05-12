import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val board = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,4,0,0,0),
        intArrayOf(0,0,0,0,0,4,4,0,0,0),
        intArrayOf(0,0,0,0,3,0,4,0,0,0),
        intArrayOf(0,0,0,2,3,0,0,0,5,5),
        intArrayOf(1,2,2,2,3,3,0,0,0,5),
        intArrayOf(1,1,1,0,0,0,0,0,0,5)
    )
    val answer = BlockGame().solution(board)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}