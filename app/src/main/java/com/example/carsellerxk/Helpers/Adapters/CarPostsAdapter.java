package com.example.carsellerxk.Helpers.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsellerxk.Interfaces.IItemClickListener;
import com.example.carsellerxk.Models.NewPostUploadModel;
import com.example.carsellerxk.R;
import com.squareup.picasso.Picasso;

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
        Picasso.get().load("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").noFade().into(holder.ivPostOwner);
        Picasso.get().load("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").noFade().into(holder.ivPostImage1);
        Picasso.get().load("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").noFade().into(holder.ivPostImage2);
        Picasso.get().load("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").noFade().into(holder.ivPostImage3);
        holder.txtPostOwner.setText("Filani");
        holder.txtPostTitle.setText("Golf 6 nga Gjermania gjendje te mire");
        holder.txtPostlocation.setText("Prishtine");
        holder.txtCarManufacturer.setText("Volkswagen");
        holder.txtCarAcceleratingType.setText("Automatik");
        holder.txtCarYearofProduction.setText("2012");
        holder.txtCarPrice.setText("12500");
    }

    @Override
    public int getItemCount() {
        return carPostModelList.size();
    }

    public void setClickListener(IItemClickListener iItemClickListener){
        this.itemClickListener=iItemClickListener;
    }

    public class CarPostsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtPostTitle,txtPostOwner,txtPostlocation,txtCarYearofProduction,txtCarAcceleratingType,txtCarPrice,txtCarManufacturer;
        ImageView ivPostOwner,ivPostImage1,ivPostImage2,ivPostImage3;
        public CarPostsViewHolder(View view) {
            super(view);
            txtPostTitle=view.findViewById(R.id.txtPostTitle);
            ivPostOwner=view.findViewById(R.id.ivPostOwner);
            txtPostOwner=view.findViewById(R.id.txtPostOwnerName);
            txtPostlocation=view.findViewById(R.id.txtPostLocation);
            txtCarYearofProduction=view.findViewById(R.id.txtCarYearOfProduction);
            txtCarAcceleratingType=view.findViewById(R.id.txtCarAcceleratingType);
            txtCarManufacturer=view.findViewById(R.id.txtCarManufacturer);
            txtCarPrice=view.findViewById(R.id.txtCarPrice);
            ivPostImage1=view.findViewById(R.id.ivpostImage1);
            ivPostImage2=view.findViewById(R.id.ivpostImage2);
            ivPostImage3=view.findViewById(R.id.ivpostImage3);

        }
        @Override
        public void onClick(View view){
            if(itemClickListener!=null){
                itemClickListener.onClick(view,getAdapterPosition());
            }
        }
    }
}


