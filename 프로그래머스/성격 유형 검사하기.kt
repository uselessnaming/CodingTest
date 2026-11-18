package 프로그래머스

class TestPersonality {
    fun solution(survey: Array<String>, choices: IntArray): String {
        val res = IntArray(SIZE)
        var answer = ""

        for (idx in survey.indices) {
            val score = choices[idx] - MIDDLE

            when(survey[idx]) {
                RION + TUBE -> res[0] += score
                TUBE + RION -> res[0] -= score
                CON + FRODO -> res[1] += score
                FRODO + CON -> res[1] -= score
                JAZY + MUZI -> res[2] += score
                MUZI + JAZY -> res[2] -= score
                APEACH + NEO -> res[3] += score
                NEO + APEACH -> res[3] -= score
            }
        }

        answer += if (res[0] <= 0) RION else TUBE
        answer += if (res[1] <= 0) CON else FRODO
        answer += if (res[2] <= 0) JAZY else MUZI
        answer += if (res[3] <= 0) APEACH else NEO

        return answer
    }

    companion object {
        const val RION = "R"
        const val TUBE = "T"
        const val CON = "C"
        const val FRODO = "F"
        const val JAZY = "J"
        const val MUZI = "M"
        const val APEACH = "A"
        const val NEO = "N"
        const val MIDDLE = 4
        const val SIZE = 4
    }
}