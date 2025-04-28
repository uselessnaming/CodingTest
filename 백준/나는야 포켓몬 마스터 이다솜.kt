package 백준

// 1620번 백준

class PocketMonster{
    val dict = mutableListOf<Pocketmon>()
    lateinit var searchDict: List<Pocketmon>
    fun run(){
        val (n, m) = readln().split(" ").map{it.toInt()}
        repeat(n){idx -> dict.add(Pocketmon(idx+1, readln().trim()))}
        searchDict = dict.toList().sortedBy{it.name}
        repeat(m){
            val keyword = readln().trim()
            var isString = true
            for(ch in keyword){
                if (ch.isDigit()) {
                    isString = false
                    break
                }
            }
            if (isString){
                println(searchPocketmon(keyword))
            } else {
                println(dict[keyword.toInt()-1].name)
            }
        }
    }
    fun searchPocketmon(keyword: String): Int{
        var left = 0
        var right = searchDict.size-1
        while(left <= right){
            val mid = (left+right) / 2
            if (searchDict[mid].name == keyword ){
                return searchDict[mid].key
            } else if (searchDict[mid].name > keyword){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return -1
    }
}
data class Pocketmon(
    val key: Int,
    val name: String
)