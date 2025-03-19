package 프로그래머스

// 아 윈도우로 해서 시간 초과가 발생한 거였네
// 이건 투포인터가 맞음
// 좌우가 모두 움직이며 데이터를 수정해야 하기 때문에

class SkillTest2_4 {
    fun solution(sequence: IntArray, k: Int): IntArray {
        var answer = intArrayOf(0, Int.MAX_VALUE)

        var l = 0
        var r = 0
        var sum = sequence[0]
        while(l < sequence.size){
            if (sum < k){
                if (r == sequence.size-1) break
                r++
                sum += sequence[r]
            } else {
                if (sum == k){
                    if(answer[1] - answer[0] > r - l){
                        answer[0] = l
                        answer[1] = r
                    }
                }
                sum -= sequence[l]
                l++
            }
        }

        return answer
    }
}