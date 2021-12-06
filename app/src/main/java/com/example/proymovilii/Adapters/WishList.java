package com.example.proymovilii.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proymovilii.Models.WishListModel;
import com.example.proymovilii.R;

import java.util.ArrayList;

public class WishList extends RecyclerView.Adapter<WishList.ViewHolderDatos>  {

    private ArrayList<WishListModel> arrayListWish;

    public WishList() {
    }

    public WishList(ArrayList<WishListModel> arrayListWish){
        this.arrayListWish = arrayListWish;
    }

    @NonNull
    @Override
    public WishList.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishList.ViewHolderDatos holder, int position) {
        holder.bindWish(arrayListWish.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayListWish.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        private TextView tvComment, tvKey;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvComment = itemView.findViewById(R.id.tvComment);
        }


        public void bindWish(WishListModel wishListModel) {
            tvComment.setText("Deseo:" + wishListModel.getWish());

        }
    }
}
