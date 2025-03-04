package 프로그래머스

class ExpressibleBinaryTree {
    tailrec fun isOk(bin: String): Boolean{
        if(bin.length == 1) return true

        val mid = bin.length / 2
        val left = bin.take(mid)
        val right = bin.takeLast(mid)

        if(bin[mid] == '0'){
            if(bin.any{it == '1'}) return false
            return true
        }

        return isOk(left) && isOk(right)
    }

    fun solution(numbers: LongArray): IntArray{
        var answer = mutableListOf<Int>()

        for (num in numbers){
            val bin = num.toString(2).let{
                var n = 0.0
                while(Math.pow(2.0, n).toInt() - 1 < it.length){ n++ }
                "0".repeat(Math.pow(2.0, n).toInt() - 1 - it.length) + it
            }

            if (isOk(bin)) answer.add(1)
            else answer.add(0)
        }
        return answer.toIntArray()
    }
}