package lambdas

inline fun String.myFilter(predicate: (Char) -> Boolean): String {
    // Inline keyword makes higher order functions more efficient when compiled to Java
    // Inline must not be used in very large functions
    // Should be used when doing a lot of iterations on a small function
    val sb = StringBuilder()
    for (i in indices) {
        val element = get(i)
        if (predicate(element)) {
            sb.append(element)
        }
    }
    return sb.toString()
}