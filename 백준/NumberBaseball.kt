package 백준
//2503번

class NumberBaseball {
    fun run() {
        var cnt = 0

        val n = readln().toInt()
        val baseballs = mutableListOf<Baseball>()
        repeat(n) {
            val (num, strike, ball) = readLine()!!.split(" ").map { it.toInt() }
            baseballs.add(Baseball(num.toString(), strike, ball))
        }

        outer@for (i in 123..987) {
            val num = i.toString()
            if (num.length != num.toSet().size || num.contains('0')) continue

            for (j in 0 until baseballs.size) {
                var strike = 0
                var ball = 0

                for (k in 0 until 3) {
                    if (num[k] == baseballs[j].num[k]) strike++
                    else if (num[k] in baseballs[j].num) ball++
                }

                if (strike != baseballs[j].strike || ball != baseballs[j].ball) continue@outer
            }
            cnt++
        }
        println(cnt)
    }
}

data class Baseball(val num: String, val strike: Int, val ball: Int)