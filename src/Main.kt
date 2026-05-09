import kotlin.random.Random

fun main() {
    val array2d = Array(9) {
        Array(9) { "." }
    }

    println("How many mines do you want on the field?")
    val input = readln().toInt()

    val random = Random


    for (i in 1..input) {
        inner@ while (true) {
            val random1 = random.nextInt(0, 9)
            val random2 = random.nextInt(0, 9)
            if (array2d[random1][random2] != "X") {
                array2d[random1][random2] = "X"
                break@inner
            }
        }
    }


    checkCorners(array2d)


//    for(i in array2d.indices){
//        for (j in array2d[i].indices){
//            when{
//                i == 0 && j == 0 -> continue
//                i == 0 && j == 8 -> continue
//                i == 8 && j == 0 -> continue
//                i == 8 && j == 8 -> continue
//            }
//        }
//    }

    for (i in array2d) {
        println(i.joinToString(""))
    }
}


fun checkCorners(array2d: Array<Array<String>>) {
    var firstCorner = 0
    var firstLastCorner = 0
    var lastFirstCorner = 0
    var lastCorner = 0

    // First Corner of Grid
    while (array2d.first()[0] != "X") {
        if (array2d.first()[1] == "X") firstCorner++
        if (array2d[1][0] == "X") firstCorner++
        if (array2d[1][1] == "X") firstCorner++
        if (firstCorner > 0) array2d.first()[0] = "$firstCorner"
        break
    }

    // First Last Corner of Grid 1
    while (array2d.first()[array2d.lastIndex] != "X") {
        if (array2d.first()[array2d.lastIndex - 1] == "X") firstLastCorner++
        if (array2d[1][array2d.lastIndex] == "X") firstLastCorner++
        if (array2d[1][array2d.lastIndex - 1] == "X") firstLastCorner++
        if (firstLastCorner > 0) array2d.first()[array2d.lastIndex] = "$firstLastCorner"
        break
    }

    // Last First Corner of Grid
    while (array2d.last()[0] != "X") {
        if (array2d.last()[1] == "X") lastFirstCorner++
        if (array2d[array2d.size - 2][0] == "X") lastFirstCorner++
        if (array2d[array2d.size - 2][1] == "X") lastFirstCorner++
        if (lastFirstCorner > 0) array2d.last()[0] = "$lastFirstCorner"
        break
    }
    // Last Last Corner of Grid
    while (array2d.last()[array2d.lastIndex] != "X") {
        if (array2d.last()[array2d.lastIndex - 1] == "X") lastCorner++
        if (array2d[array2d.lastIndex -1][array2d.lastIndex] == "X") lastCorner++
        if (array2d[array2d.lastIndex - 1][array2d.lastIndex - 1] == "X") lastCorner++
        if (lastCorner > 0) array2d.last()[array2d.lastIndex] = "$lastCorner"
        break
    }
}