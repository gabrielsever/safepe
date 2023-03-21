package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.myapplication.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var pos: LatLng
    private  var lat: Double = 0.0
    private  var log: Double = 0.0

    val database = DatabaseManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val report_button = findViewById<Button>(R.id.report_button)
        report_button.setOnClickListener {
            // Implementar a lógica para reportar ocorrências
            lat = pos.latitude
            log = pos.longitude

            database.insereReport(lat,log, "desc")


        }

        val addMarkerButton = findViewById<Button>(R.id.add_marker_button)
        addMarkerButton.setOnClickListener {
            //Limpa os marcadores
            mMap.clear()

            mMap.setOnMapClickListener { latLng ->
                // Adicione o marcador
                mMap.addMarker(MarkerOptions().position(latLng))
                pos = latLng

                // Remova o ouvinte para evitar novas marcações
                mMap.setOnMapClickListener(null)
            }
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sp = LatLng(-23.5489, -46.6388)
        mMap.addMarker(MarkerOptions().position(sp).title("Relatar ocorrência"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sp))

        mMap.setOnMapClickListener { latLng ->
            // Adicione um marcador na posição clicada
            mMap.addMarker(MarkerOptions().position(latLng).title("Relatar ocorrência"))
            mMap.setOnMapClickListener(null)

        }

    }
}