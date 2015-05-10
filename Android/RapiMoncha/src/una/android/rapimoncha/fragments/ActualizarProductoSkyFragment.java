package una.android.rapimoncha.fragments;

import una.android.rapimoncha.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ActualizarProductoSkyFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registroproduct_sky, container, false);
		TextView lblregresarini=(TextView)view.findViewById(R.id.lbl_notas_product);
		
		TextView lbltitulo=(TextView)view.findViewById(R.id.lbl_titulobienvenida_product);
		lbltitulo.setText(getActivity().getResources().getString(R.string.activity_updateproducto_lbl_titulobienvenida_product));

		TextView lbldatoimportante=(TextView)view.findViewById(R.id.lbl_datosimportantes_product);
		lbldatoimportante.setText(getActivity().getResources().getString(R.string.activity_updateproducto_lbl_datosimportantes_product));
		
		ImageView img=(ImageView)view.findViewById(R.id.img_icon_product);
		img.setImageResource(R.drawable.icon_editproduct);
		
		lblregresarini.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ActualizarProductoSkyFragment.this.getActivity().finish();
				
			}
		});
		return view;
	}
}
