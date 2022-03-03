package com.example.whalemart_og;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdapterHometry extends FirebaseRecyclerAdapter<Modelhometry,AdapterHometry.HolderProductSeller> {

    public AdapterHometry(@NonNull FirebaseRecyclerOptions<Modelhometry> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HolderProductSeller holder, int position, @NonNull Modelhometry model) {
        holder.adddress.setText(model.getsAddress());
        holder.producid.setText(model.getOrderId());
        holder.productprice.setText(model.getPrice());
        holder.producttitle.setText(model.getTitle());
        Picasso.get().load(model.getImageurl()).into(holder.productimage);
        holder.shopid=model.getShopid();
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.producid.getContext(), MainActivity.class);

                String id=getRef(position).getKey();
                FirebaseDatabase.getInstance().getReference().child("User")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child("homeTry")
                        .child(id)
                        .removeValue();

                FirebaseDatabase.getInstance().getReference().child("User")
                        .child(holder.shopid)
                        .child("yourorders")
                        .child(id)
                        .removeValue();

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.producid.getContext().startActivity(intent);

                ((Activity)holder.producid.getContext()).finish();
            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.producid.getContext(),hometry_order_details_show_self.class);
                String id=getRef(position).getKey();
                intent.putExtra("id",id);
                intent.putExtra("shopid",holder.shopid);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.producid.getContext().startActivity(intent);
                ((Activity)holder.producid.getContext()).finish();
            }
        });
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hometry_layout,parent,false);
        return  new AdapterHometry.HolderProductSeller(view);
    }

    class HolderProductSeller extends RecyclerView.ViewHolder
    {
        private ImageView productimage;
        private TextView producttitle,productprice,adddress,producid;
        private Button cancel;
        private String shopid;
        private LinearLayout card;


        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            productimage=itemView.findViewById(R.id.productimage);
            producttitle=itemView.findViewById(R.id.producttitle);
            productprice=itemView.findViewById(R.id.productprice);

            adddress=itemView.findViewById(R.id.adddress);
            producid=itemView.findViewById(R.id.producid);
            cancel=itemView.findViewById(R.id.cancel);

            card=itemView.findViewById(R.id.card);

        }
    }
}
//extends FirebaseRecyclerAdapter<Modelhometry,AdapterHometry.HolderProductSeller>