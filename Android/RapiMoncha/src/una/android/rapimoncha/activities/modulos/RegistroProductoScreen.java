package una.android.rapimoncha.activities.modulos;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.fragments.RegistroProducto1Fragment;
import una.android.rapimoncha.fragments.RegistroProducto2Fragment;
import una.android.rapimoncha.fragments.RegistroProducto3Fragment;
import una.android.rapimoncha.fragments.RegistroProductoSkyFragment;
import una.android.rapimoncha.interfaces.IGenComercio;
import una.android.rapimoncha.interfaces.IGenProducto;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RegistroProductoScreen extends Activity{
	public static final int MIN_REGISTROFRAGMENTS_INDEX = 0;
	public static final int MAX_REGISTROFRAGMENTS_INDEX = 4;
	Fragment fragment;
	int index_actual = 0;
	Button btn_prev;
	Button btn_next;
	Comercio comercio;
	Producto producto;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registroproducto);
		btn_next = (Button) findViewById(R.id.btn_next_product);
		btn_prev = (Button) findViewById(R.id.btnprev_product);
		fragment = null;
		comercio = new Comercio();
		comercio.setIdComercio(25);
		producto=new Producto();
		btn_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				++index_actual;
				if (index_actual < MAX_REGISTROFRAGMENTS_INDEX) {
					cargarFragment(index_actual);
					if (!(index_actual < MAX_REGISTROFRAGMENTS_INDEX)) {
						btn_next.setEnabled(false);
						btn_prev.setEnabled(true);
					}
				} else {
					btn_next.setEnabled(false);
					btn_prev.setEnabled(true);
					Toast.makeText(RegistroProductoScreen.this,
							R.string.activity_registroproducto_java_noiradelante, Toast.LENGTH_LONG).show();
				}
			}
		});

		btn_prev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				--index_actual;
				if (index_actual >= MIN_REGISTROFRAGMENTS_INDEX) {
					cargarFragment(index_actual);
					if (!(index_actual >= MIN_REGISTROFRAGMENTS_INDEX)) {
						btn_prev.setEnabled(false);
						btn_next.setEnabled(true);
					}
				} else {
					btn_prev.setEnabled(false);
					btn_next.setEnabled(true);
					Toast.makeText(RegistroProductoScreen.this,
							R.string.activity_registroproducto_java_noiratras, Toast.LENGTH_LONG).show();
				}
			}
		});
		cargarFragment(0);

	}

	private void cargarFragment(int index) {
		

		if (fragment != null) {
			if ((fragment instanceof IGenProducto)
					&& ((IGenProducto) fragment).actualizarProducto()) {
				//Toast.makeText(RegistroComercioScreen.this, "Actualizado",Toast.LENGTH_LONG).show();
			}
		}
		switch (index) {
		case 0:
			fragment = new RegistroProductoSkyFragment();
			break;
		case 1:
			fragment = new RegistroProducto1Fragment();
			((RegistroProducto1Fragment) fragment).setProducto(producto);
			break;
		case 2:
			fragment = new RegistroProducto2Fragment();
			((RegistroProducto2Fragment) fragment).setProducto(producto);
			break;	
		case 3:
			fragment = new RegistroProducto3Fragment();
			((RegistroProducto3Fragment) fragment).setProducto(producto);
			((RegistroProducto3Fragment) fragment).setComercio(comercio);
			break;				
		
		default:

			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frg_container_product, fragment).commit();
		}

	}

	

}
