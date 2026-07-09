package 프로그래머스

class LockAndKey {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val offset = key.size - 1
        var keyClone = key

        for (i in 0 until lock.size * 2 - 1) {
            for (j in 0 until lock.size * 2 - 1) {
                repeat(4) {
                    val arr = Array(58) { IntArray(58) }
                    for (r in lock.indices) {
                        for (c in lock.indices) {
                            arr[r + offset][c + offset] = lock[r][c]
                        }
                    }
                    for (r in i until i + key.size) {
                        for (c in j until j + key.size) {
                            arr[r][c] += keyClone[r - i][c - j]
                        }
                    }
                    if (check(arr, lock, offset)) return true
                    keyClone = rotateKey(keyClone)
                }
            }
        }
        return false
    }

    private fun check(arr: Array<IntArray>, lock: Array<IntArray>, offset: Int): Boolean {
        for (i in lock.indices) {
            for (j in lock.indices) {
                if (arr[i + offset][j + offset] == 0 || arr[i + offset][j + offset] == 2) return false
            }
        }
        return true
    }

    private fun rotateKey(key: Array<IntArray>): Array<IntArray> {
        val rot = Array(key.size) { IntArray(key.size) }

        for(i in key.indices) {
            for(j in key.indices) {
                rot[i][j] = key[key.size - 1 - j][i]
            }
        }
        return rot
    }
}