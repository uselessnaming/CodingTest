package 백준

class SumOfPermutation {
    private  var answers = mutableSetOf<List<Int>>()

    fun run() {
        val (n, s) = readln().split(" ").map { it.toInt() }
        val numbers = readln().split(" ").map { it.toInt() }
        getAnswers(numbers, s)
        printAnswer(s)
    }

    private fun getAnswers(numbers: List<Int>, target: Int) {
        for(length in 1..numbers.size) {
            println("------$length------")
            for(i in 0 .. numbers.size - length) {
                val subList = numbers.subList(i, i + length)
                println(subList)
                if (subList.sum() == target) answers.add(subList)
            }
        }
    }

    private fun printAnswer(target: Int) {
        var answer = 0
        answers.map{ if (it.sum() == target) answer++ }
        println(answer)
    }
}