package 프로그래머스

class Decoding {

    var sets = mutableListOf<Set<Int>>()
    var N = 0

    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int{
        N = n
        init()

        for(idx in q.indices){
            val withSets = mutableListOf<Set<Int>>()
            for(set in sets){
                if (set.intersect(q[idx].toSet()).size == ans[idx]){
                    withSets.add(set)
                }
            }
            sets = withSets
        }

        return sets.count()
    }

    fun init(){
        //n개의 조합을 모두 sets에 두기
        for(a in 1..N){
            for(b in a+1..N){
                for(c in b+1..N){
                    for(d in c+1..N){
                        for(e in d+1..N){
                            sets.add(setOf(a, b, c, d, e))
                        }
                    }
                }
            }
        }
    }
}

// 1부터 n까지의 숫자 중에서 5개를 고르는 경우의 수를 준비
// q, ans의 indices를 for하면서 현재 숫자 경우의 수와 q의 숫자 중 겹치는 것이 ans와 같은지 확인
// 같지 않다면 삭제, 같다면 유지
// 마지막 list의 개수를 return하면 될 듯