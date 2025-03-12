package 백준

// 백준 2992번 크면서 작은 수

const val MAX = 999999
class BigOrSmallN {

    fun run(){
        val n = readln().toInt()
        val nLength = n.toString().length
        val nSet = n.toString().toList().sorted()
        var answer = 0
        var start = "1"
        repeat(nLength-1){
            start += "0"
        }

        for(i in start.toInt()..MAX){
            if(i.toString().toList().sorted() != nSet) continue

            if(i > n) {
                answer = i
                break
            }
        }

        println(answer)
    }
}