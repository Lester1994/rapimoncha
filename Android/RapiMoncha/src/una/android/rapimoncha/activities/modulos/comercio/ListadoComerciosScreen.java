package una.android.rapimoncha.activities.modulos.comercio;

import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.ComerciosAdapter;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.sw.SwComercio;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class ListadoComerciosScreen extends Activity{
	
	ArrayList<Comercio>comercios;
	ComerciosAdapter adapter;
	ListView lstcomercios;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_comercios);
		lstcomercios=(ListView)findViewById(R.id.listviewcomercios);
		new SwComercio().getComercios(this);
	}
	
	public void setComercios(ArrayList<Comercio>comercios){
		this.comercios=comercios;
		
		adapter=new ComerciosAdapter(this, comercios);
		lstcomercios.setAdapter(adapter);
		lstcomercios.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(ListadoComerciosScreen.this,PaginaComercioScreen.class);
				intent.putExtra("idcomercio", ListadoComerciosScreen.this.comercios.get(arg2).getIdComercio());
				startActivity(intent);
				ListadoComerciosScreen.this.finish();
				return true;
			}
		});
	}

}
