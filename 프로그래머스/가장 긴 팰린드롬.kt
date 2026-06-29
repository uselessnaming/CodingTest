package 프로그래머스

class LongestPalindrome {
    fun solution(s: String): Int {
        var answer = 1

        for (l in s.length downTo 1) {
            for (idx in 0 until s.length - l + 1) {
                if (isPalindrome(s, idx, l)) return l
            }
        }

        return answer
    }

    private fun isPalindrome(s: String, idx: Int, len: Int): Boolean {
        var left = idx
        var right = idx + len - 1

        while(left < right) {
            if (s[left] != s[right]) return false

            left++
            right--
        }

        return true
    }
}