package 프로그래머스

// 첫 번째 방법
// 윈도우 슬라이싱 방법으로 합을 구하여 확인하는 방법

const val SIZE = 10
class DiscountEvent {
    val items = mutableMapOf<String, Int>()
    var answer = 0
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        // window 알고리즘 하면 될듯
        // 10크기 고정하고 10칸 동안 들어있는 것을 확인
        // 초기에 10칸에 뭐가 들어있는지 확인
        init(want, number)
        firstDay(discount)
        println("0 $items")

        // 들어있는 것의 수량과 want의 수량과 동일한지 확인
        // 한 칸씩 밀면서 제일 앞의 항을 제외, 맨 뒤 항을 추가해서 계산
        for(i in 1 until discount.size - SIZE + 1){
            nextItems(discount[i-1], discount[i+SIZE-1])
            println(discount[i-1])
            println(discount[i+SIZE-1])
            println("$i $items")
        }

        return answer
    }

    fun init(want: Array<String>, number: IntArray) {
        for(i in want.indices){
            items[want[i]] = number[i]
        }
    }
    fun firstDay(discount: Array<String>){
        for(i in 0 until SIZE){
            if (items.contains(discount[i])) items[discount[i]] = items[discount[i]]!! - 1
        }
        if (items.values.count{it == 0} == items.keys.size) answer++
    }
    fun nextItems(yesterdayItem: String, todayItem: String) {
        if (items.containsKey(yesterdayItem)) items[yesterdayItem] = items[yesterdayItem]!! + 1
        if (items.containsKey(todayItem)) items[todayItem] = items[todayItem]!! - 1
        if(items.values.count{it == 0} == items.keys.size) answer++
    }
}

// 2번째 해결 방법
// 2개의 mutable map을 준비하고
// 실제 원하는 것과 현재의 물건들을 각각 세팅하여 동일할 때 count를 세는 방법

class DiscountEvent2 {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val wants = mutableMapOf<String, Int>()
        val real = mutableMapOf<String, Int>()

        for (i in 0 until want.size){
            wants[want[i]] = number[i]
        }
        for (i in 0..9){
            if (real[discount[i]] != null){
                real[discount[i]] = real[discount[i]]!! + 1
            } else {
                real[discount[i]] = 1
            }
        }
        var answer: Int = if(real == wants) 1 else 0

        for (i in 1 until discount.size - 9){
            if (real[discount[i-1]] != null) {
                if (real[discount[i-1]] == 1){
                    real.remove(discount[i-1])
                } else {
                    real[discount[i-1]] = real[discount[i-1]]!! - 1
                }
            }
            if (real[discount[i+9]] != null){
                real[discount[i+9]] = real[discount[i+9]]!! + 1
            } else {
                real[discount[i+9]] = 1
            }
            if (real == wants) answer++
        }

        return answer
    }
}

// 실질적으로 큰 시간 차이는 나지 않았음 >> for문의 중첩이 동일해서인 것으로 보임