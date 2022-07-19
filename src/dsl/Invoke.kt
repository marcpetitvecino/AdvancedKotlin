package dsl

class Invoke(val name: String) {

    // With this we are indicating kotlin that when we call an object of Invoke() class as a function
    // We want this function to execute
    operator fun invoke() {
        println("My name is $name and it has ${name.length} characters")
    }

    operator fun invoke(anotherName: String) {
        println("My name is $anotherName and it has ${anotherName.length} characters")
    }

}