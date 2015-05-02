package una.android.rapimoncha.activities.adapter;

import java.util.ArrayList;
import java.util.List;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.CategoriaComercio;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class CategoriaComercioAdapter extends ArrayAdapter<CategoriaComercio> {
	Activity context;
	ArrayList<CategoriaComercio> objects;
	public CategoriaComercioAdapter(Activity context,	ArrayList<CategoriaComercio> objects) {
		super(context, R.layout.layout_categoriacomercio_listviewitem, objects);
		
		this.context=context;
		this.objects=objects;
	}
	  static class ViewHolder {
		    protected TextView text;
		    protected CheckBox checkbox;
		  }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=convertView;
		  if (view == null) {
		      LayoutInflater inflator = context.getLayoutInflater();
		      view = inflator.inflate(R.layout.layout_categoriacomercio_listviewitem, null,false);
		      final ViewHolder viewHolder = new ViewHolder();
		      viewHolder.text = (TextView) view.findViewById(R.id.lbl_tituloelementocategoria);
		      viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
		      viewHolder.checkbox
		          .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

		            @Override
		            public void onCheckedChanged(CompoundButton buttonView,
		                boolean isChecked) {
		              CategoriaComercio element = (CategoriaComercio) viewHolder.checkbox.getTag();
		              element.seleccionado=buttonView.isChecked();

		            }
		          });
		      view.setTag(viewHolder);
		      viewHolder.checkbox.setTag(objects.get(position));
		    } else {
		      view = convertView;
		      ((ViewHolder) view.getTag()).checkbox.setTag(objects.get(position));
		    }
		    ViewHolder holder = (ViewHolder) view.getTag();
		    holder.text.setText(objects.get(position).getNoTipoCategoria());
		    holder.checkbox.setChecked(objects.get(position).seleccionado);	 
		
		return view;
	}

}
