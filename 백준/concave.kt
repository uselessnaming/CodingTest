package 백준

// 백준 2615번 오목
const val SIZE = 19
class Concave {
    val map = Array(SIZE){IntArray(SIZE){0} }

    fun run() {
        //입력
        for (i in 0 until SIZE) { map[i] = readln().split(" ").map { it.toInt() }.toIntArray() }

        // map 전체에서 가로 열 연속 값이 있는 지 확인
        for(i in 0 until SIZE){
            val values = map[i].toList()

            for(j in 0 until SIZE-4){
                val sublist = values.subList(j, j+5)
                if (sublist.filter{it == 1}.count() == 5 && check(i, j, "right", values.size)){
                    println(1)
                    println("${i+1} ${j+1}")
                    return
                } else if (sublist.filter{it == 2}.count() == 5 && check(i, j, "right", values.size)){
                    println(2)
                    println("${i+1} ${j+1}")
                    return
                }
            }
        }

        // map 전체에서 세로 열 연속 값이 있는 지 확인
        for(i in 0 until SIZE){
            val values = map.map{it[i]}

            for(j in 0 until SIZE-4){
                val sublist = values.subList(j, j+5)
                if (sublist.filter{it == 1}.count() == 5 && check(j, i, "down", values.size)){
                    println(1)
                    println("${j+1} ${i+1}")
                    return
                } else if (sublist.filter{it == 2}.count() == 5 && check(j, i, "down", values.size)){
                    println(2)
                    println("${j+1} ${i+1}")
                    return
                }
            }
        }

        // map 전체에서 우측 하단 대각선 연속 값이 있는지 확인
        for(i in 1 until SIZE){
            var x = i
            var y = 0
            val values = mutableListOf<Int>()
            while(x < SIZE && y < SIZE){
                values.add(map[x][y])
                x++
                y++
            }
            for(idx in 0 until values.size - 4){
                val sublist = values.subList(idx, idx+5)
                if (sublist.filter{it == 1}.count() == 5 && check(i+idx, idx, "rightDown", values.size)){
                    println(1)
                    println("${i+idx+1} ${idx + 1}")
                    return
                }
                else if (sublist.filter{it == 2}.count() == 5 && check(i+idx, idx, "rightDown", values.size)){
                    println(2)
                    println("${i+idx+1} ${idx+1}")
                    return
                }
            }
        }
        for(i in 0 until SIZE-1){
            var x = 0
            var y = i
            val values = mutableListOf<Int>()
            while(x < SIZE && y < SIZE){
                values.add(map[x][y])
                x++
                y++
            }
            for(idx in 0 until values.size - 4){
                val sublist = values.subList(idx, idx+5)
                if (sublist.filter{it == 1}.count() == 5 && check(i+idx, idx, "rightDown", values.size)){
                    println(1)
                    println("${idx+1} ${i+idx+1}")
                    return
                }
                else if (sublist.filter{it == 2}.count() == 5 && check(i+idx, idx, "rightDown", values.size)){
                    println(2)
                    println("${idx+1} ${i+idx+1}")
                    return
                }
            }
        }

        // map 전체에서 우측 상단 대각선 연속 값이 있는지 확인
        for(i in 0 until SIZE){
            var x = i
            var y = 0
            val values = mutableListOf<Int>()
            while(x > -1 && y < SIZE){
                values.add(map[x][y])
                x--
                y++
            }
            for(idx in 0 until values.size - 4){
                val sublist = values.subList(idx, idx+5)
                if (sublist.filter{it == 1}.count() == 5 && check(i-idx, idx, "rightUp", values.size)){
                    println(1)
                    println("${i-idx+1} ${idx + 1}")
                    return
                }
                else if (sublist.filter{it == 2}.count() == 5 && check(i-idx, idx, "rightUp", values.size)){
                    println(2)
                    println("${i-idx+1} ${idx + 1}")
                    return
                }
            }
        }
        for(i in 0 until SIZE){
            var x = SIZE-1
            var y = i
            val values = mutableListOf<Int>()
            while(x > -1 && y < SIZE){
                values.add(map[x][y])
                x--
                y++
            }
            for(idx in 0 until values.size - 4){
                val sublist = values.subList(idx, idx+5)
                if (sublist.filter{it == 1}.count() == 5 && check(SIZE-idx-1, i+idx, "rightUp", values.size)){
                    println(1)
                    println("${SIZE-idx} ${i + idx + 1}")
                    return
                }
                else if (sublist.filter{it == 2}.count() == 5 && check(SIZE-idx-1, i+idx, "rightUp", values.size)){
                    println(2)
                    println("${SIZE-idx} ${i+idx + 1}")
                    return
                }
            }
        }

        println(0)
    }

    fun check(i: Int, j: Int, type: String, size: Int): Boolean{
        return if(size == 5){ true }
        else if (size <= 4) {
            false
        } else {
            when (type) {
                "down" -> {
                    if (i == 0) {
                        map[i + 5][j] != map[i][j]
                    } else if (i == SIZE - 5) {
                        map[i - 1][j] != map[i][j]
                    } else {
                        map[i + 5][j] != map[i][j] && map[i - 1][j] != map[i][j]
                    }
                }
                "right" -> {
                    if (j == 0) {
                        map[i][j + 5] != map[i][j]
                    } else if (j == SIZE - 5) {
                        map[i][j - 1] != map[i][j]
                    } else {
                        map[i][j + 5] != map[i][j] && map[i][j - 1] != map[i][j]
                    }
                }
                "rightUp" -> {
                    if (j == 0 || i == SIZE - 1) {
                        map[i - 5][j + 5] != map[i][j]
                    } else if (i == 4 || j == SIZE - 5) {
                        map[i + 1][j - 1] != map[i][j]
                    } else {
                        map[i - 5][j + 5] != map[i][j] && map[i + 1][j - 1] != map[i][j]
                    }
                }

                else -> {
                    if (j == 0 || i == 0) {
                        map[i + 5][j + 5] != map[i][j]
                    } else if (i == SIZE - 5 || j == SIZE - 5) {
                        map[i - 1][j - 1] != map[i][j]
                    } else {
                        map[i + 5][j + 5] != map[i][j] && map[i - 1][j - 1] != map[i][j]
                    }
                }
            }
        }
    }
}