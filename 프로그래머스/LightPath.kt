package 프로그래머스

class LightPath {

    val startDx = arrayOf(0,-1,0,1)
    val startDy = arrayOf(-1,0,1,0)
    val directionCount = 4
    var rowCount: Int = 0
    var columnCount: Int = 0

    lateinit var visible: Array<Array<BooleanArray>>

    fun solution(grid: Array<String>): IntArray {
        rowCount = grid.size
        columnCount = grid[0].length
        visible = Array(rowCount){Array(columnCount){BooleanArray(directionCount){false} }}

        val answer = ArrayList<Int>()

        for(y in 0 until rowCount){
            for(x in 0 until columnCount){
                for(d in 0 until directionCount){
                    if(visible[y][x][d]) continue
                    answer.add(lightCycle(grid, y, x,d))
                }
            }
        }

        return answer.sorted().toIntArray()
    }

    fun lightCycle(grid: Array<String>, row: Int, col: Int, direction: Int): Int{
        var lightLength = 0
        var (y, x, d) = Triple(row, col, direction)
        while(!visible[y][x][d]){
            lightLength++
            visible[y][x][d] = true

            d = rotation(grid[y][x], d)
            y = (y + startDx[d] + rowCount) % rowCount
            x = (x + startDy[d] + columnCount) % columnCount
        }
        return lightLength
    }

    fun rotation(direction: Char, entryDirection: Int): Int{
        return when(direction){
            'L' -> (entryDirection + 3) % 4
            'R' -> (entryDirection + 1) % 4
            else -> entryDirection
        }
    }
}