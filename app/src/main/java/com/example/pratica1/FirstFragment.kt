package com.example.pratica1

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var manager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var light = manager.getDefaultSensor(Sensor.TYPE_LIGHT)

        manager.registerListener(
            object : SensorEventListener {
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                }

                override fun onSensorChanged(event: SensorEvent?) {
                    if (event != null && _binding != null) {
                        var light = event.values[0]

                        binding.textLight.text = "Light: $light lx"
                    }
                }
            },
            light,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}