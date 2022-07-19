package dsl

import java.time.LocalTime

fun main() {
    // A DSL is a programming language designed with the purpose of solving a specific problem
    // Examples: HTML, Gradle, SQL

    // Kotlin syntactic sugar

    // Extension functions
    "a".uppercase() // From: StringUtils.capitalize("a")

    // Operator overloading
    "a" > "b" // From: a.compareTo(b) > 0

    // Convention get/set
    val map = mapOf(Pair("key","data"))
    map["key"] // From: map.get("key")

    // Infix functions
    1 to 2 // From 1.to(2)

    // Unstructured declarations
    val user = User("Test", "12345")
    val (username, password) = user // From val username = user.name, val password = user.password

    // Lambdas out of parenthesis
    map.filter { it.key == "a" } // From map.filter({it.key == "a"})

    // Lambdas with receiver
    val name = "Johnny"
    // This is a normal lambda
    val myString = buildString {
        it.append("My name is ")
        it.append(name)
    }

    // With this type of lambda, we get "this" instead of "it" so we can omit it
    val myOtherString = buildStringWithReceiver {
        append("My name is ")
        append(name)
    }

    // Safe-type constructor
    // This would be a normal constructor
    val normalSchedule = Schedule(Pair(LocalTime.of(12, 30), LocalTime.of(15, 45)))
    val normalCoordinates = Coordinates(41.3980, 2.1651)
    val normalAddress = Address("Barcelona", "Avinguda Diagonal", 400, normalCoordinates)

    val normalRestaurant = Restaurant("Hard Rock Cafe", normalAddress, normalSchedule)

    // But if we use the lambdas we created inside Restaurant.kt

    val safeRestaurant = restaurant {
        this.name = "Hard Rock Cafe" // Name already exists on this class and I'm lazy
        address {
            city = "Barcelona"
            street = "Avinguda Diagonal"
            number = 400
            coordinates {
                longitude = 41.3980
                latitude = 2.1651
            }
        }
        schedule {
            timeSlot = Pair(LocalTime.of(12, 30), LocalTime.of(15, 45))
        }
    }

    // Safe type + builder instantiation is the same as above, but since the variables are immutable it's safer

    val safeRestaurantBuilder = restaurant2 {
        this.name = "Hard Rock Cafe" // Name already exists on this class and I'm lazy
        address {
            city = "Barcelona"
            street = "Avinguda Diagonal"
            number = 400
            coordinates {
                longitude = 41.3980
                latitude = 2.1651
            }
        }
        // Since schedule is a list, we can add as many as we want
        // We could also create another class and make it add TimeSlot objects instead of multiple schedules
        schedule {
            timeSlot = Pair(LocalTime.of(12, 30), LocalTime.of(15, 45))
        }

        // We can use the infix fun "to" to simplify this
        schedule {
            timeSlot = LocalTime.of(16, 30) to LocalTime.of(18, 0)
        }
        // Infix functions
        schedule {
            timeSlot = (16 myInfix 30) until (18 myInfix 0)
            // Once we have implemented scope control with the annotation class RestaurantMarker
            // We can still access anything we need explicitly
            this@restaurant2.name = "Changed!"
        }
    }


    // Invoke convention

    val a = Invoke("Marc")
    a() // This will execute the operator fun
    a("Johnny") // This will execute the overloaded operator fun
    // We can also overload the function with a lambda
}

// This is a normal lambda
fun buildString(builderAction: (StringBuilder) -> Unit): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}

// This is a lambda with receiver
fun buildStringWithReceiver(builderAction: (StringBuilder).() -> Unit): String {
    val sb = StringBuilder()
    sb.builderAction()
    return sb.toString()
}