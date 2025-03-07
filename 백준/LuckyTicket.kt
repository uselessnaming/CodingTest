package 백준

// 백준 1639번 행운의 티켓

class LuckyTicket {
    fun run(){
        val tickets = readln()
        val startLength = if(tickets.length % 2 == 0) tickets.length else tickets.length-1
        for(i in startLength downTo 0 step 2){
            for(j in 0 .. tickets.length - i){
                if(isLucky(tickets.substring(j, j+i))){
                    println(i)
                    return
                }
            }
        }
    }
    fun isLucky(ticket: String): Boolean{
        val n = ticket.length
        val left = ticket.substring(0, n/2)
        val right = ticket.substring(n/2, n)

        return left.map{it.toString().toInt()}.sum() == right.map{it.toString().toInt()}.sum()
    }
}