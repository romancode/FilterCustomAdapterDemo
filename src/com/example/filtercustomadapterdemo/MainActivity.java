package com.example.filtercustomadapterdemo;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;


public class MainActivity extends Activity {

	ListView lvItems;
	EditText txtSearch;
	ItemAdapter adapter;

	List<Item> itemList=new ArrayList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.initList();

		lvItems=(ListView)findViewById(R.id.lvItems);
		txtSearch=(EditText)findViewById(R.id.txtSearch);

		adapter=new ItemAdapter(this, R.layout.list_item, itemList);
		lvItems.setAdapter(adapter);	

		txtSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				System.out.println("Text ["+s+"] - Start ["+start+"] - Before ["+before+"] - Count ["+count+"]");
				if (count < before) {
					// We're deleting char so we need to reset the adapter data
					adapter.resetData();
				}

				adapter.getFilter().filter(s.toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

	}

	private void initList() {
		// We populate the planets

		itemList.add(new Item("Children Of Heaven", "an Majed majidi master film"));
		itemList.add(new Item("Baran", "Another Majed Majidi film"));
		itemList.add(new Item("Wind will carry us", ""));
		itemList.add(new Item("A Separation", ""));
		itemList.add(new Item("Dreams", ""));
		itemList.add(new Item("Rushmono", ""));
		itemList.add(new Item("Black Board", "Sameera"));
		itemList.add(new Item("Off side", ""));
		itemList.add(new Item("Taste of cherry", "Akira kurosua"));
		itemList.add(new Item("BBB", "Akira kurosua"));


	}

}
