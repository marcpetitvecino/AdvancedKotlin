package generics

fun <T> numberOfApparitions(item: T, list: List<T>): Int {
    return list.count{ it == item }
}

val <T> List<T>.secondLast: T
    get() = this[size-2]

// fun <T: Number> List<T>.sum(): T

fun <T> foo(param: T) where T: Any, T: Comparable<T> { // Restrictions

}