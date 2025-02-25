package 백준

//10448번 유레카
//완전 탐색 쉬운 난이도
//최대 비교 값 n = 25

class Eureka{
    val arr = mutableListOf<Int>()
    fun init(){
        for(i in 1..45){
            arr.add((i*(i+1))/2)
        }
    }
    fun run(){
        val k = readln().toInt()
        init()
        repeat(k){
            val target = readln().toInt()
            var flag = false
            outer@for(i in arr){
                for(j in arr){
                    for(k in arr){
                        if (i+j+k == target){
                            flag = true
                            break@outer
                        }
                    }
                }
            }
            if(flag) println(1) else println(0)
        }
    }
}