package 백준

import java.util.LinkedList
import java.util.Queue

class DSLR{
    fun run(){
        val n = readln().toInt()
        repeat(n){
            var (a, b) = readln().split(" ").map{it.toInt()}
            val visited = BooleanArray(10001){false}
            val queue = ArrayDeque<Register>()
            queue.addFirst(Register(a, ""))
            visited[a] = true
            while(queue.isNotEmpty()) {
                val (value, cmd) = queue.removeFirst()
                if (value == b){
                    println(cmd)
                    break
                }

                val d = D(value)
                val s = S(value)
                val l = L(value)
                val r = R(value)
                if (!visited[d]) {
                    queue.addLast(Register(d, cmd+"D"))
                    visited[d] = true
                }
                if (!visited[s]) {
                    queue.addLast(Register(s, cmd+"S"))
                    visited[s] = true
                }
                if (!visited[l]) {
                    queue.addLast(Register(l, cmd+"L"))
                    visited[l] = true
                }
                if (!visited[r]) {
                    queue.addLast(Register(r, cmd+"R"))
                    visited[r] = true
                }
            }
        }
    }

    fun D(num: Int): Int{ return (num * 2) % 10000 }
    fun S(num: Int): Int{ return if(num - 1 < 0) 9999 else num - 1 }
    fun L(num: Int): Int{ return (num%1000)*10 + num/1000 }
    fun R(num: Int): Int{ return (num%10)*1000 + (num/10)%1000 }
}

data class Register(
    val cur: Int,
    val command: String
)