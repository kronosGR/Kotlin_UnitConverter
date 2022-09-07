package converter

import java.util.Scanner

enum class Unit { length, weight, temperature, unknown }

enum class Units(val type: Unit, val onoma: String, val ratio: Double) {
    meter(Unit.length, "meter", 1.0),
    kilometer(Unit.length, "kilometer", 1_000.0),
    centimeter(Unit.length, "centimeter", 0.01),
    millimeter(Unit.length, "millimeter", 0.001),
    mile(Unit.length, "mile", 1_609.35),
    yard(Unit.length, "yard", 0.9144),
    foot(Unit.length, "foot", 0.3048),
    inch(Unit.length, "inch", 0.0254),
    gram(Unit.weight, "gram", 1.0),
    kilogram(Unit.weight, "kilogram", 1_000.0),
    milligram(Unit.weight, "milligram", 0.001),
    pound(Unit.weight, "pound", 453.592),
    ounce(Unit.weight, "ounce", 28.3495),
    celsius(Unit.temperature, "degree Celsius", 0.0),
    kelvin(Unit.temperature, "kelvin", 0.0),
    fahrenheit(Unit.temperature, "degree Fahrenheit", 0.0),
    unknown(Unit.unknown, "???", 0.0);
}

fun toPlural(unit: String): String {
    if (unit == "inch") return "inches"
    else if (unit == "foot") return "feet"
    else if (unit == "degree Celsius") return "degrees Celsius"
    else if (unit == "degree Fahrenheit") return "degrees Fahrenheit"
    else if (unit == "???") return "???"
    else {
        if (checkIfExist(unit)) {
            return (unit + "s").lowercase()
        } else {
            return unit
        }
    }
}

fun checkIfExist(unit: String): Boolean {
    for (onoma in Units.values()) {
        if (onoma.onoma == unit.lowercase()) return true
    }
    return false
}

fun getUnit(unit: String): Units {
    return when (unit.lowercase()) {
        "m", "meter", "meters" -> Units.meter
        "km", "kilometer", "kilometers" -> Units.kilometer
        "cm", "centimeter", "centimeters" -> Units.centimeter
        "mm", "millimeter", "millimeters" -> Units.millimeter
        "mi", "mile", "miles" -> Units.mile
        "yd", "yard", "yards" -> Units.yard
        "ft", "foot", "feet" -> Units.foot
        "in", "inch", "inches" -> Units.inch
        "g", "gram", "grams" -> Units.gram
        "kg", "kilogram", "kilograms" -> Units.kilogram
        "mg", "milligram", "milligrams" -> Units.milligram
        "lb", "pound", "pounds" -> Units.pound
        "oz", "ounce", "ounces" -> Units.ounce
        "c", "dc", "celsius" -> Units.celsius
        "k", "kelvin", "kelvins" -> Units.kelvin
        "f", "df", "fahrenheit" -> Units.fahrenheit
        else -> Units.unknown
    }
}

fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")

        val txt = readln()

        if (txt == "exit") break

        val txtClean = txt.lowercase().replace("degrees ", "").replace("degree ", "")
        val (amountOr, fromOr, what, toOr) = txtClean.split(" ")

        try {
            val from = getUnit(fromOr)
            val amount = amountOr.toDouble()
            val to = getUnit(toOr)

            if (from == Units.unknown && to == Units.unknown) {
                println("Conversion from ${Units.unknown.onoma} to ${Units.unknown.onoma} is impossible")
                continue
            } else if (from == Units.unknown && to != Units.unknown) {
                println("Conversion from ${Units.unknown.onoma} to ${toPlural(to.onoma)} is impossible")
                continue
            } else if (from != Units.unknown && to == Units.unknown) {
                println("Conversion from ${toPlural(from.onoma)} to ${Units.unknown.onoma} is impossible")
                continue
            } else if (from == Units.unknown || to == Units.unknown) {
                println("Conversion from ${Units.unknown.onoma} to ${toPlural(toOr)} is impossible")
                continue
            } else if (from.type != to.type) {
                println("Conversion from ${toPlural(from.onoma)} to ${toPlural(to.onoma)} is impossible")
                continue
            } else if (amount < 0.0) {
                if (from.type == Unit.length) {
                    println("Length shouldn't be negative")
                    continue
                } else if (from.type == Unit.weight) {
                    println("Weight shouldn't be negative")
                    continue
                }
            }

            // val res = amount * from.ratio / to.ratio

            var res = 0.0

            if (from.type == Unit.temperature && to.type == Unit.temperature) {
                when {
                    from == Units.fahrenheit && to == Units.fahrenheit -> res = amount
                    from == Units.celsius && to == Units.celsius -> res = amount
                    from == Units.kelvin && to == Units.kelvin -> res = amount
                    from == Units.fahrenheit && to == Units.celsius -> res = (amount - 32.0) * 5.0 / 9.0
                    from == Units.celsius && to == Units.fahrenheit -> res = amount * 9.0 / 5.0 + 32.0
                    from == Units.celsius && to == Units.kelvin -> res = amount + 273.15
                    from == Units.kelvin && to == Units.celsius -> res = amount - 273.15
                    from == Units.kelvin && to == Units.fahrenheit -> res = amount * 9.0 / 5.0 - 459.67
                    from == Units.fahrenheit && to == Units.kelvin -> res = (amount + 459.67) * 5.0 / 9.0
                }
            } else {
                res = amount * from.ratio / to.ratio
            }

            val fromUnit = if (amount == 1.0) from.onoma else toPlural(from.onoma)
            val finalUnit = if (res == 1.0) to.onoma else toPlural(to.onoma)

            println("$amount $fromUnit is $res $finalUnit")
        } catch (ex: NumberFormatException) {
            println("Parse Error")
        }

    }
}
