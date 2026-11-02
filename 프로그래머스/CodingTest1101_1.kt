package 프로그래머스

// responses는 응답자의 답변
// 전체 정답을 subtasks로 나눠서 전체 점수를 체점
// subtasks는 공백으로 구분되며 첫 부분은 점수, 뒷 부분은 맞아야 하는 문항들을 뜻함
// correct answer은 맞는 답을 나타냄
// 각 사람들의 점수를 intArray로 return

class CodingTest1101_1 {
    fun solution(correctAnswers: IntArray, responses: Array<IntArray>, subtasks: Array<String>): IntArray {
        val answer = IntArray(responses.size) { 0 }

        for (idx in responses.indices) {
            for (subtask in subtasks) {
                val splited = subtask.split(" ")
                val score = splited[0].toInt()
                val problems = splited.subList(1, splited.size).map { it.toInt() }

                if (problems.any { responses[idx][it - 1] != correctAnswers[it - 1] }) continue

                answer[idx] += score
            }
        }

        return answer
    }
}