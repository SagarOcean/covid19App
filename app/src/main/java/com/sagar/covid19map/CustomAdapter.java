package com.sagar.covid19map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<CountryModel> {
    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;

    public CustomAdapter( Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.custum_list,countryModelList);
        this.context = context;
        this.countryModelList = countryModelList;
        countryModelListFiltered =countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custum_list,null,true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imgFlag);

        tvCountryName.setText(countryModelListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(imageView);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter(){

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length()==0){
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;
                }
                else {
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String srchstr= charSequence.toString().toLowerCase();

                    for(CountryModel itemModel:countryModelList){
                        if(itemModel.getCountry().toLowerCase().contains(srchstr))
                            resultsModel.add(itemModel);
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countryModelListFiltered = (List<CountryModel>) filterResults.values;
                AffectedCountries.countryModelList =(List<CountryModel>) filterResults.values;
                notifyDataSetChanged();

            }
        };

        return filter;
    }
}
