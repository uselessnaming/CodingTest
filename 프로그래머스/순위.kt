package 프로그래머스

class Order {
    private lateinit var edgeCnt: IntArray
    private lateinit var visited: Array<BooleanArray>
    private lateinit var winList: Array<MutableList<Int>>

    fun solution(n: Int, results: Array<IntArray>): Int {
        var answer = 0
        edgeCnt = IntArray(n + 1)
        visited = Array(n + 1) { BooleanArray(n + 1) }
        winList = Array(n + 1) { mutableListOf() }

        results.forEach{ (winner, loser) ->
            winList[winner].add(loser)
        }

        repeat(n) { countEdgesOf(it + 1, it + 1) }
        repeat(n){ if(edgeCnt[it] == n - 1) answer++ }

        return answer
    }

    private fun countEdgesOf(src: Int, cur: Int) {
        winList[cur].forEach { loser ->
            if (!visited[src][loser]) {
                visited[src][loser] = true

                edgeCnt[src]++
                edgeCnt[loser]++
                countEdgesOf(src, loser)
            }
        }
    }
}