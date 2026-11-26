package 프로그래머스

class ParkingFeeCalculation {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        return records
            .map { it.split(' ') }
            .groupBy({ it[1] }, { it[0].convertToMinute() to it[2] })
            .toSortedMap()
            .values.map { calculateParkingMinutes(it).calculateFee(fees) }
            .toIntArray()
    }

    private fun calculateParkingMinutes(logs: List<Pair<Int, String>>): Int {
        var total = logs.sumOf { (time, type) -> if (type == Type.IN.name) -time else time }
        if (logs.last().second == Type.IN.name) total += LIMIT
        return total
    }

    private fun String.convertToMinute(): Int {
        val (hour, minute) = this.split(':').map { it.toInt() }
        return hour * HOUR + minute
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
        const val HOUR = 60
        const val LIMIT = 23 * HOUR + 59
    }
}