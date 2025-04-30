package 백준

// 1300번 K번째 수
// 핵심 아이디어는 2차원 배열에서 B[k] 값보다 작은 수의 값을 찾아야 한다는 것
// 거기서 B[k] 보다 작은 값의 개수는 결국 (n / 현재 열의 index)로 구하며
// 이를 기반으로 수식 값을 계산

class KNumber{
    val arr = mutableMapOf<Long, Int>()
    fun run(){
        val n = readln().toLong()
        val k = readln().toLong()

        var left = 1L
        var right = n*n
        while(left <= right){
            val mid = (left + right) / 2
            if (checkIdx(n, mid) >= k){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        println(left)
    }

    fun checkIdx(n: Long, value: Long): Long{
        var sum = 0L
        for(i in 1..n){
            var rem = value / i
            if (rem >= n){rem = n}
            sum += rem
        }
        return sum
    }
}