package 프로그래머스

class SearchText {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        val answer = IntArray(queries.size) { 0 }

        val trie = Trie()
        val reverseTrie = Trie()

        words.forEach { word ->
            trie.add(word, 0, 1)
            reverseTrie.add(word, word.length - 1, -1)
        }

        val dictionary = hashMapOf<String, Int>()
        queries.forEachIndexed { idx, q ->
            answer[idx] = dictionary[q] ?: run {
                val type = q.startsWith("?").not()
                val count = if (type) trie.count(q, 0) else reverseTrie.count(q.reversed(), 0)
                dictionary[q] = count
                count
            }
        }

        return answer
    }

    class Trie {
        private var count = 0
        private val children = hashMapOf<Char, Trie>()

        fun add(word: String, idx: Int, dir: Int) {
            if (idx < 0 || word.length == idx) {
                count = 1
                return
            }

            val cur = word[idx]
            if (cur !in children) children[cur] = Trie()
            children[cur]!!.add(word, idx + dir, dir)
        }

        fun count(query: String, idx: Int): Int {
            if (idx == query.length) return count

            if (children.isEmpty()) return 0
            if (query[idx] != '?') {
                if (query[idx] !in children) return 0
                return children[query[idx]]!!.count(query, idx + 1)
            }
            return children.values.sumOf { it.count(query, idx + 1) }
        }
    }
}