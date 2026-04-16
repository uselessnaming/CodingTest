package 프로그래머스

import java.util.StringTokenizer

class MergeTable {
    companion object {
        const val SIZE = 2500;
        val parents = Array<Int>(SIZE + 1) { 0 }
        val values = Array<String>(SIZE + 1) { "" }
    }

    private fun init() {
        for (i in 1..SIZE) {
            parents[i] = i
            values[i] = ""
        }
    }

    private fun find(target: Int): Int {
        if (parents[target] == target) return target

        parents[target] = find(parents[target])
        return parents[target]
    }

    private fun union(a: Int, b: Int): Boolean {
        val rootA = find(a)
        val rootB = find(b)
        if (rootA == rootB) return false

        parents[rootB] = rootA
        return true
    }

    private fun getCvtIdx(r: Int, c: Int): Int {
        val res = 50 * (r - 1);
        return res + c;
    }

    fun solution(commands: Array<String>): Array<String> {
        init()
        val result = arrayListOf<String>()

        for (cmd in commands) {
            val st = StringTokenizer(cmd)
            centralProcess(st, result)
        }

        return result.toTypedArray()
    }

    private fun centralProcess(st: StringTokenizer, list: ArrayList<String>) {
        when (st.nextToken()) {
            "UPDATE" -> {
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

            "MERGE" -> {
                val r1 = st.nextToken().toInt()
                val c1 = st.nextToken().toInt()
                val r2 = st.nextToken().toInt()
                val c2 = st.nextToken().toInt()
                merge(r1, c1, r2, c2)
            }

            "UNMERGE" -> {
                val r = st.nextToken().toInt()
                val c = st.nextToken().toInt()
                unMerge(r, c)
            }

            "PRINT" -> {
                val r = st.nextToken().toInt()
                val c = st.nextToken().toInt()
                print(r, c, list)
            }
        }
    }

    private fun updateAll(value1: String, value2: String) {
        for (i in 1..SIZE) {
            if (value1 == values[i]) {
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
        val cIdx1 = getCvtIdx(r1, c1)
        val cIdx2 = getCvtIdx(r2, c2)
        val rootA = find(cIdx1)
        val rootB = find(cIdx2)
        if (rootA == rootB) return

        val rootValue = values[rootA].ifEmpty { values[rootB] }

        values[rootA] = ""
        values[rootB] = ""
        union(rootA, rootB)
        values[rootA] = rootValue
    }

    private fun unMerge(r: Int, c: Int) {
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

        for (idx in dList) {
            parents[idx] = idx
        }
    }

    private fun print(r: Int, c: Int, list: ArrayList<String>) {
        val cIdx = getCvtIdx(r, c)
        val root = find(cIdx)
        val value = values[root].ifEmpty { "EMPTY" }
        list.add(value)
    }
}