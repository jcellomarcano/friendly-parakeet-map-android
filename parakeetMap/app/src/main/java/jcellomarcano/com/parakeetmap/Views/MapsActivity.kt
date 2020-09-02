package jcellomarcano.com.parakeetmap.Views

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.ktx.addMarker
import jcellomarcano.com.parakeetmap.Models.ApiMapAdapter
import jcellomarcano.com.parakeetmap.Models.ApiMapSearchService
import jcellomarcano.com.parakeetmap.Models.PoiRepositoryImpl
import jcellomarcano.com.parakeetmap.Models.UserLocation
import jcellomarcano.com.parakeetmap.R
import jcellomarcano.com.parakeetmap.ViewModels.CandidateViewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var candidateViewModel: CandidateViewModel? = null

    private lateinit var lastLocation: Location
    private var httpResponse: ApiMapSearchService? = null

    companion object {
        const val USER_PERMISSION_LOCATION_REQUEST =1
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        this.initMap()

        // Add a marker in Sydney and move the camera

        mMap.setOnMarkerClickListener((this))
        mMap.uiSettings.isZoomControlsEnabled = true

        val cosonguitaHouse = LatLng(10.368141, -66.965026)
        mMap.addMarker{
            position(cosonguitaHouse)
            title("Cosonguita House")
            snippet("Donde queda Cosonguita House")
        }

//
//        Log.i("MapsActivity", "onMapReady: ${httpResponse.getPoiMaps()}")
//        val poiRequestGet = PoiRepositoryImpl()
        Log.i("MAPS", "onMapReady: prelaunch")
        Log.i("MAPS", "onMapReady: "+ UserLocation.userLatitud)
        Log.i("MAPS", "onMapReady: "+ UserLocation.userLongitude)
        val httpResponse = ApiMapSearchService()
        httpResponse.makeRequestNearbyPoints()
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return false
    }

    private fun initMap(){
        if(ActivityCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                USER_PERMISSION_LOCATION_REQUEST
            )
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if(location != null){
                lastLocation = location
                UserLocation.userLatitud = location.latitude.toString()
                UserLocation.userLongitude = location.longitude.toString()
                val currentLatLong = LatLng(location.latitude,location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 10f))
            }
        }
    }

    fun getAllCandidates(){

    }

    fun setupBindings(savedInstanceState: Bundle?){
        var activityMainBinding: jcellomarcano.com.parakeetmap.DataBinderMapperImpl
                = DataBindingUtil.setContentView(this, R.layout.activity_maps)

        candidateViewModel = CandidateViewModel() by viewModels()

        activityMainBinding.setModel(couponViewModel)
        setupListUpdate()

    }


}