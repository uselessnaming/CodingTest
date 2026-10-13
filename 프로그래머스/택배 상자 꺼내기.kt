package 프로그래머스

class TakeOutBox {
    fun solution(n: Int, w: Int, num: Int): Int {
        var answer = 0
        var cnt = 1
        var dir = true
        var targetIdx = -1

        while(cnt <= n) {
            val idx = if (dir) {
                if (cnt % w == 0) w
                else cnt % w
            } else {
                if (cnt % w == 0) 1
                else w - (cnt % w) + 1
            }

            if (cnt == num) {
                targetIdx = idx
            }

            if (targetIdx != -1 && idx == targetIdx) {
                answer++
            }

            if (cnt % w == 0) dir = !dir
            cnt++
        }

        return answer
    }
}
