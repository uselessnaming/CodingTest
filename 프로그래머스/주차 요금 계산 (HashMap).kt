package 프로그래머스

class ParkingFeeCalculationWithHashMap {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val recordMap = hashMapOf<String, Int>()
        val sumMap = hashMapOf<String, Int>()
        val (baseTime, baseFee, addTime, addFee) = fees

        for (record in records) {
            val (time, num, type) = record.split(' ')

            when (type) {
                Type.IN.name -> {
                    recordMap[num] = time.convertToMinute()
                }

                Type.OUT.name -> {
                    val duration = time.convertToMinute() - recordMap[num]!!
                    if (!sumMap.contains(num)) {
                        sumMap[num] = duration
                    } else {
                        sumMap[num] = sumMap[num]!! + duration
                    }
                    recordMap[num] = -1
                }
            }
        }

        for ((num, time) in recordMap) {
            if (time != -1) sumMap[num] = sumMap[num]!! + (LIMIT - time)
        }

        return sumMap.toSortedMap()
            .values.map { it.calculateParkingFee(baseTime, baseFee, addTime, addFee) }
            .toIntArray()
    }

    private fun String.convertToMinute(): Int {
        val (hour, minute) = this.split(':').map { it.toInt() }
        return hour * HOUR + minute
    }

    private fun Int.calculateParkingFee(baseTime: Int, baseFee: Int, addTime: Int, addFee: Int): Int {
        return if (this <= baseTime) {
            baseFee
        } else {
            baseFee + (addFee * ((this - baseTime + (addTime - 1)) / addTime))
        }
    }

    enum class Type { IN, OUT }

    companion object {
        const val HOUR = 60
        const val LIMIT = 23 * HOUR + 59
    }
}