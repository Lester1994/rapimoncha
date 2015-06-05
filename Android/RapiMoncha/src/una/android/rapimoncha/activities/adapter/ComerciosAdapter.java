package una.android.rapimoncha.activities.adapter;

import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.CategoriaComercio;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Galeria;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ComerciosAdapter extends ArrayAdapter<Comercio>{
	
	Activity context;
	ArrayList<Comercio> objects;
	
	public ComerciosAdapter(Activity context,	ArrayList<Comercio> objects) {
		super(context, R.layout.layout_comercio_listviewitem, objects);
		
		this.context=context;
		this.objects=objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v=convertView;
		if(v==null){
		 LayoutInflater inflator = context.getLayoutInflater();
	      v = inflator.inflate(R.layout.layout_comercio_listviewitem, null,false);
		}
		
		TextView comercionom_data=(TextView)v.findViewById(R.id.comercionom_data);
		TextView comerciopropie_data=(TextView)v.findViewById(R.id.comerciopropie_data);
		TextView comerciotel_data=(TextView)v.findViewById(R.id.comerciotel_data);
		TextView comercioemail_data=(TextView)v.findViewById(R.id.comercioemail_data);
		ImageView comercioicon=(ImageView)v.findViewById(R.id.comercioicon);
		Comercio comercio=objects.get(position);
		comercionom_data.setText(comercio.getNoComerncio());
		comerciopropie_data.setText(comercio.getPrComercio());
		comerciotel_data.setText(comercio.getT1Comercio());
		comercioemail_data.setText(comercio.getEmComercio());
		if(comercio.getImagenes()!=null&&comercio.getImagenes().size()>0&&comercio.getImagenes().get(0)!=null){
			Galeria g=comercio.getImagenes().get(0);
			byte[] decodedString = Base64.decode(g.getDtImagen(),
					Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(
					decodedString, 0, decodedString.length);
			comercioicon.setImageBitmap(decodedByte);
		}else{
			comercioicon.setImageResource(R.drawable.icon_myhome);
		}
		
		
		return v;
	}

}
