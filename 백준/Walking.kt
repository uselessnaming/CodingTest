package 백준

// 백준 1459번 걷기

class Walking {
    fun run(){
        val (x, y, w, s) = readln().split(" ").map{it.toLong()}
        var answer = 0L
        //집 좌표 x, y 중 작은 것을 선택
        val max = maxOf(x, y)
        val min = minOf(x, y)
        // 만약 2*w 보다 s가 작다면 answer + 작은 것 * s
        // 아니라면 answer + 작은 것 * 2 * w
        if (2*w < s){
            answer += min * 2 * w
        } else {
            answer += min * s
        }
        // 직선 거리에서 w < s라면
        if (w < s){
            answer += (max-min) * w
        } else {
            if((max - min) % 2L == 1L){
                answer += (max-min-1) * s
                answer += w
            } else {
                answer += (max-min) * s
            }
        }

        println(answer)
    }
}