import 백준.*
import 프로그래머스.*

fun main() {
    //프로그래머스
    val solution = RestoreExpression()
    val expressions = arrayOf("2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X")
    println(solution.solution(expressions).toList())

//    // 백준
//    val test = EggHit()
//    test.run()
}