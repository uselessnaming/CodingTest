package 프로그래머스

// k는 글자 길이
// submit은 단어와 match 여부를 나타내는 정보
// 공백으로 구분되며 앞에는 입력, 뒤에는 결과
// N은 단어에 없음, M은 글자와 자리까지 맞음, I는 글자는 맞지만 자리는 맞지 않음
// words 내에서 이 결과에 맞는 답들만 골라서 추려내 return

class CodingTest1101_2 {
    fun solution(k: Int, words: Array<String>, submit: Array<String>): Array<String> {
        val none = Array<MutableSet<Char>>(k) { mutableSetOf() }
        val mustHave = mutableSetOf<Char>()

        for (s in submit) {
            val (target, res) = s.split(' ')

            for (idx in target.indices) {
                when (res[idx]) {
                    'N' -> {
                        none.forEach { n -> n.add(target[idx]) }
                    }

                    'M' -> {
                        none[idx].addAll(alpha)
                        none[idx].remove(target[idx])
                    }

                    'I' -> {
                        none[idx].add(target[idx])
                        mustHave.add(target[idx])
                    }
                }
            }
        }

        for (idx in 0 until k) {
            if (none[idx].size == alpha.size - 1) {
                val ch = none[idx].first()

                if (ch in mustHave) {
                    mustHave.remove(ch)
                }
            }
        }

        return words.filter { word -> isAvailable(word, none) && mustHave.all { it in word } }.toTypedArray()
    }

    private fun isAvailable(word: String, none: Array<MutableSet<Char>>): Boolean {
        return word.withIndex().all { (idx, ch) ->
            ch !in none[idx]
        }
    }

    companion object {
        val alpha = mutableSetOf<Char>(
            'A', 'B', 'C',
            'D', 'E', 'F',
            'G', 'H', 'I',
            'J', 'K', 'L',
            'M', 'N', 'O',
            'P', 'Q', 'R',
            'S', 'T', 'U',
            'V', 'W', 'X',
            'Y', 'Z'
        )
    }
}