package 프로그래머스

import java.util.Deque
import java.util.LinkedList
import kotlin.math.min

class MatchingCard {
    private val SIZE = 4
    private val dx = intArrayOf(0, 0, -1, 1)
    private val dy = intArrayOf(-1, 1, 0, 0)
    private lateinit var board: Array<IntArray>
    private lateinit var cards: List<Pair<Int, Int>>

    private var answer = Int.MAX_VALUE

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        this.board = board
        cards = getAllCards()
        println(cards)

        val visited = BooleanArray(cards.size) { false }

        dfs(r, c, visited, 0)

        return answer
    }

    private fun dfs(startX: Int, startY: Int, visited: BooleanArray, score: Int) {
        println("dfs : $startX $startY $score")
        println("visited : ${visited.toList()}")

        if (visited.all { it }) {
            answer = min(answer, score)
            println("fetch answer : $answer")
            return
        }

        for (idx in cards.indices) {
            if (visited[idx]) continue

            val (targetX, targetY) = cards[idx]
            println("card : ${cards[idx]}")

            val next =
                getMinScore(startX, startY, board[targetX][targetY], cards.filterIndexed { idx, value -> visited[idx] })
            println("next : $next")

            visited[idx] = true

            for ((nextX, nextY, nextScore) in next) {
                val (matchX, matchY, matchScore) = getMinScore(
                    nextX,
                    nextY,
                    board[nextX][nextY],
                    cards.filterIndexed { idx, value -> visited[idx] })[0]
                println("match : $matchX $matchY $matchScore")

                val matchIdx = cards.indexOf(matchX to matchY)
                visited[matchIdx] = true
                dfs(matchX, matchY, visited, score + nextScore + matchScore)
                visited[matchIdx] = false
            }
        }
    }

    private fun getAllCards(): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        for (x in 0 until SIZE) {
            for (y in 0 until SIZE) {
                if (board[x][y] != 0) result.add(x to y)
            }
        }
        return result
    }

    private fun getMinScore(
        startX: Int,
        startY: Int,
        targetScore: Int,
        cardVisited: List<Pair<Int, Int>>
    ): List<Triple<Int, Int, Int>> {
        val visited = Array(SIZE) { BooleanArray(SIZE) { false } }
        visited[startX][startY] = true
        cardVisited.forEach { (x, y) ->
            visited[x][y] = true
        }

        val queue: Deque<List<Int>> = LinkedList()
        queue.add(listOf(startX, startY, 1))

        var cnt = 0

        while (queue.isNotEmpty()) {
            val (x, y, score) = queue.removeFirst()

            for (idx in 0 until 4) {
                val nx = x + dx[idx]
                val ny = y + dy[idx]

                if (nx in 0 until SIZE && ny in 0 until SIZE) {
                    if (board[nx][ny] == targetScore && startX to startY != nx to ny) {
                        return listOf(Triple(nx, ny, score + 1))
                    }

                    if (visited[nx][ny]) continue

                    visited[nx][ny] = true
                    queue.add(listOf(nx, ny, score + 1))
                }
            }

            if (x > 1) {
                for (nnx in x - 1 downTo 0) {
                    if (board[nnx][y] != 0) {
                        if (nnx == x - 1) break

                        if (!visited[nnx][y]) {
                            visited[nnx][y] = true
                            queue.add(listOf(nnx, y, score + 1))
                            break
                        }
                    }

                    if (nnx == 0 && !visited[nnx][y]) queue.add(listOf(nnx, y, score + 1))
                }
            }

            if (x < 2) {
                for (nnx in x + 1 until SIZE) {
                    if (board[nnx][y] != 0) {
                        if (nnx == x + 1) break

                        visited[nnx][y] = true
                        queue.add(listOf(nnx, y, score + 1))
                        break
                    }

                    if (nnx == SIZE - 1 && !visited[nnx][y]) queue.add(listOf(nnx, y, score + 1))
                }
            }

            if (y > 1) {
                for (nny in y - 1 downTo 0) {
                    if (board[x][nny] != 0) {
                        if (nny == y - 1) break

                        visited[x][nny] = true
                        queue.add(listOf(x, nny, score + 1))
                        break
                    }
                    if (nny == 0 && !visited[x][nny]) queue.add(listOf(x, nny, score + 1))
                }
            }

            if (y < 2) {
                for (nny in y + 1 until SIZE) {
                    if (board[x][nny] != 0) {
                        if (nny == y + 1) break

                        visited[x][nny] = true
                        queue.add(listOf(x, nny, score + 1))
                        break
                    }

                    if (nny == SIZE - 1 && !visited[x][nny]) queue.add(listOf(x, nny, score + 1))
                }
            }

            cnt++
        }

        return emptyList()
    }
}