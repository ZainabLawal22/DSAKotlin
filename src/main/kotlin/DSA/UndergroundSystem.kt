package DSA


/**Sure, here are some possible clarifying questions you could ask about the LeetCode problem:

Can we assume that the input for station names will always be valid strings? In other words, should we check for any input validation or error handling?
Can we assume that the id for each customer is unique?
Can we assume that the start and end stations will always be different? In other words, is it possible for a customer to check in and out at the same station?
Can we assume that the getAverageTime() method will only be called for pairs of stations that have at least one direct travel record (i.e. check in at start station and check out at end station)?
Can we use any built-in libraries or data structures to implement the class, or should we implement everything from scratch?
Can we assume that the time values will always be integers and represent seconds or minutes?
Can we assume that there will not be more than one customer with the same id checking in or out at the same time?


Here's how this implementation works:

We use two HashMaps to store the information about customer travel times and check-ins. The travelTimes HashMap maps station pairs (in the form of "startStation-endStation") to a list of travel times for customers who have traveled between those stations. The checkIns HashMap maps customer IDs to a pair of the station name and the check-in time.
The checkIn() method takes an ID, a station name, and a time, and stores the check-in information in the checkIns HashMap.
The checkOut() method takes an ID, a station name, and a time, and retrieves the corresponding check-in information from the checkIns HashMap. It then calculates the travel time by subtracting the check-in time from the check-out time and stores the travel time in the travelTimes HashMap.
The getAverageTime() method takes two station names and retrieves the corresponding travel times from the travelTimes HashMap. It then calculates the average travel time by summing the travel times and dividing by the number of travel times.


Sure, I'd be happy to answer a follow-up question!

What would be the time and space complexity of this implementation?

The time complexity of the checkIn() and checkOut() methods is O(1) since they only involve simple HashMap lookups and updates. The time complexity of the getAverageTime() method is O(n), where n is the number of travel times between the start and end stations, since we need to iterate over the list of travel times and calculate the sum. In practice, the number of travel times between any two stations is likely to be small, so the average-case time complexity of getAverageTime() is probably close to O(1).

The space complexity of this implementation is O(n), where n is the number of unique station pairs (i.e., "startStation-endStation" pairs) for which we have travel time data. In practice, the number of unique station pairs is likely to be small compared to the number of check-ins and check-outs, so the space complexity is unlikely to be a concern.

 */

// this implementation is without error handling exception
/** class UndergroundSystem {
    private val travelTimes = hashMapOf<String, MutableList<Int>>()
    private val checkIns = hashMapOf<Int, Pair<String, Int>>()

    fun checkIn(id: Int, stationName: String, t: Int) {
        checkIns[id] = stationName to t
    }

    fun checkOut(id: Int, stationName: String, t: Int) {
        val (startStation, checkInTime) = checkIns[id]!!
        val travel = travelTimes.getOrPut("$startStation-$stationName") { mutableListOf() }
        travel.add(t - checkInTime)
    }

    fun getAverageTime(startStation: String, endStation: String): Double {
        val travel = travelTimes["$startStation-$endStation"]!!
        return travel.sum().toDouble() / travel.size
    }
}*/


class UndergroundSystem {
    private val travelTimes = hashMapOf<String, MutableList<Int>>()
    private val checkIns = hashMapOf<Int, Pair<String, Int>>()

    fun checkIn(id: Int, stationName: String, t: Int) {
        if (checkIns.containsKey(id)) {
            throw IllegalArgumentException("Customer $id is already checked in.")
        }
        checkIns[id] = stationName to t
    }

    fun checkOut(id: Int, stationName: String, t: Int) {
        val checkInInfo = checkIns[id] ?: throw IllegalArgumentException("Customer $id is not checked in.")
        val (startStation, checkInTime) = checkInInfo
        if (startStation == stationName) {
            throw IllegalArgumentException("Customer $id cannot check out at the same station as check-in.")
        }
        val travel = travelTimes.getOrPut("$startStation-$stationName") { mutableListOf() }
        travel.add(t - checkInTime)
        checkIns.remove(id)
    }

    fun getAverageTime(startStation: String, endStation: String): Double {
        val travel = travelTimes["$startStation-$endStation"] ?: throw IllegalArgumentException("No travel time data for $startStation to $endStation.")
        return travel.sum().toDouble() / travel.size
    }
}

