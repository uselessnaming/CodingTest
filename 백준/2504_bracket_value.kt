package 백준

class Bracket {

    val stack = mutableListOf<Char>()
    var result = 0
    var flag = false

    fun run() {
        val brackets = readln().trimIndent()

        if (isBracketAvailable(brackets)) {
            getValue(brackets)
            println(result)
        } else {
            println(0)
        }
    }

    private fun getValue(brackets: String) {
        for (bracket in brackets) {
            val cur = getBracketValue()

            if (bracket == '(' || bracket == '[') {
                stack.add(bracket)
                flag = true
            } else {
                if (bracket == ')' && stack.last() == '(') {
                    stack.removeLast()
                    if (flag) {
                        result += cur
                        flag = false
                    }
                } else if(bracket == ']' && stack.last() == '[') {
                    stack.removeLast()
                    if (flag) {
                        result += cur
                        flag = false
                    }
                }
            }
            println("bracket : ${bracket}")
            println("result : $result")
        }
    }

    private fun getBracketValue(): Int {
        return if (stack.isEmpty()) {
            0
        } else {
            var result = 1
            stack.forEach{
                if (it == '(') {
                    result *= 2
                } else {
                    result *= 3
                }
            }
            result
        }
    }

    private fun isBracketAvailable(brackets: String): Boolean {
        for (bracket in brackets) {
            if (bracket == '(' || bracket == '[') {
                stack.add(bracket)
            } else {
                if (stack.isEmpty()) return false

                if (bracket == ')' && stack.last() == '(') {
                    stack.removeLast()
                } else if(bracket == ']' && stack.last() == '[') {
                    stack.removeLast()
                } else {
                    return false
                }
            }
        }
        return stack.isEmpty()
    }
}