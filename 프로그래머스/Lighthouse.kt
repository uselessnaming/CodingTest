package 프로그래머스

// 이 문제의 경우 다른 것보다 어떻게 해야 최소한의 등대를 켤 수 있는지를 생각해야 함
// leaf 노드는 켜질 경우 굉장히 비효율적임
// 따라서 leaf 노드에서 출발하여 연결된 등대를 켜고 다른 등대들을 제거하는 방법으로 하는 것이 좋음

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