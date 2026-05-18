import kotlin.math.sign
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




    printGrids(array2d)
    while (true) {

        println("Set/delete mine marks (x and y coordinates): ")
        val x = readln().toInt()
        val y = readln().toInt()
        when(array2d[y-1][x-1]){
            "1","2","3","4","5","6","7","8" -> println("There is a number here!")
            "*" -> {
                array2d[y-1][x-1] = "."
                printGrids(array2d)
            }
            else -> {
                array2d[y-1][x-1] = "*"
                printGrids(array2d)
            }
        }

    }
    //pause here


}


fun printGrids(array2d: Array<Array<String>>){

    checkCorners(array2d)
    checkSides(array2d)
    checkCenters(array2d)

    println(" │123456789│")

    println("—│—————————│")

    for (i in array2d.indices) {
        print("${i+1}│")
        for (j in array2d[i].indices) {
            if (array2d[i][j] == "X") print(".")
            else print(array2d[i][j])
        }
        println("│")
    }

    println("—│—————————│")
}

fun checkCenters(array2d: Array<Array<String>>) {

    //Center checks of Grid excluding corners and 4 sides
    for (i in 1..array2d.size - 2) {
        for (j in 1..array2d[i].size - 2) {
            var centerX = 0
            if (array2d[i][j] != "X" && array2d[i][j] != "*") {
                if (array2d[i - 1][j - 1] == "X") centerX++
                if (array2d[i - 1][j] == "X") centerX++
                if (array2d[i - 1][j + 1] == "X") centerX++
                if (array2d[i][j - 1] == "X") centerX++
                if (array2d[i][j + 1] == "X") centerX++
                if (array2d[i + 1][j - 1] == "X") centerX++
                if (array2d[i + 1][j] == "X") centerX++
                if (array2d[i + 1][j + 1] == "X") centerX++
                if (centerX > 0) array2d[i][j] = "$centerX"
            }
        }
    }
}


fun checkSides(array2d: Array<Array<String>>) {

    //Top Sides Check of Grid excluding corners
    for (i in 1..array2d[0].size - 2) {
        var firstRowX = 0
        if (array2d[0][i] != "X" && array2d[0][i] != "*") {
            if (array2d[0][i - 1] == "X") firstRowX++
            if (array2d[0][i + 1] == "X") firstRowX++
            if (array2d[1][i - 1] == "X") firstRowX++
            if (array2d[1][i] == "X") firstRowX++
            if (array2d[1][i + 1] == "X") firstRowX++
            if (firstRowX > 0) array2d[0][i] = "$firstRowX"
        }
    }

    //Left Side check of Grid excluding corners
    for (i in 1..array2d.size - 2) {
        var leftSideX = 0
        if (array2d[i][0] != "X" && array2d[i][0] != "*") {
            if (array2d[i - 1][0] == "X") leftSideX++
            if (array2d[i - 1][1] == "X") leftSideX++
            if (array2d[i][1] == "X") leftSideX++
            if (array2d[i + 1][0] == "X") leftSideX++
            if (array2d[i + 1][1] == "X") leftSideX++
            if (leftSideX > 0) array2d[i][0] = "$leftSideX"
        }
    }

    //Right Side check of Grid excluding corners
    for (i in 1..array2d.size - 2) {
        var rightSideX = 0
        if (array2d[i][array2d.lastIndex] != "X" && array2d[i][array2d.lastIndex] != "*") {
            if (array2d[i - 1][array2d.lastIndex] == "X") rightSideX++
            if (array2d[i - 1][array2d.lastIndex - 1] == "X") rightSideX++
            if (array2d[i][array2d.lastIndex - 1] == "X") rightSideX++
            if (array2d[i + 1][array2d.lastIndex] == "X") rightSideX++
            if (array2d[i + 1][array2d.lastIndex - 1] == "X") rightSideX++
            if (rightSideX > 0) array2d[i][array2d.lastIndex] = "$rightSideX"
        }
    }

    //Bottom Side check of Grid excluding corners
    for (i in 1..array2d.last().size - 2) {
        var bottomSideX = 0
        if (array2d[array2d.lastIndex][i] != "X" && array2d[array2d.lastIndex][i] != "*") {
            if (array2d[array2d.lastIndex][i - 1] == "X") bottomSideX++
            if (array2d[array2d.lastIndex - 1][i - 1] == "X") bottomSideX++
            if (array2d[array2d.lastIndex - 1][i] == "X") bottomSideX++
            if (array2d[array2d.lastIndex - 1][i + 1] == "X") bottomSideX++
            if (array2d[array2d.lastIndex][i + 1] == "X") bottomSideX++
            if (bottomSideX > 0) array2d[array2d.lastIndex][i] = "$bottomSideX"
        }
    }
}


fun checkCorners(array2d: Array<Array<String>>) {
    var firstCorner = 0
    var firstLastCorner = 0
    var lastFirstCorner = 0
    var lastCorner = 0

    // First Corner of Grid
    while (array2d.first()[0] != "X" && array2d.first()[0] != "*") {
        if (array2d.first()[1] == "X") firstCorner++
        if (array2d[1][0] == "X") firstCorner++
        if (array2d[1][1] == "X") firstCorner++
        if (firstCorner > 0) array2d.first()[0] = "$firstCorner"
        break
    }

    // First Last Corner of Grid
    while (array2d.first()[array2d.lastIndex] != "X" && array2d.first()[array2d.lastIndex] != "*") {
        if (array2d.first()[array2d.lastIndex - 1] == "X") firstLastCorner++
        if (array2d[1][array2d.lastIndex] == "X") firstLastCorner++
        if (array2d[1][array2d.lastIndex - 1] == "X") firstLastCorner++
        if (firstLastCorner > 0) array2d.first()[array2d.lastIndex] = "$firstLastCorner"
        break
    }

    // Last First Corner of Grid
    while (array2d.last()[0] != "X" && array2d.last()[0] != "*" ) {
        if (array2d.last()[1] == "X") lastFirstCorner++
        if (array2d[array2d.size - 2][0] == "X") lastFirstCorner++
        if (array2d[array2d.size - 2][1] == "X") lastFirstCorner++
        if (lastFirstCorner > 0) array2d.last()[0] = "$lastFirstCorner"
        break
    }
    // Last Corner of Grid
    while (array2d.last()[array2d.lastIndex] != "X" && array2d.last()[array2d.lastIndex] != "*") {
        if (array2d.last()[array2d.lastIndex - 1] == "X") lastCorner++
        if (array2d[array2d.lastIndex - 1][array2d.lastIndex] == "X") lastCorner++
        if (array2d[array2d.lastIndex - 1][array2d.lastIndex - 1] == "X") lastCorner++
        if (lastCorner > 0) array2d.last()[array2d.lastIndex] = "$lastCorner"
        break
    }
}