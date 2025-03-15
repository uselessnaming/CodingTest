package 프로그래머스

class FindCollisionRisk {
    private var R = 0
    private var C = 0
    private val pointMap = mutableMapOf<Int, Point>()

    data class Point(val x: Int, val y: Int)

    fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
        setBoardSize(points)
        setPointMap(points)

        var longestRouteDist = 0
        val shortestRoutes = mutableListOf<List<Point>>()

        for(route in routes){
            val shortestRoute = findShortestRoute(route)
            shortestRoutes.add(shortestRoute)
            longestRouteDist = maxOf(longestRouteDist, shortestRoute.size)
        }
        return findStrikeCount(shortestRoutes, longestRouteDist)
    }

    private fun setBoardSize(points: Array<IntArray>) {
        for(point in points){
            R = maxOf(R, point[0])
            C = maxOf(C, point[1])
        }
    }

    private fun setPointMap(points: Array<IntArray>) {
        for ((idx, point) in points.withIndex()){
            pointMap[idx+1] = Point(point[1], point[0])
        }
    }

    private fun findStrikeCount(shortestRoutes: List<List<Point>>, longestRouteDist: Int): Int {
        var totalCount = 0

        for(i in 0 until longestRouteDist){
            val countMap = mutableMapOf<Point, Int>()

            for (route in shortestRoutes){
                val point = route.getOrNull(i)
                if(point != null){ countMap[point] = countMap.getOrDefault(point, 0) + 1 }
            }

            totalCount += countMap.values.count{it > 1}
        }
        return totalCount
    }

    private fun findShortestRoute(routePoints: IntArray): List<Point> {
        val shortestRoute = mutableListOf<Point>()

        for(i in 1 until routePoints.size){
            val start = pointMap[routePoints[i-1]]!!
            val end = pointMap[routePoints[i]]!!
            var r = start.y
            var c = start.x

            if(shortestRoute.isEmpty()){
                shortestRoute.add(Point(r,c))
            }

            while(r != end.y){
                r += if (r>end.y) -1 else 1
                shortestRoute.add(Point(r,c))
            }
            while(c != end.x){
                c += if (c > end.x) -1 else 1
                shortestRoute.add(Point(r,c))
            }
        }
        return shortestRoute
    }
}