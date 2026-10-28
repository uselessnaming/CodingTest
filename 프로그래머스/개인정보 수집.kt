package 프로그래머스

class PersonalInfoCollection {
    private val durations = mutableMapOf<String, Int>()

    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val answer = ArrayList<Int>()
        val today = today.toDays()

        for(term in terms) {
            val (type, duration) = term.split(" ")
            durations[type] = duration.toInt() * DAYS_IN_MONTH
        }

        for(idx in privacies.indices) {
            val (privacy, type) = privacies[idx].split(" ")

            if (today >= privacy.toDays() + durations[type]!!) {
                answer.add(idx + 1)
            }
        }

        return answer.toIntArray()
    }

    private fun String.toDays(): Int {
        val (year, month, day) = this.split(".").map { it.toInt() }
        return (year - 2000) * DAYS_IN_YEAR + month * DAYS_IN_MONTH + day
    }

    companion object {
        const val DAYS_IN_MONTH = 28
        const val DAYS_IN_YEAR = DAYS_IN_MONTH * 12
    }
}