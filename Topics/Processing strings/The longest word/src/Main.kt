fun main() {
    // write your code here
    val sentence = readln()
    val array = sentence.split(" ")

    var max = 0
    var maxW = ""
    for (word in array) {
        if (word.length > max) {
            max = word.length
            maxW = word
        }
    }

    println(maxW)
}