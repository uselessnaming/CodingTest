package 프로그래머스

import java.util.*

class ForkLiftAndCrane {
    lateinit var map: Array<String>
    lateinit var isOut: Array<IntArray>

    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(storage: Array<String>, requests: Array<String>): Int{
        map = storage
        isOut = Array(storage.size+2){ i ->
            IntArray(storage[0].length+2){ j ->
                if (i == 0 || j == 0 || i == storage.size + 1 || j == storage[0].length + 1) 1 else 0
            }
        }

        for(request in requests){
            val nodes = mutableListOf<Pair<Int, Int>>()
            val visitedType = if(request.length == 1) 1 else 2

            for (i in storage.indices){
                for((j, c) in storage[i].withIndex()){
                    if (request[0] != c || isOut[i+1][j+1] != 0) continue

                    val start = Pair(i+1, j+1)
                    if (request.length == 1 && isConnect(start)){
                        nodes.add(start)
                    } else if (request.length == 2){
                        nodes.add(start)
                    }
                }
            }

            nodes.forEach{ (r,c) -> isOut[r][c] = visitedType}

            val q = LinkedList<Pair<Int, Int>>()
            for (i in isOut.indices){
                for ((j, type) in isOut[i].withIndex()){
                    val start = i to j
                    if(type == 2 && isConnect(start)) q.add(start)
                }
            }

            while(q.isNotEmpty()){
                val (r, c) = q.poll()
                isOut[r][c] = 1

                for((mr, mc) in moves){
                    var nr = r + mr
                    val nc = c + mc

                    if (nr in isOut.indices && nc in isOut[0].indices && isOut[nr][nc] == 2){
                        q.offer(nr to nc)
                    }
                }
            }
        }

        return isOut.sumOf{arr -> arr.count{it == 0}}
    }

    private fun isConnect(start: Pair<Int, Int>): Boolean{
        return moves.any{ (mr, mc) ->
            val nr = start.first + mr
            val nc = start.second + mc
            nr in isOut.indices && nc in isOut[0].indices && isOut[nr][nc] == 1
        }
    }
}