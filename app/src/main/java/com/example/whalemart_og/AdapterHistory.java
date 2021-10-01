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


public class AdapterHistory extends FirebaseRecyclerAdapter<Modelhistory,AdapterHistory.HolderProductSeller> {



    public AdapterHistory(@NonNull FirebaseRecyclerOptions<Modelhistory> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HolderProductSeller holder, int position, @NonNull Modelhistory model) {
        Picasso.get().load(model.getUrl1()).into(holder.productimage);
        holder.productprice.setText(model.getPrice());
        holder.producttitle.setText(model.getTitle());
        holder.productdesc.setText(model.getDesc());
        holder.viewproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout,parent,false);
        return  new AdapterHistory.HolderProductSeller(view);
    }

    class HolderProductSeller extends RecyclerView.ViewHolder
    {
        private ImageView productimage;
        private TextView producttitle,productprice,productdesc;
        private Button viewproduct;

        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            productimage=itemView.findViewById(R.id.productimage);
            producttitle=itemView.findViewById(R.id.producttitle);
            productprice=itemView.findViewById(R.id.productprice);

            productdesc=itemView.findViewById(R.id.productdesc);
            viewproduct=itemView.findViewById(R.id.viewproduct);

        }
    }
}
