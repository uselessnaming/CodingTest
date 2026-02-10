class PrintStar {
    fun run() {
        val n = readln().trim().toInt()
        var line = ""

        repeat(n * 2) { i ->
            line = ""
            repeat(n) { j ->
                val i = i + 1
                val j = j + 1

                if (i % 2 == 1) {
                    if (j % 2 == 1) {
                        line += "*"
                    } else {
                        line += " "
                    }
                } else {
                    if (j % 2 == 0) {
                        line += "*"
                    } else {
                        line += " "
                    }
                }
            }
            println(line)
        }
    }
}