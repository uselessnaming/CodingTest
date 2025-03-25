package 프로그래머스

class Lighthouse {
    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        var cnt = 0

        for(idx in 1..n){graph[idx] = mutableSetOf()}

        lighthouse.forEach{
            graph[it[0]]!!.add(it[1])
            graph[it[1]]!!.add(it[0])
        }

        while (graph.isNotEmpty()){
            for(idx in 1..n){
                if (graph[idx]?.size == 1){
                    val first = graph[idx]!!.first()

                    cnt++

                    graph[first]!!.forEach{ i ->
                        if (graph[i]!!.size == 1) graph.remove(i)
                        else graph[i]!!.remove(first)
                    }

                    graph.remove(idx)
                    graph.remove(first)
                }
            }
        }

        return cnt
    }
}