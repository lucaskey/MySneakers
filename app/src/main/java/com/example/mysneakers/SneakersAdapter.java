package com.example.mysneakers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SneakersAdapter extends BaseAdapter {

    Context context;
    List<Sneakers> sneakers;

    private static class SneakersHolder {
        public TextView textViewSneakers;
    }

    public SneakersAdapter(Context context, List<Sneakers> sneakers) {
        this.context = context;
        this.sneakers = sneakers;
    }

    @Override
    public int getCount() {
        return sneakers.size();
    }

    @Override
    public Object getItem(int i) {
        return sneakers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        SneakersHolder holder;

        if (view == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_sneakers, viewGroup, false);

            holder = new SneakersHolder();

            holder.textViewSneakers = view.findViewById(R.id.textViewSneakers);

            view.setTag(holder);

        }else{

            holder = (SneakersHolder) view.getTag();
        }

        holder.textViewSneakers.setText(sneakers.get(i).getMarca());
        holder.textViewSneakers.setText(sneakers.get(i).getNome());
        holder.textViewSneakers.setText(sneakers.get(i).getColorway());
        holder.textViewSneakers.setText(sneakers.get(i).getTamanho());

        return view;
    }


}
