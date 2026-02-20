package 백준

class Slope {
    private val map = mutableListOf<List<Int>>()
    private var result = 0
    private lateinit var isBuilt: MutableList<Boolean>

    fun run() {
        val (n, l) = readln().split(" ").map { it.toInt() }
        repeat(n) {
            val line = readln().split(" ").map { it.toInt() }
            map.add(line)
        }
        initBuilt()

        countAvailableRoute(l)
        println(result)
    }

    private fun countAvailableRoute(l: Int) {
        map.forEachIndexed { idx, value ->
            println("-----------------------")
            println("step : $idx / result : $result")
            val targetRow = map[idx]
            initBuilt()
            if (isAvailable(targetRow, l) && isAvailableReversed(targetRow, l)) {
                println("---------ROW----------")
                println(targetRow)
                result++
            }

            initBuilt()
            val targetColumn = map.map { it[idx] }
            if (isAvailable(targetColumn, l) && isAvailableReversed(targetColumn, l)) {
                println("---------COLUMN----------")
                println(targetColumn)
                result++
            }
        }
    }

    private fun isAvailable(target: List<Int>, l: Int): Boolean {
        var bef = target.first()
        target.forEachIndexed { idx, value ->
            if (value > bef) {
                println("is built : $isBuilt")
                if (idx + l >= target.size) return false
                if (target.subList(idx, idx + l).any { it != bef + 1 }) return false
                if (isBuilt.subList(idx, idx + l).any { it }) return false

                println("success value : $target")
                repeat(l) {
                    isBuilt[it + l] = true
                }
                bef = value
            }
        }

        return true
    }

    private fun isAvailableReversed(target: List<Int>, l: Int): Boolean {
        var bef = target.last()
        for (idx in target.size - 1 downTo 0) {
            val value = target[idx]
            if (value > bef) {
                println("is built : $isBuilt")
                if (idx - l < 0) return false
                if (target.subList(idx - l + 1, idx + 1).any { it != bef - 1 }) return false
                if (isBuilt.subList(idx - l + 1, idx + 1).any { it }) return false

                println("reversed success value : $target")
                repeat(l - 1) {
                    isBuilt[idx - l] = true
                }
                bef = value
            }
        }

        return true
    }

    private fun initBuilt() {
        isBuilt = MutableList(map.size) { false }
    }
}