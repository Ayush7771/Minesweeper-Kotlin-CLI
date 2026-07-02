import java.util.Scanner
import kotlin.random.Random

fun main() {

    val grid = MutableList(9) {
        MutableList(9) {
            "."
        }
    }

    print("How many mines do you want on the field? ")
    val inputOfTotalMines = readln().toInt()

    printGrids(grid)

    val random = Random

    repeat(inputOfTotalMines) {

        while (true) {
            val x = random.nextInt(0, grid.size)
            val y = random.nextInt(0, grid.size)

            if (grid[x][y] != "X") {
                grid[x][y] = "X"
                break
            }
        }

    }
    checkCorners(grid)
    checkSides(grid)
    checkCenters(grid)

    val scanner = Scanner(System.`in`)

    while (true) {
        print("Set/unset mine marks or claim a cell as free: ")
        val x = scanner.nextInt()
        val y = scanner.nextInt()
        val option = scanner.next()
        break
    }
    printGrids(grid)
}


fun printGrids(grid: MutableList<MutableList<String>>) {

    print(" │")
    for (i in 1..grid.size) {
        print(i)
    }
    println("│")

    print("—│")
    for (i in 1..grid.size) {
        print("—")
    }
    println("│")

    for (i in grid.indices) {
        print("${i + 1}│")
        for (j in grid[i].indices) {
            print(if (grid[i][j] == "X") "." else grid[i][j])
        }
        println("│")
    }

    print("—│")
    for (i in 1..grid.size) {
        print("—")
    }
    println("│")
}

fun exposeHints(grid: MutableList<MutableList<String>>, x : Int, y : Int){
    var X = x
    var Y = y
    while (true){
        for (i in y-1 .. 0){

        }
    }
}

fun checkCenters(grid: MutableList<MutableList<String>>) {

    //Center checks of Grid excluding corners and 4 sides
    for (i in 1..grid.size - 2) {
        for (j in 1..grid[i].size - 2) {
            var centerX = 0
            if (grid[i][j] != "X" ) {
                if (grid[i - 1][j - 1] == "X") centerX++
                if (grid[i - 1][j] == "X") centerX++
                if (grid[i - 1][j + 1] == "X") centerX++
                if (grid[i][j - 1] == "X") centerX++
                if (grid[i][j + 1] == "X") centerX++
                if (grid[i + 1][j - 1] == "X") centerX++
                if (grid[i + 1][j] == "X") centerX++
                if (grid[i + 1][j + 1] == "X") centerX++
                if (centerX > 0) grid[i][j] = "$centerX"
            }
        }
    }
}

fun checkSides(grid: MutableList<MutableList<String>>) {

    //Top Sides Check of Grid excluding corners
    for (i in 1..grid[0].size - 2) {
        var firstRowX = 0
        if (grid[0][i] != "X") {
            if (grid[0][i - 1] == "X") firstRowX++
            if (grid[0][i + 1] == "X") firstRowX++
            if (grid[1][i - 1] == "X") firstRowX++
            if (grid[1][i] == "X") firstRowX++
            if (grid[1][i + 1] == "X") firstRowX++
            if (firstRowX > 0) grid[0][i] = "$firstRowX"
        }
    }

    //Left Side check of Grid excluding corners
    for (i in 1..grid.size - 2) {
        var leftSideX = 0
        if (grid[i][0] != "X") {
            if (grid[i - 1][0] == "X") leftSideX++
            if (grid[i - 1][1] == "X") leftSideX++
            if (grid[i][1] == "X") leftSideX++
            if (grid[i + 1][0] == "X") leftSideX++
            if (grid[i + 1][1] == "X") leftSideX++
            if (leftSideX > 0) grid[i][0] = "$leftSideX"
        }
    }

    //Right Side check of Grid excluding corners
    for (i in 1..grid.size - 2) {
        var rightSideX = 0
        if (grid[i][grid.lastIndex] != "X") {
            if (grid[i - 1][grid.lastIndex] == "X") rightSideX++
            if (grid[i - 1][grid.lastIndex - 1] == "X") rightSideX++
            if (grid[i][grid.lastIndex - 1] == "X") rightSideX++
            if (grid[i + 1][grid.lastIndex] == "X") rightSideX++
            if (grid[i + 1][grid.lastIndex - 1] == "X") rightSideX++
            if (rightSideX > 0) grid[i][grid.lastIndex] = "$rightSideX"
        }
    }

    //Bottom Side check of Grid excluding corners
    for (i in 1..grid.last().size - 2) {
        var bottomSideX = 0
        if (grid[grid.lastIndex][i] != "X") {
            if (grid[grid.lastIndex][i - 1] == "X") bottomSideX++
            if (grid[grid.lastIndex - 1][i - 1] == "X") bottomSideX++
            if (grid[grid.lastIndex - 1][i] == "X") bottomSideX++
            if (grid[grid.lastIndex - 1][i + 1] == "X") bottomSideX++
            if (grid[grid.lastIndex][i + 1] == "X") bottomSideX++
            if (bottomSideX > 0) grid[grid.lastIndex][i] = "$bottomSideX"
        }
    }

}


fun checkCorners(grid: MutableList<MutableList<String>>) {
    var firstCorner = 0
    var firstLastCorner = 0
    var lastFirstCorner = 0
    var lastCorner = 0

    // First Corner of Grid
    while (grid.first()[0] != "X") {
        if (grid.first()[1] == "X") firstCorner++
        if (grid[1][0] == "X") firstCorner++
        if (grid[1][1] == "X") firstCorner++
        if (firstCorner > 0) grid.first()[0] = "$firstCorner"
        break
    }

    // First Last Corner of Grid
    while (grid.first()[grid.lastIndex] != "X") {
        if (grid.first()[grid.lastIndex - 1] == "X") firstLastCorner++
        if (grid[1][grid.lastIndex] == "X") firstLastCorner++
        if (grid[1][grid.lastIndex - 1] == "X") firstLastCorner++
        if (firstLastCorner > 0) grid.first()[grid.lastIndex] = "$firstLastCorner"
        break
    }

    // Last First Corner of Grid
    while (grid.last()[0] != "X" ) {
        if (grid.last()[1] == "X") lastFirstCorner++
        if (grid[grid.size - 2][0] == "X") lastFirstCorner++
        if (grid[grid.size - 2][1] == "X") lastFirstCorner++
        if (lastFirstCorner > 0) grid.last()[0] = "$lastFirstCorner"
        break
    }
    // Last Corner of Grid
    while (grid.last()[grid.lastIndex] != "X" ) {
        if (grid.last()[grid.lastIndex - 1] == "X") lastCorner++
        if (grid[grid.lastIndex - 1][grid.lastIndex] == "X") lastCorner++
        if (grid[grid.lastIndex - 1][grid.lastIndex - 1] == "X") lastCorner++
        if (lastCorner > 0) grid.last()[grid.lastIndex] = "$lastCorner"
        break
    }
}