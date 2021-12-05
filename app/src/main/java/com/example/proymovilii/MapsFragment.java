package com.example.proymovilii;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    UiSettings uiSettings;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            uiSettings = googleMap.getUiSettings();

            LatLng tienda = new LatLng(-17.38360365590329, -66.16947671505993);
            googleMap.addMarker(new MarkerOptions().position(tienda).title("El rincon del Otaku"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(tienda));

            //tipo de mapa
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            //agregar zoom:
            uiSettings.setZoomControlsEnabled(true);
            //gestos:
            uiSettings.setZoomGesturesEnabled(true);
            //gestos para desplazamiento:
            uiSettings.setScrollGesturesEnabled(true);
            //inclinacion con gestos:
            uiSettings.setTiltGesturesEnabled(true);
            //rotacion con gestos:
            uiSettings.setRotateGesturesEnabled(true);

            //camara personalizada:
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(tienda)
                    .zoom(20)
                    .build();
            //asignar la camara al mapa:
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}