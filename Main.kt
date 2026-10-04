import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    val userIds = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
    val bannedIds = arrayOf("fr*d*", "*rodo", "******", "******")

    val answer = IllegalUserSecond().solution(userIds, bannedIds)
    println(answer)
}