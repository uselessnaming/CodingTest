package 백준

import java.lang.Integer.max
import java.util.Collections.min

//백준 1062번 가르침

class Teaching {
    val words = mutableListOf<String>()
    var n = 0
    var k = 0
    var max = 0

    fun run(){
        val (_n, _k) = readln().split(" ").map{it.toInt()}
        n = _n
        k = _k
        repeat(n){
            val word = readln()
            words.add(word)
        }

        print(getThingsToRead())
    }

    fun getThingsToRead(): Int{

        if (k < 5){ return 0 }
        else if (k == 26) { return n }

        combi(hashSetOf(0,2,8,13,19),0,0)
        return max
    }

    fun combi(set: HashSet<Int>, idx: Int, cnt: Int){
        if (cnt == k - 5){
            solve(set)
            return
        }

        for(i in idx until 26){
            if (!set.contains(i)){
                set.add(i)
                combi(set, i+1, cnt+1)
                set.remove(i)
            }
        }
    }

    fun solve(set: HashSet<Int>){
        val alpha = Array(26){false}
        var result = 0

        loop@
        for(str in words){
            var cnt = 0
            for(ch in str){
                val idx = (ch - 'a')

                if(!set.contains(idx)){
                    continue@loop
                }

                if(!alpha[idx]){
                    alpha[idx] = true
                    cnt++
                }

                if(cnt > k) continue@loop
            }
            result++
        }
        max = max(max, result)
    }
}