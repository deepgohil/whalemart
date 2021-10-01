package com.example.whalemart_og;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ordertype extends AppCompatActivity {
    String title,id,price,url,size,desc,productcount;
    TextView producttitle,productprice,psize,productdesc;
    LinearLayout gotowalktry,gotohometry;
    ImageView productimage;
    int myNum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordertype);

        title=getIntent().getStringExtra("title");
        id=getIntent().getStringExtra("id");
        price=getIntent().getStringExtra("price");
        url=getIntent().getStringExtra("url1");
        size=getIntent().getStringExtra("size");
        desc=getIntent().getStringExtra("desc");
        productcount=getIntent().getStringExtra("productcount");

//        Toast.makeText(ordertype.this,productcount, Toast.LENGTH_SHORT).show();
        try {
            myNum = Integer.parseInt(productcount);
            myNum--;
            Toast.makeText(ordertype.this,""+myNum, Toast.LENGTH_SHORT).show();
        } catch(NumberFormatException nfe) {
            Toast.makeText(ordertype.this,"Could not parse " + nfe, Toast.LENGTH_SHORT).show();
            Log.d("error", String.valueOf(nfe));
        }






        productimage=findViewById(R.id.productimage);
        producttitle=findViewById(R.id.producttitle);
        productprice=findViewById(R.id.productprice);
        psize=findViewById(R.id.psize);
        productdesc=findViewById(R.id.productdesc);

        productprice.setText(price+"₹");
    producttitle.setText(title);
    psize.setText(size);
    productdesc.setText(desc);
    Picasso.get().load(url).into(productimage);
    ////////////////////walktry
        gotowalktry=findViewById(R.id.gotowalktry);
        gotowalktry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starthome = new Intent(ordertype.this, walkTry.class);
                starthome.putExtra("url1",url);
                startActivity(starthome);

            }
        });

////////////////////////hometry
        gotohometry=findViewById(R.id.gotohometry);
        gotohometry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starthome = new Intent(ordertype.this, homeTry.class);
                starthome.putExtra("title",title);
                starthome.putExtra("price",price);
                starthome.putExtra("size",size);
                starthome.putExtra("desc",desc);
                starthome.putExtra("id",id);
                starthome.putExtra("url1",url);
                startActivity(starthome);
            }
        });
    }
}