package 프로그래머스

class MatchingScore {
    private lateinit var baseScore: IntArray
    private lateinit var externalLinks: Array<MutableList<String>>
    private lateinit var pageUrls: Array<String>

    fun solution(word: String, pages: Array<String>): Int {
        baseScore = IntArray(pages.size)
        externalLinks = Array(pages.size) { mutableListOf() }
        pageUrls = Array(pages.size) { "" }

        extractUrls(pages)
        calculateBaseScore(word.lowercase(), pages)
        extractsLinks(pages)

        return calculateMatchingScore()
    }

    private fun extractUrls(pages: Array<String>) {
        val urlRegex = Regex("""<meta property="og:url" content="(.*?)"/>""")

        pages.forEachIndexed { idx, page ->
            pageUrls[idx] = urlRegex.find(page)!!.groupValues[1]
        }
    }

    private fun calculateBaseScore(word: String, pages: Array<String>) {
        val bodyRegex = Regex("""<body>(.*?)</body>""", RegexOption.DOT_MATCHES_ALL)
        val wordRegex = Regex("""(?<![a-z])${Regex.escape(word)}(?![a-z])""")

        pages.forEachIndexed { idx, page ->
            val body = bodyRegex.find(page)!!.groupValues[1].lowercase()
            val plainText = body.replace(Regex("""<[^>]+>"""), " ")

            baseScore[idx] = wordRegex.findAll(plainText).count()
        }
    }

    private fun extractsLinks(pages: Array<String>) {
        val linkRegex = Regex("""<a href="(.*?)">""")

        pages.forEachIndexed { idx, page ->
            externalLinks[idx].addAll(linkRegex.findAll(page).map { it.groupValues[1] })
        }
    }

    private fun calculateMatchingScore(): Int {
        val scores = baseScore.map { it.toDouble() }.toDoubleArray()
        val urlMap = pageUrls.withIndex().associate { it.value to it.index }

        for (idx in pageUrls.indices) {
            if (externalLinks[idx].isEmpty()) continue

            val linkScore = baseScore[idx].toDouble() / externalLinks[idx].size

            for (link in externalLinks[idx]) {
                val targetIdx = urlMap[link] ?: continue
                scores[targetIdx] += linkScore
            }
        }

        var answer = 0
        var maxScore = scores[0]

        for (idx in scores.indices) {
            if (scores[idx] > maxScore) {
                maxScore = scores[idx]
                answer = idx
            }
        }

        return answer
    }
}