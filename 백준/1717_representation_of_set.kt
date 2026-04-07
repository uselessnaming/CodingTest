package 백준

class RepresentationOfSet {
    private lateinit var parents: MutableMap<Int, Int>

    fun run() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        parents = mutableMapOf()

        // 초기 n+1 개 map setting
        makeSet(n)

        // 연산에 따라 0 이면 union
        // 1이면 find 연산
        repeat(m) {
            val (op, a, b) = readln().split(" ").map { it.toInt() }
            if (op == 0) {
                union(a, b)
            } else {
                if(find(a, b) == null) {
                    println("NO")
                } else {
                    println("YES")
                }
            }
        }
    }

    private fun makeSet(n: Int) {
        for (i in 0..n) {
            parents[i] = i
        }
    }

    private fun find(a: Int, b: Int): Int? {
        val rootA = findTarget(a)
        val rootB = findTarget(b)

        return if (rootA == rootB) {
            findTarget(a)
        } else {
            null
        }
    }

    private fun findTarget(target: Int): Int {
        if (parents[target] != target) {
            parents[target] = findTarget(parents[target]!!)
        }

        return parents[target]!!
    }

    private fun union(a: Int, b: Int) {
        val rootA = findTarget(a)
        val rootB = findTarget(b)
        parents[rootA] = rootB
    }
}