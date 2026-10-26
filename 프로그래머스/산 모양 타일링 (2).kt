package 프로그래머스

class MountainTiling {
    fun solution(n: Int, tops: IntArray): Int {
        var rightNotMatch = 1
        var rightMatch = if (tops[0] == 1) 3 else 2

        for (i in 2..n) {
            val tmp = (rightNotMatch + rightMatch) % STD

            if (tops[i - 1] == 1) {
                rightMatch = (rightNotMatch * 2 + rightMatch * 3) % STD
            } else {
                rightMatch = (rightNotMatch + rightMatch * 2) % STD
            }
            rightNotMatch = tmp
        }

        return (rightMatch + rightNotMatch) % STD
    }

    companion object {
        const val STD = 10007
    }
}