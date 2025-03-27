package 백준

import java.util.*
import kotlin.math.max
import kotlin.math.min

// 전형적인 그리디 알고리즘
// 정렬 순서가 잘못되었음
// 보내는 마을, 받는 마을, 양 순으로 정렬하지 말고 받는 마을 오름차순으로 정렬해야 함
// 각 마을의 보낼 수 있는 최대치를 list로 만들고
// 순서대로 마을마다 보낼 경우 변동되는 최적을 list에 감산함
// 최종적으로 list에 감소한 값만큼 배송이 가능하다는 것을 알 수 있음

class Delivery {
    fun run (){
        val deliveries = mutableListOf<Shipment>()
        var result = 0

        // 입력
        val (n, stackLimit) = readln().split(" ").map{it.toInt()}
        val k = readln().toInt()
        val maxStack = MutableList<Int>(n){stackLimit}
        repeat(k){
            val temp = readln().split(" ").map{it.toInt()}
            deliveries.add(Shipment(temp[0], temp[1], temp[2]))
        }

        // 택배 정보 정렬 받는마을 >> 보내는마을
        deliveries.sortWith(compareBy<Shipment>{it.end}.thenBy{it.start})


        for(delivery in deliveries){
            var minValue = stackLimit
            for(day in delivery.start until delivery.end){
                minValue = min(minValue, maxStack[day])
            }

            if (minValue >= delivery.amount){
                for(day in delivery.start until delivery.end){
                    maxStack[day] -= delivery.amount
                }
                result += delivery.amount
            } else {
                for(day in delivery.start until delivery.end){
                    maxStack[day] -= minValue
                }
                result += minValue
            }

            println("calculate : $maxStack")
            println(maxStack)
        }

        println(result)
    }
}

data class Shipment(
    val start: Int,
    val end: Int,
    val amount: Int
)