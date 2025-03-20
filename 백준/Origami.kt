package 백준

// 백준 1802번 종이접기

class Origami {
    var isAvailable = true
    fun run (){
        val n = readln().toInt()
        repeat(n){
            val paper = readln()
            isAvailable = true
            dfs(paper)

            println(if(isAvailable) "YES" else "NO")
        }
    }

    fun dfs(paper: String){
        if(!isAvailable) return
        if (paper.length == 1) return

        val middle = paper.length/2
        val left = paper.substring(0, middle)
        val right = paper.substring(middle+1, paper.length)

        for(idx in left.indices){
            if (left[idx].toString().toInt() + right[right.length - idx - 1].toString().toInt() != 1) {
                isAvailable = false
                return
            }
        }

        dfs(left)
        dfs(right)
    }
}