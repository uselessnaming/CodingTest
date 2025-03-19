package 백준

// 백준 5585번 거스름돈

class Change {
    fun run(){
        // n을 입력
        val n = readln().toInt()
        var answer = 0
        // 1000 - n을 change로 지정
        var change = 1000 - n
        // change / 500 > 0 을 확인하고 맞다면 answer+= change/500, change = change % 500으로 변경
        if (change / 500 > 0){
            answer += change/500
            change %= 500
        }
        if (change / 100 > 0){
            answer += change/100
            change %= 100
        }
        if (change / 50 > 0){
            answer += change/50
            change %= 50
        }
        if (change / 10 > 0){
            answer += change/10
            change %= 10
        }
        if (change / 5 > 0){
            answer += change/5
            change %= 5
        }
        if (change / 1 > 0){
            answer += change/1
        }
        // 모든 돈에 대해 해당 과정을 반복한 후 println(answer)
        println(answer)
    }
}