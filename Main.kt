import 백준.*
import 프로그래머스.*

fun main() {
    //프로그래머스
    val solution = FindCollisionRisk()
//    val points = arrayOf(
//        intArrayOf(2,2),
//        intArrayOf(2,3),
//        intArrayOf(2,7),
//        intArrayOf(6,6),
//        intArrayOf(5,2),
//    )
//    val routes = arrayOf(
//        intArrayOf(2,3,4,5),
//        intArrayOf(1,3,4,5),
//    )
    val points = arrayOf(
        intArrayOf(3,2),
        intArrayOf(6,4),
        intArrayOf(4,7),
        intArrayOf(1,4),
    )
    val routes = arrayOf(
        intArrayOf(4,2),
        intArrayOf(1,3),
        intArrayOf(4,2),
        intArrayOf(4,3),
    )
    println(solution.solution(points, routes))

//    // 백준
//    val test = HanHateStudy()
//    test.run()
}