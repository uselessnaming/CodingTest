package 프로그래머스

class FlexibleWorking {
    private val WEEKDAYS = 7
    private val DURATION = 10
    private val HOUR = 60
    private val TIME_SEPRATOR = 100
    private val SAT = 5

    fun solution(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int{
        return schedules.indices.count{ idx -> canGet(timelogs[idx], schedules[idx], startday - 1) }
    }

    private fun canGet(timelog: IntArray, start: Int, startDay: Int): Boolean {
        val availableEnd = start + DURATION

        repeat(WEEKDAYS) { time ->
            val currentDay = (startDay + time) % WEEKDAYS

            if (currentDay >= SAT) return@repeat

            if (timelog[time].toMinute() > availableEnd.toMinute()) return false
        }

        return true
    }

    private fun Int.toMinute(): Int {
        val hour = this / TIME_SEPRATOR
        val minute = this % TIME_SEPRATOR

        return hour * HOUR + minute
    }
}