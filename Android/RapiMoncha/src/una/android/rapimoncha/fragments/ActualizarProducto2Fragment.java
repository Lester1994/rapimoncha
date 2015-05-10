package una.android.rapimoncha.fragments;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.GaleriaAdapter;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Galeria;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.interfaces.IGenComercio;
import una.android.rapimoncha.interfaces.IGenProducto;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class ActualizarProducto2Fragment extends Fragment implements IGenProducto {

	private Producto producto;
	private ArrayList<Galeria> imagenes;
	private static final int RESULT_LOAD_IMAGE = 0;
	BaseAdapter adapter;
public ActualizarProducto2Fragment() {
	// TODO Auto-generated constructor stub
	initImages();
}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registroproduct_scr2,
				container, false);
		TextView lblregresarini = (TextView) view
				.findViewById(R.id.lbl_rc2regresar_producto);
		lblregresarini.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ActualizarProducto2Fragment.this.getActivity().finish();
			}
		});
		TextView txt = (TextView) view.findViewById(R.id.lbl_tituloscr2_product);
		txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(i, RESULT_LOAD_IMAGE);

			}
		});
		restoreFragment();
		GridView grd = (GridView) view.findViewById(R.id.grid_imgproducto);
		adapter = new GaleriaAdapter(imagenes, getActivity());
		Log.i("Tamaño de la lista",imagenes.size()+"");
		grd.setAdapter(adapter);
		grd.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				imagenes.remove(position);
				((GaleriaAdapter) adapter).setImagenes(imagenes);

				return true;
			}
		});

		return view;
	}

	private void restoreFragment() {
		if(producto!=null&&producto.getImagenes()!=null){
			Log.i("Tamaño de la lista","entreeeeeeeeeeeeeeee"+producto.getImagenes().size());
			initImages();
			for(Galeria g: producto.getImagenes()){
				Galeria g2=new Galeria();
				g2.setIdImagen(g.getIdImagen());
				g2.setDtImagen(g.getDtImagen());
				imagenes.add(g2);
			}
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE
				&& resultCode == Activity.RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getActivity().getContentResolver().query(
					selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			Bitmap bm = BitmapFactory.decodeFile(picturePath);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
			// added lines
			bm.recycle();
			bm = null;
			// added lines
			byte[] b = baos.toByteArray();
			String b64 = Base64.encodeToString(b, Base64.DEFAULT);

			Galeria ga = new Galeria();
			ga.setDtImagen(b64);
			Log.i("dato", b64);
			imagenes.add(ga);
			Log.i("tamanio es ",imagenes.size()+" <<<<");
			((GaleriaAdapter) adapter).setImagenes(imagenes);
			adapter.notifyDataSetChanged();

		}else{
			Log.i("dato resultado", ""+resultCode);
		}

	}

	@Override
	public boolean actualizarProducto() {
	boolean resp=true;
	try{
		Log.i("datooo","entro a imagenes ahora jeje");
		producto.limpiarImagenes();
		for(Galeria g:imagenes){
			Log.i("MEnsaje","Agregue imagen");
			producto.addImagen(g);
		}
	}catch(Exception ex){
		ex.printStackTrace();
		Log.i("Eerror","Error "+ex.getMessage()+ex.getLocalizedMessage());
	}
	return resp;
	}

	private void initImages() {
		imagenes = new ArrayList<Galeria>();

	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
