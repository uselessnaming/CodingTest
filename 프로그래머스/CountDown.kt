package 프로그래머스

class CountDown{
    val seq = intArrayOf(1,18,4,13,6,10,15,2,17,3,19,7,16,8,11,14,9,12,5,50,20)
    val cache = mutableMapOf<Int, Pair<Int, Int>>()
    fun solution(target: Int): IntArray{
        val result = dp(target)
        return intArrayOf(result.first, result.second)
    }

    fun dp(n: Int): Pair<Int, Int>{
        if (n == 0) return Pair(0,0)
        if (cache.contains(n)) return cache[n]!!

        val arr = mutableListOf<Pair<Int, Int>>()

        for (i in seq){
            if (i == 50 && n >= 50){
                val temp = dp(n-50)
                arr.add(Pair(temp.first + 1, temp.second + 1))
            } else {
                if (n >= i){
                    val temp = dp(n - i)
                    arr.add(Pair(temp.first + 1, temp.second + 1))
                }
                if (n >= i*2){
                    val temp = dp(n - i*2)
                    arr.add(Pair(temp.first + 1, temp.second))
                }
                if (n >= i*3){
                    val temp = dp(n - i*3)
                    arr.add(Pair(temp.first + 1, temp.second))
                }
            }
        }
        val best = arr.minWithOrNull(compareBy<Pair<Int, Int>>{it.first}.thenByDescending{it.second})!!
        cache[n] = best
        return best
    }
}