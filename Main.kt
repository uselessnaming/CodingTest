import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val rocks = intArrayOf(2, 14, 11, 21, 17)
    val n = 2
    val distance = 25
    val answer = SteppingStone().solution(distance, rocks, n)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}