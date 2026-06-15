package 프로그래머스

import java.util.TreeMap

class SquareArray {
    fun solution(arr: IntArray, l: Long, r: Long): LongArray {
        val n = arr.size

        val lenPrefix = LongArray(n + 1)
        val sumPrefix = LongArray(n + 1)

        for (i in arr.indices) {
            lenPrefix[i + 1] = lenPrefix[i] + arr[i].toLong()
            sumPrefix[i + 1] = sumPrefix[i] + arr[i].toLong() * arr[i]
        }

        val windowLength = r - l + 1
        val totalLength = lenPrefix[n]
        val k = rangeSum(n, l, r, arr, lenPrefix, sumPrefix)
        val startCount = totalLength - windowLength + 1

        if (startCount == 1L) return longArrayOf(k, 1L)

        val events = TreeMap<Long, Long>()
        var boundary = 0L

        for (i in 0 until n - 1) {
            boundary += arr[i].toLong()

            val prev = arr[i].toLong()
            val next = arr[i + 1].toLong()

            if (boundary in 1 until startCount) {
                events[boundary] = (events[boundary] ?: 0L) + (prev - next)
            }

            val enterBoundary = boundary - windowLength

            if (enterBoundary in 1 until startCount) {
                events[enterBoundary] = (events[enterBoundary] ?: 0L) + (next - prev)
            }
        }

        var currentSum = rangeSum(n, 1, windowLength, arr, lenPrefix, sumPrefix)
        var diff = valueAt(n, windowLength + 1, arr, lenPrefix) - valueAt(n, 1, arr, lenPrefix)
        var answerCount = 0L
        var currentPos = 1L

        for ((eventPos, delta) in events) {
            val endDiffPos = eventPos - currentPos + 1

            answerCount += countAp(currentSum, diff, endDiffPos, k)
            currentSum += endDiffPos * diff
            diff += delta
            currentPos = eventPos + 1
        }

        val remainingDiffCount = startCount - currentPos + 1

        answerCount += countAp(currentSum, diff, remainingDiffCount, k)
        return longArrayOf(k, answerCount)
    }

    private fun valueAt(n: Int, pos: Long, arr: IntArray, lenPrefix: LongArray): Long {
        var left = 0
        var right = n - 1

        while (left < right) {
            val mid = (left + right) ushr 1

            if (lenPrefix[mid + 1] >= pos) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return arr[left].toLong()
    }

    private fun prefixSum(n: Int, pos: Long, arr: IntArray, lenPrefix: LongArray, sumPrefix: LongArray): Long {
        if (pos <= 0L) return 0L

        var left = 0
        var right = n

        while (left < right) {
            val mid = (left + right) ushr 1

            if (lenPrefix[mid + 1] >= pos) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        val idx = left

        return sumPrefix[idx] + (pos - lenPrefix[idx]) * arr[idx].toLong()
    }

    private fun rangeSum(
        n: Int,
        start: Long,
        end: Long,
        arr: IntArray,
        lenPrefix: LongArray,
        sumPrefix: LongArray
    ): Long {
        return prefixSum(n, end, arr, lenPrefix, sumPrefix) - prefixSum(n, start - 1, arr, lenPrefix, sumPrefix)
    }

    private fun countAp(first: Long, diff: Long, count: Long, target: Long): Long {
        if (count <= 0) return 0L
        if (diff == 0L) return if (first == target) count else 0L

        val delta = target - first

        if (delta % diff != 0L) return 0L

        val idx = delta / diff

        return if (idx in 0 until count) 1L else 0L
    }
}