package 프로그래머스

class SumOfSubSequence {
    lateinit var elements: IntArray
    val numSet = mutableSetOf<Int>()
    fun solution(_elements: IntArray): Int {
        elements = _elements

        for(size in 1 .. elements.size){
            getSum(size)
        }

        return numSet.size
    }

    fun getSum(size: Int) {
        var s = elements.toList().subList(0, size).sum()
        numSet.add(s)
        var start = 1
        var end = size+1
        while(start < elements.size){
            s = s - elements[start-1] + elements[(end-1)%elements.size]
            numSet.add(s)
            start++
            end++
        }
    }
}