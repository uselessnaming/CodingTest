package 백준

// 백준 1895번 필터

class Filter {
    var data = mutableListOf<List<Int>>()
    val result = mutableListOf<Int>()
    fun run(){
        val (r, c) = readln().trim().split(" ").map{it.toInt()}

        //입력
        repeat(r){ data.add(readln().split(" ").map{it.trim().toInt()}) }

        //기준 값
        val min = readln().toInt()

        for(i in 0 .. r-3){
            for(j in 0..c-3){
                val l = listOf(data[i][j], data[i][j+1], data[i][j+2],
                    data[i+1][j], data[i+1][j+1], data[i+1][j+2],
                    data[i+2][j],  data[i+2][j+1], data[i+2][j+2]
                )
                result.add(l.sorted()[4])
            }
        }

        println(result.count{it > min})
    }
}