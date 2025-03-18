package 프로그래머스

class SkillTest2 {
    val score = mutableMapOf<String, Int>()
    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        val answer = IntArray(photo.size){0}

        init(name, yearning)
        for(idx in photo.indices){
            var total = 0
            for(person in photo[idx]){
                if(score.contains(person)){
                    total += score[person]!!
                }
            }
            answer[idx] = total
        }

        return answer
    }

    fun init(name: Array<String>, yearning: IntArray){
        for(i in name.indices){
            score[name[i]] = yearning[i]
        }
    }
}