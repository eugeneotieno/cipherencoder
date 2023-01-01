fun main() {
    decode()
}

fun decode() {
    println("Input encoded string:")
    val inputString = readln()

    println()
    println("The result:")

    println(decodeFromChuckNorrisBinary(inputString))
}

fun decodeFromChuckNorrisBinary(text: String): String {
    val binaryArray = text.split(" ")
    val binaryStr = StringBuilder()
    var tempChar: String? = null
    for (c in binaryArray) {
        tempChar = if (tempChar == null) {
            c
        } else {
            if (tempChar == "0") {
                binaryStr.append("1".repeat(c.length))
                null
            } else {
                binaryStr.append("0".repeat(c.length))
                null
            }
        }
    }

    val blocks = binaryStr.chunked(7)
    val characters = blocks.map { it.toInt(radix = 2).toChar() }
    return characters.joinToString("")
}

fun encode() {
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