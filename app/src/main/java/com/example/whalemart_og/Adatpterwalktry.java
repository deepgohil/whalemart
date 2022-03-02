package com.example.whalemart_og;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whalemart_og.activity.MainActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.security.acl.AclNotFoundException;

public class Adatpterwalktry extends FirebaseRecyclerAdapter<Modelwalktry, Adatpterwalktry.HolderProductSeller> {

 
    public Adatpterwalktry(@NonNull FirebaseRecyclerOptions<Modelwalktry> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HolderProductSeller holder, int position, @NonNull Modelwalktry model) {
//        private TextView shopname,adddress,openmap,orederTime,orderId;
        holder.orderId.setText(model.getID());
        holder.shopname.setText(model.getShopname());
        holder.adddress.setText(model.getdAddress());
        holder.orederTime.setText(model.getTime());
        holder.Saddress=model.getsAddress();
        holder.Daddress=model.getdAddress();
//        holder.orederTime.setText(model.getTime());
        holder.shpoid=model.getShopid();

        Picasso.get().load(model.getImageurl()).into(holder.imgproduct);
//        Picasso.get().load(Uri.parse(list.get(position).getImgeurl())).into(holder.roundedImageView);
//holder.openmap.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        Intent intent=new Intent(holder.orderId.getContext(),openMap.class);
//        String id=getRef(position).getKey();
//        intent.putExtra("orderid",model.getID());
//        intent.putExtra("id",id);
//        intent.putExtra("shopname",model.getShopname());
//        intent.putExtra("addrss",model.getAddress());
//        intent.putExtra("ordertime",model.getTime());
//
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        holder.orderId.getContext().startActivity(intent);
//
//        ((Activity)holder.orderId.getContext()).finish();
//
//    }
//});
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=getRef(position).getKey();
                FirebaseDatabase.getInstance().getReference().child("User")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child("walkTry")
                        .child(id)
                        .removeValue();


                Intent intent=new Intent(holder.orderId.getContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.orderId.getContext().startActivity(intent);
                ((Activity)holder.orderId.getContext()).finish();


            }
        });
holder.openmap.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      try {
          Uri uri=Uri.parse("https://www.google.co.in/maps/dir/" +holder.Saddress +"/" +holder.Daddress);
          Intent intent=new Intent(Intent.ACTION_VIEW,uri);
          intent.setPackage("com.google.android.apps.maps");
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          holder.orderId.getContext().startActivity(intent);
//          ((Activity)holder.orderId.getContext()).finish();
      }
      catch (ActivityNotFoundException e)
      {
          Uri uri=Uri.parse("https://www.google.co.in/maps/dir/" +holder.Saddress +"/" +holder.Daddress);
          Intent intent=new Intent(Intent.ACTION_VIEW,uri);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          holder.orderId.getContext().startActivity(intent);
//          ((Activity)holder.orderId.getContext()).finish();

      }
    }
});

holder.card.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(holder.orderId.getContext(),walktry_order_details_show_self.class);
        String id=getRef(position).getKey();
//        intent.putExtra("orderid",model.getID());
        intent.putExtra("id",id);
//        intent.putExtra("shopname",model.getShopname());
//        intent.putExtra("addrss",model.getAddress());
//        intent.putExtra("ordertime",model.getTime());
//
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        holder.orderId.getContext().startActivity(intent);
        ((Activity)holder.orderId.getContext()).finish();

    }
});

    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.walktry_layout,parent,false);
        return  new Adatpterwalktry.HolderProductSeller(view);
    }

    class HolderProductSeller extends RecyclerView.ViewHolder
    {
        private ImageView imgproduct;
        private Button cancel;
        private String shpoid;
        private String Saddress,Daddress;
        private LinearLayout card;

        private LinearLayout getdiretion;
        private TextView shopname,adddress,openmap,orederTime,orderId;
      

        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            imgproduct=itemView.findViewById(R.id.imgproduct);
            getdiretion=itemView.findViewById(R.id.getdiretion);
            cancel=itemView.findViewById(R.id.cancel);
            shopname=itemView.findViewById(R.id.shopname);
            adddress=itemView.findViewById(R.id.adddress);
            openmap=itemView.findViewById(R.id.openmap);
            orederTime=itemView.findViewById(R.id.orederTime);
            orderId=itemView.findViewById(R.id.orderId);
            card=itemView.findViewById(R.id.card);

        }
    }
}
