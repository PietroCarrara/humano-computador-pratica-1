package com.example.pratica1

import android.Manifest
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.example.pratica1.databinding.FragmentFirstBinding
import kotlin.math.abs

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var _location: LocationManager? = null

    private val location get() = _location!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var activity = this.activity!!

        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            42
        )

        this._location = activity.baseContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        location.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 6000, 10f
        ) { p0 ->
            setPos(p0)
        }

        var l = location.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (l != null) {
            setPos(l)
        }
    }

    private fun setPos(p: Location) {
        binding.textLight.text = "Altitude: ${p.altitude}\nLatitude ${p.latitude}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _location = null
    }
}