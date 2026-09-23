import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
//    val arr = intArrayOf(16952, 70276, 16771, 37992, 87549, 54906, 36718, 20478, 57088, 27916, 51509, 83422, 51707, 18807, 80859, 2673, 37734, 93380)
//    val l = 149845L
//    val r = 228204L

    val numbers = longArrayOf(2, 7, 15, 4)

    val answer = FindBit().solution(numbers)
    println(answer.toList())
}