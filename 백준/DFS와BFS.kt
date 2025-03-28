package 백준

// 1260번 백준 DFS와 BFS

class DAndB {

    lateinit var visited: BooleanArray
    val graph = mutableMapOf<Int, MutableList<Int>>()
    val dfsNodes = mutableListOf<Int>()
    val bfsNodes = mutableListOf<Int>()

    fun run(){
        //입력
        val (n, m, start) = readln().split(" ").map{it.toInt()}
        visited = BooleanArray(n+1){false}
        for(i in 1..n){ graph[i] = mutableListOf() }
        repeat(m){
            val (start, end) = readln().split(" ").map{it.toInt()}
            graph[start]!!.add(end)
            graph[end]!!.add(start)
        }
        graph.keys.forEach{
            graph[it]!!.sort()
        }
        visited[start] = true

        //dfs
        dfsNodes.add(start)
        dfs(start)

        //bfs
        visited = BooleanArray(n+1){false}
        visited[start] = true
        bfsNodes.add(start)
        bfs(start)

        //출력
        println(dfsNodes.joinToString(" "))
        println(bfsNodes.joinToString(" "))
    }

    fun dfs(start: Int){
        graph[start]!!.forEach{
            if(!visited[it]){
                dfsNodes.add(it)
                visited[it] = true
                dfs(it)
            }
        }
    }

    fun bfs(start: Int){
        val queue = ArrayDeque<Int>()
        queue.addLast(start)
        while(queue.isNotEmpty()){
            val node = queue.removeFirst()
            for(n in graph[node]!!){
                if (!visited[n]){
                    queue.addLast(n)
                    bfsNodes.add(n)
                    visited[n] = true
                }
            }
        }
    }
}