package com.example.whalemart_og;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class AdapterShowOrderToSeller extends FirebaseRecyclerAdapter<ModelShowOrderToSeller, AdapterShowOrderToSeller.HolderProductSeller> {



    public AdapterShowOrderToSeller(@NonNull FirebaseRecyclerOptions<ModelShowOrderToSeller> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HolderProductSeller holder, int position, @NonNull ModelShowOrderToSeller model) {
        holder.username.setText(model.getUsername());
        holder.productname.setText(model.getTitle());
        holder.price.setText(model.getPrice());
        holder.time.setText(model.getTime());
        holder.date.setText(model.getDate());
        Picasso.get().load(model.getImageurl()).into(holder.productimage);
        holder.orderdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.username.getContext(),showorderDetails.class);
                String id=getRef(position).getKey();
//                intent.putExtra("title",model.getTitle());
                intent.putExtra("id",id);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.username.getContext().startActivity(intent);
                ((Activity)holder.username.getContext()).finish();
            }
        });
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_for_owner,parent,false);
        return  new AdapterShowOrderToSeller.HolderProductSeller(view);
    }


    class HolderProductSeller extends RecyclerView.ViewHolder
    {
        private ImageView productimage;

        private TextView username,productname,price,time,date;

        private LinearLayout orderdetails;

        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            productimage=itemView.findViewById(R.id.productimage);
            username=itemView.findViewById(R.id.username);

            productname=itemView.findViewById(R.id.productname);
            price=itemView.findViewById(R.id.price);
            time=itemView.findViewById(R.id.time);
            date=itemView.findViewById(R.id.date);
            orderdetails=itemView.findViewById(R.id.orderdetails);

        }
    }
}
