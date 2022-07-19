package generics

fun main() {
    val sofa = Sofa("Praga", 3, Dimension(5.2, 1.8, 1.6))
    val sofaBox = Box(sofa, Dimension(6.0, 2.0, 1.8))

    val table = Table("Oak wood", Dimension(2.5, 2.5, 1.7))
    // We can reuse box with different furniture since it has a generic parameter
    val tableBox = Box(table, Dimension(3.0, 3.0, 2.0)) //

    val sofaB = Sofa("Confort", 6, Dimension(8.2, 1.5, 2.3))
    val tableB = Table("Cheap plastic", Dimension(1.5, 1.5, 1.0))

    val inventory = listOf(table, table, sofa, sofaB, table, sofaB, tableB)

    // Function with generics
    val numberOfTables = numberOfApparitions(table, inventory)

    val myAny: Any = "This is a String in an Any"
    val myString: String = "This is a normal String"

    printAny(myAny)
    printAny(myString) // THis works since String is a child of Any

    val myAnyList = mutableListOf(myAny)
    val myStringList = mutableListOf(myString)

    printAnyList(myAnyList)
    // printAnyList(myStringList) -> This is an error since MutableList<String> is not a child of MutableList<Any>
    // They are both MutableLists with generics, it's not the same as String being a child of Any

    val covariantBox = CovariantBox("abc")
    showItem(covariantBox) // Same as above, to fix is we use te "out" keyword
}

fun printAny(item: Any) {
    println(item)
}

fun printAnyList(list: MutableList<Any>) {
    for (x in list) {
        println(x)
    }
}

fun showItem(box: CovariantBox<Any>) {
    print(box.getItem().toString())
}

/*
GENERIC CHARACTERS:
E -> ELEMENT
K -> KEY
N -> NUMBER
T -> TYPE
V -> VALUES

Generics default to Any? type, if we want to make it non-nullable we must use <T: Any>
 */