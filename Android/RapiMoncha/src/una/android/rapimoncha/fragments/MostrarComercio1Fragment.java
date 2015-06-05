package una.android.rapimoncha.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;

public class MostrarComercio1Fragment extends Fragment{
	Comercio comercio;
	TextView txtnombrecomercio;
	TextView txtpropietario;
	TextView txtt1;
	TextView txtt2;
	TextView txtemail;
	TextView txtdireccion;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mostrarcomercio_scr1,
				container, false);
		txtnombrecomercio = (TextView) view.findViewById(R.id.txtnocomercio);
		txtpropietario = (TextView) view.findViewById(R.id.txtprocomercio);
		txtt1 = (TextView) view.findViewById(R.id.txtt1comercio);
		txtt2 = (TextView) view.findViewById(R.id.txtt2comercio);
		txtemail = (TextView) view.findViewById(R.id.txtemcomercio);
		txtdireccion = (TextView) view.findViewById(R.id.txtdicomercio);
		restoreFragment();
		
		
		return view;

	}
	
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	private void restoreFragment(){
		if(comercio!=null){
			txtnombrecomercio.setText(comercio.getNoComerncio());
			txtpropietario.setText(comercio.getPrComercio());
			txtt1.setText(comercio.getT1Comercio());
			txtt2.setText(comercio.getT2Comercio());
			txtemail.setText(comercio.getEmComercio());
			txtdireccion.setText(comercio.getDiComercio());
		}
	}
}
