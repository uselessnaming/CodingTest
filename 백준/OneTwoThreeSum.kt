package 백준

//백준 9095번 1,2,3 더하기
class OneTwoThreeSum{
    val res = MutableList<Int>(10+1){0}
    fun run(){
        val n = readln().toInt()
        res[1] = 1
        res[2] = 2
        res[3] = 4
        repeat(n){
            val k = readln().toInt()
            println(dp(k))
        }
    }
    fun dp(n: Int): Int {
        if(res[n] != 0) return res[n]
        if(n == 1 || n == 2 || n == 3) return res[n]

        res[n] = dp(n-1) + dp(n-2) + dp(n-3)
        return res[n]
    }
}