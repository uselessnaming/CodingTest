package 프로그래머스

const val SIZE = 10
class SkillTest4 {
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
        for(i in 1 until discount.size - SIZE){
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
            if (items.contains(discount[i])){
                items[discount[i]] = items[discount[i]]!! - 1
            }
        }
        if (items.values.sum() == 0){answer++}
    }
    fun nextItems(yesterdayItem: String, todayItem: String) {
        if (items.containsKey(yesterdayItem)){
            items[yesterdayItem] = items[yesterdayItem]!! + 1
        }
        if (items.containsKey(todayItem)){
            if(items[todayItem]!! > 0){
                items[todayItem] = items[todayItem]!! - 1
            }
        }
        if(items.values.sum() == 0) answer++
    }
}