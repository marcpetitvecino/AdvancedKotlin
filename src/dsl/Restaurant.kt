package dsl

import java.time.LocalTime
import kotlin.time.Duration.Companion.minutes

class Restaurant (var name: String? = null, var address: Address? = null, var schedule: Schedule? = null)

class Address (var city: String? = null, var street: String? = null, var number: Int? = null,
               var coordinates: Coordinates? = null)

class Schedule (var timeSlot: Pair<LocalTime, LocalTime>? = null)

class Coordinates (var longitude: Double? = null, var latitude: Double? = null)

// Safe-type constructor

fun restaurant(block: Restaurant.() -> Unit) = Restaurant().apply(block)

fun Restaurant.address(block: Address.() -> Unit) {
    address = Address().apply(block)
}

fun Restaurant.schedule(block: Schedule.() -> Unit) {
    schedule = Schedule().apply(block)
}

fun Address.coordinates(block: Coordinates.() -> Unit) {
    coordinates = Coordinates().apply(block)
}
