fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) { readLine()!!.toInt() }
    for (i in 1 until n) {
        if (arr[i] < arr[i-1]) {
            println("NO")
            return
        }
    }
    print("YES")
}