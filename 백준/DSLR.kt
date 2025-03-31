package 백준

import java.util.LinkedList
import java.util.Queue

// 알고리즘 자체는 맞았음
// 단지 String에서 Int형으로 변환할 때 toInt(), toString()의 경우
// 연산과 함께 쓰기에는 다소 무리가 있으며 조심해야 함
// 알 수 없는 오류들이 꽤나 빈번하게 발생
// 따라서 자리수 변환 같은 경우 /와 %를 적절하게 이용하는 방벙이 더 나음

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