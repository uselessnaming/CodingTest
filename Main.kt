import 백준.NumberBaseball
import 프로그래머스.CountDown
import 프로그래머스.MakeAllZero

fun main() {
    //프로그래머스
    val solution = MakeAllZero()
    val a = intArrayOf(-5,0,2,1,2)
    val edges = arrayOf(
        intArrayOf(0,1),
        intArrayOf(3,4),
        intArrayOf(2,3),
        intArrayOf(0,3),
    )
    println(solution.solution(a, edges))

//    // 백준
//    val test = NumberBaseball()
//    test.run()
}