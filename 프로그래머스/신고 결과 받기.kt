package 프로그래머스

class ReportedResult {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val reported = id_list.associateWith { mutableSetOf<String>() }
            .toMutableMap()
        val answer = id_list.associateWith { 0 }
            .toMutableMap()

        for (r in report) {
            val (user, target) = r.split(' ')
            reported[target]!!.add(user)
        }

        for (target in id_list) {
            if (reported[target]!!.size >= k) {
                for (r in reported[target]!!) {
                    answer[r] = answer[r]!! + 1
                }
            }
        }

        return answer.values.toIntArray()
    }
}