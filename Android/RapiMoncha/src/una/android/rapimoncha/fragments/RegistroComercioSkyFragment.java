package una.android.rapimoncha.fragments;

import una.android.rapimoncha.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class RegistroComercioSkyFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registrocomer_sky, container, false);
		TextView lblregresarini=(TextView)view.findViewById(R.id.lbl_notas);
		lblregresarini.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegistroComercioSkyFragment.this.getActivity().finish();
				
			}
		});
		return view;
	}
}
