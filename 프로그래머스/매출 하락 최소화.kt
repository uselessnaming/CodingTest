package 프로그래머스

class MinimizeSales {
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var dp: Array<IntArray>
    private lateinit var salesArr: IntArray

    fun solution(sales: IntArray, links: Array<IntArray>): Int {
        salesArr = sales
        graph = Array(sales.size + 1) { mutableListOf<Int>() }

        for ((parent, child) in links) {
            graph[parent].add(child)
        }

        dp = Array(sales.size + 1) { IntArray(2) }

        dfs(1)

        return minOf(dp[1][0], dp[1][1])
    }

    private fun dfs(now: Int) {
        dp[now][1] = salesArr[now - 1]

        if (graph[now].isEmpty()) return

        var hasAttend = false
        var needExtra = Int.MAX_VALUE

        for (next in graph[now]) {
            dfs(next)

            dp[now][1] += minOf(dp[next][0], dp[next][1])

            dp[now][0] += minOf(dp[next][0], dp[next][1])

            if (dp[next][1] <= dp[next][0]) hasAttend = true

            needExtra = minOf(needExtra, dp[next][1] - dp[next][0])
        }

        if (!hasAttend) dp[now][0] += needExtra
    }
}