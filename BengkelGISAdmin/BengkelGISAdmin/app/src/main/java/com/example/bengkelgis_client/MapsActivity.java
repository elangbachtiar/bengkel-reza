package com.example.bengkelgis_client;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.String;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private String[] id, nama ,telp ,email ,alamat ,hari ,jam_buka ,jam_tutup ,gambar ,rating ;
    int numData;
    LatLng latLng[];
    Boolean markerD[];
    private Double[] latitude, longitude;
    int REQUEST_CHECK_SETTINGS = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLokasi();
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    // Add a marker in Sydney and move the camera
//                    LatLng jakbar = new LatLng(location.getLatitude(), location.getLongitude());
                    LatLng jakbar = new LatLng(-6.1669575,106.790465);
                    mMap.addMarker(new MarkerOptions().position(jakbar).title("Your Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(jakbar));
                    mMap.getUiSettings().setMyLocationButtonEnabled(true);
                    mMap.getUiSettings().setZoomControlsEnabled(true);
                    mMap.getUiSettings().setCompassEnabled(true);
                    mMap.getUiSettings().setMapToolbarEnabled(true);
                    // Do it all with location
                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    // Display in Toast
//                    Toast.makeText(MapsActivity.this,
//                            "Lat : " + location.getLatitude() + " Long : " + location.getLongitude(),
//                            Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private void getLokasi() {
        String url = "http://192.168.1.9/bengkel_gis_api/";
        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET, url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        numData = response.length();
                        Log.d("DEBUG_", "Parse JSON");
                        latLng = new LatLng[numData];
                        markerD = new Boolean[numData];
                        nama = new String[numData];
                        alamat = new String[numData];
                        telp = new String[numData];
                        email = new String[numData];
                        hari = new String[numData];
                        jam_buka = new String[numData];
                        jam_tutup = new String[numData];
                        rating = new String[numData];
                        id = new String[numData];
                        gambar = new String[numData];
                        latitude = new Double[numData];
                        longitude = new Double[numData];

                        for (int i = 0; i < numData; i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                id[i] = data.getString("id");
                                latLng[i] = new LatLng(data.getDouble("latitude"),
                                        data.getDouble("longitude"));
                                nama[i] = data.getString("nama");
                                alamat[i] = data.getString("alamat");
                                telp[i] = data.getString("telp");
                                email[i] = data.getString("email");
                                hari[i] = data.getString("hari");
                                jam_buka[i] = data.getString("jam_buka");
                                jam_tutup[i] = data.getString("jam_tutup");
                                rating[i] = data.getString("rating");
                                gambar[i] = data.getString("gambar");
                                latitude[i] = data.getDouble("latitude");
                                longitude[i] = data.getDouble("longitude");
                                Log.d("PRINT_TEST", "onResponse: " + id[i]);
                                markerD[i] = false;
                                mMap.addMarker(new MarkerOptions()
                                        .position(latLng[i])
                                        .title(nama[i])
                                        .snippet(alamat[i])
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_maps_pin_red)));

                            } catch (JSONException je) {
                            }
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng[i], 15.5f));
                        }

                        //----------------------MARKER KLIK
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                Log.d("DEBUG_", "Marker clicked");
                                for (int i = 0; i < numData; i++) {

                                    if (marker.getTitle().equals(nama[i])) {
                                        Intent intent = new Intent(MapsActivity.this, DetailBengkel.class);
                                        intent.putExtra("id", id[i]);
                                        startActivity(intent);
                                    } else {
                                        markerD[i] = false;
                                    }
                                }
                                return false;
                            }

                        });
                    }

                } , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                        builder.setTitle("Error!");
                        builder.setMessage("No Internet Connection");
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        builder.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getLokasi();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
        Volley.newRequestQueue(this).add(request);
    }
}
