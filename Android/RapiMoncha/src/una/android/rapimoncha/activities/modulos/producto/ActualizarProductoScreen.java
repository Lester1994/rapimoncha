package una.android.rapimoncha.activities.modulos.producto;



import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.fragments.ActualizarProducto1Fragment;
import una.android.rapimoncha.fragments.ActualizarProducto2Fragment;
import una.android.rapimoncha.fragments.ActualizarProducto3Fragment;
import una.android.rapimoncha.fragments.ActualizarProductoSkyFragment;
import una.android.rapimoncha.fragments.RegistroProducto1Fragment;
import una.android.rapimoncha.fragments.RegistroProducto2Fragment;
import una.android.rapimoncha.fragments.RegistroProducto3Fragment;
import una.android.rapimoncha.fragments.RegistroProductoSkyFragment;
import una.android.rapimoncha.interfaces.IGenComercio;
import una.android.rapimoncha.interfaces.IGenProducto;
import una.android.rapimoncha.sw.SwProducto;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActualizarProductoScreen extends Activity{
	public static final int MIN_REGISTROFRAGMENTS_INDEX = 0;
	public static final int MAX_REGISTROFRAGMENTS_INDEX = 4;
	Fragment fragment;
	int index_actual = 0;
	Button btn_prev;
	Button btn_next;
	Comercio comercio;
	Producto producto;
	int idcomercio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registroproducto);
		btn_next = (Button) findViewById(R.id.btn_next_product);
		btn_prev = (Button) findViewById(R.id.btnprev_product);
		fragment = null;
		initProducto();

		

	}
	
	private void initProducto(){
		comercio = new Comercio();
		
		Bundle bundle=getIntent().getExtras();
		producto=(Producto) bundle.getSerializable("producto");//de aqui sacamos el id 
		SharedPreferences sharedPref = getApplication().getSharedPreferences(
				"rapimoncha", Context.MODE_PRIVATE);
		idcomercio = sharedPref.getInt("idcomercio", 0);
		comercio.setIdComercio(idcomercio);
		new SwProducto().getProducto(idcomercio,producto.getIdProducto(), this);
	}

	public void setProducto(Producto producto){
		this.producto=producto;
		cargarFragment(0);
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
					Toast.makeText(ActualizarProductoScreen.this,
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
					Toast.makeText(ActualizarProductoScreen.this,
							R.string.activity_registroproducto_java_noiratras, Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
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
			fragment = new ActualizarProductoSkyFragment();
			break;
		case 1:
			fragment = new ActualizarProducto1Fragment();
			((ActualizarProducto1Fragment) fragment).setProducto(producto);
			break;
		case 2:
			fragment = new ActualizarProducto2Fragment();
			((ActualizarProducto2Fragment) fragment).setProducto(producto);
			break;	
		case 3:
			fragment = new ActualizarProducto3Fragment();
			((ActualizarProducto3Fragment) fragment).setProducto(producto);
			((ActualizarProducto3Fragment) fragment).setComercio(comercio);
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
