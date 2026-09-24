package minesweeper

import java.util.Scanner
import kotlin.random.Random

fun main() {
    val scanner = Scanner(System.`in`)

    print("How many mines do you want on the field? ")
    val inputNumberOfMines = readln().toInt()

    val minesweeper = Minesweeper(inputNumberOfMines)
    while (true) {
        minesweeper.printPlayerGrid()
        print("Set/unset mine marks or claim a cell as free: ")
        val column = scanner.nextInt() - 1
        val row = scanner.nextInt() - 1
        val action = scanner.next()
        if (action.lowercase() == "free") {
            if (minesweeper.gameGrid[row][column] == "X") {
                minesweeper.printGameGrid()
                println("You stepped on a mine and failed!")
                break
            }
            minesweeper.floodfill(row, column)
        }
        if (action.lowercase() == "mine") {
            if (minesweeper.gameGrid[row][column] != "X"){
                minesweeper.printGameGrid()
                println("You marked on a free cell and failed!")
                break
            }
            if (minesweeper.playerGrid[row][column] == "*"){
                minesweeper.playerGrid[row][column] = "."
                minesweeper.minesFound--
            } else {
                minesweeper.playerGrid[row][column] = "*"
                minesweeper.minesFound++
            }
        }

        if (minesweeper.minesFound == inputNumberOfMines ||
            minesweeper.cellsFound + minesweeper.minesFound == 81 - (inputNumberOfMines - minesweeper.minesFound)
        ) {
            minesweeper.printPlayerGrid()
            println("Congratulations! You found all the mines!")
            break
        }
    }

}

class Minesweeper(numberOfMines: Int) {

    val gameGrid = MutableList(9) {
        MutableList(9) { "." }
    }

    val playerGrid = MutableList(9) {
        MutableList(9) { "." }
    }

    var minesFound = 0
    var cellsFound = 0

    fun printPlayerGrid() {
        println("\n │123456789│")

        println("—│—————————│")

        for (i in playerGrid.indices) {
            print("${i + 1}│")
            for (j in playerGrid[i].indices) {
                print(playerGrid[i][j])
            }
            println("│")
        }

        println("—│—————————│")
    }

    fun printGameGrid() {
        println("\n │123456789│")

        println("—│—————————│")

        for (i in gameGrid.indices) {
            print("${i + 1}│")
            for (j in gameGrid[i].indices) {
                print(gameGrid[i][j])
            }
            println("│")
        }

        println("—│—————————│")
    }

    init {
        val random = Random

        repeat(numberOfMines) {
            val pickRandomColumn = random.nextInt(0, 9)
            val pickRandomRow = random.nextInt(0, 9)

            gameGrid[pickRandomRow][pickRandomColumn] = "X"
        }

        checkCenters(gameGrid)
        checkCorners(gameGrid)
        checkSides(gameGrid)
    }

    fun floodfill(row: Int, column: Int) {

        if (row < 0 || row >= gameGrid.size || column < 0 || column >= gameGrid[0].size || gameGrid[row][column] == "/") return

        val list = listOf("1", "2", "3", "4", "5", "6", "7", "8")

        if (list.contains(gameGrid[row][column])) {
            playerGrid[row][column] = gameGrid[row][column]
            cellsFound++
            return
        } else if (gameGrid[row][column] == ".") {
            gameGrid[row][column] = "/"
            playerGrid[row][column] = "/"
            cellsFound++
        }

        floodfill(row + 1, column + 1)
        floodfill(row + 1, column)
        floodfill(row + 1, column - 1)
        floodfill(row, column + 1)
        floodfill(row, column - 1)
        floodfill(row - 1, column + 1)
        floodfill(row - 1, column)
        floodfill(row - 1, column - 1)

    }

}


fun checkCenters(gameGrid: MutableList<MutableList<String>>) {

    //Center checks of gameGrid excluding corners and 4 sides
    for (i in 1..gameGrid.size - 2) {
        for (j in 1..gameGrid[i].size - 2) {
            var centerX = 0
            if (gameGrid[i][j] != "X") {
                if (gameGrid[i - 1][j - 1] == "X") centerX++
                if (gameGrid[i - 1][j] == "X") centerX++
                if (gameGrid[i - 1][j + 1] == "X") centerX++
                if (gameGrid[i][j - 1] == "X") centerX++
                if (gameGrid[i][j + 1] == "X") centerX++
                if (gameGrid[i + 1][j - 1] == "X") centerX++
                if (gameGrid[i + 1][j] == "X") centerX++
                if (gameGrid[i + 1][j + 1] == "X") centerX++
                if (centerX > 0) gameGrid[i][j] = "$centerX"
            }
        }
    }
}


fun checkSides(gameGrid: MutableList<MutableList<String>>) {

    //Top Sides Check of gameGrid excluding corners
    for (i in 1..gameGrid[0].size - 2) {
        var firstRowX = 0
        if (gameGrid[0][i] != "X") {
            if (gameGrid[0][i - 1] == "X") firstRowX++
            if (gameGrid[0][i + 1] == "X") firstRowX++
            if (gameGrid[1][i - 1] == "X") firstRowX++
            if (gameGrid[1][i] == "X") firstRowX++
            if (gameGrid[1][i + 1] == "X") firstRowX++
            if (firstRowX > 0) gameGrid[0][i] = "$firstRowX"
        }
    }

    //Left Side check of gameGrid excluding corners
    for (i in 1..gameGrid.size - 2) {
        var leftSideX = 0
        if (gameGrid[i][0] != "X") {
            if (gameGrid[i - 1][0] == "X") leftSideX++
            if (gameGrid[i - 1][1] == "X") leftSideX++
            if (gameGrid[i][1] == "X") leftSideX++
            if (gameGrid[i + 1][0] == "X") leftSideX++
            if (gameGrid[i + 1][1] == "X") leftSideX++
            if (leftSideX > 0) gameGrid[i][0] = "$leftSideX"
        }
    }

    //Right Side check of gameGrid excluding corners
    for (i in 1..gameGrid.size - 2) {
        var rightSideX = 0
        if (gameGrid[i][gameGrid.lastIndex] != "X") {
            if (gameGrid[i - 1][gameGrid.lastIndex] == "X") rightSideX++
            if (gameGrid[i - 1][gameGrid.lastIndex - 1] == "X") rightSideX++
            if (gameGrid[i][gameGrid.lastIndex - 1] == "X") rightSideX++
            if (gameGrid[i + 1][gameGrid.lastIndex] == "X") rightSideX++
            if (gameGrid[i + 1][gameGrid.lastIndex - 1] == "X") rightSideX++
            if (rightSideX > 0) gameGrid[i][gameGrid.lastIndex] = "$rightSideX"
        }
    }

    //Bottom Side check of gameGrid excluding corners
    for (i in 1..gameGrid.last().size - 2) {
        var bottomSideX = 0
        if (gameGrid[gameGrid.lastIndex][i] != "X") {
            if (gameGrid[gameGrid.lastIndex][i - 1] == "X") bottomSideX++
            if (gameGrid[gameGrid.lastIndex - 1][i - 1] == "X") bottomSideX++
            if (gameGrid[gameGrid.lastIndex - 1][i] == "X") bottomSideX++
            if (gameGrid[gameGrid.lastIndex - 1][i + 1] == "X") bottomSideX++
            if (gameGrid[gameGrid.lastIndex][i + 1] == "X") bottomSideX++
            if (bottomSideX > 0) gameGrid[gameGrid.lastIndex][i] = "$bottomSideX"
        }
    }
}


fun checkCorners(gameGrid: MutableList<MutableList<String>>) {
    var firstCorner = 0
    var firstLastCorner = 0
    var lastFirstCorner = 0
    var lastCorner = 0

    // First Corner of gameGrid
    while (gameGrid.first()[0] != "X") {
        if (gameGrid.first()[1] == "X") firstCorner++
        if (gameGrid[1][0] == "X") firstCorner++
        if (gameGrid[1][1] == "X") firstCorner++
        if (firstCorner > 0) gameGrid.first()[0] = "$firstCorner"
        break
    }

    // First Last Corner of gameGrid
    while (gameGrid.first()[gameGrid.lastIndex] != "X") {
        if (gameGrid.first()[gameGrid.lastIndex - 1] == "X") firstLastCorner++
        if (gameGrid[1][gameGrid.lastIndex] == "X") firstLastCorner++
        if (gameGrid[1][gameGrid.lastIndex - 1] == "X") firstLastCorner++
        if (firstLastCorner > 0) gameGrid.first()[gameGrid.lastIndex] = "$firstLastCorner"
        break
    }

    // Last First Corner of gameGrid
    while (gameGrid.last()[0] != "X") {
        if (gameGrid.last()[1] == "X") lastFirstCorner++
        if (gameGrid[gameGrid.size - 2][0] == "X") lastFirstCorner++
        if (gameGrid[gameGrid.size - 2][1] == "X") lastFirstCorner++
        if (lastFirstCorner > 0) gameGrid.last()[0] = "$lastFirstCorner"
        break
    }
    // Last Corner of gameGrid
    while (gameGrid.last()[gameGrid.lastIndex] != "X") {
        if (gameGrid.last()[gameGrid.lastIndex - 1] == "X") lastCorner++
        if (gameGrid[gameGrid.lastIndex - 1][gameGrid.lastIndex] == "X") lastCorner++
        if (gameGrid[gameGrid.lastIndex - 1][gameGrid.lastIndex - 1] == "X") lastCorner++
        if (lastCorner > 0) gameGrid.last()[gameGrid.lastIndex] = "$lastCorner"
        break
    }
}

