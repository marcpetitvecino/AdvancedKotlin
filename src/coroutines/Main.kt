package coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    // runBlocking blocks the current thread, normally used in main() functions or test functions

    // "launch" launches tasks that don't return a value asynchronously

    // async is the same as launch but tasks either return a value or exception, returns Deferred<T>
    // to get the value we must use .await()
    // await() blocks the coroutine until you get the result

    val result1 = async { expensiveCalculus() }
    val result2 = async { anotherExpensiveCalculus() }
    val finalResult = result1.await() + result2.await()
    println("The result is $finalResult")

    val cancelledJob = launch {
        repeat(20) {
            delay(100L)
            println("Calculus $it/19 realized")
        }
    }

    cancelledJob.cancel() // Suspend funs are cancellable, there are coroutines that aren't cancellable
    // To cancel them, we have to use
    while (cancelledJob.isActive) {
        // Do stuff
    }

    withTimeout(1300L) {
        // Do stuff and cancel if time > 1.3 secs but if it interrupts the coroutine it will launch an exception
    }

    // Or we can use this

    withTimeoutOrNull(1300L) {
        // Do stuff
    }

    // Job status cycle
    /*
    New
    Active
    Completing
    Cancelling
    Cancelled
    Completed
     */

    // Dispatchers
    /*
    Hoe to use -> launch(coroutineContext: CoroutineContext) {}
    Unconfined -> Not recommended, used when we need a coroutine operation to execute immediately
    Default -> Intensive CPU use
    newSingleThreadContext("myThread") -> Create new thread, it's expensive and we must manage its lifecycle
    Dispatchers.IO -> Optimized for In/Out operations
    Dispatchers.Main -> Executed on main thread, must use when interacting with UI elements (Android)
    To change context within a coroutine, we use withContext(coroutineContext: CoroutineContext) {}
         */


}

suspend fun expensiveCalculus(): Int {
    repeat(20) {
        delay(100L)
        println("Calculus $it/19 realized")
    }
    return 1
}

suspend fun anotherExpensiveCalculus(): Int {
    repeat(20) {
        delay(100L)
        println("Task $it/19")
    }
    return 1
}

suspend fun showExpensiveCalculus() {
    println("The result of the calculus is ${expensiveCalculus()}")
}