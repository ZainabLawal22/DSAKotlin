fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    var checkInMap = hashMapOf<Int, Any>()
   // val map: HashMap<Int, Any> = hashMapOf(1 to "x", 2 to "y", -1 to "zz")

    fun checkIn(id: Int, stationName: String, t: Int) {
        checkInMap[id] = (stationName to t)
        println(checkInMap)
       // checkInMap[id] = hashMapOf(stationName to t)

    }
    println(checkIn(4, "Busstop", 5))
}