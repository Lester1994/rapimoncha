package una.android.rapimoncha.fragments;

import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.CategoriaComercioAdapter;
import una.android.rapimoncha.entidades.CategoriaComercio;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.interfaces.IGenComercio;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MostrarComercio2Fragment extends Fragment {

	Comercio comercio;
	ArrayList<CategoriaComercio> categoriascomercio;
	CategoriaComercioAdapter adaptadorcategorias;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mostrarcomercio_scr2,
				container, false);

		ListView lsttipocomercio = (ListView) view
				.findViewById(R.id.lstcatcomercio);
		restoreFragment();
		adaptadorcategorias = new CategoriaComercioAdapter(this.getActivity(),
				categoriascomercio);
		lsttipocomercio.setAdapter(adaptadorcategorias);
		return view;
	}

	public MostrarComercio2Fragment() {
		// TODO Auto-generated constructor stub
	}

	public MostrarComercio2Fragment(Comercio comercio,
			ArrayList<CategoriaComercio> categoriascomercio) {
		// TODO Auto-generated constructor stub
		this.comercio = comercio;
		this.categoriascomercio = categoriascomercio;
	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	public void setCategorias(ArrayList<CategoriaComercio> categoriascomercio) {
		this.categoriascomercio = categoriascomercio;
	}

	private void restoreFragment() {
		if (comercio != null && comercio.getCaComercio() != null) {
			int size = this.categoriascomercio.size();
			for (int i = 0; i < size; i++) {
				ArrayList<CategoriaComercio> aux = comercio.getCaComercio();
				for (CategoriaComercio categoria : aux) {
					if (categoria != null) {
						if (categoria.getIdTipoCategoria() == categoriascomercio
								.get(i).getIdTipoCategoria()
								&& categoria.seleccionado) {
							CategoriaComercio categoriaaux = categoria;
							categoriascomercio.set(i, categoriaaux);

						}
					}
				}
			}

			if (categoriascomercio != null) {
				ArrayList<CategoriaComercio> aux = new ArrayList<CategoriaComercio>();
				for (CategoriaComercio categoria : categoriascomercio) {
					if (categoria.seleccionado) {
						aux.add(categoria);
					}
				}

				categoriascomercio = aux;
			}
		}
	}

}
