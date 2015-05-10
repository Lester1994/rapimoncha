package una.android.rapimoncha.activities;

import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.sw.SwUsuario;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class WelcomeScreen extends Activity {
 	ArrayList<Comercio>comercios;
 	Spinner spinnercomercios;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcomescreen);
		Button btn_irregistro=(Button)findViewById(R.id.btnregistro);
		spinnercomercios=(Spinner)findViewById(R.id.spinnerempresas);
		SharedPreferences sharedPref =getApplication().getSharedPreferences("rapimoncha",Context.MODE_PRIVATE);
		String username = sharedPref.getString(getResources().getString(R.string.function_userloginstatus_usernamekey), null);
		String status = sharedPref.getString(getResources().getString(R.string.function_userloginstatus_statuskey), null);
		
		Log.i("username", username+" este es el valor welcome");
		Log.i("status",status+" este es status welcome");
		
		
		btn_irregistro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// lanzar activity ir registro
				Intent intent=new Intent(WelcomeScreen.this,RegistroComercioScreen.class);
				startActivity(intent);
			}
		});
		Usuario usuario=new Usuario();
		usuario.setUsername(username);
	
		new SwUsuario().getComerciosUsuarios(usuario, this);
		
		Button btniniciar=(Button)findViewById(R.id.btniniciarempresa);
		btniniciar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Comercio comercio=comercios.get(spinnercomercios.getSelectedItemPosition());
				//guardar que si se registro el login
				SharedPreferences sharedPref = getSharedPreferences("rapimoncha",Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt("idcomercio", comercio.getIdComercio());
				editor.commit();
				Intent intent=new Intent(WelcomeScreen.this,Main.class);
				startActivity(intent);
			}
		});
	}
	public void setComercios(ArrayList<Comercio>comercios){
		this.comercios=comercios;
		Log.i("cantidad",comercios.size()+"");
		ArrayList<String>lista=new ArrayList<String>();
		for(Comercio comer:comercios){
			Log.i("resultado ","entreee"+comer.getNoComerncio());
			lista.add(comer.getIdComercio() +" - "+comer.getNoComerncio());
		}
		ArrayAdapter<String>adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,lista);
		spinnercomercios.setAdapter(adapter);
	}
}
