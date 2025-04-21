package 백준

// 3079번 입국심사
// 이진 탐색인데 초기 start는 1L로 설정
// 초기 end값은 (심사를 받아야하는 m명 * 심사의 초)의 최소 값으로 설정
// 그 이후 이진탐색 알고리즘으로 구하면 됨

class EntryScreening{
    val minValues = mutableListOf<Long>()
    fun run(){
        val (n, m) = readln().split(" ").map{ it.toInt() }
        repeat(n){ minValues.add(readln().toLong()) }

        var start = 1L
        var end = 0L
        for (t in minValues){
            end = end.coerceAtLeast(m*t)
        }
        var ans = 0L
        while(start <= end){
            val mid = (start + end) / 2L
            if (isFinish(m, mid)){
                end = mid - 1
                ans = mid
            } else {
                start = mid + 1
            }
        }

        println(ans)
    }

    fun isFinish(target: Int, mid: Long): Boolean {
        var sum = 0L
        for(t in minValues){
            sum += mid/t
            if (sum >= target) return true
        }
        return false
    }
}