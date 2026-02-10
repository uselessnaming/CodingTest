package 백준

class MaxValue {
    fun run() {
        var idx = 0
        var maxValue = 0

        repeat(9) { i ->
            val score = readln().trim().toInt()
            if (score > maxValue) {
                maxValue = score
                idx = i+1
            }
        }

        println(maxValue)
        println(idx)
    }
}