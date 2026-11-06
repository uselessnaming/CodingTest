package 프로그래머스

// k는 글자 길이
// submit은 단어와 match 여부를 나타내는 정보
// 공백으로 구분되며 앞에는 입력, 뒤에는 결과
// N은 단어에 없음, M은 글자와 자리까지 맞음, I는 글자는 맞지만 자리는 맞지 않음
// words 내에서 이 결과에 맞는 답들만 골라서 추려내 return

class CodingTest1101_2 {
    fun solution(k: Int, words: Array<String>, submit: Array<String>): Array<String> {
        val banned = hashSetOf<Char>()
        val included = hashSetOf<Pair<Char, Int>>()
        val matched = CharArray(k) { '.' }

        for (s in submit) {
            val (target, res) = s.split(' ')

            for (idx in target.indices) {
                when (res[idx]) {
                    'N' -> {
                        banned.add(target[idx])
                    }

                    'M' -> {
                        matched[idx] = target[idx]
                    }

                    'I' -> {
                        included.add(target[idx] to idx)
                    }
                }
            }
        }

        val reg = Regex(matched.joinToString(""))

        return words.filter{ word -> word.all{ it !in banned } }
            .filter{ word -> reg.matches(word) }
            .filter{ word -> isAllIncluded(word, included) }
            .sorted()
            .toTypedArray()
    }

    private fun isAllIncluded(word: String, hashSet: HashSet<Pair<Char, Int>>): Boolean {
        for ((ch, idx) in hashSet) {
            if (ch !in word) return false
            if (word[idx] == ch) return false
        }
        return true
    }
}