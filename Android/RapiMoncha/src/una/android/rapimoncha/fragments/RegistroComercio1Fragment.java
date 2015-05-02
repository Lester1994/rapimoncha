package una.android.rapimoncha.fragments;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.interfaces.IGenComercio;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class RegistroComercio1Fragment extends Fragment implements IGenComercio {
	Comercio comercio;
	EditText txtnombrecomercio;
	EditText txtpropietario;
	EditText txtt1;
	EditText txtt2;
	EditText txtemail;
	EditText txtdireccion;
	TextView lblregresarini;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registrocomer_scr1,
				container, false);
		lblregresarini = (TextView) view.findViewById(R.id.lbl_rc1notas);
		txtnombrecomercio = (EditText) view.findViewById(R.id.txtnocomercio);
		txtpropietario = (EditText) view.findViewById(R.id.txtprocomercio);
		txtt1 = (EditText) view.findViewById(R.id.txtt1comercio);
		txtt2 = (EditText) view.findViewById(R.id.txtt2comercio);
		txtemail = (EditText) view.findViewById(R.id.txtemcomercio);
		txtdireccion = (EditText) view.findViewById(R.id.txtdicomercio);
		restoreFragment();
		lblregresarini.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RegistroComercio1Fragment.this.getActivity().finish();	
			}
		});
		return view;

	}

	public RegistroComercio1Fragment() {
		// TODO Auto-generated constructor stub
	}

	public RegistroComercio1Fragment(Comercio comercio) {
		// TODO Auto-generated constructor stub
		this.comercio = comercio;
	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	@Override
	public boolean actualizarComercio() {
		boolean respuesta = true;
		try{
			comercio.setNoComerncio(txtnombrecomercio.getText().toString());
			comercio.setPrComercio(txtpropietario.getText().toString());
			comercio.setT1Comercio(txtt1.getText().toString());
			comercio.setT2Comercio(txtt2.getText().toString());
			comercio.setEmmComercio(txtemail.getText().toString());
			comercio.setDiComercio(txtdireccion.getText().toString());
			Log.i("Terminado en comercio 1 ","al parecer llegó aqui");
			
		}catch(Exception ex){
			Log.i("Error ",ex.getMessage());
		}
		return respuesta;

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
