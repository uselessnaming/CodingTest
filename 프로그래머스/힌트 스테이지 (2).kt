package 프로그래머스

import kotlin.collections.mutableListOf

class HintStageSecond {
    private var hintSet = mutableListOf<IntArray>()

    fun solution(cost: Array<IntArray>, hint: Array<IntArray>): Int {
        // 각 스테이지 별 힌트 구매 여부에 따라 살 수 있는 hint 셋 마련
        // hint 셋에 따라 비용 계산
        // 최소 값 찾기

        createHintSet(hint)

        return getMinHintValue(cost, hint, hint[0].size - 1)
    }

    private fun createHintSet(hint: Array<IntArray>) {
        val hintN = hint.size

        // hint 셋 생성 함수
        dfs(0, IntArray(hintN) { 0 }, hintN)
    }

    private fun dfs(currentStep: Int, currentSet: IntArray, limit: Int) {
        if (currentStep == limit) {
            hintSet.add(currentSet)
            return
        }

        dfs(currentStep + 1, currentSet.copyOf(), limit)
        currentSet[currentStep] = 1
        dfs(currentStep + 1, currentSet.copyOf(), limit)
    }

    private fun getMinHintValue(cost: Array<IntArray>, hint: Array<IntArray>, k: Int): Int {
        val n = cost.size
        var result = Int.MAX_VALUE

        // hint 셋 기반 계산 함수
        for (availableHints in hintSet) {
            var currentValue = cost[0][0]
            val availableHint = availableHints.withIndex()
                .filter { it.value == 1 }
                .map { hint[it.index] }

            val hints = IntArray(n) { 0 }

            for (ah in availableHint) {
                currentValue += ah[0]

                for (hintIdx in 1..k) {
                    val idx = ah[hintIdx] - 1
                    hints[idx] = (hints[idx] + 1).coerceAtMost(n - 1)
                }
            }

            for (step in 1 until cost.size) {
                currentValue += cost[step][hints[step]]
            }

            if (currentValue < result) {
                result = currentValue
            }
        }

        return result
    }
}