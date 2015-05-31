package una.android.rapimoncha.fragments;

import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Preferencia;
import una.android.rapimoncha.interfaces.IGenComercio;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

public class ActualizarComercio5Fragment extends Fragment implements IGenComercio{

	
	private Comercio comercio;
	Switch contactosinsub;
	Switch productsinsub;
	Switch servexpres;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registrocomer_src5,
				container, false);
		contactosinsub=(Switch)view.findViewById(R.id.switchcontactosinsub);
		productsinsub=(Switch)view.findViewById(R.id.switchprodusinsub);
		servexpres=(Switch)view.findViewById(R.id.switchservicioexpress);
		restoreFragment();
		return view;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	@Override
	public boolean actualizarComercio() {
		// TODO Auto-generated method stub
		boolean resp=true;
		try{
		   Preferencia pref=new Preferencia();
		   pref.setContactoSinSubcripcion(contactosinsub.isChecked());
		   pref.setVerProductoSinSubscrib(productsinsub.isChecked());
		   pref.setHasServicioExpress(servexpres.isChecked());
		   comercio.limpiarPreferencias();
		   comercio.addPreferencia(pref);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resp;
	}
	
	private void restoreFragment(){
		if(comercio!=null&&comercio.getPreferenciascomercio()!=null){
			ArrayList<Preferencia>preferenciaux=comercio.getPreferenciascomercio();
			for(Preferencia p:preferenciaux){
				contactosinsub.setChecked(p.isContactoSinSubcripcion());
				productsinsub.setChecked(p.isVerProductoSinSubscrib());
				servexpres.setChecked(p.isHasServicioExpress());
			}
		}
	}
	
	

}
