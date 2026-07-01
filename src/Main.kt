import java.util.Scanner
import kotlin.random.Random

fun main(){

    val grid = MutableList(9){
        MutableList(9){
            "."
        }
    }

    print("How many mines do you want on the field? ")
    val inputOfTotalMines = readln().toInt()

    printGrids(grid)

    val random = Random

    repeat (inputOfTotalMines){

        while (true){
            val x = random.nextInt(0,grid.size)
            val y = random.nextInt(0,grid.size)

            if (grid[x][y] != "X") {
                grid[x][y] = "X"
                break
            }
        }

    }

    val scanner = Scanner(System.`in`)

    while (true){
        print("Set/unset mine marks or claim a cell as free: ")
        val x =  scanner.nextInt()
        val y = scanner.nextInt()
        val option = scanner.next()
    }
}


fun printGrids(grid : MutableList<MutableList<String>>){

    print(" │")
    for (i in 1..grid.size){
        print(i)
    }
    println("│")

    print("—│")
    for (i in 1..grid.size){
        print("—")
    }
    println("│")

    for (i in grid.indices){
        print("${i + 1}│")
        for (j in grid[i].indices){
            print(grid[i][j])
        }
        println("│")
    }

    print("—│")
    for (i in 1..grid.size){
        print("—")
    }
    println("│")
}