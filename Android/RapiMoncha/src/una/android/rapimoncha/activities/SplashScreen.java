package una.android.rapimoncha.activities;


import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.modulos.usuario.RegistroUsuarioScreen;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreen extends Activity {
	Handler handler;
	TextView lbl_proceso;
	ProgressBar prg_proceso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
		lbl_proceso = (TextView) SplashScreen.this
				.findViewById(R.id.lbl_proceso);
		prg_proceso = (ProgressBar) SplashScreen.this
				.findViewById(R.id.prg_barraprogreso);
		handler = new Handler();

		// utilizando el post delayed lo qeu se hace es basicamente hacer una
		// minipausa en la ejecucion del programa
		handler.postDelayed(new Runnable() {
			public void run() {
				lbl_proceso
						.setText(R.string.activity_splashscreen_progress_fase1);
				prg_proceso.setProgress(15);
				handler.postDelayed(new Runnable() {
					public void run() {
						lbl_proceso
								.setText(R.string.activity_splashscreen_progress_fase2);
						prg_proceso.setProgress(25);
						handler.postDelayed(new Runnable() {
							public void run() {
								lbl_proceso
										.setText(R.string.activity_splashscreen_progress_fase3);
								prg_proceso.setProgress(30);
								handler.postDelayed(new Runnable() {
									public void run() {
										lbl_proceso
												.setText(R.string.activity_splashscreen_progress_fase4);
										prg_proceso.setProgress(85);
										handler.postDelayed(new Runnable() {
											public void run() {
												lbl_proceso
														.setText(R.string.activity_splashscreen_progress_fase5);
												prg_proceso.setProgress(100);
												Intent intent=new Intent(SplashScreen.this,RegistroUsuarioScreen.class);
												startActivity(intent);
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
