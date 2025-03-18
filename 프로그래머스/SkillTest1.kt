package 프로그래머스

class SkillTest1 {
    fun solution(s: String): String {
        var answer = ""
        val words = s.split(" ")

        for(w in words.indices){
            for(idx in words[w].indices){
                answer += if (idx % 2 == 0) words[w][idx].uppercase() else words[w][idx].lowercase()
            }
            if (w != words.size - 1){
                answer += " "
            }
        }

        return answer
    }
}