package lambdas

fun main() {
                        // Parameters -> body
    val multiplication = { x: Int, y: Int -> x * y } // (Int, Int) -> Int
    val showMessage = { x: String -> println(x) } // (String) -> Unit
    val showHello = { println("Hello") } // () -> Unit

    val cylinderVolume = myLambda@{ radius: Double, height: Double -> // We can give the scope a tag
        if (height < 0 || radius < 0) return@myLambda 0.0 // Do this to return in a tagged lambda
        val baseArea = Math.PI * radius * radius
        // Line marked with ^lambda is the return of the function, we can also use return if scope tag specified
        baseArea * height
    }


    val myList = listOf(2, 6, 10, 8, 22)
    val filteredList = myList.filter { it > 5 } // Higher order function

    foo { println("Hello") } // If it's the last argument, no parenthesis needed
    foo(showHello) // Same stuff

    showBinaryOperation("multiplication", 2, 3) { x, y ->
        x * y
    }
    showBinaryOperation("add", 2, 3) { x, y ->
        x + y
    }

    val myString = "sd0234nsdf985nsd13"
    val onlyNumbers = myString.myFilter { it in '0'.. '9' }
    println(onlyNumbers)


    // Collections and sequences
    val intList = listOf(5, 9 ,3)
    val squareMoreThan25 = intList
        .map { it * it } // 25, 81, 9
        .filter { it > 25 } // 81
    // With a bigger list using collections may cause a performance overload
    // The best solution is to use sequences
    val squareMoreThan25Sequence = intList
        .asSequence()
        .map { it * it } // Intermediate operation
        .filter { it > 25 } // Intermediate operation
        .toList() // Terminal operation
    // Sequences are lazy evaluated, the processing is delayed until the result is needed
    // To get a better performance when using big collections, we should use sequences
    // If we have to apply a chain of operations on our collections, the best is to use sequences
    // We can also create sequences using the function generateSequence
    var counter = 5
    val mySequence = generateSequence { (counter--).takeIf { it > 0 } }
    println(mySequence.toList())
    val myOtherSequence = generateSequence(5) { it - 1 }
    println(myOtherSequence.take(5).toList())
}

fun foo(lambda: () -> Unit) {
    println("Running foo")
    lambda()
}
fun showBinaryOperation(operationName: String, x: Int, y: Int, operation: (Int, Int) -> Int) {
    val result = operation(x, y)
    println("The result of $operationName is $result")
}