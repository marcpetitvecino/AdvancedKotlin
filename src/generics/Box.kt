package generics

data class Box<T: Furniture> (
    val furniture: T,
    val dimension: Dimension) {
    init {
        if (furniture.dimensions.x > dimension.x
            || furniture.dimensions.y > dimension.y
            || furniture.dimensions.z > dimension.z) {
            throw IllegalArgumentException("Furniture bigger than box!")
        }
    }
}