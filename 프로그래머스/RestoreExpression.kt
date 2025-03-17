package 프로그래머스

class RestoreExpression {

    lateinit var expression: Array<String>
    val answer = mutableListOf<String>()

    val expressionInX = mutableListOf<String>()
    val expressionNotInX = mutableListOf<String>()
    var minN = 0
    val available = BooleanArray(10){true}

    fun solution(expressions: Array<String>): Array<String> {
        available[0] = false
        available[1] = false
        expression = expressions

        // 수식을 X가 포함된 미지정 수식과 모든게 보이는 복원 수식으로 분리
        seperateExpression()
        // 수식 전반에 존재하는 숫자 중 가장 큰 수를 N으로 하여 N+1 ~ 9 진법까지 for문
        getMinN()
        minN++

        for(i in 0 until minN){ available[i] = false }
        for(i in minN until 10){
            // 복원 수식으로 분리된 수식들에 n진법 계산을 통하여 정답과 일치하는 지 확인
            // 일치하지 않는 수식이 있다면 n진법은 불가능
            if(!isAvailableN(i)) available[i] = false
        }
        println(available.toList())

        // 가능한 n진법 리스트를 가지고 x가 포함된 미지정 수식에 값을 넣음
        getAnswer()

        // 넣은 값이 모두 같다면 해당 답을 x대신 채워서 수식 완성 후 answer에 추가
        // 값이 다르다면 x대신 ?를 채워서 수식을 완성 후 answer에 추가
        
        return answer.toTypedArray()
    }

    fun seperateExpression(){
        for(exp in expression){
            val tmp = exp.split(" ")
            if (tmp[4] == "X") {
                expressionInX.add(exp)
            } else {
                expressionNotInX.add(exp)
            }
        }
    }
    fun getMinN(){
        for(exp in expression){
            for(idx in exp.indices){
                if (exp[idx].isDigit()){
                    if (exp[idx].toString().toInt() > minN){
                        minN = exp[idx].toString().toInt()
                    }
                }
            }
        }
    }
    fun isAvailableN(n: Int): Boolean {
        for(exp in expressionNotInX){
            val temp = exp.split(" ")
            if (temp[1] == "+" && addInBaseN(temp[0], temp[2], n) == temp[4]){
                continue
            } else if (temp[1] == "-" && subtractInBaseN(temp[0], temp[2], n) == temp[4]){
                continue
            } else {
                return false
            }
        }
        return true
    }
    fun getAnswer(){
        val results = mutableListOf<MutableList<String>>()
        available.withIndex().filter{it.value}.map{
            val n = it.index
            val result = mutableListOf<String>()
            for(exp in expressionInX){
                val temp = exp.split(" ").map{it.trim()}
                val newExp = if (temp[1] == "+"){
                    exp.replace("X", addInBaseN(temp[0], temp[2], n))
                } else if (temp[1] == "-"){
                    exp.replace("X", subtractInBaseN(temp[0], temp[2], n))
                } else {
                    exp.replace("X", "?")
                }
                result.add(newExp)
            }
            results.add(result)
        }
        for(i in 0 until results[0].size){
            if(results.map{it[i]}.distinct().size == 1) {
                answer.add(results[0][i])
            } else {
                val exp = results[0][i].split(" ").toMutableList()
                exp[4] = "?"
                answer.add(exp.joinToString(" "))
            }
        }
        println(results)
    }

    fun addInBaseN(a: String, b: String, n: Int): String = (a.toInt(n) + b.toInt(n)).toString(n)
    fun subtractInBaseN(a: String, b: String, n: Int): String = (a.toInt(n) - b.toInt(n)).toString(n)
}