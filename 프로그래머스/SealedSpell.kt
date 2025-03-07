package 프로그래머스

// 정렬을 통해서 시간 초과를 압축시킬 수 있음
// 나보다 앞에 있는 문자열을 확인하기 전 sort를 하면 반복문의 수를 1번으로 줄일 수 있음
import kotlin.math.pow

class SealedSpell {
    val alpha = mapOf<String, Int>(
        "a" to 1, "b" to 2, "c" to 3, "d" to 4,
        "e" to 5, "f" to 6, "g" to 7, "h" to 8,
        "i" to 9, "j" to 10, "k" to 11, "l" to 12,
        "m" to 13, "n" to 14, "o" to 15, "p" to 16,
        "q" to 17, "r" to 18, "s" to 19, "t" to 20,
        "u" to 21, "v" to 22, "w" to 23, "x" to 24,
        "y" to 25, "z" to 26
    )
    fun solution(n: Long, bans: Array<String>): String {
        var target = n
        val banScore = Array(bans.size){0L}
        for(idx in bans.indices){ banScore[idx] = getScore(bans[idx]) }
        banScore.sort()

        for (idx in banScore.indices) {
            if (target >= banScore[idx]) { target++ }
        }

        return getString(target)
    }

    fun getScore(ban: String): Long {
        var result = 0L
        val reversedBan = ban.reversed()
        for(idx in reversedBan.indices){
            result += 26.0.pow(idx).toLong() * alpha[reversedBan[idx].toString()]!!.toLong()
        }
        return result
    }

    fun getString(target: Long): String{
        var cur = target
        var result = ""
        while(cur > 0){
            cur--
            val r = (cur % 26).toInt()
            result = ('a'+r).toString() + result
            cur /= 26
        }
        return result
    }
}