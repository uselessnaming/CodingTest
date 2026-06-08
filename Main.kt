import 백준.*
import 프로그래머스.*
import kotlin.random.Random

fun main() {
    //프로그래머스
    val h = 3
    val grid = arrayOf(
        ".#.##..",
        ".#..##.",
        ".......",
        "##.###.",
        ".@.#...",
        "...#..."
    )
    val panels = arrayOf(
        intArrayOf(2, 3, 4),
        intArrayOf(2, 5, 6),
        intArrayOf(1, 1, 1),
        intArrayOf(3, 6, 3),
    )
    val seqs = arrayOf(
        intArrayOf(3, 2),
        intArrayOf(1, 2),
        intArrayOf(4, 1),
        intArrayOf(4, 3),
    )

    val answer = PowerPlantRecovery().solution(h, grid, panels, seqs)
    println(answer)

//    // 백준
//    StartAndLink().apply { run() }
}