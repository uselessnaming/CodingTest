package 백준

// 백준 1057번 토너먼트

class Tournament {
    fun run(){
        val (max, i, target) = readln().split(" ").map{it.toInt()}

        var answer = 1
        var gamer = MutableList(max+1){0}
        gamer[i] = 1
        gamer[target] = 2

        var n = max

        while(n >= 1){
            val winners = MutableList((if(n%2 == 0) n/2 else n/2+1)+1){0}

            for(i in 1 until n step 2){
                if((gamer[i] == 1 && gamer[i + 1] == 2) || (gamer[i] == 2 && gamer[i + 1] == 1)){
                    println(answer)
                    return
                } else if((gamer[i] == 1 && gamer[i+1] == 0) || (gamer[i] == 0 && gamer[i+1] == 1)){
                    winners[i/2+1] = 1
                } else if((gamer[i] == 2 && gamer[i+1] == 0) || (gamer[i] == 0 && gamer[i+1] == 2)){
                    winners[i/2+1] = 2
                } else {
                    winners[i/2+1] = 0
                }
            }
            if (n%2 == 1) winners[winners.lastIndex] = gamer[gamer.lastIndex]
            n = if(n%2 == 0) n/2 else n/2+1
            gamer = winners.toMutableList()
            answer++
        }
    }
}