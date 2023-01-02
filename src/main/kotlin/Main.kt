fun main() {
    var exit = false
    do {
        println("Please input operation (encode/decode/exit):")
        when(val action = readln()) {
            "encode" -> {
                encode()
            }
            "decode" -> {
                decode()
            }
            "exit" -> {
                println("Bye!")
                exit = true
            }
            else -> {
                println("There is no '$action' operation")
                println()
            }
        }
    } while (!exit)
}

fun decode() {
    println("Input encoded string:")
    val inputString = readln()

    var isValid = true

    val countValid = inputString.count { it == '0' } + inputString.count { it == ' ' }
    if (countValid != inputString.length) isValid = false

    val firstBlock = inputString.split(" ")[0]
    if (firstBlock != "0") {
        if (firstBlock != "00") {
            isValid = false
        }
    }

    if (isValid) {
        decodeFromChuckNorrisBinary(inputString)
    } else {
        println("Encoded string is not valid.\n")
    }
}

fun decodeFromChuckNorrisBinary(text: String) {
    val binaryArray = text.split(" ")

    val binaryStr = StringBuilder()
    var tempChar: String? = null
    var isValid = true

    for (c in binaryArray) {
        if (tempChar == null) {
            if (c != "0") {
                if (c != "00") {
                    isValid = false
                    break
                }
            }
            tempChar = c
        } else {
            tempChar = if (tempChar == "0") {
                binaryStr.append("1".repeat(c.length))
                null
            } else {
                binaryStr.append("0".repeat(c.length))
                null
            }
        }
    }

    if (isValid) {
        if (binaryStr.toString().length % 7 != 0) {
            println("Encoded string is not valid.\n")
        } else {
            val blocks = binaryStr.chunked(7)
            val characters = blocks.map { it.toInt(radix = 2).toChar() }

            println("Decoded string:")
            println(characters.joinToString(""))
            println()
        }
    } else {
        println("Encoded string is not valid.\n")
    }

}

fun encode() {
    println("Input string:")
    val inputString = readln()

    println("Encoded string:")
    println(encodeToChuckNorrisBinary(inputString))
    println()
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