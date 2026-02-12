package 백준

class TruckPark {
    val parkingLot = MutableList(101) { 0 }
    fun run() {
        val (a, b, c) = readln().trim().split(" ").map { it.toInt() }

        repeat(3) {
            val (start, end) = readln().trim().split(" ").map { it.toInt() }
            for (i in start until end) {
                parkingLot[i] += 1
            }
        }

        val aTotal = parkingLot.count { it == 1 } * a
        val bTotal = parkingLot.count { it == 2 } * b * 2
        val cTotal = parkingLot.count { it == 3 } * c * 3
        println(aTotal + bTotal + cTotal)
    }
}