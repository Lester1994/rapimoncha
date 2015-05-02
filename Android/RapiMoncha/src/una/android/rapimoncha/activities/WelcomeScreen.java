package una.android.rapimoncha.activities;

import una.android.rapimoncha.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreen extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcomescreen);
		Button btn_irregistro=(Button)findViewById(R.id.btnregistro);
		TextView lbl_irlogin=(TextView)findViewById(R.id.lblirlogin);
		
		
		btn_irregistro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// lanzar activity ir registro
				Intent intent=new Intent(WelcomeScreen.this,RegistroComercioScreen.class);
				startActivity(intent);
			}
		});
		
		lbl_irlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// lanzar activity ir login
				Intent intent=new Intent(WelcomeScreen.this,LoginScreen.class);
				startActivity(intent);
				
			}
		});
		
	}
}
