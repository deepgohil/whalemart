package com.example.whalemart_og;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;
import java.util.PrimitiveIterator;

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
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            productimage=itemView.findViewById(R.id.productimage);
            producttitle=itemView.findViewById(R.id.producttitle);
            productprice=itemView.findViewById(R.id.productprice);

            adddress=itemView.findViewById(R.id.adddress);
            producid=itemView.findViewById(R.id.producid);
            cancel=itemView.findViewById(R.id.cancel);

        }
    }
}
//extends FirebaseRecyclerAdapter<Modelhometry,AdapterHometry.HolderProductSeller>