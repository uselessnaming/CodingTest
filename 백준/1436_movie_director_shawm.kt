package 백준

class MovieDirector {
    fun run() {
        val n = readln().toInt()
        val answer = getFinalNumber(repeater = n)
        println(answer)
    }

    private fun getFinalNumber(repeater: Int): Int {
        var cnt = repeater
        var num = 666

        if (repeater == 1) return 666

        while(true) {
            if (isFinalNumber(num)) cnt--
            if (cnt == 0) break

            num++
        }

        return num
    }

    private fun isFinalNumber(target: Int): Boolean {
        val txt = target.toString()
        return txt.contains("""666""")
    }
}