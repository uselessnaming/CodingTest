package 프로그래머스

import kotlin.math.max
import kotlin.math.min

class MoveBallSimulation {
    fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
        var rowStart = y
        var rowEnd = y
        var colStart = x
        var colEnd = x

        val queries = queries.reversed()

        queries.forEach { query ->
            val (cmd, dx) = query
            println("cmd : $cmd, dx : $dx")

            when (cmd) {
                0 -> {
                    if (rowStart != 0) {
                        rowStart += dx
                    }
                    rowEnd = min(m - 1, rowEnd + dx)
                }

                1 -> {
                    if (rowEnd != m - 1) {
                        rowEnd -= dx
                    }
                    rowStart = max(0, rowStart - dx)
                }

                2 -> {
                    if (colStart != 0) {
                        colStart += dx
                    }
                    colEnd = min(n - 1, colEnd + dx)
                }

                3 -> {
                    if (colEnd != n - 1) {
                        colEnd -= dx
                    }
                    colStart = max(0, colStart - dx)
                }
            }

            if (rowStart > rowEnd || colStart > colEnd) {
                return 0
            }
        }

        return (rowEnd - rowStart + 1).toLong() * (colEnd - colStart + 1).toLong()
    }
}