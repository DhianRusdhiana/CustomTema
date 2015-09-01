package com.dhian;

import android.os.*;
import android.app.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.widget.ListView.OnItemClickListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Tema extends ActionBarActivity
{
	
	private int color;

	public class Item {

		String ItemString;
		Item( String t){

			ItemString = t;
		}
	}

	static class ViewHolder {
		ImageView icon;
		TextView text;	
	}

	public class ItemsListAdapter extends BaseAdapter {

		private Context context;
		private List<Item> list;

		ItemsListAdapter(Context c, List<Item> l){
			context = c;
			list = l;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = convertView;

			if (position == 0){
				color = 0xfff44336;
			}
			if (position == 1){
				color = 0xffe91e63;
			}
			if (position == 2){
				color = 0xff9c27b0;
			}
			if (position == 3){
				color = 0xff673ab7;
			}
			if (position == 4){
				color = 0xff3f51b5;
			}
			if (position == 5){
				color = 0xff2196f3;
			}
			if (position == 6){
				color = 0xff03a9f4;
			}
			if (position == 7){
				color = 0xff00bcd4;
			}
			if (position == 8){
				color = 0xff009688;
			}
			if (position == 9){
				color = 0xff4caf50;
			}
			if (position == 10){
				color = 0xff8bc34a;
			}
			if (position == 11){
				color = 0xffcddc39;
			}
			if (position == 12){
				color = 0xffffeb3b;
			}
			if (position == 13){
				color = 0xffffc107;
			}
			if (position == 14){
				color = 0xffff9800;
			}
			if (position == 15){
				color = 0xffff5722;
			}
			if (position == 16){
				color = 0xff795548;
			}
			if (position == 17){
				color = 0xff9e9e9e;
			}
			if (position == 18){
				color = 0xff607d8b;
			}

		    // reuse views
		    if (rowView == null) {
		    	LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		    	rowView = inflater.inflate(R.layout.list_item_tema, null);

		    	ViewHolder viewHolder = new ViewHolder();
		    	viewHolder.icon = (ImageView) rowView.findViewById(R.id.rowImageView);
		    	viewHolder.text = (TextView) rowView.findViewById(R.id.rowTextView);
		    	rowView.setTag(viewHolder);	
		    }

		    ViewHolder holder = (ViewHolder) rowView.getTag();
		    holder.icon.setImageResource(R.drawable.palete);
			holder.icon.setColorFilter(color);
		    holder.text.setText(list.get(position).ItemString);

		    return rowView;
		}
	}

	List<Item> items;
	ListView listView;
	ItemsListAdapter myItemsListAdapter;
	ImageView preview;
	TextView namaTema;
	private int warna;
	private SharedPreferences prefs;
	private String prefName = "tema"; 
	private String temanya;
	private int warnaAwal;
	private Toolbar toolbar;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getID("tema","layout"));

		 toolbar = (Toolbar) findViewById(getID("toolbar","id"));
		setSupportActionBar(toolbar);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		listView = (ListView)findViewById(getID("listview","id"));
		preview = (ImageView)findViewById(getID("preview","id"));
		namaTema = (TextView)findViewById(getID("nama_tema","id"));
        
		prefs = Tema.this.getSharedPreferences(prefName, Tema.this.MODE_PRIVATE);
		warna = prefs.getInt("warna",Utils.setColor());
		preview.setColorFilter(warna);
		temanya = prefs.getString("nama","'Default'");
		namaTema.setText(temanya);
		namaTema.setShadowLayer(5,0,4,warna);
		toolbar.setBackgroundColor(warna);
        warnaAwal = warna;
		initItems();
		myItemsListAdapter = new ItemsListAdapter(this, items);
		listView.setAdapter(myItemsListAdapter);

		listView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
										int position, long id) {

					if (position == 0){
						color = 0xfff44336;
					}
					if (position == 1){
						color = 0xffe91e63;
					}
					if (position == 2){
						color = 0xff9c27b0;
					}
					if (position == 3){
						color = 0xff673ab7;
					}
					if (position == 4){
						color = 0xff3f51b5;
					}
					if (position == 5){
						color = 0xff2196f3;
					}
					if (position == 6){
						color = 0xff03a9f4;
					}
					if (position == 7){
						color = 0xff00bcd4;
					}
					if (position == 8){
						color = 0xff009688;
					}
					if (position == 9){
						color = 0xff4caf50;
					}
					if (position == 10){
						color = 0xff8bc34a;
					}
					if (position == 11){
						color = 0xffcddc39;
					}
					if (position == 12){
						color = 0xffffeb3b;
					}
					if (position == 13){
						color = 0xffffc107;
					}
					if (position == 14){
						color = 0xffff9800;
					}
					if (position == 15){
						color = 0xffff5722;
					}
					if (position == 16){
						color = 0xff795548;
					}
					if (position == 17){
						color = 0xff9e9e9e;
					}
					if (position == 18){
						color = 0xff607d8b;
					}
					String tema;
					tema = "'"+((Item)(parent.getItemAtPosition(position))).ItemString+"'";
					preview.setColorFilter(color);
					namaTema.setText(tema);
					namaTema.setShadowLayer(3,0,3,color);
					toolbar.setBackgroundColor(color);
					Toast.makeText(Tema.this, 
								   ((Item)(parent.getItemAtPosition(position))).ItemString, 
								   Toast.LENGTH_LONG).show();

					prefs = Tema.this.getSharedPreferences(prefName, Tema.this.MODE_PRIVATE);		
					SharedPreferences.Editor editor = prefs.edit();
					editor.putInt("warna", color);
					editor.putString("nama",tema);
					editor.commit();
					Intent intent = new Intent();
					intent.setAction("GANTI_TEMA");
					intent.putExtra("warnaTema", color);
					sendBroadcast(intent);
				}});

	}

	private void initItems(){
		items = new ArrayList<Item>();


		TypedArray arrayText = getResources().obtainTypedArray(getID("tema","array"));

		for(int i=0; i<arrayText.length(); i++){

			String s = arrayText.getString(i);
			Item item = new Item(s);
			items.add(item);
		}


		arrayText.recycle();
	}
	public void colorPicker(View v){
		AmbilWarnaDialog dialog = new AmbilWarnaDialog( Tema.this, warnaAwal, new AmbilWarnaDialog.OnAmbilWarnaListener() {
				@Override
				public void onOk(AmbilWarnaDialog dialog, int color) {
					Tema.this.toolbar.setBackgroundColor(color);
					String cs = "'Custom'";
					namaTema.setText(cs);
					namaTema.setShadowLayer(5,0,4,color);
					preview.setColorFilter(color);
					prefs = Tema.this.getSharedPreferences(prefName, Tema.this.MODE_PRIVATE);		
					SharedPreferences.Editor editor = prefs.edit();
					editor.putInt("warna", color);
					editor.putString("nama",cs);
					editor.commit();
					Intent intent = new Intent();
					intent.setAction("GANTI_TEMA");
					intent.putExtra("warnaTema", color);
					sendBroadcast(intent);
				}

				@Override
				public void onCancel(AmbilWarnaDialog dialog) {
					Toast.makeText(Tema.this, "Batal", Toast.LENGTH_SHORT).show();
				}
			});
		dialog.show();
	}
	public int getID(String name, String Type) {
		return getBaseContext().getResources().getIdentifier(name, Type, getBaseContext().getPackageName());
	}
		
}
