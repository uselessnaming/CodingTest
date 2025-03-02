package 프로그래머스

class Tuple {
    fun solution(s: String): IntArray{
        val answer = mutableListOf<Int>()
        val str = s.substring(2, s.length-2).split("},{").sortedBy{ it.length }
        str.forEach{ row -> row.split(",").forEach{ if(!answer.contains(it.toInt())) answer.add(it.toInt())}}

        return answer.toIntArray()
    }
}