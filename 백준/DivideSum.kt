package 백준

//백준 2231번 분해합
class DivideSum{
    fun run(){
        val n = readln().toInt()
        for(i in 1 until n){
            var sum = i
            val s = i.toString()
            for(c in s){
                sum += c.toString().toInt()
            }
            if (sum == n){
                println(i)
                return
            }
        }
        println(0)
    }
}