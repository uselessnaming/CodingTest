import 백준.*
import 프로그래머스.*
import java.util.*

fun main() {
    //프로그래머스
    val words = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao")
    val queries = arrayOf("fro??", "????o", "fr???", "fro???", "pro?")
    val answer = SearchText().solution(words, queries)
    print(answer.toList())

//    // 백준
//    StartAndLink().apply { run() }
}