import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val board = arrayOf(
        intArrayOf(1,1,1),
        intArrayOf(1,0,1),
        intArrayOf(1,1,1),
    )
    val aloc = intArrayOf(1,0)
    val bloc = intArrayOf(1,2)

    val answer = DisappearingScaffold().solution(board, aloc, bloc)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}