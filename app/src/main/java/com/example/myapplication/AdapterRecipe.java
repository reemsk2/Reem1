package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;




public class AdapterRecipe extends RecyclerView.Adapter<AdapterRecipe.ViewHolder>{
    private List<RestFoods> mData;
    private LayoutInflater mInflater;
    private AdapterRecipe.ItemClickListener mClickListener;

    AdapterRecipe(Context context, List<RestFoods> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    @Override
    public AdapterRecipe.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.row, parent, false);
        return new AdapterRecipe.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterRecipe.ViewHolder holder, int position) {
        RestFoods rest = mData.get(position);
        holder.tvName.setText(rest.name());

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        ImageView ivPhoto;
        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameRecipeRow);
            ivPhoto = itemView.findViewById(R.id.ivPhotoRecipeRow);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    RestFoods getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(AdapterRecipe.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
