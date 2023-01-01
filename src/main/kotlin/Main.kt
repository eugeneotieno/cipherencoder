fun main() {
    println("Input string:")
    val inputString = readln()

    println()
    println("The result:")

    for (c in inputString) {
        println("$c = ${charTo7BitBinary(c)}")
    }
}

fun charTo7BitBinary(c: Char): String {
    val intValue = c.code
    val binaryString = Integer.toBinaryString(intValue)
    return binaryString.padStart(7, '0')
}