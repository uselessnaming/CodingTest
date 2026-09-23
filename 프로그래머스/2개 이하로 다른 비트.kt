package 프로그래머스

class FindBit {
    fun solution(numbers: LongArray): LongArray {
        return numbers.map{ getBits(it) }
            .toLongArray()
    }

    private fun getBits(num: Long): Long {
        // 우측 첫 0의 위치를 가져오기
        val lowestZeroBit = (num.inv()).takeLowestOneBit()

        // 우측 첫 0의 위치가 최하단이 아니라면 0 위치를 1로 1개만 변경
        return if (num >= lowestZeroBit) {
            if (lowestZeroBit == 1L) {
                num + lowestZeroBit
            } else {
                (num + lowestZeroBit) - (lowestZeroBit / 2)
            }
        } else {
            // 최상단이라면 최상단 1을 추가하고 그 옆 비트를 0으로 변경
            ((num + 1) or num) - (lowestZeroBit / 2)
        }
    }
}