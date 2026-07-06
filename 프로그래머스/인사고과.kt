package 프로그래머스

class HREvaluationDepartment {
    fun solution(scores: Array<IntArray>): Int {
        val oneho = scores[0]
        var maxSecondScore = 0
        val filteredScores = mutableListOf<Int>()

        scores.sortWith(compareByDescending<IntArray> { it[0] }.thenBy { it[1] })

        for (score in scores) {
            if (score[1] < maxSecondScore) {
                if (score.contentEquals(oneho)) {
                    return -1
                }
                continue
            }

            maxSecondScore = if (maxSecondScore > score[1]) maxSecondScore else score[1]
            filteredScores.add(score[0] + score[1])
        }

        return filteredScores.count{ it > oneho[0] + oneho[1] } + 1
    }
}