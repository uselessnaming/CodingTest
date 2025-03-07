package 프로그래머스

class FlexiableWorkSystem {
    fun solution(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int{
        val result = IntArray(schedules.size){ 0 }

        for(idx in schedules.indices){
            var stdValue = schedules[idx] + 10
            if (stdValue % 100 >= 60){ stdValue = stdValue + 100 - 60 }

            for(x in 0 until 7){
                val xx = (startday + x-1) % 7
                if(xx == 5 || xx == 6) continue

                if(timelogs[idx][x] <= stdValue){ result[idx]++ }
            }
        }

        return result.count{it == 5}
    }
}