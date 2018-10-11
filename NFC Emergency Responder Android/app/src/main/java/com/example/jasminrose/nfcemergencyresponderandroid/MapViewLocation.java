package com.example.jasminrose.nfcemergencyresponderandroid;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.nfc.NfcAdapter;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.geocoding.v5.GeocodingCriteria;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MapViewLocation implements OnMapReadyCallback, LocationEngineListener, PermissionsListener {
    private MapView mapView;
    private MapboxMap map;
    LocationEngine locationEngine;
    LocationLayerPlugin locationLayerPlugin;
    PermissionsManager permissionsManager;
    Location originLayout;

    Context context;
    private String locationName;

    public MapViewLocation(Context context, MapView mapView) {
        this.context = context;
        this.mapView = mapView;
    }

    protected void onCreate() {
        Mapbox.getInstance(context,"pk.eyJ1IjoianJsaW0xMyIsImEiOiJjam4yZ3locWQwMnlkM3BvNGY0OHhxam04In0.4AV4682kDn9Kjmu6imk5ew");
        mapView.getMapAsync(this);

    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
       /* LocationPluginActivity.this.map = map;
        enableLocationPlugin();*/
        map = mapboxMap;
        locationEnable();
        mapboxMap.getUiSettings().setZoomControlsEnabled(true);
        mapboxMap.getUiSettings().setZoomGesturesEnabled(true);
        mapboxMap.getUiSettings().setScrollGesturesEnabled(true);
        mapboxMap.getUiSettings().setAllGesturesEnabled(true);
    }

    void locationEnable() {
        if (PermissionsManager.areLocationPermissionsGranted(context)) {
            intialLocationEngine();
            intializLocationLayer();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions((Activity) context);
        }
    }

    void intialLocationEngine() {
        locationEngine = new LocationEngineProvider(context).obtainBestLocationEngineAvailable();
        locationEngine.setPriority(LocationEnginePriority.HIGH_ACCURACY);
        locationEngine.activate();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = locationEngine.getLastLocation();
        if (lastLocation != null) {
            originLayout = lastLocation;
            setCamerpostion(lastLocation);
            getCurrentLocationAddress(lastLocation);
        } else {
            locationEngine.addLocationEngineListener(this);
        }

    }

    void intializLocationLayer() {
        locationLayerPlugin = new LocationLayerPlugin(mapView, map, locationEngine);
        locationLayerPlugin.setLocationLayerEnabled(true);
        locationLayerPlugin.setCameraMode(CameraMode.TRACKING);
        locationLayerPlugin.setRenderMode(RenderMode.NORMAL);
    }

    public void setCamerpostion(Location camerpostion) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(camerpostion.getLatitude(), camerpostion.getLongitude()), 17.0));
    }

    public void getCurrentLocationAddress(Location position) {
        Point locationPoint = Point.fromLngLat(position.getLongitude(), position.getLatitude());

        MapboxGeocoding client = MapboxGeocoding.builder()
                .accessToken("pk.eyJ1IjoianJsaW0xMyIsImEiOiJjam4yZ3locWQwMnlkM3BvNGY0OHhxam04In0.4AV4682kDn9Kjmu6imk5ew")
                .query(locationPoint)
                .build();

        client.enqueueCall(new Callback<GeocodingResponse>() {
            @Override
            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
                List<CarmenFeature> results = response.body().features();
                if (results.size() > 0) {
                    CarmenFeature feature = results.get(0);
                    Toast.makeText(context, feature.placeName(), Toast.LENGTH_SHORT).show();
                    locationName = feature.placeName();

                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.readFromIntent(((MainActivity) context).getIntent());


                } else {
                    Toast.makeText(context, "No results", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GeocodingResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onConnected() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationEngine.requestLocationUpdates();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            originLayout = location;
            setCamerpostion(location);
            getCurrentLocationAddress(location);
        }
    }


    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            locationEnable();
        }

    }

    @SuppressWarnings("MissingPermission")
    public void onStart() {
        if (locationEngine != null)
            locationEngine.requestLocationUpdates();
        mapView.onStart();
    }

    public void onDestroy() {
        if (locationEngine!=null)
        {
            locationEngine.deactivate();
        }
        mapView.onDestroy();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
