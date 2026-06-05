import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val depth = intArrayOf(1,2,3,4,5,6,7,8,9,10)
    val money = 55
    val treasureIdx = Random.nextInt(0, depth.size)
    val excavate: (Int) -> Int = {
        if (treasureIdx == it) it
        else if (treasureIdx > it) 1
        else -1
    }

    val answer = FindTreasure().solution(depth, money, excavate)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}