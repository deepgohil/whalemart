package com.example.whalemart_og;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class AdapterToShopOwnerShowProduct extends FirebaseRecyclerAdapter<ModelToShopOwnerShowProduct, AdapterToShopOwnerShowProduct.HolderProductSeller> {

    public AdapterToShopOwnerShowProduct(@NonNull FirebaseRecyclerOptions<ModelToShopOwnerShowProduct> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HolderProductSeller holder, int position, @NonNull ModelToShopOwnerShowProduct model) {
        holder.title.setText(model.getTitle());
        holder.price.setText(model.getRealprice());
        Picasso.get().load(model.getImg1()).into(holder.imageone);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.title.getContext(),owner_can_edit_data_here.class);
                String id=getRef(position).getKey();
                intent.putExtra("title",model.getTitle());
                intent.putExtra("id",id);
                intent.putExtra("price",model.getRealprice());
                intent.putExtra("url1",model.getImg1());
                intent.putExtra("catagory",model.getCatagory());



                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.title.getContext().startActivity(intent);
                ((Activity)holder.title.getContext()).finish();
            }
        });
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dukan_vadane_potana_product_batavva_na,parent,false);
        return  new AdapterToShopOwnerShowProduct.HolderProductSeller(view);
    }

    class HolderProductSeller extends RecyclerView.ViewHolder
    {

            private ImageView imageone;

            private TextView title,price;
            private Button edit;


        public HolderProductSeller(@NonNull View itemView) {
                super(itemView);
                imageone=itemView.findViewById(R.id.imageone);
                title=itemView.findViewById(R.id.title);
                price=itemView.findViewById(R.id.price);
            edit=itemView.findViewById(R.id.edit);
        }
    }
}
