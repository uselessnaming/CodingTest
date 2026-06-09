package 프로그래머스

class MatrixMultiplication {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val answer = Array(arr1.size) { IntArray(arr2[0].size) }

        for (i in arr1.indices) {
            for (j in arr2[0].indices) {
                answer[i][j] = multiply(arr1[i], arr2.map { it[j] }.toIntArray())
            }
        }

        return answer
    }

    private fun multiply(arr1: IntArray, arr2: IntArray): Int {
        var result = 0
        for (idx in arr1.indices) {
            result += (arr1[idx] * arr2[idx])
        }
        return result
    }
}