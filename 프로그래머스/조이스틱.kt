package 프로그래머스

class JoyStick {
    private val map = mapOf(
        'A' to 0,
        'B' to 1,
        'C' to 2,
        'D' to 3,
        'E' to 4,
        'F' to 5,
        'G' to 6,
        'H' to 7,
        'I' to 8,
        'J' to 9,
        'K' to 10,
        'L' to 11,
        'M' to 12,
        'N' to 13,
        'O' to 12,
        'P' to 11,
        'Q' to 10,
        'R' to 9,
        'S' to 8,
        'T' to 7,
        'U' to 6,
        'V' to 5,
        'W' to 4,
        'X' to 3,
        'Y' to 2,
        'Z' to 1,
    )

    fun solution(name: String): Int {
        var answer = 0

        for (ch in name) {
            answer += map[ch]!!
        }

        var move = name.length - 1

        for (i in name.indices) {
            var next = i + 1

            while (next < name.length && name[next] == 'A') {
                next++
            }

            move = minOf(move, i * 2 + (name.length - next), i + (name.length - next) * 2)
        }

        return answer + move
    }
}