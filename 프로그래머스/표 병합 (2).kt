package 프로그래머스

import java.util.StringTokenizer

class MergeTableSecond {
    private val parents = IntArray(SIZE + 1) { 0 }
    private val values = Array<String>(SIZE + 1) { "" }
    private val answer = ArrayList<String>()

    init {
        for (i in 1..SIZE) {
            parents[i] = i
            values[i] = ""
        }
        answer.clear()
    }

    fun solution(commands: Array<String>): Array<String> {
        for (cmd in commands) {
            process(cmd)
        }

        return answer.toTypedArray()
    }

    private fun process(cmd: String) {
        val st = StringTokenizer(cmd)

        when (st.nextToken()) {
            Command.UPDATE.name -> {
                if (st.countTokens() == 2) {
                    val value1 = st.nextToken()
                    val value2 = st.nextToken()
                    updateAll(value1, value2)
                } else {
                    val r = st.nextToken().toInt()
                    val c = st.nextToken().toInt()
                    val value = st.nextToken()
                    updateCell(r, c, value)
                }
            }

            Command.MERGE.name -> {
                val r1 = st.nextToken().toInt()
                val c1 = st.nextToken().toInt()
                val r2 = st.nextToken().toInt()
                val c2 = st.nextToken().toInt()
                merge(r1, c1, r2, c2)
            }

            Command.UNMERGE.name -> {
                val r = st.nextToken().toInt()
                val c = st.nextToken().toInt()
                unmerge(r, c)
            }

            Command.PRINT.name -> {
                val r = st.nextToken().toInt()
                val c = st.nextToken().toInt()
                printCell(r, c)
            }
        }
    }

    private fun getCvtIdx(r: Int, c: Int): Int = ((r - 1) * MAX) + c

    private fun updateAll(value1: String, value2: String) {
        for (i in 1..SIZE) {
            if (values[i] == value1) {
                values[i] = value2
            }
        }
    }

    private fun updateCell(r: Int, c: Int, value: String) {
        val cIdx = getCvtIdx(r, c)
        val root = find(cIdx)
        values[root] = value
    }

    private fun merge(r1: Int, c1: Int, r2: Int, c2: Int) {
        val cIdxA = getCvtIdx(r1, c1)
        val cIdxB = getCvtIdx(r2, c2)
        val rootA = find(cIdxA)
        val rootB = find(cIdxB)
        if (rootA == rootB) return

        val rootValue = values[rootA].ifEmpty{ values[rootB] }

        values[rootA] = ""
        values[rootB] = ""
        union(cIdxA, cIdxB)
        values[rootA] = rootValue
    }

    private fun unmerge(r: Int, c: Int) {
        val cIdx = getCvtIdx(r, c)
        val root = find(cIdx)
        val rootValue = values[root]
        values[root] = ""
        values[cIdx] = rootValue

        val dList = arrayListOf<Int>()
        for (i in 1..SIZE) {
            if (find(i) == root) {
                dList.add(i)
            }
        }

        for (idx in dList) parents[idx] = idx
    }

    private fun printCell(r: Int, c: Int) {
        val cIdx = getCvtIdx(r, c)
        val root = find(cIdx)
        answer.add(values[root].ifEmpty { EMPTY })
    }

    private fun find(target: Int): Int {
        if (parents[target] == target) return target

        parents[target] = find(parents[target])
        return parents[target]
    }

    private fun union(a: Int, b: Int) {
        val rootA = find(a)
        val rootB = find(b)

        parents[rootB] = rootA
    }

    enum class Command {
        UPDATE, MERGE, UNMERGE, PRINT
    }

    companion object {
        const val MAX = 50
        const val SIZE = MAX * MAX
        const val EMPTY = "EMPTY"
    }
}