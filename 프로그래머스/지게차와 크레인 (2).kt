package 프로그래머스

import java.util.LinkedList

class ForkLiftAndCraneSecond() {
    private lateinit var isOut: Array<IntArray>
    private val moves = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

    fun solution(storage: Array<String>, requests: Array<String>): Int {
        isOut = Array(storage.size + 2) { i ->
            IntArray(storage[0].length + 2) { j ->
                if (i == 0 || j == 0 || i == storage.size + 1 || j == storage[0].length + 1) 1 else 0
            }
        }

        for(request in requests) {
            val nodes = mutableListOf<Pair<Int, Int>>()
            val type = request.length

            for(i in storage.indices) {
                for(j in storage[i].indices) {
                    val container = storage[i][j]

                    if (container != request[0] || isOut[i + 1][j + 1] != 0) continue

                    if (type == 1 && isConnect(i + 1, j + 1)) {
                        nodes.add(i + 1 to j + 1)
                    } else if (type == 2) {
                        nodes.add(i + 1 to j + 1)
                    }
                }
            }

            nodes.forEach { (r, c) -> isOut[r][c] = type }

            val outList = LinkedList<Pair<Int, Int>>()
            for (i in isOut.indices) {
                for (j in isOut[i].indices) {
                    if (isOut[i][j] == 2 && isConnect(i , j)) {
                        outList.add(i to j)
                    }
                }
            }

            while(outList.isNotEmpty()) {
                val (r, c) = outList.poll()
                isOut[r][c] = 1

                for ((dr, dc) in moves) {
                    val nr = r + dr
                    val nc = c + dc

                    if (nr !in isOut.indices || nc !in isOut[0].indices) continue
                    if (isOut[nr][nc] == 2) {
                        outList.offer(nr to nc)
                    }
                }
            }
        }

        return isOut.sumOf { arr -> arr.count { it == 0 } }
    }

    private fun isConnect(r: Int, c: Int): Boolean {
        return moves.any{ (dr, dc) ->
            val nr = r + dr
            val nc = c + dc

            nr in isOut.indices && nc in isOut[0].indices && isOut[nr][nc] == 1
        }
    }
}