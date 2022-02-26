package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRecipe extends RecyclerView.Adapter<AdapterRecipe.ViewHolder>{
    private List<Recipe> mData;
    private LayoutInflater mInflater;
    private AdapterRecipe.ItemClickListener mClickListener;
    AdapterRecipe(Context context,List<Recipe> data){
        this.mInflater = LayoutInflater.from(context)
                this.mData = data;
    }


    private final AdapterRecipe.ItemClickListener = new MyRecyclerViewAdapter.ItemClickListener(){
        @Override
        public void onItemClick(View view, int position) {
            Recipe recipe = mData.get(position);
            Intent i = new Intent(context, RecipeDetailsActivity.class);
            i.putExtra("recipe", recipe);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    };

    AdapterRecipe(Context context, List<Recipe> data){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }
    @Override
    public AdapterRecipe.ViewHolder onCreatViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.row_recipe, parent, false);
        return new AdapterRecipe.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRecipe.ViewHolder holder, int position) {
        Recipe recipe = mData.get(position);
        holder.tvName.setText(recipe.getName());
        Picasso.get().load(recipe.getPhoto()).into(holder.ivPhoto);
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
    Recipe getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    /*
    void setClickListener(AdapterRestaurant.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }*/

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

