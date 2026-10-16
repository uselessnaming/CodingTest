package 프로그래머스

class FlexibleWorking {
    private val WEEKDAYS = 7
    private val DURATION = 10
    private val HOUR = 60

    fun solution(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int{
        var result = 0

        for (idx in schedules.indices) {
            if (canGet(timelogs[idx], schedules[idx], startday - 1)) result++
        }

        return result
    }

    private fun canGet(timelog: IntArray, start: Int, startDay: Int): Boolean {
        val availableEnd =
            if ((start + DURATION) % 100 >= HOUR) start + DURATION - HOUR + 100
            else start + DURATION

        repeat(WEEKDAYS) { time ->
            val currentDay = (startDay + time) % WEEKDAYS

            if (currentDay == 5 || currentDay == 6) return@repeat

            if (timelog[time] > availableEnd) return false
        }

        return true
    }
}