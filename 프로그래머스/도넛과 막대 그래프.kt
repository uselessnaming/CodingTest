package 프로그래머스

class DonutAndGraph {
    private val nodeInfo = mutableMapOf<Int, NodeInfo>()

    fun solution(edges: Array<IntArray>): IntArray {
        for ((start, end) in edges) {
            if (nodeInfo[start] == null) {
                nodeInfo[start] = NodeInfo(start, 0, 1)
            } else {
                nodeInfo[start]!!.nodeOut++
            }

            if (nodeInfo[end] == null) {
                nodeInfo[end] = NodeInfo(end, 1, 0)
            } else {
                nodeInfo[end]!!.nodeIn++
            }
        }

        val startPoint = nodeInfo.filter { it.value.nodeIn == 0 }.values.maxByOrNull { it.nodeOut }!!.value
        val stick = nodeInfo.filter { it.value.nodeOut == 0 }.size
        val eight = nodeInfo.filter { it.value.nodeIn >= 2 && it.value.nodeOut == 2 }.size
        val donut = nodeInfo[startPoint]!!.nodeOut - stick - eight

        return intArrayOf(startPoint, donut, stick, eight)
    }

    data class NodeInfo(
        val value: Int,
        var nodeIn: Int,
        var nodeOut: Int
    )
}