package una.android.rapimoncha.fragments;

import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.GeoLocalizacion;
import una.android.rapimoncha.interfaces.IGenComercio;
import android.app.Fragment;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MostrarComercio3Fragment extends Fragment  {
	GoogleMap map;
	Comercio comercio;
	private static View view;
	ArrayList<Marker> ubicaciones;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);
		}
		ubicaciones = new ArrayList<Marker>();
		try {

			view = inflater.inflate(R.layout.fragment_mostrarcomercio_scr3,
					container, false);

		} catch (InflateException e) {
			/* map is already there, just return view as it is */
			e.printStackTrace();
		}
		startMap();
		restoreFragment();

		return view;
	}

	public MostrarComercio3Fragment() {
		// TODO Auto-generated constructor stub
	}

	public MostrarComercio3Fragment(Comercio comercio) {
		// TODO Auto-generated constructor stub
		this.comercio = comercio;
	}

	private void startMap() {

		map = ((MapFragment) getActivity().getFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		map.setMyLocationEnabled(true);
		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(android.content.Context.LOCATION_SERVICE);
		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location location = locationManager.getLastKnownLocation(provider);
		try {
			if (map.isMyLocationEnabled()) {
				LatLng yo = new LatLng(location.getLatitude(),
						location.getLongitude());
				map.addMarker(new MarkerOptions()
						.title("Estás Aquí, según gps")
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_RED))
						.position(yo));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addMarker(double lat, double lon) {
		LatLng ltn = new LatLng(lat, lon);

		Marker marker = (map.addMarker(new MarkerOptions()
				.title("lat: " + lat + " long: " + lon)
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
				.position(ltn)));

		ubicaciones.add(marker);
	}

	private void restoreFragment() {
		if (comercio != null && comercio.getUbicacionmapa() != null) {
			for (GeoLocalizacion geo : comercio.getUbicacionmapa()) {
				addMarker(geo.getLatitud(), geo.getLongitud());
			}
		}
	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}


}
