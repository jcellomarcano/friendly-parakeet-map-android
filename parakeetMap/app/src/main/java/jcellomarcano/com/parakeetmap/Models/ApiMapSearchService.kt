package jcellomarcano.com.parakeetmap.Models

import android.app.Activity
import android.app.Dialog
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import jcellomarcano.com.parakeetmap.Constants.GlobalConstants
import jcellomarcano.com.parakeetmap.Views.MapsActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiMapSearchService {
    // VARIABLES PARA TODAS LAS PETICIONES
    private val gson = Gson()
    private val jobTimeout = 30000L
    private val TAG = "ApiMapSearchService"
    private lateinit var urlApi: String


    //LocationVaribale
    private lateinit var mMap: GoogleMap
//    private lateinit var  locationContext: Location
    private var lastLocation: Location? = null
    private lateinit var locationManager: LocationManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var  globalConstant: GlobalConstants

    private var typePOI = "ATM"

    private fun initMap(activity: Activity): Location? {
        if(ActivityCompat.checkSelfPermission(activity.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                MapsActivity.USER_PERMISSION_LOCATION_REQUEST
            )
        }
//        mMap.isMyLocationEnabled = true
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        fusedLocationClient.lastLocation.addOnSuccessListener(activity) { location ->
            if(location != null){
                 this.lastLocation = location
//                val currentLatLong = LatLng(location.latitude, location.longitude)
//                locationContext = location
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 10f))
            }
        }
        return this.lastLocation
    }


    fun makeRequestNearbyPoints(): ApiMapAdapter {
//        val location = initMap(activity)

//        urlApi = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${this.lastLocation?.latitude},${this.lastLocation?.longitude}&radius=1500&type=restaurant&keyword=cruise&key=${GlobalConstants.API_MAPS_KEY}"
        urlApi = "https://maps.googleapis.com/maps/api/place/nearbysearch/"
        val authInterceptor = Interceptor { chain ->
            val url = chain.request().url().newBuilder()
//                .addQueryParameter("format", "json")
//                .addQueryParameter("location", "-33.8670522,151.1957362")
//                .addQueryParameter("radius","1500")
//                .addQueryParameter("type", "restaurant")
//                .addQueryParameter("keyword", "cruise")
//                .addQueryParameter("", GlobalConstants.API_MAPS_KEY)
                .build()

            Log.i(TAG, "makeRequestNearbyPoints: $url")

            val newRequest = chain.request()
                .newBuilder()
                .url(url)
                .build()

            chain.proceed(newRequest)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(urlApi)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return  retrofit.create(ApiMapAdapter::class.java)
    }



}