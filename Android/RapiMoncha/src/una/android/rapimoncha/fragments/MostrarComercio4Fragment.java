package una.android.rapimoncha.fragments;

import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.GaleriaAdapter;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Galeria;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class MostrarComercio4Fragment extends Fragment {

	private Comercio comercio;
	private ArrayList<Galeria> imagenes;
	private ArrayList<Galeria> imagenes2;
	BaseAdapter adapter;

	public MostrarComercio4Fragment() {
		// TODO Auto-generated constructor stub
		initImages();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mostrarcomercio_scr4,
				container, false);

		restoreFragment();
		GridView grd = (GridView) view.findViewById(R.id.grid_imgcomercio);
		adapter = new GaleriaAdapter(imagenes, getActivity());
		grd.setAdapter(adapter);

		return view;
	}

	private void restoreFragment() {
		if (comercio != null && comercio.getImagenes() != null) {
			Log.i("Tamaño de la lista", "entreeeeeeeeeeeeeeee"
					+ comercio.getImagenes().size());
			imagenes = comercio.getImagenes();
			for (Galeria img : comercio.getImagenes()) {
				if(imagenes2==null){
					imagenes2=new ArrayList<Galeria>();
				}
				imagenes2.add(img);
			}
		}
	}

	private void initImages() {
		imagenes = new ArrayList<Galeria>();

	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

}
