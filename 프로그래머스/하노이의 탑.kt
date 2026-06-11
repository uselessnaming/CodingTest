package 프로그래머스

class TopOfHanoi {
    private var answer = mutableListOf<IntArray>()

    fun solution(n: Int): Array<IntArray> {
        hanoi(n, 1, 2, 3)
        return answer.toTypedArray()
    }

    private fun hanoi(n: Int, from: Int, via: Int, to: Int) {
        if (n == 1) {
            answer.add(intArrayOf(from, to))
            return
        }

        hanoi(n - 1, from, to, via)
        answer.add(intArrayOf(from, to))
        hanoi(n - 1, via, from, to)
    }
}