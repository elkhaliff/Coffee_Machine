fun main() {
    // write your code here
    var m = 0
    val n = readLine()!!.toInt()
    val arr = Array(n) { readLine()!!.toInt() }
    for (i in 0 until n) { m += arr[i] }
    println(m)
}
