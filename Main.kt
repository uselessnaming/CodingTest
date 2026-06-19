import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val user_id = arrayOf(
        "frodo",
        "fradi",
        "crodo",
        "abc123",
        "frodoc",
    )
    val banned_id = arrayOf(
        "*rodo",
        "*rodo",
        "******"
    )

    val answer = InvalidUser().solution(user_id, banned_id)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}