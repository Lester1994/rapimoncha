package una.android.rapimoncha.activities.adapter;

import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.modulos.usuario.DetalleUsuarioScreen;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.entidades.SolicitudCliente;
import una.android.rapimoncha.sw.SwSolicitud;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SolicitudesAdapter extends ArrayAdapter<SolicitudCliente> {
	Activity context;
	ArrayList<SolicitudCliente> solicitudes;
	SolicitudCliente solicitudcliente;
	SwSolicitud swsoli;
	public SolicitudesAdapter(Activity context,
			ArrayList<SolicitudCliente> solicitudes) {

		super(context, R.layout.layout_solicitud_listviewitem, solicitudes);
		this.context = context;
		this.solicitudes = solicitudes;
		swsoli=new SwSolicitud();
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return solicitudes.size();

	}
	
	public void setSolicitudes(ArrayList<SolicitudCliente>solicitudes){
		this.solicitudes=solicitudes;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			v = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.layout_solicitud_listviewitem, parent,
							false);
		}

		SolicitudCliente s = solicitudes.get(position);
		TextView lblusuariosolicitante=(TextView)v.findViewById(R.id.lblusuariosolicitante);
		lblusuariosolicitante.setTag(position);
		TextView lblfechasolicitud=(TextView)v.findViewById(R.id.lblfechasolicitud);
		TextView lblproductossolicitud=(TextView)v.findViewById(R.id.lblproductossolicitud);
		TextView lblcostofinalsolicitud=(TextView)v.findViewById(R.id.lblcostofinalsolicitud);
		TextView lblesperohasta=(TextView)v.findViewById(R.id.lblesperohasta);
		TextView btnresolversolicitud=(TextView)v.findViewById(R.id.btnresolversolicitud);
		btnresolversolicitud.setTag(position);

		lblusuariosolicitante.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int position=Integer.parseInt(v.getTag().toString());
				solicitudcliente=solicitudes.get(position);
				if(solicitudcliente.getUsuario()!=null){
					Intent intent=new Intent(SolicitudesAdapter.this.context,DetalleUsuarioScreen.class);
					intent.putExtra("usuario", solicitudcliente.getUsuario());
					SolicitudesAdapter.this.context.startActivity(intent);
				}
				
			}
		});
		btnresolversolicitud.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int position=Integer.parseInt(v.getTag().toString());
				Toast.makeText(context.getApplicationContext(), "El valor es "+position, Toast.LENGTH_LONG).show();
				solicitudcliente=solicitudes.get(position);
				if(solicitudcliente.getEsSolicitud()==1){
				swsoli.resolverSolicitudComercio(solicitudcliente, context);
				}else{
					Log.i("Entreeeee","Entre a eliminar");
					swsoli.eliminarSolicitudComercio(solicitudcliente, context);
				}
				
			}
		});
		if(s!=null){
			Resources r=context.getResources();
			lblusuariosolicitante.setText(r.getString(R.string.layout_solicitud_listviewitem_lblusuariosolicitante)+" "+s.getUsuario().getNombreCompleto());
			lblfechasolicitud.setText(r.getString(R.string.layout_solicitud_listviewitem_lblfechasolicitud)+" "+s.getDateTimeSolicitud());
			lblproductossolicitud.setText(r.getString(R.string.layout_solicitud_listviewitem_lblproductossolicitud)+" "+s.getDetalles().size());
			lblcostofinalsolicitud.setText(r.getString(R.string.layout_solicitud_listviewitem_lblcostofinalsolicitud)+" "+s.getCoFinalSoli()+"");
			lblesperohasta.setText(r.getString(R.string.layout_solicitud_listviewitem_lblesperohasta)+" "+s.getDateTimeEsperoHasta());
			switch (s.getEsSolicitud()) {
			case 1:
				btnresolversolicitud.setText(context.getResources().getString(R.string.layout_solicitud_listviewitem_btnresolversolicitud));
				btnresolversolicitud.setBackgroundColor(Color.GRAY);	
				break;
			case 2:
				btnresolversolicitud.setText(context.getResources().getString(R.string.layout_solicitud_listviewitem_btnresolversolicitud_resuelto));
				btnresolversolicitud.setBackgroundColor(Color.RED);				
				break;
			}
		}		
		
		return  v;
	}
	
	@Override
    public boolean areAllItemsEnabled() 
    {
        return true;
    }

    @Override
    public boolean isEnabled(int arg0) 
    {
        return true;
    }
	
	
}
