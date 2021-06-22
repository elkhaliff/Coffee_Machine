import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val arr = Array(5) { scanner.next() }
    arr.forEach { println(it) }
}