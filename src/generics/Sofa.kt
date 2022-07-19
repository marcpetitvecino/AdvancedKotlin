package generics

class Sofa(
    name: String,
    val numberOfSeats: Int,
    dimensions: Dimension
): Furniture(name, dimensions)