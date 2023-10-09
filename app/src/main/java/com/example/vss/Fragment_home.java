package com.example.vss;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Fragment_home extends Fragment implements LocationListener {
    private GoogleMap marker;
    private Double lat, lng;
    private Location location;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //
        //
        //map view codes
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().
                findFragmentById(R.id.home_map_frag);

        //check if the location permission is enabled or not.
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        getLocation();

        if (mapFragment != null) {
            mapFragment.getMapAsync(googleMap -> {
                marker = googleMap;
                LatLng latLng = new LatLng(lat, lng);
                MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                marker.addMarker(markerOptions);
                marker.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                marker.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));
            });
        }
        return view;
    }

    private void getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            if (androidx.core.app.ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && androidx.core.app.ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            /*locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                    5, (LocationListener) getActivity());*/
            if(locationManager != null){
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                Toast.makeText(getActivity(), "locationManager", Toast.LENGTH_SHORT).show();
                if(location != null){
                    lat = location.getLatitude();
                    lng = location.getLongitude();
//                    Toast.makeText(getActivity(), lat+" "+lng, Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
//        Toast.makeText(getActivity(), "locationChanged: "+lat+" "+lng, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}
