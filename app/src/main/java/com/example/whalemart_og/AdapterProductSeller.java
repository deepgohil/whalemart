//package com.example.whalemart_og;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//import io.reactivex.rxjava3.annotations.NonNull;
//
//public class AdapterProductSeller extends RecyclerView.Adapter<AdapterProductSeller.HolderProductSeller> implements Filterable {
//    private Context context;
//    public ArrayList<ModelProduct> productList,filterList;
//    private FliterProduct filter;
//
//    public AdapterProductSeller(home context, ArrayList<ModelProduct> productList) {
////        this.context = getcontext();
//        this.productList = productList;
//        this.filterList=productList;
//    }
//
//    @androidx.annotation.NonNull
//    @Override
//    public HolderProductSeller onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
/////////////////////inflate layout
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent,false);
//        return  new HolderProductSeller(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@androidx.annotation.NonNull HolderProductSeller holder, int position) {
/////get data
//        ModelProduct modelProduct=productList.get(position);
//        String Title=modelProduct.getTitle();
//        String  catagory=modelProduct.getCatagory();
//        String  img1=modelProduct.getImg1();
//        String  realprice=modelProduct.getRealprice();
/////////////set data
//        holder.titleofproduct.setText(Title);
//        holder.price.setText(realprice);
//        try {
//
//           Picasso.get().load(img1).placeholder(R.drawable.shirt).into(holder.imageView);
//        }
//        catch (Exception e)
//        {
//            holder.imageView.setImageResource(R.drawable.shirt);
//        }
//        holder.titleofproduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ////////////////////////handl click
//
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//    @Override
//    public Filter getFilter() {
//        if(filter==null)
//        {
//            filter=new FliterProduct(this,filterList);
//        }
//        return filter;
//    }
//
//    class HolderProductSeller extends RecyclerView.ViewHolder
//    {
//        private ImageView imageView;
//        private TextView titleofproduct,price;
//
//        public HolderProductSeller(@NonNull View itemView) {
//            super(itemView);
//            imageView=itemView.findViewById(R.id.imageone);
//            titleofproduct=itemView.findViewById(R.id.titleofproduct);
//            price=itemView.findViewById(R.id.price);
//
//        }
//    }
//
//}
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

class AdapterSeller extends FirebaseRecyclerAdapter<ModelProduct,AdapterSeller.HolderProductSeller> {


    public AdapterSeller(@NonNull FirebaseRecyclerOptions<ModelProduct> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HolderProductSeller holder, int position, @NonNull ModelProduct model) {
        holder.titleofproduct.setText(model.getTitle());
        holder.price.setText(model.getRealprice());
        Picasso.get().load(model.getImg1()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(holder.titleofproduct.getContext(),product.class);
                String id=getRef(position).getKey();
                intent.putExtra("title",model.getTitle());
                intent.putExtra("id",id);
                intent.putExtra("price",model.getRealprice());
                intent.putExtra("url1",model.getImg1());
                intent.putExtra("catogary",model.getCatagory());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.titleofproduct.getContext().startActivity(intent);
                ((Activity)holder.titleofproduct.getContext()).finish();

            }
        });
        holder.titleofproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.titleofproduct.getContext(),product.class);
                String id=getRef(position).getKey();
                intent.putExtra("title",model.getTitle());
                intent.putExtra("id",id);
                intent.putExtra("price",model.getRealprice());
                intent.putExtra("url1",model.getImg1());
                intent.putExtra("catogary",model.getCatagory());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.titleofproduct.getContext().startActivity(intent);
                ((Activity)holder.titleofproduct.getContext()).finish();

            }
        });

        holder.trynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.titleofproduct.getContext(),product.class);
                String id=getRef(position).getKey();
                intent.putExtra("title",model.getTitle());
                intent.putExtra("id",id);
                intent.putExtra("price",model.getRealprice());
                intent.putExtra("url1",model.getImg1());
                intent.putExtra("catogary",model.getCatagory());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.titleofproduct.getContext().startActivity(intent);
                ((Activity)holder.titleofproduct.getContext()).finish();

            }
        });


    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent,false);
        return  new HolderProductSeller(view);
    }

    class HolderProductSeller extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView titleofproduct,price;
        private  String catagory;
        private Button trynow,buynow;

        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageone);
            titleofproduct=itemView.findViewById(R.id.titleofproduct);
            price=itemView.findViewById(R.id.price);
            trynow=itemView.findViewById(R.id.trynow);
            buynow=itemView.findViewById(R.id.buyNow);

        }
    }

}
