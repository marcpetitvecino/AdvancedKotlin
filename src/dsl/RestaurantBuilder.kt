package dsl

import java.time.LocalTime

@DslMarker
annotation class RestaurantMarker // We must use this to avoid out of scope calls when using our builders

class Restaurant2 (val name: String? = null, val address: Address2? = null, val schedule: List<Schedule2>)

class Address2 (val city: String? = null, val street: String? = null, val number: Int? = null,
               val coordinates: Coordinates2? = null)

class Schedule2 (val timeSlot: Pair<LocalTime, LocalTime>? = null)

class Coordinates2 (val longitude: Double? = null, val latitude: Double? = null)

// Builder pattern

@RestaurantMarker
class RestaurantBuilder {
    var name: String? = null
    var address: Address2? = null
    var schedule = mutableListOf<Schedule2>()

    fun build() = Restaurant2(name, address, schedule)
}

@RestaurantMarker
class AddressBuilder {
    var city: String? = null
    var street: String? = null
    var number: Int? = null
    var coordinates: Coordinates2? = null

    fun build() = Address2(city, street, number, coordinates)
}

@RestaurantMarker
class ScheduleBuilder {
    var timeSlot: Pair<LocalTime, LocalTime>? = null

    fun build() = Schedule2(timeSlot)
}

@RestaurantMarker
class CoordinatesBuilder {
    var longitude: Double? = null
    var latitude: Double? = null

    fun build() = Coordinates2(longitude, latitude)
}


// Safe-type constructor + builder pattern


fun restaurant2(block: RestaurantBuilder.() -> Unit) = RestaurantBuilder().apply(block).build()

fun RestaurantBuilder.address(block: AddressBuilder.() -> Unit) {
    address = AddressBuilder().apply(block).build()
}

fun RestaurantBuilder.schedule(block: ScheduleBuilder.() -> Unit) {
    schedule.add(ScheduleBuilder().apply(block).build())
}

fun AddressBuilder.coordinates(block: CoordinatesBuilder.() -> Unit) {
    coordinates = CoordinatesBuilder().apply(block).build()
}

// Infix functions always have to be member functions or extension funtions

infix fun Int.myInfix(minutes: Int) = LocalTime.of(this, minutes)

infix fun LocalTime.until(closure: LocalTime) = Pair(this, closure)