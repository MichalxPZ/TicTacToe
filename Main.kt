package tictactoe
import java.util.*
import kotlin.math.abs


fun display(game: CharArray){
    println("---------")
    for (i in 0..6 step 3){
        println("| ${game[i]} ${game[i+1]} ${game[i+2]} |")
    }
    println("---------")
    when(findResults(game)){
        "Game not finished" -> place(game)
        "Draw" -> println("Draw")
        "X wins" -> println("X wins")
        "O wins" -> println("O wins")
        "Impossible" -> place(game)
    }
}


fun findResults(game: CharArray): String{
    var x_wins: Boolean = false
    var o_wins: Boolean = false
    var draw: Boolean = false
    var impossible: Boolean = false
    var gamenotfinished: Boolean = false

    //impossible
    var x = 0
    var o = 0
    for(i in game){
        if(i == 'X'){
            x += 1
        }
        if(i == 'O'){
            o += 1
        }
    }
    if (abs(x - o) > 1){
        impossible = true
    }
    //horizontal check
    for (i in 0..6 step 3){
        if ("${game[i]} ${game[i+1]} ${game[i+2]}" == "X X X"){
            x_wins = true
        }
        if ("${game[i]} ${game[i+1]} ${game[i+2]}" == "O O O"){
            o_wins = true
        }
    }
    //diagonal check
    if ("${game[0]} ${game[4]} ${game[8]}" == "X X X"){
        x_wins = true
    }
    if ("${game[0]} ${game[4]} ${game[8]}" == "O O O"){
        o_wins = true
    }
    if ("${game[2]} ${game[4]} ${game[6]}" == "X X X"){
        x_wins = true
    }
    if ("${game[2]} ${game[4]} ${game[6]}" == "O O O"){
        o_wins = true
    }
    //vertical check
    for (i in 0..2) {
        if ("${game[i]} ${game[i + 3]} ${game[i + 6]}" == "X X X") {
            x_wins = true
        }
        if ("${game[i]} ${game[i + 3]} ${game[i + 6]}" == "O O O") {
            o_wins = true
        }
    }

    //draw or game not finished
    if (' ' in game && (!x_wins && !o_wins)){
       gamenotfinished = true
    }else if (!o_wins && !x_wins){
        draw = true
    }

    if (impossible == true){
        return "Impossible"
    }else{
        if (x_wins && o_wins){
            return "Impossible"
        } else{
            if (gamenotfinished){
                return "Game not finished"
            }
            if (draw){
                return "Draw"
            }
            if (x_wins){
                return "X wins"
            }
            if (o_wins){
                return "O wins"
            }
        }
    }
    return "0"
}


fun place(game: CharArray) {
    var move = 'X'
    var countX = 0
    var countO = 0
    for(i in game){
        if (i == 'X'){
            countX += 1
        }else if(i == 'O'){
            countO +=1
        }
    }
    if(countX > countO){
        move = 'O'
    }
    val scanner = Scanner(System.`in`)
    print("Enter the coordinates: ")
    try {
        val x = scanner.nextInt()
        val y = scanner.nextInt()
        val placeX: Int
        if (!(x in 1..3)) {
            println("Coordinates should be from 1 to 3!")
            place(game)
        } else if (!(y in 1..3)) {
            println("Coordinates should be from 1 to 3!")
            place(game)
        } else {
            placeX = (x - 1) * 3 + y
            if (game[placeX - 1] == 'X' || game[placeX - 1] == 'O') {
                println("This cell is occupied! Choose another one!")
                place(game)
            } else {
                game[placeX - 1] = move
            }
        }
    } catch (e: Exception){
        println("Ypu should enter numbers")
        place(game)
    }finally { }
    display(game)
}

fun main() {
    val scanner = Scanner(System.`in`)
    var game: CharArray = charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
    display(game)

}
