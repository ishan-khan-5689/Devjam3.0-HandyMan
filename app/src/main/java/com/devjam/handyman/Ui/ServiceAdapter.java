package com.devjam.handyman.Ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devjam.handyman.Model.Service;
import com.devjam.handyman.R;

import java.util.ArrayList;
import java.util.Collection;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolderClass> implements Filterable {

    private ArrayList<Service> myArr;
    private ArrayList<Service> myArrFull;

    public ServiceAdapter(ArrayList<Service> myArr) {
        this.myArr = myArr;
        myArrFull = new ArrayList<>(myArr);
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_service,parent,false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        holder.name_txt.setText(myArr.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myArr.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Service> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length()==0){
                filteredList.addAll(myArrFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Service service : myArrFull){
                    if(service.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(service);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            myArr.clear();
            myArr.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        public TextView name_txt;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name_txt = itemView.findViewById(R.id.item_service_name_text);
        }
    }

}
