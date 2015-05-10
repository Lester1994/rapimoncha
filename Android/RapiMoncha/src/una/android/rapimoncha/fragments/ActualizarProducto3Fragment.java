package una.android.rapimoncha.fragments;

import java.io.IOException;
import java.io.OutputStreamWriter;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.recursos.Utilidades;
import una.android.rapimoncha.sw.SwComercio;
import una.android.rapimoncha.sw.SwProducto;
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
import android.widget.TextView;
import android.widget.Toast;

public class ActualizarProducto3Fragment extends Fragment{
	Comercio comercio;
	Producto producto;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registroproduct_scr3, container, false);
		Button btn=(Button)view.findViewById(R.id.btn_finalizar_product);
		TextView lblnota=(TextView)view.findViewById(R.id.lbl_scr5notasextra_product);
		lblnota.setText(getActivity().getResources().getString(R.string.fragment_updateproducto3_lbl_scr5notasextra_product));
		btn.setText(getActivity().getResources().getString(R.string.fragment_updateproducto3_btn_finalizar_product));
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//comercio.addProducto(producto);
				Log.i("Objeto final",producto.getJson().toString());
				
				//Utilidades.writeToFile(getActivity(),comercio.getJson().toString());
				SwProducto swproducto=new SwProducto();
				
				swproducto.actualizarProducto(producto,comercio.getIdComercio(), getActivity());
			}
		});
		return view;
	}
	

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	public void setProducto(Producto producto) {
		this.producto= producto;
	}	
	

}
