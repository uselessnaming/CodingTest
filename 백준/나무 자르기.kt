package 백준

// 이분 탐색 시 크기 비교를 할 때 조건에 맞춰서 순서가 중요할 때도 있는 듯
// 이번 코딩에서는 순서가 중요함! if 문의 순서가 바뀔 경우 제대로 안될 수 있음

class CuttingTree{
    lateinit var trees: List<Int>
    fun run(){
        val (n, m) = readln().split(" ").map{it.toInt()}
        trees = readln().split(" ").map{it.toInt()}

        var start = 0
        var end = trees.max()

        while(start <= end){
            val mid = (start+end) / 2

            val myTrees = calTree(mid)

            if (myTrees >= m.toLong()){
                start = mid + 1
            } else {
                end = mid - 1
            }
        }

        println(end)
    }

    fun calTree(target: Int): Long = trees.filter{it > target}.map{(it - target).toLong()}.sum()
}