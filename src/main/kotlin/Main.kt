fun main() {
    println("Input string:")
    val inputString = readln()

    println()
    println("The result:")

    println(encodeToChuckNorrisBinary(inputString))
}

fun charTo7BitBinary(c: Char): String {
    val intValue = c.code
    val binaryString = Integer.toBinaryString(intValue)
    return binaryString.padStart(7, '0')
}

fun encodeToChuckNorrisBinary(text: String): String {

    val builder = StringBuilder()

    val allBinary = StringBuilder()
    for (c in text) {
        val binary = charTo7BitBinary(c)
        allBinary.append(binary)
    }

    val binaryArray = mutableListOf<String>()
    var tempChar: String? = null
    var tempVal = ""

    for (b in allBinary) {
        if (tempChar == null) {
            tempVal += b.toString()
            tempChar = b.toString()
        } else {
            if (tempChar == b.toString()) {
                tempVal += b.toString()
                tempChar = b.toString()
            } else {
                binaryArray.add(tempVal)
                tempVal = b.toString()
                tempChar = b.toString()
            }
        }
    }
    binaryArray.add(tempVal)

    for (pair in binaryArray) {
        if (pair.contains("1")) {
            builder.append(" 0 ")
            builder.append("0".repeat(pair.length))
        } else {
            builder.append(" 00 ")
            builder.append("0".repeat(pair.length))
        }
    }

    return builder.toString().trim()
}
//0 0 00 00 0 0 00 000 0 00 00 0 0 0 00 00 0 0 00 0 0 0 00 000000 0 0000 00 000 0 00 00 00 0 00
//0 0 00 00 0 0 00 000 0 00 00 0 0 0 00 00 0 0 00 0 0 0 00 000000 0 0000 00 000 0 00 00 00 0 00