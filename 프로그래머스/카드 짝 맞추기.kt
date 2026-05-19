package 프로그래머스

import java.util.Deque
import java.util.LinkedList
import kotlin.math.min
import java.util.*
import kotlin.math.min


//class MatchingCard {
//    private val SIZE = 4
//    private val dx = intArrayOf(0, 0, -1, 1)
//    private val dy = intArrayOf(-1, 1, 0, 0)
//    private lateinit var board: Array<IntArray>
//    private lateinit var cards: List<Pair<Int, Int>>
//
//    private var answer = Int.MAX_VALUE
//
//    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
//        this.board = board
//        cards = getAllCards()
//        println(cards)
//
//        val visited = BooleanArray(cards.size) { false }
//
//        dfs(r, c, visited, 0)
//
//        return answer
//    }
//
//    private fun dfs(startX: Int, startY: Int, visited: BooleanArray, score: Int) {
//        println("dfs : $startX $startY $score")
//        println("visited : ${visited.toList()}")
//
//        if (visited.all { it }) {
//            answer = min(answer, score)
//            println("fetch answer : $answer")
//            return
//        }
//
//        for (idx in cards.indices) {
//            if (visited[idx]) continue
//
//            val (targetX, targetY) = cards[idx]
//            println("card : ${cards[idx]}")
//            println("$startX $startY $targetX $targetY")
//
//            val next =
//                getMinScore(startX, startY, targetX, targetY, cards.filterIndexed { idx, value -> visited[idx] })
//            println("next : $next")
//
//            visited[idx] = true
//
//            for ((nextX, nextY, nextScore) in next) {
//                val (matchX, matchY, matchScore) = getMinScore(
//                    nextX,
//                    nextY,
//                    board[nextX][nextY],
//                    cards.filterIndexed { idx, value -> visited[idx] })[0]
//                println("match : $matchX $matchY $matchScore")
//
//                val matchIdx = cards.indexOf(matchX to matchY)
//                println("match idx : $matchIdx")
//                visited[matchIdx] = true
//                dfs(matchX, matchY, visited, score + nextScore + matchScore)
//                visited[matchIdx] = false
//            }
//
//            visited[idx] = false
//            println("for end visited : ${visited.toList()}")
//        }
//    }
//
//    private fun getAllCards(): List<Pair<Int, Int>> {
//        val result = mutableListOf<Pair<Int, Int>>()
//        for (x in 0 until SIZE) {
//            for (y in 0 until SIZE) {
//                if (board[x][y] != 0) result.add(x to y)
//            }
//        }
//        return result
//    }
//
//    private fun getMinScore(
//        startX: Int,
//        startY: Int,
//        targetX: Int,
//        targetY: Int,
//        cardVisited: List<Pair<Int, Int>>
//    ): List<Triple<Int, Int, Int>> {
//        if (startX to startY == targetX to targetY) return listOf(Triple(startX, startY, 1))
//
//        val visited = Array(SIZE) { BooleanArray(SIZE) { false } }
//        visited[startX][startY] = true
//        cardVisited.forEach { (x, y) ->
//            visited[x][y] = true
//        }
//
//        val queue: Deque<List<Int>> = LinkedList()
//        queue.add(listOf(startX, startY, 1))
//
//        while (queue.isNotEmpty()) {
//            val (x, y, score) = queue.removeFirst()
//
//            for (idx in 0 until 4) {
//                val nx = x + dx[idx]
//                val ny = y + dy[idx]
//
//                if (nx in 0 until SIZE && ny in 0 until SIZE) {
//                    if (targetX to targetY == nx to ny) {
//                        return listOf(Triple(nx, ny, score + 1))
//                    }
//
//                    if (visited[nx][ny]) continue
//
//                    visited[nx][ny] = true
//                    queue.add(listOf(nx, ny, score + 1))
//                }
//            }
//
//            if (x == 1) {
//                if (visited[2][y]) continue
//
//                if (!visited[3][y]) {
//                    if (targetX to targetY == 3 to y && startX to startY != 3 to y) {
//                        return listOf(Triple(3, y, score + 1))
//                    } else {
//                        queue.add(listOf(3, y, score + 1))
//                        visited[3][y] = true
//                        continue
//                    }
//                }
//            } else if (x == 2) {
//                if (visited[1][y]) continue
//
//                if (!visited[0][y]) {
//                    if (targetX to targetY == 0 to y && startX to startY != 0 to y) {
//                        return listOf(Triple(0, y, score + 1))
//                    } else {
//                        queue.add(listOf(0, y, score + 1))
//                        visited[0][y] = true
//                        continue
//                    }
//                }
//            }
//
//            if (y == 1) {
//                if (visited[x][2]) continue
//
//                if (!visited[x][3]) {
//                    if (targetX to targetY == x to 3 && startX to startY != x to 3) {
//                        return listOf(Triple(x, 3, score + 1))
//                    } else {
//                        queue.add(listOf(x, 3, score + 1))
//                        visited[x][3] = true
//                        continue
//                    }
//                }
//            } else if (y == 2) {
//                if (visited[x][1]) continue
//
//                if (!visited[x][0]) {
//                    if (targetX to targetY == x to 0 && startX to startY != x to 0) {
//                        return listOf(Triple(x, 0, score + 1))
//                    } else {
//                        queue.add(listOf(x, 0, score + 1))
//                        visited[x][0] = true
//                        continue
//                    }
//                }
//            }
//        }
//
//        return emptyList()
//    }
//
//    private fun getMinScore(
//        startX: Int,
//        startY: Int,
//        targetScore: Int,
//        cardVisited: List<Pair<Int, Int>>
//    ): List<Triple<Int, Int, Int>> {
//        val visited = Array(SIZE) { BooleanArray(SIZE) { false } }
//        visited[startX][startY] = true
//        cardVisited.forEach { (x, y) ->
//            visited[x][y] = true
//        }
//
//        val queue: Deque<List<Int>> = LinkedList()
//        queue.add(listOf(startX, startY, 1))
//
//        while (queue.isNotEmpty()) {
//            val (x, y, score) = queue.removeFirst()
//
//            for (idx in 0 until 4) {
//                val nx = x + dx[idx]
//                val ny = y + dy[idx]
//
//                if (nx in 0 until SIZE && ny in 0 until SIZE) {
//                    if (board[nx][ny] == targetScore && startX to startY != nx to ny) {
//                        return listOf(Triple(nx, ny, score + 1))
//                    }
//
//                    if (visited[nx][ny]) continue
//
//                    visited[nx][ny] = true
//                    queue.add(listOf(nx, ny, score + 1))
//                }
//            }
//
//            if (x == 1) {
//                if (visited[2][y]) continue
//
//                if (!visited[3][y]) {
//                    if (board[3][y] == targetScore) {
//                        return listOf(Triple(3, y, score + 1))
//                    } else {
//                        queue.add(listOf(3, y, score + 1))
//                        visited[3][y] = true
//                        continue
//                    }
//                }
//            } else if (x == 2) {
//                if (visited[1][y]) continue
//
//                if (!visited[0][y]) {
//                    if (board[0][y] == targetScore) {
//                        return listOf(Triple(0, y, score + 1))
//                    } else {
//                        queue.add(listOf(0, y, score + 1))
//                        visited[0][y] = true
//                        continue
//                    }
//                }
//            }
//
//            if (y == 1) {
//                if (visited[x][2]) continue
//
//                if (!visited[x][3]) {
//                    if (board[x][3] == targetScore && startX to startY != x to 3) {
//                        return listOf(Triple(x, 3, score + 1))
//                    } else {
//                        queue.add(listOf(x, 3, score + 1))
//                        visited[x][3] = true
//                        continue
//                    }
//                }
//            } else if (y == 2) {
//                if (visited[x][1]) continue
//
//                if (!visited[x][0]) {
//                    if (board[x][0] == targetScore && startX to startY != x to 0) {
//                        return listOf(Triple(x, 0, score + 1))
//                    } else {
//                        queue.add(listOf(x, 0, score + 1))
//                        visited[x][0] = true
//                        continue
//                    }
//                }
//            }
//        }
//
//        return emptyList()
//    }
//}

class MatchingCard {

    private val dx = intArrayOf(-1, 1, 0, 0)
    private val dy = intArrayOf(0, 0, -1, 1)

    private var answer = Int.MAX_VALUE

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {

        val cardMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()

        for (i in 0..3) {
            for (j in 0..3) {
                if (board[i][j] != 0) {
                    cardMap.getOrPut(board[i][j]) { mutableListOf() }
                        .add(i to j)
                }
            }
        }

        dfs(board, r, c, cardMap.keys.toList(), 0, 0)

        return answer
    }

    private fun dfs(
        board: Array<IntArray>,
        x: Int,
        y: Int,
        cards: List<Int>,
        idx: Int,
        cost: Int
    ) {

        if (cost >= answer) return

        if (idx == cards.size) {
            answer = min(answer, cost)
            return
        }

        for (card in cards) {

            if (!exists(board, card)) continue

            val positions = mutableListOf<Pair<Int, Int>>()

            for (i in 0..3) {
                for (j in 0..3) {
                    if (board[i][j] == card) {
                        positions.add(i to j)
                    }
                }
            }

            val (ax, ay) = positions[0]
            val (bx, by) = positions[1]

            // A -> B
            run {
                val toA = bfs(board, x, y, ax, ay)
                val toB = bfs(board, ax, ay, bx, by)

                board[ax][ay] = 0
                board[bx][by] = 0

                dfs(
                    board,
                    bx,
                    by,
                    cards,
                    idx + 1,
                    cost + toA + toB + 2
                )

                board[ax][ay] = card
                board[bx][by] = card
            }

            // B -> A
            run {
                val toB = bfs(board, x, y, bx, by)
                val toA = bfs(board, bx, by, ax, ay)

                board[ax][ay] = 0
                board[bx][by] = 0

                dfs(
                    board,
                    ax,
                    ay,
                    cards,
                    idx + 1,
                    cost + toA + toB + 2
                )

                board[ax][ay] = card
                board[bx][by] = card
            }
        }
    }

    private fun exists(board: Array<IntArray>, target: Int): Boolean {
        for (i in 0..3) {
            for (j in 0..3) {
                if (board[i][j] == target) return true
            }
        }
        return false
    }

    private fun bfs(
        board: Array<IntArray>,
        sx: Int,
        sy: Int,
        tx: Int,
        ty: Int
    ): Int {

        val visited = Array(4) { BooleanArray(4) }
        val queue: Queue<IntArray> = LinkedList()

        queue.add(intArrayOf(sx, sy, 0))
        visited[sx][sy] = true

        while (queue.isNotEmpty()) {

            val (x, y, dist) = queue.poll()

            if (x == tx && y == ty) {
                return dist
            }

            for (d in 0 until 4) {

                // 일반 이동
                var nx = x + dx[d]
                var ny = y + dy[d]

                if (nx in 0..3 && ny in 0..3 && !visited[nx][ny]) {
                    visited[nx][ny] = true
                    queue.add(intArrayOf(nx, ny, dist + 1))
                }

                // Ctrl 이동
                nx = x
                ny = y

                while (true) {

                    val mx = nx + dx[d]
                    val my = ny + dy[d]

                    if (mx !in 0..3 || my !in 0..3) break

                    nx = mx
                    ny = my

                    if (board[nx][ny] != 0) break
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true
                    queue.add(intArrayOf(nx, ny, dist + 1))
                }
            }
        }

        return Int.MAX_VALUE
    }
}