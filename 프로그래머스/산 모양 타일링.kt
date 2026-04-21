package 프로그래머스

class Tiling {
    private val STD = 10007

    private lateinit var a: IntArray
    private lateinit var b: IntArray

    fun solution(n: Int, tops: IntArray): Int {
        var answer = 0
        a = IntArray(n + 1) { 0 }
        b = IntArray(n + 1) { 0 }

        a[1] = 1
        if (tops[0] == 1) b[1] = 3
        else b[1] = 2

        for (i in 2..n) {
            a[i] = (a[i - 1] + b[i - 1]) % STD
            if (tops[i - 1] == 1) {
                b[i] = (2 * a[i - 1] + 3 * b[i - 1])% STD
            } else {
                b[i] = (a[i - 1] + 2 * b[i - 1]) % STD
            }
        }

        answer = (a[n] + b[n]) % STD
        return answer
    }
}