package scopefunctions

import java.io.File
import java.net.URL

fun main() {
    val names = mutableListOf("Johnny, Jake, Sam, Mary")
    names.filter { it.contains('a') }
        .sorted()
        .let {
            println(it)
        }

    // With is recommended when calling member functions of an object, and we are not going to use the lambda result
    with(names) { // If the name in the scope is "this", we can omit it
        add("Emma")
        println("$this contains $size elements")
    }

    val user = User("Marc", "12345")
    with(user) {
        name = "Marc"
        password = "999999"
    }

    // Apply is most commonly used as a builder to instantiate objects
    // Apply "applies" the code inside the block and then returns the object
    val cheese = Ingredients().apply {
        calories = 110.0
        carbohydrates = 25.0
        fats = 12.0
        proteins = 1.2
    }

    // Run is most commonly used when you need to do a configuration and an action
    // And what you want is the result of the action
    val connection = URL("https://es.wikipedia.org").openConnection()
    val data = connection.run {
        connectTimeout = 2000
        getInputStream().bufferedReader().readText()
    }
    println(data)

    // Also is most commonly used when we want to chain actions that relate one to another
    val userList = mutableListOf<User>()
    val newUser = User("Travis", "91239312")
        .also { userList.add(it) }
        .also { println("User $it added") }



    val content = File("my-file.txt")
        .takeIf { it.exists() && it.canRead() } // Returns the item if true
        ?.readText()
    // We can also use takeUnless to get the item if the boolean is false


    // Tips

    // With and run are almost the same, but we should use run when dealing with nullable data types

    // We should use let over run when we need to pass "it" as an argument

    // Run and apply are used in similar cases but apply returns the object and run returns the result of the lambda

}

fun greet(list: List<String?>) {
    for (name in list) {
        // Let is also used for quick null checks
        name?.let {
            println("Hello $it")
        } ?: run {
            println("Name is null")
        }
    }
}