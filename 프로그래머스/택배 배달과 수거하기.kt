package 프로그래머스

import kotlin.math.max

class DeliveryAndPickup {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        val deliveries = deliveries.copyOf()
        val pickups = pickups.copyOf()

        var remainDelivery = cap // 남은 적재량
        var remainPickup = cap // 남은 픽업량

        var answer = 0L
        var endDeliveryIdx = deliveries.indexOfLast{ it != 0 }
        var endPickupIdx = pickups.indexOfLast{ it != 0 }

        while (endDeliveryIdx >= 0 || endPickupIdx >= 0) {
            while(endDeliveryIdx >= 0) {
                if (deliveries[endDeliveryIdx] != 0) break

                endDeliveryIdx--
            }

            while(endPickupIdx >= 0) {
                if (pickups[endPickupIdx] != 0) break

                endPickupIdx--
            }

            val far = max(endPickupIdx, endDeliveryIdx)
            answer += (far + 1) * 2

            while(remainDelivery > 0 && endDeliveryIdx >= 0) {
                if (remainDelivery - deliveries[endDeliveryIdx] >= 0) {
                    remainDelivery -= deliveries[endDeliveryIdx]
                    deliveries[endDeliveryIdx] = 0
                } else {
                    deliveries[endDeliveryIdx] -= remainDelivery
                    remainDelivery = 0
                    break
                }

                endDeliveryIdx--
            }

            while(remainPickup > 0 && endPickupIdx >= 0) {
                if (remainPickup - pickups[endPickupIdx] >= 0) {
                    remainPickup -= pickups[endPickupIdx]
                    pickups[endPickupIdx] = 0
                }  else {
                    pickups[endPickupIdx] -= remainPickup
                    remainPickup = 0
                    break
                }

                endPickupIdx--
            }

            remainDelivery = cap
            remainPickup = cap
        }

        return answer
    }
}