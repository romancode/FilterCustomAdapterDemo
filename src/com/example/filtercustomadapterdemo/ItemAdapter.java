package com.example.filtercustomadapterdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


public class ItemAdapter extends ArrayAdapter<Item> implements Filterable {

	List<Item> Itemlists;
	private List<Item> origItemList;
	Activity act;
	Context context;
	ItemFilter itemFilter;

	public ItemAdapter(Context ctx, int resource, List<Item> itemList) {

		super(ctx, resource, itemList);
		this.context=ctx;
		this.Itemlists=itemList;
		this.origItemList = itemList;
	}

	public int getCount() {
		return Itemlists.size();
	}

	public Item getItem(int position) {
		return Itemlists.get(position);
	}

	public long getItemId(int position) {
		return Itemlists.get(position).hashCode();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		ItemHolder holder = new ItemHolder();

		// First let's verify the convertView is not null
		if (convertView == null) {
			// This a new view we inflate the new layout
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item, null);
			// Now we can fill the layout with the right values
			TextView tv = (TextView) v.findViewById(R.id.txtListName);
			TextView distView = (TextView) v.findViewById(R.id.txtListDescription);


			holder.nameView = tv;
			holder.descView = distView;

			v.setTag(holder);
		}
		else 
			holder = (ItemHolder) v.getTag();

		Item p = Itemlists.get(position);
		holder.nameView.setText(p.getName());
		holder.descView.setText("" + p.getDescription());


		return v;
	}
	private static class ItemHolder {
		public TextView nameView;
		public TextView descView;
	}


	public void resetData() {
		Itemlists = origItemList;
	}
	/*
	 * We create our filter	
	 */

	@Override
	public Filter getFilter() {
		if (itemFilter == null)
			itemFilter = new ItemFilter();

		return itemFilter;
	}

	private class ItemFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				results.values = origItemList;
				results.count = origItemList.size();
			}
			else {
				// We perform filtering operation
				List<Item> nPlanetList = new ArrayList<Item>();

				for (Item p : Itemlists) {
					if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
						nPlanetList.add(p);
				}

				results.values = nPlanetList;
				results.count = nPlanetList.size();

			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			// Now we have to inform the adapter about the new list filtered
			if (results.count == 0)
				notifyDataSetInvalidated();
			else {
				Itemlists = (List<Item>) results.values;
				notifyDataSetChanged();
			}

		}

	};



}




