package 백준

//백준 3085번 사탕 게임
class CandyGame{
    var map = mutableListOf<String>()
    var n = 0
    var answer = 0

    fun run(){
        n = readln().toInt()
        repeat(n){ map.add(readln()) }

        for(i in 0 until n){
            for(j in 0 until n){
                //좌우 교환 후 확인
                swap(i, j, "row")
                eatCandy()
                //다시 제자리
                swap(i, j, "row")
                //상하 교환 후 확인
                swap(i, j, "col")
                eatCandy()
                //다시 제자리
                swap(i, j, "col")
            }
        }
        println(answer)
    }
    fun swap(x: Int, y: Int, type: String){
        if(type == "col"){
            if (x + 1 < n) {
                val temp = map[x][y]
                var m = map[x].toCharArray()
                m[y] = map[x+1][y]
                map[x] = m.joinToString("")
                m = map[x+1].toCharArray()
                m[y] = temp
                map[x+1] = m.joinToString("")
            } else {
                return
            }
        } else {
            if (y + 1 < n){
                val temp = map[x][y]
                val m = map[x].toCharArray()
                m[y] = map[x][y+1]
                m[y+1] = temp
                map[x] = m.joinToString("")
            } else {
                return
            }
        }
    }
    fun eatCandy(){
        var s = 1
        for(i in 0 until n){
            var bef = map[i][0]
            s = 1
            for(j in 1 until n){
                if (bef == map[i][j]){ s++ }
                else {
                    if (s > answer){ answer = s }
                    s = 1
                    bef = map[i][j]
                }
            }
            if (s > answer){ answer = s }
        }
        for(i in 0 until n){
            var bef = map[0][i]
            s = 1
            for(j in 1 until n){
                if (bef == map[j][i]){ s++ }
                else {
                    if (s > answer){ answer = s }
                    s = 1
                    bef = map[j][i]
                }
            }
            if (s > answer){ answer = s }
        }
    }
}