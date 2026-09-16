package 프로그래머스

import java.util.TreeMap

class SquareArraySecond {
    fun solution(arr: IntArray, l: Long, r: Long): LongArray {
        val n = arr.size
        val lenPrefix = LongArray(n + 1)
        val sumPrefix = LongArray(n + 1)

        for (idx in 1 .. n) {
            lenPrefix[idx] = lenPrefix[idx - 1] + arr[idx - 1]
            sumPrefix[idx] = sumPrefix[idx - 1] + (arr[idx - 1] * arr[idx - 1]).toLong()
        }

        val k = rangeSum(lenPrefix, sumPrefix, arr, r) - rangeSum(lenPrefix, sumPrefix, arr, l - 1)
        val windowLength = r - l + 1
        val totalLength = lenPrefix.last()
        val lastStart = totalLength - windowLength + 1

        if (lastStart == 1L) return longArrayOf(k, 1L)

        val boundaries = TreeMap<Long, Long>()

        for (i in 0 until n - 1) {
            val outBoundary = lenPrefix[i + 1]

            val prev = arr[i].toLong()
            val next = arr[i + 1].toLong()

            if (outBoundary in 1 until lastStart) {
                boundaries[outBoundary] = (boundaries[outBoundary] ?: 0L) + (prev - next)
            }

            val enterBoundary = outBoundary - windowLength

            if (enterBoundary in 1 until lastStart) {
                boundaries[enterBoundary] = (boundaries[enterBoundary] ?: 0L) + (next - prev)
            }
        }

        var currentSum = rangeSum(lenPrefix, sumPrefix, arr, windowLength)
        var diff = valueAt(n, windowLength + 1, arr, lenPrefix) - valueAt(n, 1, arr, lenPrefix)
        var answerCount = 0L
        var currentPos = 1L

        for ((boundaryPos, delta) in boundaries) {
            val endDiffPos = boundaryPos - currentPos + 1

            answerCount += countAp(currentSum, diff, endDiffPos, k)
            currentSum += endDiffPos * diff
            diff += delta
            currentPos = boundaryPos + 1
        }

        val remainingDiffCount = lastStart - currentPos + 1

        answerCount += countAp(currentSum, diff, remainingDiffCount, k)
        return longArrayOf(k, answerCount)
    }

    private fun rangeSum(lenPrefix: LongArray, sumPrefix: LongArray, arr: IntArray, end: Long): Long {
        if (end <= 0L) return 0L

        var left = 0
        var right = arr.size

        while(left < right) {
            val mid = (left + right) / 2

            if (lenPrefix[mid + 1] >= end) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return sumPrefix[left] + (arr[left] * (end - lenPrefix[left]))
    }

    private fun valueAt(n: Int, pos: Long, arr: IntArray, lenPrefix: LongArray): Long {
        if (pos <= 0L) return 0L

        var left = 0
        var right = n

        while (left < right) {
            val mid = (left + right) /2

            if (lenPrefix[mid + 1] >= pos) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return arr[left].toLong()
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