import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val land = arrayOf(
        intArrayOf(1,4,8,10),
        intArrayOf(5,5,5,5),
        intArrayOf(10,10,10,10),
        intArrayOf(10,10,10,20),
    )
    val height = 3

    val answer = MoveArea().solution(land, height)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}