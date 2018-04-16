package br.ufpe.cin.if1001.rss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemRSSAdapter extends ArrayAdapter<ItemRSS> {

    public ItemRSSAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ItemRSSAdapter(Context context, int resource, List<ItemRSS> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlista, null);
        }

        ItemRSS p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.item_titulo);
            TextView tt2 = (TextView) v.findViewById(R.id.item_data);

            if (tt1 != null) {
                tt1.setText(p.getTitle());
            }

            if (tt2 != null) {
                tt2.setText(p.getPubDate());
            }
        }

        return v;
    }

}
