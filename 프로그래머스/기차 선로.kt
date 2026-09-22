package 프로그래머스

class TrainTile {
    private val DR = intArrayOf(-1, 0, 1, 0)
    private val DC = intArrayOf(0, 1, 0, -1)
    private fun opposite(d: Int) = (d + 2) % 4

    private val portMask = intArrayOf(
        0,
        (1 shl 1) or (1 shl 3),
        (1 shl 0) or (1 shl 2),
        15,
        (1 shl 0) or (1 shl 3),
        (1 shl 0) or (1 shl 1),
        (1 shl 2) or (1 shl 1),
        (1 shl 2) or (1 shl 3)
    )

    private lateinit var grid: Array<IntArray>
    private lateinit var work: IntArray
    private var n = 0
    private var m = 0
    private lateinit var requiredCells: IntArray
    private val crossUsed = HashMap<Int, Int>()
    private var count = 0

    fun solution(grid: Array<IntArray>): Int {
        this.grid = grid
        n = grid.size
        m = grid[0].size

        work = IntArray(n * m)
        for (r in 0 until n) {
            for (c in 0 until m) {
                work[r * m + c] = grid[r][c]
            }
        }

        val req = ArrayList<Int>()
        for (r in 0 until n) {
            for (c in 0 until m) {
                if (grid[r][c] != 0 && grid[r][c] != -1) req.add(r * m + c)
            }
        }
        requiredCells = req.toIntArray()

        count = 0
        dfs(0, 0, 1, 0, HashSet())
        return count
    }

    private fun otherPort(mask: Int, entry: Int): Int {
        val rest = mask and (1 shl entry).inv()
        return Integer.numberOfTrailingZeros(rest)
    }

    private fun dfs(r: Int, c: Int, dir: Int, visitedMask: Int, seenSinceDecision: HashSet<Int>) {
        val key = r * m + c

        if (r == n - 1 && c == m - 1) {
            val entry = opposite(dir)
            val destPorts = portMask[grid[r][c]]
            if ((destPorts shr entry) and 1 == 0) return

            val finalMask = visitedMask or (1 shl key)

            for (cell in requiredCells) {
                if ((finalMask shr cell) and 1 == 0) return
            }

            for ((_, dirs) in crossUsed) {
                if (Integer.bitCount(dirs) < 4) return
            }

            count++
            return
        }

        val entry = opposite(dir)

        if (grid[r][c] == 0 && work[key] == 0) {
            for (t in 1..7) {
                val pmask = portMask[t]
                if ((pmask shr entry) and 1 == 0) continue

                val exitDir = if (t == 3) dir else otherPort(pmask, entry)
                val nr = r + DR[exitDir]
                val nc = c + DC[exitDir]
                if (nr !in 0 until n || nc !in 0 until m) continue
                if (grid[nr][nc] == -1) continue

                work[key] = t
                var addedCross = false
                if (t == 3) {
                    crossUsed[key] = (1 shl entry) or (1 shl exitDir)
                    addedCross = true
                }

                dfs(nr, nc, exitDir, visitedMask or (1 shl key), HashSet())

                work[key] = 0
                if (addedCross) crossUsed.remove(key)
            }

            return
        }

        val cellType = work[key]
        val pmask = portMask[cellType]

        if ((pmask shr entry) and 1 == 0) return

        val exitDir = if (cellType == 3) dir else otherPort(pmask, entry)
        val state = key * 4 + dir

        if (seenSinceDecision.contains(state)) return

        val newSeen = HashSet(seenSinceDecision)
        newSeen.add(state)

        val nr = r + DR[exitDir]
        val nc = c + DC[exitDir]

        if (nr !in 0 until n || nc !in 0 until m) return
        if (grid[nr][nc] == -1) return

        var addedCross = false
        var prevMask = 0
        if (cellType == 3) {
            prevMask = crossUsed.getOrDefault(key, 0)
            crossUsed[key] = prevMask or (1 shl entry) or (1 shl exitDir)
            addedCross = true
        }

        dfs(nr, nc, exitDir, visitedMask or (1 shl key), newSeen)

        if (addedCross) {
            if (prevMask == 0) crossUsed.remove(key) else crossUsed[key] = prevMask
        }
    }
}