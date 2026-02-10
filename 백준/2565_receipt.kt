package 백준

class Receipt {
    fun run() {
        var totalPrice = readln().trim().toInt()

        repeat(9) {
            val n = readln().trim().toInt()
            totalPrice -= n
        }

        println(totalPrice)
    }
}