package 백준

class Scale {
    fun run() {
        val scaleMap = mapOf(
            1 to 'c',
            2 to 'd',
            3 to 'e',
            4 to 'f',
            5 to 'g',
            6 to 'a',
            7 to 'b',
            8 to 'C'
        )

        val scale = readlnOrNull()?.split(' ')?.map {
            scaleMap[it.toInt()]
        }?.joinToString("")

        val answer = when(scale) {
            "cdefgabC" -> "ascending"
            "Cbagfedc" -> "descending"
            else -> "mixed"
        }
        println(answer)
    }
}