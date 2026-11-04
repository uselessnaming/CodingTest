package 프로그래머스

// sell 목록은 파는 목록, buy 목록은 사는 목록
// 최대한의 순이익을 return

class CodingTest1101_3 {
    private val to = ArrayList<Int>()
    private val cap = ArrayList<Int>()
    private val cost = ArrayList<Int>()
    private val g = ArrayList<MutableList<Int>>()

    fun solution(sell: Array<String>, buy: Array<String>): Int {
        val s = node()
        val t = node()
        val ids = HashMap<String, Int>()

        fun id(x: String) = ids.getOrPut(x) { node() }

        for (i in sell.indices) {
            val b = buy[i]
            val se = sell[i]

            when {
                b[0].isDigit() -> addEdge(s, id(se), b.toInt())
                se[0].isDigit() -> addEdge(id(b), t, -se.toInt())
                else -> addEdge(id(b), id(se), 0)
            }
        }

        var total = 0
        while (true) {
            val n = g.size
            val dist = IntArray(n) { Int.MAX_VALUE }
            val prev = IntArray(n) { -1 }
            val inQ = BooleanArray(n)
            val q = ArrayDeque<Int>()
            dist[s] = 0
            q.add(s)

            while(q.isNotEmpty()) {
                val u = q.removeFirst()
                inQ[u] = false

                for (e in g[u]) {
                    if (cap[e] > 0 && dist[u] + cost[e] < dist[to[e]]) {
                        dist[to[e]] = dist[u] + cost[e]
                        prev[to[e]] = e
                        if (!inQ[to[e]]) {
                            inQ[to[e]] = true
                            q.add(to[e])
                        }
                    }
                }
            }

            if (dist[t] == Int.MAX_VALUE || dist[t] >= 0) break

            var v = t
            while(v != s) {
                val e = prev[v]
                cap[e] -= 1
                cap[e xor 1] += 1
                v = to[e xor 1]
            }
            total += dist[t]
        }

        return -total
    }

    private fun node(): Int {
        g.add(mutableListOf())
        return g.size - 1
    }

    private fun addEdge(u: Int, v: Int, c: Int) {
        g[u].add(to.size)
        to.add(v)
        cap.add(1)
        cost.add(c)

        g[v].add(to.size)
        to.add(u)
        cap.add(0)
        cost.add(-c)
    }
}