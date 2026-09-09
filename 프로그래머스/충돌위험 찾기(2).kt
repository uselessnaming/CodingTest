package 프로그래머스

// 각 점들의 이동 방식 세로 좌표를 먼저 맞춘 후 가로 좌표를 맞춘다
// 점들의 이동 경로에 대해 숫자로 표기
// map을 MutableList로 해서 지나가는 경로에 대한 숫자를 표기
// 전체 map을 확인하고 map에 중복된 숫자가 있는 개수를 count

class FindCollisionRiskSecond {
    private val maxX = 101
    private val maxY = 101
    private val routeMap = List(maxX) { List(maxY) { mutableListOf<Int>() } }

    fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {

        routes.forEach { route -> writeRoute(points, route) }

        return routeMap.sumOf { row ->
            row.sumOf { col ->
                col.groupingBy{ it }
                    .eachCount()
                    .count { (_, cnt) -> cnt >= 2 }
            }
        }
    }

    private fun writeRoute(points: Array<IntArray>, route: IntArray) {
        val init = points[route[0] - 1]
        routeMap[init[0]][init[1]].add(0)
        var cnt = 1

        for (idx in 0 until route.size - 1) {
            val start = points[route[idx] - 1]
            val end = points[route[idx + 1] - 1]

            if (start[0] != end[0]) {
                val dx = if (start[0] < end[0]) 1 else -1
                var xx = start[0] + dx

                while(true) {
                    routeMap[xx][start[1]].add(cnt)
                    cnt++

                    if (xx == end[0]) break

                    xx += dx
                }
            }

            if (start[1] != end[1]) {
                val dy = if (start[1] < end[1]) 1 else -1
                var yy = start[1] + dy

                while(true) {
                    routeMap[end[0]][yy].add(cnt)
                    cnt++

                    if (yy == end[1]) break

                    yy += dy
                }
            }
        }
    }
}