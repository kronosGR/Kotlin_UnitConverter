fun main() {
    // write your code here
    val word = readln()
    var newW = ""
    if (word.length % 2 == 0) {
        // remove two in the middle
        newW = word.removeRange(word.length / 2 - 1..word.length / 2)
    } else {
        // remove one
        newW = word.removeRange(word.length / 2 until word.length / 2 + 1)
    }

    println(newW)
}