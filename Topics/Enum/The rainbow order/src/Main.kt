enum class Rainbow {
    NULL, RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

fun getOrdinal(color: String): Int {
    for (enum in Rainbow.values()) {
        if (color.uppercase() == enum.name) {
            return enum.ordinal
        }
    }
    return 0
}

fun main() {
    val color = readln()
    // put your code here
    println(getOrdinal(color))
}
