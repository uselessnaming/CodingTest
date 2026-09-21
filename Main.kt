import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
//    val arr = intArrayOf(16952, 70276, 16771, 37992, 87549, 54906, 36718, 20478, 57088, 27916, 51509, 83422, 51707, 18807, 80859, 2673, 37734, 93380)
//    val l = 149845L
//    val r = 228204L

    val m = 4
    val n = 6
    val h = 1
    val w = 2
    val drops = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, 3),
        intArrayOf(0, 5),
        intArrayOf(1, 1),
        intArrayOf(1, 3),
        intArrayOf(1, 5),
        intArrayOf(2, 1),
        intArrayOf(2, 3),
        intArrayOf(2, 5),
        intArrayOf(3, 1),
        intArrayOf(3, 3),
        intArrayOf(3, 5),
    )

    val answer = HidingCactus().solution(m, n, h, w, drops)
    println(answer.toList())
}