package generics

class CovariantBox<out T>(val item: T) {
    // out keyword means items of type T will only be returned, but never consumed by the class


    // Since we marked T as out, we can't get any T parameter inside the class
    // We can say that this class is covariant in parameter T
    // If we use the in keyword, it becomes contravariant

    // Basically out forbids setter-type functions and in forbids getter-type functions
    // out = you can't receive T in a parameter in any function of this class
    // in = you can't return T in any function of this class

    // fun changeItem(anotherItem: T) {
    //     item = anotherItem
    // }

    @JvmName("getItem1")
    // This was not expected but since JVM has a function called getItem we have to implement this annotation
    // If T had the in keyword, this function wouldn't be possible
    fun getItem() = item
}