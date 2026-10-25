package 프로그래머스

class MountainTiling {
    private lateinit var a: IntArray // 우측 하단 삼각이 짝이 있는 경우
    private lateinit var b: IntArray // 우측 하단 삼각이 짝이 없는 경우

    fun solution(n: Int, tops: IntArray): Int {
        a = IntArray(n + 1) { 0 }
        b = IntArray(n + 1) { 0 }

        a[1] = 1
        b[1] = if (tops[0] == 1) 3 else 2

        for (i in 2..n) {
            a[i] = (a[i - 1] + b[i - 1]) % STD

            if (tops[i - 1] == 1) {
                b[i] = (a[i - 1] * 2 + b[i - 1] * 3) % STD
            } else {
                b[i] = (a[i - 1] + b[i - 1] * 2) % STD
            }
        }

        return (a[n] + b[n]) % STD
    }

    companion object {
        const val STD = 10007
    }
}