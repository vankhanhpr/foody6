package com.example.khanh.foody4;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.khanh.foody4.customadapter.getdata;
import com.example.khanh.foody4.insert_restaurant.MyDecodeLocationMethod;
import com.example.khanh.foody4.myinterface.ICallBackAsynsTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {



    private LinearLayout back_button_choose_location;
    private TextView text_view_select;
    private TextView text_view_name_location;
    private ProgressBar progress_bar;
    private LinearLayout linear_layout_my_location;

    private double selectedLat;
    private double selectedLong;
    private LatLng selectedPosition;
    private final float MAP_ZOOM = 15.0f;
    Location myLocation = null;
    private AsyncTask asyncTaskDecodeLocation;
    Context context;

    public MapsActivity(Context context)
    {
        //this.context=context;
    }

    //no
    private GoogleMap mMap;
    GoogleMap.OnMyLocationChangeListener listener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(),location.getLongitude());

            if(mMap != null){
                mMap.clear();

                mMap.addMarker(new MarkerOptions().position(loc));

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc,16.0f));
            }
        }
    };
    //no
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      /*  SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);*/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context=getApplicationContext();
        initView();
/*
        this.selectedLat = getIntent().getDoubleExtra("lat", -1.0d);
        this.selectedLong = getIntent().getDoubleExtra("long", -1.0d);*/
    }
    //no
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*// Add a marker in Sydney and move the camera
       *//* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*//*

       //kieu hien thi cua map
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //xin cap phep cua ng dung
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.setOnMyLocationChangeListener(listener);*/
        this.mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        this.mMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.mMap.getUiSettings().setZoomControlsEnabled(false);
        this.mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.mMap.setOnMapClickListener(onMapClickListener);
        this.mMap.setOnCameraChangeListener(new onCameraChangeListener(mMap));

        if (selectedLat == -1.0d || selectedLong == -1.0d) {
            markMyLocation();
        } else {
            markLocation(selectedLat, selectedLong);
        }
    }


    private void initView() {
        back_button_choose_location = (LinearLayout) findViewById(R.id.back_button_choose_location);
        linear_layout_my_location = (LinearLayout) findViewById(R.id.linear_layout_my_location);

        text_view_select = (TextView) findViewById(R.id.text_view_select);
        text_view_name_location = (TextView) findViewById(R.id.text_view_name_location);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        back_button_choose_location.setOnClickListener(this);
        linear_layout_my_location.setOnClickListener(this);
        text_view_select.setOnClickListener(this);
    }


    private void markLocation(double lat, double lng) {
        this.selectedLat = lat;
        this.selectedLong = lng;
        this.mMap.clear();
        this.selectedPosition = new LatLng(lat, lng);
        this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(selectedPosition, MAP_ZOOM), 100, null);
    }

    private void markMyLocation() {
        myLocation = getMyLocation();

        if (myLocation == null) {
            markLocation(getdata.getDefaultMyLocation().latitude, getdata.getDefaultMyLocation().longitude);
        } else {
            markLocation(myLocation.getLatitude(), myLocation.getLongitude());
        }
    }

    private Location getMyLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = null;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        }
        return location;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_choose_location:

                finish();
                break;
            case R.id.text_view_select:
                getCenterLocationMap();
                sendData();
                break;
            case R.id.linear_layout_my_location:
                mMap.clear();
                markMyLocation();
                break;
            default:
                break;
        }
    }

    private void sendData(){
        Intent intent=new Intent();
        intent.putExtra("lat",selectedLat);
        intent.putExtra("long",selectedLong);
        setResult(999,intent);
        finish();

    }


    private void getCenterLocationMap() {
        if (this.mMap != null) {
            VisibleRegion visibleRegion = this.mMap.getProjection().getVisibleRegion();
            LatLng center = this.mMap.getProjection().fromScreenLocation(new Point(this.mMap.getProjection().toScreenLocation(visibleRegion.farRight).x / 2,
                    this.mMap.getProjection().toScreenLocation(visibleRegion.nearLeft).y / 2));
            this.selectedLat = center.latitude;
            this.selectedLong = center.longitude;
            markLocation(this.selectedLat, this.selectedLong);
        }
    }




    class onCameraChangeListener implements GoogleMap.OnCameraChangeListener {
        GoogleMap googleMap;

        public onCameraChangeListener(GoogleMap googleMap) {
            this.googleMap = googleMap;
        }

        class CallBackAsynTask implements ICallBackAsynsTask<String> {
            CameraPosition cameraPosition;

            public CallBackAsynTask(CameraPosition cameraPosition) {
                this.cameraPosition = cameraPosition;
            }

            @Override
            public void onRunnin() {

            }

            @Override
            public void onSuccess(String str) {
                text_view_name_location.setText(str + "");
                progress_bar.setVisibility(View.GONE);
                selectedLat = cameraPosition.target.latitude;
                selectedLong = cameraPosition.target.longitude;
            }

            @Override
            public void onFail(String str) {
                text_view_name_location.setText(getString(R.string.TEXT_NOT_FOUND_ADDRESS) + "");
                progress_bar.setVisibility(View.GONE);
            }
        }

        @Override
        public void onCameraChange(CameraPosition cameraPosition) {
            asyncTaskDecodeLocation = new MyDecodeLocationMethod(context, cameraPosition.target.latitude,
                    cameraPosition.target.longitude, new CallBackAsynTask(cameraPosition)).execute();
            text_view_name_location.setText("");
            progress_bar.setVisibility(View.VISIBLE);
        }
    }

    GoogleMap.OnMapClickListener onMapClickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            MapsActivity.this.markLocation(latLng.latitude, latLng.longitude);
        }
    };

}
