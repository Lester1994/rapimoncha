package una.android.rapimoncha.fragments;

import java.io.IOException;
import java.io.OutputStreamWriter;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.recursos.Utilidades;
import una.android.rapimoncha.sw.SwComercio;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class RegistroComercio6Fragment extends Fragment{
	Comercio comercio;
	CheckBox checkbox;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registrocomer_scr6, container, false);
		Button btn=(Button)view.findViewById(R.id.btn_finalizar);
	 checkbox=(CheckBox)view.findViewById(R.id.chck_aceptaterminos);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkbox.isChecked()){
				Log.i("Objeto final",comercio.toString());
				Utilidades.writeToFile(getActivity(),comercio.getJson().toString());
				SwComercio swcomercio=new SwComercio();
				swcomercio.agregarcomercio(comercio, getActivity());
				}else{
					Toast.makeText(getActivity().getApplicationContext(),
							R.string.fragment_registrocomercio5_java_msgaceptarterminos,
							Toast.LENGTH_LONG).show();
				}
				
			}
		});
		return view;
	}
	

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	

}
