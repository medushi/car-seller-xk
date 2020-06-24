package com.example.carsellerxk.Helpers.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsellerxk.Interfaces.IItemClickListener;
import com.example.carsellerxk.Models.NewPostUploadModel;
import com.example.carsellerxk.R;

import java.util.List;

public class CarPostsAdapter extends RecyclerView.Adapter<CarPostsAdapter.CarPostsViewHolder> {

    private List<NewPostUploadModel> carPostModelList;
    private Context context;
    private IItemClickListener itemClickListener;

    public CarPostsAdapter(List<NewPostUploadModel> carPostsList,Context context) {
        this.carPostModelList = carPostsList;
        this.context=context;
    }

    @NonNull
    @Override
    public CarPostsAdapter.CarPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_activity_home, parent, false);
        return new CarPostsAdapter.CarPostsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarPostsViewHolder holder, int position) {
        //holder.txtPostTitle.setText(carPostModelList.get(position).getCity());
        holder.txtPostTitle.setText(carPostModelList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return carPostModelList.size();
    }

    public void setClickListener(IItemClickListener iItemClickListener){
        this.itemClickListener=iItemClickListener;
    }

    public class CarPostsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtPostTitle;

        public CarPostsViewHolder(View view) {
            super(view);
            txtPostTitle=view.findViewById(R.id.txtPostTitle);
        }
        @Override
        public void onClick(View view){
            if(itemClickListener!=null){
                itemClickListener.onClick(view,getAdapterPosition());
            }
        }
    }
}


