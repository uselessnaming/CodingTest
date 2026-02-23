package 백준

class BlackJack {
    private lateinit var numbers: List<Int>
    private var answer = 0

    fun run() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        numbers = readln().split(" ").map { it.toInt() }
        checkScore(m)
        println(answer)
    }

    private fun checkScore(target: Int) {
        for (i in numbers.indices) {
            for (j in i + 1 until numbers.size) {
                for (k in j + 1 until numbers.size) {
                    val score = numbers[i] + numbers[j] + numbers[k]
                    if (score in (answer + 1)..target) {
                        println("${numbers[i]} + ${numbers[j]} + ${numbers[k]} = $score / $target")
                        answer = score
                    }
                    if (score == target) return
                }
            }
        }
    }
}