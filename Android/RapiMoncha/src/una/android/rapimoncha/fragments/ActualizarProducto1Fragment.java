package una.android.rapimoncha.fragments;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.interfaces.IGenComercio;
import una.android.rapimoncha.interfaces.IGenProducto;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ActualizarProducto1Fragment extends Fragment implements IGenProducto {
	Producto producto;
	EditText txtnoproducto;
	EditText txtdeproducto;
	EditText txtpeproducto;
	TextView lblregresarini;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registroproduct_scr1,
				container, false);
		lblregresarini = (TextView) view.findViewById(R.id.lbl_rc1notas);
		txtnoproducto = (EditText) view.findViewById(R.id.txtnoproducto);
		txtdeproducto = (EditText) view.findViewById(R.id.txtdeproducto);
		txtpeproducto = (EditText) view.findViewById(R.id.txtpeproducto);

		restoreFragment();
		lblregresarini.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ActualizarProducto1Fragment.this.getActivity().finish();	
			}
		});
		return view;

	}






	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public boolean actualizarProducto() {
		boolean respuesta = true;
		try{
			producto.setNoProducto(txtnoproducto.getText().toString());
			producto.setDeProducto(txtdeproducto.getText().toString());
			producto.setPrProducto(Double.valueOf(txtpeproducto.getText().toString()));

			Log.i("Terminado en producto 1 ","al parecer llegó aqui");
			
		}catch(Exception ex){
			Log.i("Error ",ex.getMessage()+"");
		}
		return respuesta;

	}
	
	private void restoreFragment(){
		if(producto!=null){
			txtnoproducto.setText(producto.getNoProducto());
			txtdeproducto.setText(producto.getDeProducto());
			txtpeproducto.setText(producto.getPrProducto()+"");
		}
	}

}
