package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import org.apache.http.NameValuePair;
import org.json.JSONArray;

import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.service.R;


public class MainActivity extends BaseActivity implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener, LocationSource, OnMarkerClickListener {

		// Creating JSON Parser object
		// JSONParser jParser = new JSONParser();
		String Lugar;
		String Desc;
		Double Lat,Lng;
		Timer timer;
		public String state[];
	
		
		// url to get all products list
		//private static String url_all_products = "http://192.168.1.98/Rutas/get_position.php";
		
		//private static String url_all_products="http://ec2-54-237-98-201.compute-1.amazonaws.com/Rutas/get_position.php";
		// JSON Node names
	/*	private static final String TAG_SUCCESS = "success";
		private static final String TAG_RESULTS = "results";
		private static final String TAG_ID = "Camion";
		private static final String TAG_CAMINO = "Ruta";
		private static final String TAG_LATITUD = "Lat";
		private static final String TAG_LONGITUD = "Lng";
  */
		// products JSONArray
	//	JSONArray Results = null;

	  private GoogleMap mMap;
	  private OnLocationChangedListener mListener;
	  private LocationClient mLocationClient;

	  
	  private static final LocationRequest REQUEST = LocationRequest.create()
	      .setInterval(5000)         // 5 seconds
	      .setFastestInterval(16)    // 16ms = 60fps
	      .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_map);
	    
	    /*
		state = new String[30];
		
		
		state[0]="1";
		state[1]="2";
		state[2]="3";
		state[3]="4";
		state[4]="5";
		

        */
	    
        Spinner s = (Spinner) findViewById(R.id.Spinner01);
  
        
        
        //ArrayAdapter adapter = new ArrayAdapter(this,       android.R.layout.simple_spinner_item, state);
        //      s.setAdapter(adapter);
                
        
       
        
        }
	  
	   @Override
	  protected void onResume() {
	    super.onResume();
	    setUpLocationClientIfNeeded();
	    setUpMapIfNeeded();
	    mLocationClient.connect();
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    if (mLocationClient != null) {
	      mLocationClient.disconnect();
	    }
	  }
	  @Override
	  protected void onStop(){
			super.onStop();
			//timer.cancel();
		}

	  private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (mMap == null) {
	      // Try to obtain the map from the SupportMapFragment
	    	
	      mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)) .getMap();
	    	
	      // Check if we were successful in obtaining the map.
	      if (mMap != null) {
	    	 // Aqui ira mi metodo para iniciar el mapa con actividad asincrona
	    	 // startListener();	
//	    	  mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
	    	  mMap.setMyLocationEnabled(true);
	     //     mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	          mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

	      }
	    }
	  }
	  	
	  private void setUpLocationClientIfNeeded() {
	    if (mLocationClient == null) {
	      mLocationClient = new LocationClient(getApplicationContext(),this,this);// ConnectionCallbacks , OnConnectionFailedListener
	    }
	  }

	  @Override
	  public void onLocationChanged(Location location) {
//	    mMessageView.setText("Location = " + location);
		  if( mListener != null )
		    {
		        mListener.onLocationChanged( location );
		        //Move the camera to the user's location and zoom in!
		        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
		    }
	  }
	  @Override
	  public void onConnected(Bundle connectionHint) {
	    mLocationClient.requestLocationUpdates(
	        REQUEST,
	        this);  // LocationListener
	    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLocationClient.getLastLocation().getLatitude(), mLocationClient.getLastLocation().getLongitude()), 17.0f));
	    
	    Lugar = "Plaza fiesta";
		Desc = "Lugar bonito";
		
		Lat = 25.684076687236207;
		Lng = -100.32184839232286;
		
//				notificacion(Results.toString());
				mMap.addMarker(new MarkerOptions()
				  .position(new LatLng(Lat,Lng))
				  .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker1))
				  .title(Lugar)
				  .snippet(Desc));
				
				 mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			            @Override
			            public void onInfoWindowClick(Marker marker) {
			               Intent intent = new Intent(MainActivity.this,Stores.class);
			               startActivity(intent);


			            }
			        });
				 
				 
				
	  }
	  

	 
	  
	  public void notificacion(String n){
		  Toast.makeText(getApplicationContext(),n, Toast.LENGTH_SHORT).show();
	  }

	@Override
	public boolean onMarkerClick(Marker marker1) {
		
		 Intent intent = new Intent(MainActivity.this,Stores.class);
         startActivity(intent);
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activate(OnLocationChangedListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnected() {
	  // Do nothing
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
	  // Do nothing
	}

	 /*
	  *  class LoadRoutes extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... args) {
		//	List<NameValuePair> params = new ArrayList<NameValuePair>();
		//	params.add(new BasicNameValuePair(TAG_RUTA, "1"));
		//	params.add(new BasicNameValuePair(TAG_CAMION, "1"));
		//	JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
		
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Results = json.getJSONArray(TAG_RESULTS);

					for (int i = 0; i < Results.length(); i++) {
						JSONObject c = Results.getJSONObject(i);
						Ruta   = c.getString(TAG_RUTA);
					    Camion = c.getString(TAG_CAMION);
					    Lat    = c.getDouble(TAG_LATITUD);
					    Lng    = c.getDouble(TAG_LONGITUD);

						
					}
				} else {
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}   

			return null;
		}
*/
	
	@Override
		protected void onPostExecute() {
			
			
					
		}


	
	/*
	
	public void startListener() {
	    final Handler handler = new Handler();
	    timer = new Timer();
	    TimerTask doAsynchronousTask = new TimerTask() {       
	        @Override
	        public void run() {
	            handler.post(new Runnable() {
	                @Override
					public void run() {       
	                    try {
	                        LoadRoutes Load = new LoadRoutes();
	                        Load.execute();
	                    } catch (Exception e) {
	                        // TODO Auto-generated catch block
	                    }
	                }
	            });
	        }
	    };
	    timer.schedule(doAsynchronousTask, 5000, 10000); 
	}

*/
	}

