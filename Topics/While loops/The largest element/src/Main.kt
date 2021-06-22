fun main() {
    // put your code here
    var a = Int.MIN_VALUE
    var p = -1
    while (p != 0) {
        p = readLine()!!.toInt()
        if (p > a) a = p
    }
    println(a)
}