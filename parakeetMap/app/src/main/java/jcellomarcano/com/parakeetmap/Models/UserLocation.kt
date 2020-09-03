package jcellomarcano.com.parakeetmap.Models

class UserLocation {

    var userLatitude: Double = 10.475028
    var userLongitude: Double = -66.837357

    override fun toString(): String {
        return "%.6f,%.6f".format(userLatitude,userLongitude)
    }


}