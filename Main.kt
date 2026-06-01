import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val clockHands = arrayOf(
        intArrayOf(0,3,3,0),
        intArrayOf(3,2,2,3),
        intArrayOf(0,3,2,0),
        intArrayOf(0,3,3,3)
    )
    val answer = ArchaeologicalDiscovery().solution(clockHands)
    print(answer)

//    // 백준
//    StartAndLink().apply { run() }
}