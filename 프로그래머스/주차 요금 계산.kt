package 프로그래머스

class ParkingFeeCalculation {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val answer = ArrayList<Int>()
        val sorted = records.map{it.split(' ')}.sortedBy {it[1]}
        var bef = sorted[0][1]
        var totalTime = 0

        for ((time, num, type) in sorted) {
            if (bef != num) {
                if (totalTime <= 0) { totalTime += LIMIT.convertToMinute() }

                answer.add(totalTime.calculateFee(fees))
                bef = num
                totalTime = 0
            }

            when(type) {
                Type.IN.name -> { totalTime -= time.convertToMinute() }

                Type.OUT.name -> { totalTime += time.convertToMinute() }
            }
        }

        if (totalTime <= 0) { totalTime += LIMIT.convertToMinute() }

        answer.add(totalTime.calculateFee(fees))

        return answer.toIntArray()
    }

    private fun String.convertToMinute(): Int {
        val (hour, minute) = this.split(':').map{it.toInt()}
        return hour * 60 + minute
    }

    private fun Int.calculateFee(fees: IntArray): Int {
        val (baseTime, baseFee, addTime, addFee) = fees

        return if (this <= baseTime) {
            baseFee
        } else {
            baseFee + (((this - baseTime) + (addTime - 1)) / addTime) * addFee
        }
    }

    enum class Type { IN, OUT }

    companion object {
        const val LIMIT = "23:59"
    }
}