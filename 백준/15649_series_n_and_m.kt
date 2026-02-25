package 백준

import java.time.LocalDateTime

class NAndMSeries {
    private var secondAnswers = mutableSetOf<List<Int>>()
    private var thirdAnswers = mutableSetOf<List<Int>>()

    /** 15649 n과 m (1) */
    fun firstRun() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val visited = BooleanArray(n + 1) { false }
        dfs(0, m, "", visited)
    }

    private fun dfs(depth: Int, limit: Int, answer: String, visited: BooleanArray) {
        if (depth == limit) {
            println(answer)
            return
        }

        for (i in 1 until visited.size) {
            if (!visited[i]) {
                visited[i] = true
                dfs(depth + 1, limit, "$answer$i ", visited)
                visited[i] = false
            }
        }
    }

    /** 15649 n과 m (2) */
    fun secondRun() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val visited = BooleanArray(n + 1) { false }
        dfsAddValue(0, m, emptyList(), visited)
        secondAnswers.forEach { answer ->
            println(answer.joinToString(" "))
        }
    }

    private fun dfsAddValue(depth: Int, limit: Int, answer: List<Int>, visited: BooleanArray) {
        if (depth == limit) {
            secondAnswers.add(answer.sorted())
            return
        }

        for (i in 1 until visited.size) {
            if (!visited[i]) {
                visited[i] = true
                dfsAddValue(depth + 1, limit, answer + i, visited)
                visited[i] = false
            }
        }
    }

    /** 15651 n과 m (3) */
    fun thirdRun() {
        val now = System.currentTimeMillis()
        val (n, m) = readln().split(" ").map { it.toInt() }
        thirdDfs(0, n, m, emptyList())
        println(thirdAnswers.joinToString("\n") { it.joinToString(" ") })
    }

    private fun thirdDfs(depth: Int, end: Int, limit: Int, answer: List<Int>) {
        if (depth == limit) {
            thirdAnswers.add(answer)
            return
        }

        for (i in 1..end) {
            thirdDfs(depth + 1, end, limit, answer + i)
        }
    }
}