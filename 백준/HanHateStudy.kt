package 백준

// 백준 3182번 한동이는 공부가 하기 싫어!

class HanHateStudy {
    lateinit var visited: BooleanArray

    fun run(){
        var answer = 0
        var result = 0
        val n = readln().toInt()
        val seniors = mutableListOf<Int>().apply{this.add(0)}

        repeat(n){ seniors.add(readln().toInt()) }

        for(idx in seniors.indices){
            visited = BooleanArray(n+1){false}
            visited[idx] = true
            var cur = seniors[idx]
            var cnt = 1
            while(true){
                if (visited[cur]) break
                visited[cur] = true
                cur = seniors[cur]
                cnt++
            }
            if (cnt > result) {
                answer = idx
                result = cnt
            }
        }

        println(answer)
    }
}