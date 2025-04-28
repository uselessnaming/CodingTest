package 백준

// 3020번 백준

// 2진 탐색 방법이 틀렸다
// 높이를 2진 탐색으로 하는 것이 아니라 높이는 완전탐색을 사용하고
// 높이에 해당하는 종유석 계산을 정렬과 이진탐색을 활용해서 하는 방법이다
// 이진 탐색에서 중요한 것은 start와 end를 뭘로 잡을지와 어떤 것에 이분탐색을 취해 시간을 줄일지, 어떤 조건에 따라 start와 end값을 수정할지

class FireFly {
    val hurdles = mutableListOf<Int>()
    var maxHeight = 0
    fun run(){
        val (n, h) = readln().split(" ").map{it.toInt()}
        repeat(n){ hurdles.add(readln().toInt()) }
        maxHeight = h
        val arr = hurdles.filterIndexed{ idx, value -> idx == 0 || idx % 2 == 0 }.sorted()
        val reversedArr = hurdles.filterIndexed{ idx, value -> idx % 2 != 0}.sorted()

        var res = 987654321
        var ans = 0
        for (height in 1..h){
            var forVal = n/2 - lowerBound(0, n/2, arr, height)
            var reverseVal = n/2 - lowerBound(0, n/2, reversedArr, h+1-height)
            if (res == forVal + reverseVal){
                ans++
            } else if (res > forVal + reverseVal){
                res = forVal + reverseVal
                ans = 1
            }
        }
        println("$res $ans")
    }

    fun lowerBound(start: Int, end: Int, arr: List<Int>, height: Int): Int{
        var s = start
        var e = end

        while(s < e){
            val mid = (s+e)/2
            if (arr[mid] >= height){
                e = mid
            } else {
                s = mid+1
            }
        }
        return e
    }
}

// 1 ~ H 까지의 범위 내 이분탐색을 진행하여 최솟값이 되는 지점을 찾는다
// 최솟값이 되는 지점을 찾으면 그것을 기반으로 몇 개가 있는지 확인