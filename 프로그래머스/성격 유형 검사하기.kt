package 프로그래머스

class PersonalityTest {
    fun solution(survey: Array<String>, choices: IntArray): String {
        val res = IntArray(SIZE)
        var answer = ""

        for (idx in survey.indices) {
            val score = choices[idx] - MIDDLE

            when(survey[idx]) {
                RION + TUBE -> res[IDX_RT] += score
                TUBE + RION -> res[IDX_RT] -= score
                CON + FRODO -> res[IDX_CF] += score
                FRODO + CON -> res[IDX_CF] -= score
                JAZY + MUZI -> res[IDX_JM] += score
                MUZI + JAZY -> res[IDX_JM] -= score
                APEACH + NEO -> res[IDX_AN] += score
                NEO + APEACH -> res[IDX_AN] -= score
            }
        }

        answer += if (res[IDX_RT] <= 0) RION else TUBE
        answer += if (res[IDX_CF] <= 0) CON else FRODO
        answer += if (res[IDX_JM] <= 0) JAZY else MUZI
        answer += if (res[IDX_AN] <= 0) APEACH else NEO

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

        const val IDX_RT = 0
        const val IDX_CF = 1
        const val IDX_JM = 2
        const val IDX_AN = 3
    }
}