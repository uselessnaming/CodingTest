import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val gems = arrayOf(
        "DIA",
        "RUBY",
        "RUBY",
        "DIA",
        "DIA",
        "EMERALD",
        "SAPPHIRE",
        "DIA"
    )
    val gems2 = arrayOf(
        "AA",
        "AB",
        "AC",
        "AA",
        "AC"
    )
    val gems3 = arrayOf(
        "XYZ",
        "XYZ",
        "XYZ",
    )
    val gems4 = arrayOf(
        "ZZZ",
        "YYY",
        "NNNN",
        "YYY",
        "BBB",
    )
    val gemsEx = arrayOf(
        "AA",
        "AA",
        "AA",
        "AA",
        "BB",
        "CC",
    )
    val gemsEx2 = arrayOf(
        "AA",
        "BB",
        "BB",
        "CC",
        "AA",
        "CC",
        "BB",
        "BB",
        "AA",
    )

    val answer = GemShopping().solution(gemsEx2)
    println(answer.toList())

//    // 백준
//    StartAndLink().apply { run() }
}