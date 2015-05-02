package una.android.rapimoncha.activities.adapter;

import java.util.ArrayList;



import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Galeria;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class GaleriaAdapter extends ArrayAdapter<Galeria> {


	ArrayList<Galeria> imagenes;
	Context context;
	public GaleriaAdapter(ArrayList<Galeria> imagenes,Context context){
		super(context, R.layout.layout_galeriaimagen_gridviewitem, imagenes);
		this.imagenes=imagenes;
		this.context=context;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imagenes.size();
	}


	public void setImagenes(ArrayList<Galeria> imagenes){
		this.imagenes=imagenes;
		notifyDataSetChanged();
	}
	@Override
	public View getView(int position, View converView, ViewGroup parent) {
		View v=converView;
	
			v=((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.layout_galeriaimagen_gridviewitem, parent,false);
			
			Galeria g=imagenes.get(position);
			ImageView img=(ImageView)v.findViewById(R.id.image_element);
			byte[] decodedString = Base64.decode(g.getDtImagen(), Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
			img.setImageBitmap(decodedByte);
			
	
		
		return v;
	}

}
