package 프로그래머스

// 홀짝트리

class OddAndEven {
    val graph = mutableMapOf<Int, MutableList<Int>>()
    val answer = IntArray(2){0}

    fun solution(nodes: IntArray, edges: Array<IntArray>): IntArray {
        nodes.forEach{ node -> graph[node] = mutableListOf() }
        edges.forEach{ edge ->
            val (start, end) = edge
            graph[start]!!.add(end)
            graph[end]!!.add(start)
        }

        val visited = mutableSetOf<Int>()
        // 모든 node를 root로 돌아봐야 하기 때문에 돌리면 됨
        for(node in nodes){
            if (visited.contains(node)) continue
            val exploreResult = ExploreResult()
            exploreTree(visited, exploreResult, node)
            answer[0] += exploreResult.getOddEvenTree()
            answer[1] += exploreResult.getReverseOddEvenTree()
        }

        return answer
    }

    fun exploreTree(visited: MutableSet<Int>, exploreResult: ExploreResult, current: Int){
        val nexts = graph[current]!!

        when{
            nexts.size % 2 == 0 && current % 2 == 0 -> exploreResult.evenNode++
            nexts.size % 2 == 1 && current % 2 == 0 -> exploreResult.reverseEvenNode++
            nexts.size % 2 == 0 && current % 2 == 1 -> exploreResult.reverseOddNode++
            nexts.size % 2 == 1 && current % 2 == 1 -> exploreResult.oddNode++
        }

        visited.add(current)
        for(next in nexts){
            if (!visited.contains(next)){
                exploreTree(visited, exploreResult, next)
            }
        }
    }
}

class ExploreResult{
    var oddNode = 0
    var evenNode = 0
    var reverseOddNode = 0
    var reverseEvenNode = 0

    fun getReverseOddEvenTree(): Int{
        return if (reverseOddNode == 1 && reverseEvenNode == 0) 1
        else if (reverseOddNode == 0 && reverseEvenNode == 1) 1
        else 0
    }

    fun getOddEvenTree(): Int{
        return if ((oddNode == 1 && evenNode == 0) || (oddNode == 0 && evenNode == 1)) 1
        else 0
    }
}