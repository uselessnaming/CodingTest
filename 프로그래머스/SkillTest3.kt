package 프로그래머스

class SkillTest3 {

    val carInfo = mutableMapOf<String, Int>()

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var answer = mutableListOf<Int>()

        // 차량 번호 별로 누적 시간을 계산해야 함
        // 시간을 모두 분으로 변경해서 계산
        setInfo(records)
        println(carInfo)

        // 게산 전 차량 누적 계산 결과를 번호 작은 순으로 정렬
        carInfo.keys.sorted().forEach{ carNum ->
            val fee = calFee(fees, carNum)
            // 180분 이하일 경우 >> 5000원
            // 이상일 경우 5000 + (시간 - 180) / 10 .council() * 600
            answer.add(fee)
            println("$carNum $fee")
        }

        return answer.toIntArray()
    }

    fun setInfo(records: Array<String>) {
        val recordMap = mutableMapOf<String, Int>()
        for(record in records){
            val (time, carNum, type) = record.split(" ")

            if(type == "IN"){
                recordMap[carNum] = transTime(time)
            } else {
                if (carInfo.contains(carNum)){
                    carInfo[carNum] = carInfo[carNum]!! + calTime(time, recordMap[carNum]!!)
                } else {
                    carInfo[carNum] = calTime(time, recordMap[carNum]!!)
                }
                recordMap.remove(carNum)
            }
        }

        if (recordMap.isNotEmpty()){
            recordMap.keys.map{
                if (carInfo.contains(it)){
                    carInfo[it] = carInfo[it]!! + calTime("23:59", recordMap[it]!!)
                } else {
                    carInfo[it] = calTime("23:59", recordMap[it]!!)
                }
            }
        }
    }
    fun calFee(fees: IntArray, carNum: String): Int {
        return if(carInfo[carNum]!! > fees[0]){
            if ((carInfo[carNum]!! - fees[0]) % fees[2] == 0){
                fees[1] + (((carInfo[carNum]!! - fees[0]) / fees[2]) * fees[3])
            } else {
                fees[1] + ((((carInfo[carNum]!! - fees[0]) / fees[2]) + 1) * fees[3])
            }
        } else {
            fees[1]
        }
    }

    fun transTime(time: String): Int {
        val (h, m) = time.split(":").map{it.toInt()}
        return h*60 + m
    }
    fun calTime(time: String, record: Int): Int {
        return transTime(time) - record
    }
}
