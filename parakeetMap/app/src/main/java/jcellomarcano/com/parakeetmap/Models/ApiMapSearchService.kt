package jcellomarcano.com.parakeetmap.Models

import android.app.Activity
import android.app.Dialog
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import jcellomarcano.com.parakeetmap.Views.MapsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull


class ApiMapSearchService {
    // VARIABLES PARA TODAS LAS PETICIONES
    private val gson = Gson()
    private val jobTimeout = 30000L
    private val TAG = "ApiMapSearchService"


    //LocationVaribale
    private lateinit var mMap: GoogleMap
    private lateinit var  locationContext: Location
    private lateinit var lastLocation: Location
    private lateinit var locationManager: LocationManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var typePOI = "ATM"

    private fun initMap(activity: Activity){
        if(ActivityCompat.checkSelfPermission(activity.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                MapsActivity.USER_PERMISSION_LOCATION_REQUEST
            )
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(activity) { location ->
            if(location != null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                locationContext = location
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 10f))
            }
        }
    }
    
    fun getNearbyPoints(activity: Activity){
        
        
        CoroutineScope(IO).launch { 
            val job : Unit? = withTimeoutOrNull(jobTimeout){
                makeRequestNearbyPoints(activity)
            }
        }
        
    }

    private fun makeRequestNearbyPoints(activity: Activity) {
        initMap(activity)

    }
}