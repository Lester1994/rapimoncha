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

public class ActualizarComercio2Fragment extends Fragment implements IGenComercio {

	Comercio comercio;
	ArrayList<CategoriaComercio> categoriascomercio;
	CategoriaComercioAdapter adaptadorcategorias;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registrocomer_scr2,
				container, false);
		TextView lblregresarini = (TextView) view
				.findViewById(R.id.lbl_tituloelementocategoria);
		lblregresarini.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ActualizarComercio2Fragment.this.getActivity().finish();
			}
		});
		ListView lsttipocomercio = (ListView) view
				.findViewById(R.id.lstcatcomercio);
		restoreFragment();
		adaptadorcategorias = new CategoriaComercioAdapter(this.getActivity(),
				categoriascomercio);
		lsttipocomercio.setAdapter(adaptadorcategorias);
		return view;
	}

	public ActualizarComercio2Fragment() {
		// TODO Auto-generated constructor stub
	}

	public ActualizarComercio2Fragment(Comercio comercio,
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

	@Override
	public boolean actualizarComercio() {
		boolean resp = true;
		try {
			if(comercio!=null){
				comercio.limpiarClasificaciones();
			}
			for (CategoriaComercio categoria : categoriascomercio) {
				if (categoria != null && categoria.seleccionado) {
					comercio.addClasificacion(categoria);
				}

			}
			Log.i("Terminado en comercio 2 ", "al parecer llegó aqui");

		} catch (Exception ex) {
			Log.i("Error ", ex.getMessage());
		}
		return resp;
	}

	private void restoreFragment() {
		if (comercio != null && comercio.getCaComercio() != null) {
			int size = this.categoriascomercio.size();
			Log.i("El tamanio de las categorias ",size+" <<<sizeee");
			for (int i = 0; i < size; i++) {
				ArrayList<CategoriaComercio> aux = comercio.getCaComercio();
				for (CategoriaComercio categoria : aux) {
					if (categoria != null) {
						if (categoria.getIdTipoCategoria() == categoriascomercio
								.get(i).getIdTipoCategoria()
								&& categoria.seleccionado) {
								CategoriaComercio categoriaaux=categoria;
								categoriascomercio.set(i, categoriaaux);
								
						}
					}
				}
			}

		}
	}

}
