fun main() {
    // write your code here
    val n = readln().toInt()

    val arrs = mutableListOf<String>()
    for (i in 0 until n) {
        arrs.add(readln())
    }

    val arr = arrs.joinToString(separator = "") { it -> it }

    val pms = readln().split(" ")
    val pm = pms.joinToString(separator = "") { it -> it }
    val pmR = pms.reversed().joinToString(separator = "") { it -> it }

    if (pm in arr || pmR in arr) {
        println("NO")
    } else {
        println("YES")
    }

}