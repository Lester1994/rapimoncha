package una.android.rapimoncha.fragments;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.GaleriaAdapter;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Galeria;
import una.android.rapimoncha.interfaces.IGenComercio;
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

public class RegistroComercio4Fragment extends Fragment implements IGenComercio {

	private Comercio comercio;
	private ArrayList<Galeria> imagenes;
	private ArrayList<Galeria> imagenes2;
	private static final int RESULT_LOAD_IMAGE = 0;
	BaseAdapter adapter;
public RegistroComercio4Fragment() {
	// TODO Auto-generated constructor stub
	initImages();
}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_registrocomer_scr4,
				container, false);
		TextView lblregresarini = (TextView) view
				.findViewById(R.id.lbl_rc4regresar);
		lblregresarini.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RegistroComercio4Fragment.this.getActivity().finish();
			}
		});
		TextView txt = (TextView) view.findViewById(R.id.lbl_tituloscr4);
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
		GridView grd = (GridView) view.findViewById(R.id.grid_imgcomercio);
		adapter = new GaleriaAdapter(imagenes, getActivity());
		Log.i("Tamaño de la lista",imagenes.size()+"");
		grd.setAdapter(adapter);
		grd.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				imagenes2.remove(position);
				((GaleriaAdapter) adapter).setImagenes(imagenes2);

				return true;
			}
		});

		return view;
	}

	private void restoreFragment() {
		if(comercio!=null&&comercio.getImagenes()!=null){
			Log.i("Tamaño de la lista","entreeeeeeeeeeeeeeee"+comercio.getImagenes().size());
			imagenes=comercio.getImagenes();
			for ( Galeria img :comercio.getImagenes()) {
				imagenes2.add(img);
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
			imagenes2.add(ga);
			((GaleriaAdapter) adapter).setImagenes(imagenes2);
			adapter.notifyDataSetChanged();

		}

	}

	@Override
	public boolean actualizarComercio() {
	boolean resp=true;
	try{
		comercio.limpiarImagenes();
		for(Galeria g:imagenes2){
			Log.i("MEnsaje","Agregue imagen");
			comercio.addImagen(g);
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
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
}
