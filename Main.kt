import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
//    val arr = intArrayOf(16952, 70276, 16771, 37992, 87549, 54906, 36718, 20478, 57088, 27916, 51509, 83422, 51707, 18807, 80859, 2673, 37734, 93380)
//    val l = 149845L
//    val r = 228204L

    val cost = arrayOf(
        intArrayOf(49250, 42271, 40724, 36310, 32560, 30670, 24100, 10378),
        intArrayOf(58510, 56101, 54078, 32864, 31443, 19451, 18098, 7187),
        intArrayOf(68812, 66112, 65024, 60529, 53992, 39865, 31325, 17700),
        intArrayOf(13768, 12866, 11379, 10425, 6853, 6176, 5655, 2556),
        intArrayOf(51748, 48647, 41478, 39756, 25302, 18081, 16504, 811),
        intArrayOf(52690, 34113, 32370, 29555, 19343, 11763, 7566, 5962),
        intArrayOf(9306, 9190, 8196, 7573, 6275, 4723, 1316, 212),
        intArrayOf(40713, 40158, 31449, 22349, 20956, 20377, 19489, 14450)
    )

    val hint = arrayOf(
        intArrayOf(0, 2, 4, 6, 2),
        intArrayOf(0, 6, 3, 3, 4),
        intArrayOf(0, 6, 4, 5, 5),
        intArrayOf(0, 7, 5, 8, 7),
        intArrayOf(0, 7, 7, 7, 7),
        intArrayOf(0, 7, 7, 7, 8),
        intArrayOf(0, 8, 8, 8, 8)
    )

    val answer = HintStageSecond().solution(cost, hint)
    println(answer)
}