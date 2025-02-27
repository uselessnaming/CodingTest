package 백준

//백준 2309번 일곱 난쟁이
// 01번(9제곱) 반복을 통해 filter로 i,j항에 해당하는 값을 제외하고 더해서 100이 나오면 print
class SevenDwarfs {
    val heights = mutableListOf<Int>()
    fun run(){
        repeat(9){
            val height = readln().toInt()
            heights.add(height)
        }
        outer@for(i in 0 until 9){
            for(j in 0 until 9){
                if (i != j){
                    val res = exceptSum(i, j)
                    if (res == 100){
                        printHeights(i, j)
                        break@outer
                    }
                }
            }
        }
    }
    fun exceptSum(i: Int, j: Int): Int = heights.filter{it != heights[i] && it != heights[j]}.sum()

    fun printHeights(i: Int, j: Int){
        heights.filter{it != heights[i] && it != heights[j]}.sorted().map{
            println(it)
        }
    }
}