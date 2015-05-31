package una.android.rapimoncha.activities.adapter;

import java.util.ArrayList;

import una.android.rapimoncha.R;
import una.android.rapimoncha.entidades.MenuVItem;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter<MenuVItem> {
	ArrayList<MenuVItem> items;
	Context context;

	public MenuAdapter(ArrayList<MenuVItem> items, Context context) {
		super(context, R.layout.layout_menu_item, items);
		this.items = items;
		this.context = context;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v=convertView;
		if(v==null){
			v=((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.layout_menu_item, parent,false);
		}
		MenuVItem i=items.get(position);
		TextView lbl=(TextView)v.findViewById(R.id.lbltitulomenu);
		lbl.setText(i.titulo);
		ImageView img=(ImageView)v.findViewById(R.id.imageView1);
		img.setImageResource(i.icono);
		lbl.setTag(position);
		
		lbl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int position=Integer.parseInt(v.getTag().toString());
				MenuVItem item=items.get(position);
				Intent intent=new Intent(context,item.activityobjetivo);
				context.startActivity(intent);
			}
		});
		
		
		return v;
	}
}
