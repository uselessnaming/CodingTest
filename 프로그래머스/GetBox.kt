package 프로그래머스

class GetBox {
    fun solution(n: Int, w: Int, num: Int): Int{
        var answer = 0

        val stacks = MutableList(w){mutableListOf<Int>()}
        var dir = true
        var curIdx = 1
        var i = 0

        while(curIdx <= n){
            if(dir){
                stacks[i].add(curIdx)
                i++
            } else {
                i--
                stacks[i].add(curIdx)
            }
            if (curIdx % w == 0) dir = !dir
            curIdx++
        }

        for(stack in stacks){
            if (num in stack){
                answer = stack.size - stack.indexOf(num)
                break
            }
        }

        return answer
    }
}