package una.android.rapimoncha.activities.modulos.usuario;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.LoginScreen;
import una.android.rapimoncha.activities.SplashScreen;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LogoutScreen extends Activity{
	
	Handler handler;
	TextView lbl_proceso;
	ProgressBar prg_proceso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logout);
		Recursos.validateSession(this);//validamos la sesión
		lbl_proceso = (TextView) LogoutScreen.this.findViewById(R.id.lbl_description_text);
		prg_proceso = (ProgressBar) LogoutScreen.this.findViewById(R.id.prg_logout_percent);
		handler = new Handler();

		// utilizando el post delayed lo qeu se hace es basicamente hacer una
		// minipausa en la ejecucion del programa
		handler.postDelayed(new Runnable() {
			public void run() {
				lbl_proceso
						.setText(R.string.activity_logout_lbl_description_text_msg0);
				prg_proceso.setProgress(15);
				handler.postDelayed(new Runnable() {
					public void run() {
						SharedPreferences sharedPref = getSharedPreferences("rapimoncha",Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedPref.edit();
						editor.putString(getApplicationContext().getResources().getString(R.string.function_userloginstatus_usernamekey),null);
						editor.putString(getApplicationContext().getResources().getString(R.string.function_userloginstatus_statuskey), null);
						editor.commit();
						lbl_proceso
								.setText(R.string.activity_logout_lbl_description_text_msg1);
						prg_proceso.setProgress(25);
						handler.postDelayed(new Runnable() {
							public void run() {
								lbl_proceso
										.setText(R.string.activity_logout_lbl_description_text_msg2);
								prg_proceso.setProgress(30);
								handler.postDelayed(new Runnable() {
									public void run() {
										lbl_proceso.setText(R.string.activity_logout_lbl_description_text_msg3);
										prg_proceso.setProgress(85);
										handler.postDelayed(new Runnable() {
											public void run() {

												prg_proceso.setProgress(100);
												Intent intent=new Intent(LogoutScreen.this,LoginScreen.class);
												startActivity(intent);
												LogoutScreen.this.finish();
											}
										}, 500);
									}
								}, 500);
							}
						}, 500);
					}
				}, 500);
			}
		}, 500);
	}

}
