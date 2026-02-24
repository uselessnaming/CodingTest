package 백준

class EveryPermutation {
    private var n: Int = 0

    fun run() {
        n = readln().toInt()
        val visited = BooleanArray(n + 1) { false }
        dfs(0, "", visited)
    }

    private fun dfs(depth: Int, s: String, visited: BooleanArray) {
        if (depth == n) {
            println(s)
            return
        }

        for (i in 1..n) {
            if (!visited[i]) {
                visited[i] = true
                dfs(depth + 1, "$s$i ", visited)
                visited[i] = false
            }
        }
    }
}