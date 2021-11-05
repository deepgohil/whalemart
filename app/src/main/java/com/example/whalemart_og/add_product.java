package com.example.whalemart_og;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whalemart_og.activity.MainActivity;
import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class add_product extends AppCompatActivity {

    EditText title,description,actualprice,realprice,xs_size,s_size,m_size,l_size,xl_size;
    TextInputLayout title1,description1,actualprice1,realprice1,xs_size1,s_size1,m_size1,l_size1,xl_size1;
    SwitchCompat clothsswitch;
    TextView clothssize;

    EditText titleshoes,descriptionshoe,actualpriceshoe,realpriceshoe,suk7,suk8,suk9,suk10,suk11,suk12,off;
    TextInputLayout title2,description2,actualprice2,realprice2,suk71,suk81,suk91,suk101,suk111,suk121,off1;
    SwitchCompat shoes;
    TextView shoesize;

    EditText titleelectronice,descriptionelectronics,actualpriceelectronic,realpriceelectronic,quantity;
    TextInputLayout title3,description3,actualprice3,realprice3,quantity1;
    SwitchCompat switchelectronics;
    
    String common_title,common_price;

    String flag="";


String shopaddress,shopname;
ImageView img1,img2,img3;
Button clothsbtn,shoebtn,elcebtn;
Uri uri1,uri2,uri3;
ProgressBar progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        //get shop name and address
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        shopname = dataSnapshot.child("shopname").getValue(String.class);
                        shopaddress = dataSnapshot.child("shopaddress").getValue(String.class);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(add_product.this, "ERROR ", Toast.LENGTH_SHORT).show();
                    }
                });


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    img1=findViewById(R.id.img1);
    img2=findViewById(R.id.img2);
    img3=findViewById(R.id.img3);
    progressBar2=findViewById(R.id.progressBar2);

        clothsbtn=findViewById(R.id.clothsbtn);
///////////////////////////////////////shoe error
        off=findViewById(R.id.off);
        off1=findViewById(R.id.off1);


////////////////////
        clothsswitch=findViewById(R.id.clothsswitch);

        shoes=findViewById(R.id.shoes);


        switchelectronics=findViewById(R.id.switchelectronics);
//////////////////////////////////cloths




        clothssize=findViewById(R.id.clothssize);

        title1=findViewById(R.id.title1);
        description1=findViewById(R.id.description1);
        actualprice1=findViewById(R.id.actualprice1);
        realprice1=findViewById(R.id.realprice1);
        xs_size1=findViewById(R.id.xs_size1);
        s_size1=findViewById(R.id.s_size1);
        m_size1=findViewById(R.id.m_size1);
        l_size1=findViewById(R.id.l_size1);
        xl_size1=findViewById(R.id.xl_size1);

        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        actualprice=findViewById(R.id.actualprice);
        realprice=findViewById(R.id.realprice);
        xs_size=findViewById(R.id.xs_size);
        s_size=findViewById(R.id.s_size);
        m_size=findViewById(R.id.m_size);
        l_size=findViewById(R.id.l_size);
        xl_size=findViewById(R.id.xl_size);




        ///////////////////////////////////////////////hide
        clothssize.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        description.setVisibility(View.GONE);
        actualprice.setVisibility(View.GONE);
        realprice.setVisibility(View.GONE);
        xs_size.setVisibility(View.GONE);
        s_size.setVisibility(View.GONE);
        m_size.setVisibility(View.GONE);
        l_size.setVisibility(View.GONE);
        xl_size.setVisibility(View.GONE);
        clothsbtn.setVisibility(View.GONE);

        title1.setVisibility(View.GONE);
        description1.setVisibility(View.GONE);
        actualprice1.setVisibility(View.GONE);
        realprice1.setVisibility(View.GONE);
        xs_size1.setVisibility(View.GONE);
        s_size1.setVisibility(View.GONE);
        m_size1.setVisibility(View.GONE);
        l_size1.setVisibility(View.GONE);
        xl_size1.setVisibility(View.GONE);

        /////////////////////////////////////////////////////////////////////////////////////////////

        clothsswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {

//                    clothsswitch.setVisibility(View.GONE);
//                    shoes.setVisibility(View.GONE);
//                    switchelectronics.setVisibility(View.GONE);

                    shoes.setVisibility(View.GONE);
                    switchelectronics.setVisibility(View.GONE);
                    clothssize.setVisibility(View.VISIBLE);
                    title.setVisibility(View.VISIBLE);
                    description.setVisibility(View.VISIBLE);
                    actualprice.setVisibility(View.VISIBLE);
                    realprice.setVisibility(View.VISIBLE);
                    xs_size.setVisibility(View.VISIBLE);
                    s_size.setVisibility(View.VISIBLE);
                    m_size.setVisibility(View.VISIBLE);
                    l_size.setVisibility(View.VISIBLE);
                    xl_size.setVisibility(View.VISIBLE);


                    title1.setVisibility(View.VISIBLE);
                    description1.setVisibility(View.VISIBLE);
                    actualprice1.setVisibility(View.VISIBLE);
                    realprice1.setVisibility(View.VISIBLE);
                    xs_size1.setVisibility(View.VISIBLE);
                    s_size1.setVisibility(View.VISIBLE);
                    m_size1.setVisibility(View.VISIBLE);
                    l_size1.setVisibility(View.VISIBLE);
                    xl_size1.setVisibility(View.VISIBLE);


                    clothsbtn.setVisibility(View.VISIBLE);
                }
                else {

                    shoes.setVisibility(View.VISIBLE);
                    switchelectronics.setVisibility(View.VISIBLE);

                    clothssize.setVisibility(View.GONE);
                    title.setVisibility(View.GONE);
                    description.setVisibility(View.GONE);
                    actualprice.setVisibility(View.GONE);
                    realprice.setVisibility(View.GONE);
                    xs_size.setVisibility(View.GONE);
                    s_size.setVisibility(View.GONE);
                    m_size.setVisibility(View.GONE);
                    l_size.setVisibility(View.GONE);
                    xl_size.setVisibility(View.GONE);

                    title1.setVisibility(View.GONE);
                    description1.setVisibility(View.GONE);
                    actualprice1.setVisibility(View.GONE);
                    realprice1.setVisibility(View.GONE);
                    xs_size1.setVisibility(View.GONE);
                    s_size1.setVisibility(View.GONE);
                    m_size1.setVisibility(View.GONE);
                    l_size1.setVisibility(View.GONE);
                    xl_size1.setVisibility(View.GONE);


                    clothsbtn.setVisibility(View.GONE);
                }
            }
        });



//
//        shoesize
//                clothssize
//        switchelectronics

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////shoe
//        EditText titleshoes,descriptionshoe,actualpriceshoe,realpriceshoe,suk7,suk8,suk9,suk10,suk11,suk12;
//        TextInputLayout title2,description2,actualprice2,realprice2,suk71,suk81,suk91,suk101,suk111,suk121;
//        SwitchCompat shoes;
//        TextView shoesize;
        shoebtn=findViewById(R.id.shoebtn);

        shoesize=findViewById(R.id.shoesize);

        titleshoes=findViewById(R.id.titleshoes);
        descriptionshoe=findViewById(R.id.descriptionshoe);
        actualpriceshoe=findViewById(R.id.actualpriceshoe);
        realpriceshoe=findViewById(R.id.realpriceshoe);
        suk7=findViewById(R.id.suk7);
        suk8=findViewById(R.id.suk8);
        suk9=findViewById(R.id.suk9);
        suk10=findViewById(R.id.suk10);
        suk11=findViewById(R.id.suk11);
        suk12=findViewById(R.id.suk12);

        title2=findViewById(R.id.title2);
        description2=findViewById(R.id.description2);
        actualprice2=findViewById(R.id.actualprice2);
        realprice2=findViewById(R.id.realprice2);
        suk71=findViewById(R.id.suk71);
        suk81=findViewById(R.id.suk81);
        suk91=findViewById(R.id.suk91);
        suk101=findViewById(R.id.suk101);
        suk111=findViewById(R.id.suk111);
        suk121=findViewById(R.id.suk121);



        //////////////////////////////invisibla
        shoebtn.setVisibility(View.GONE);

        shoesize.setVisibility(View.GONE);

        titleshoes.setVisibility(View.GONE);
        descriptionshoe.setVisibility(View.GONE);
        actualpriceshoe.setVisibility(View.GONE);
        realpriceshoe.setVisibility(View.GONE);
        off.setVisibility(View.GONE);
        off1.setVisibility(View.GONE);
        suk7.setVisibility(View.GONE);
        suk8.setVisibility(View.GONE);
        suk9.setVisibility(View.GONE);
        suk10.setVisibility(View.GONE);
        suk11.setVisibility(View.GONE);
        suk12.setVisibility(View.GONE);


        title2.setVisibility(View.GONE);
        description2.setVisibility(View.GONE);
        actualprice2.setVisibility(View.GONE);
        realprice2.setVisibility(View.GONE);
        suk71.setVisibility(View.GONE);
        suk81.setVisibility(View.GONE);
        suk91.setVisibility(View.GONE);
        suk101.setVisibility(View.GONE);
        suk111.setVisibility(View.GONE);
        suk121.setVisibility(View.GONE);

        /////////////////////////////////////////////shoesswitch
        shoes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    clothsswitch.setVisibility(View.GONE);
//                    shoesize.setVisibility(View.GONE);
                    switchelectronics.setVisibility(View.GONE);

                    off.setVisibility(View.VISIBLE);
                    off1.setVisibility(View.VISIBLE);
                    shoebtn.setVisibility(View.VISIBLE);
                    shoesize.setVisibility(View.VISIBLE);
                    titleshoes.setVisibility(View.VISIBLE);
                    descriptionshoe.setVisibility(View.VISIBLE);
                    actualpriceshoe.setVisibility(View.VISIBLE);
                    realpriceshoe.setVisibility(View.VISIBLE);
                    suk7.setVisibility(View.VISIBLE);
                    suk8.setVisibility(View.VISIBLE);
                    suk9.setVisibility(View.VISIBLE);
                    suk10.setVisibility(View.VISIBLE);
                    suk11.setVisibility(View.VISIBLE);
                    suk12.setVisibility(View.VISIBLE);


                    title2.setVisibility(View.VISIBLE);
                    description2.setVisibility(View.VISIBLE);
                    actualprice2.setVisibility(View.VISIBLE);
                    realprice.setVisibility(View.VISIBLE);
                    suk71.setVisibility(View.VISIBLE);
                    suk81.setVisibility(View.VISIBLE);
                    suk91.setVisibility(View.VISIBLE);
                    suk101.setVisibility(View.VISIBLE);
                    suk111.setVisibility(View.VISIBLE);
                    suk121.setVisibility(View.VISIBLE);


                }
                else {

                    clothsswitch.setVisibility(View.VISIBLE);
//                    shoes.setVisibility(View.GONE);
                    switchelectronics.setVisibility(View.VISIBLE);

                    off.setVisibility(View.GONE);
                    off1.setVisibility(View.GONE);

                    shoebtn.setVisibility(View.GONE);
                    shoesize.setVisibility(View.GONE);

                    titleshoes.setVisibility(View.GONE);
                    descriptionshoe.setVisibility(View.GONE);
                    actualpriceshoe.setVisibility(View.GONE);
                    realpriceshoe.setVisibility(View.GONE);
                    suk7.setVisibility(View.GONE);
                    suk8.setVisibility(View.GONE);
                    suk9.setVisibility(View.GONE);
                    suk10.setVisibility(View.GONE);
                    suk11.setVisibility(View.GONE);
                    suk12.setVisibility(View.GONE);


                    title2.setVisibility(View.GONE);
                    description2.setVisibility(View.GONE);
                    actualprice2.setVisibility(View.GONE);
                    realprice.setVisibility(View.GONE);
                    suk71.setVisibility(View.GONE);
                    suk81.setVisibility(View.GONE);
                    suk91.setVisibility(View.GONE);
                    suk101.setVisibility(View.GONE);
                    suk111.setVisibility(View.GONE);
                    suk121.setVisibility(View.GONE);

                }
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// electronics

        titleelectronice=findViewById(R.id.titleelectronice);
        descriptionelectronics=findViewById(R.id.descriptionelectronics);
        actualpriceelectronic=findViewById(R.id.actualpriceelectronic);
        realpriceelectronic=findViewById(R.id.realpriceelectronic);

        quantity=findViewById(R.id.quantity);
        quantity1=findViewById(R.id.quantity1);

        title3=findViewById(R.id.title3);
        description3=findViewById(R.id.description3);
        actualprice3=findViewById(R.id.actualprice3);
        realprice3=findViewById(R.id.realprice3);



        elcebtn=findViewById(R.id.elcebtn);




        elcebtn.setVisibility(View.GONE);
        quantity.setVisibility(View.GONE);
        quantity1.setVisibility(View.GONE);
         titleelectronice.setVisibility(View.GONE);
         descriptionelectronics.setVisibility(View.GONE);
         actualpriceelectronic.setVisibility(View.GONE);
         realpriceelectronic.setVisibility(View.GONE);


        title3.setVisibility(View.GONE);
        description3.setVisibility(View.GONE);
        actualprice3.setVisibility(View.GONE);
        realprice3.setVisibility(View.GONE);

        switchelectronics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    clothsswitch.setVisibility(View.GONE);
                    shoes.setVisibility(View.GONE);
//                    switchelectronics.setVisibility(View.GONE);
                    quantity.setVisibility(View.VISIBLE);
                    quantity1.setVisibility(View.VISIBLE);
                    elcebtn.setVisibility(View.VISIBLE);
                    titleelectronice.setVisibility(View.VISIBLE);
                    descriptionelectronics.setVisibility(View.VISIBLE);
                    actualpriceelectronic.setVisibility(View.VISIBLE);
                    realpriceelectronic.setVisibility(View.VISIBLE);


                    title3.setVisibility(View.VISIBLE);
                    description3.setVisibility(View.VISIBLE);
                    actualprice3.setVisibility(View.VISIBLE);
                    realprice3.setVisibility(View.VISIBLE);
                }
                else {

                    clothsswitch.setVisibility(View.VISIBLE);
                    shoes.setVisibility(View.VISIBLE);

                    elcebtn.setVisibility(View.GONE);
                    titleelectronice.setVisibility(View.GONE);
                    descriptionelectronics.setVisibility(View.GONE);
                    actualpriceelectronic.setVisibility(View.GONE);
                    realpriceelectronic.setVisibility(View.GONE);
                    quantity.setVisibility(View.GONE);
                    quantity1.setVisibility(View.GONE);


                    title3.setVisibility(View.GONE);
                    description3.setVisibility(View.GONE);
                    actualprice3.setVisibility(View.GONE);
                    realprice3.setVisibility(View.GONE);
                }
            }
        });



clothsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(title.getText().toString())){
                    Toast.makeText(add_product.this, "Please Enter title Address", Toast.LENGTH_SHORT).show();
                }
                else if( description.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter description", Toast.LENGTH_SHORT).show();
                }
                else if(actualprice.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter actual price", Toast.LENGTH_SHORT).show();
                }
                else if(realprice.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter real price", Toast.LENGTH_SHORT).show();
                }
                else if(xs_size.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "XS size data is missing", Toast.LENGTH_SHORT).show();
                }
                else if(s_size.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "S size data is missing", Toast.LENGTH_SHORT).show();
                }
                else if(m_size.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "M size data is missing", Toast.LENGTH_SHORT).show();
                }

                else if(l_size.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "L size data is missing", Toast.LENGTH_SHORT).show();
                }

                else if(xl_size.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Xl size data is missing", Toast.LENGTH_SHORT).show();
                }
                else if(uri1 == null){
                    Toast.makeText(add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else if(uri2 == null){
                    Toast.makeText(add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else if(uri3 == null){
                    Toast.makeText(add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else if(shopaddress.isEmpty()){
                    Toast.makeText(add_product.this, "wait for two seconds", Toast.LENGTH_SHORT).show();
                }

                else if(shopname.isEmpty()){
                    Toast.makeText(add_product.this, "wait for two seconds", Toast.LENGTH_SHORT).show();
                }
                else {
                    common_price=realprice.getText().toString();
                    common_title=title.getText().toString();
                    upload1();
                    flag="cloths";
                }
            }

         
        });


//    Button clothsbtn,shoebtn,elcebtn;
        shoebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(titleshoes.getText().toString())){
                    Toast.makeText(add_product.this, "Please Enter title Address", Toast.LENGTH_SHORT).show();
                }
                else if( descriptionshoe.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter description", Toast.LENGTH_SHORT).show();
                }
                else if(actualpriceshoe.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter actual price", Toast.LENGTH_SHORT).show();
                }
                else if(off.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter real Price", Toast.LENGTH_SHORT).show();
                }
//                suk7,suk8,suk9,suk10,suk11,suk12

                else if(suk7.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Uk 7 data is missing", Toast.LENGTH_SHORT).show();
                }
                else if(suk8.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Uk 8 data is missing", Toast.LENGTH_SHORT).show();
                }
                else if(suk9.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Uk 9 data is missing", Toast.LENGTH_SHORT).show();
                }
                else if(suk10.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Uk 10 data is missing", Toast.LENGTH_SHORT).show();
                }
                else if(suk11.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Uk 11 data is missing", Toast.LENGTH_SHORT).show();
                }
                else if(suk12.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Uk 12 data is missing", Toast.LENGTH_SHORT).show();
                }

                else if(uri1 == null){
                    Toast.makeText(add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else if(uri2 == null){
                    Toast.makeText(add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else if(uri3 == null){
                    Toast.makeText(add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else if(shopaddress.isEmpty()){
                    Toast.makeText(add_product.this, "wait for two seconds", Toast.LENGTH_SHORT).show();
                }

                else if(shopname.isEmpty()){
                    Toast.makeText(add_product.this, "wait for two seconds", Toast.LENGTH_SHORT).show();
                }
                else {

                    common_price=off.getText().toString();
                    common_title=titleshoes.getText().toString();
                    upload1();
                    flag="shoes";
                }



            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////elec btn
        elcebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditText titleelectronice,descriptionelectronics,actualpriceelectronic,realpriceelectronic,quantity;
                if(TextUtils.isEmpty(titleelectronice.getText().toString())){
                    Toast.makeText(add_product.this, "Please Enter title Address", Toast.LENGTH_SHORT).show();
                }
                else if(descriptionelectronics.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter description", Toast.LENGTH_SHORT).show();
                }
                else if(actualpriceelectronic.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter actual price", Toast.LENGTH_SHORT).show();
                }
                else if(realpriceelectronic.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter real price", Toast.LENGTH_SHORT).show();
                }
                else if(quantity.getText().toString().isEmpty())
                {
                    Toast.makeText(add_product.this, "Please Enter Quantity", Toast.LENGTH_SHORT).show();
                }
                else if(uri1 == null){
                    Toast.makeText(add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else if(uri2 == null){
                    Toast.makeText(add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else if(uri3 == null){
                    Toast.makeText(add_product.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else if(shopaddress.isEmpty()){
                    Toast.makeText(add_product.this, "wait for two seconds", Toast.LENGTH_SHORT).show();
                }

                else if(shopname.isEmpty()){
                    Toast.makeText(add_product.this, "wait for two seconds", Toast.LENGTH_SHORT).show();
                }
                else {

                    common_price=realpriceelectronic.getText().toString();
                    common_title=titleelectronice.getText().toString();
                    upload1();
                    flag="elec";
                }



            }
        });
img1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ImagePicker.Companion.with(add_product.this)
                .crop()
                .compress(1024)
                .maxResultSize(1080,1080)
                .crop(3f, 4f)
                .start(1);
    }
});
img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(add_product.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .crop(3f, 4f)
                        .start(2);
            }
        });
img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(add_product.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .crop(3f, 4f)
                        .start(3);
            }
        }); }

    private void upload1() {
        progressBar2.setVisibility(View.VISIBLE);
        final StorageReference riversRef = FirebaseStorage.getInstance().getReference().child("product/"+System.currentTimeMillis()+".png");
        riversRef.putFile(uri1).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                progressBar2.setVisibility(View.GONE);
                Toast.makeText(add_product.this, exception.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uri1 = uri;
                        upload2();
                    }
                });

            }
        });
    }
    private void upload2() {
        final StorageReference riversRef = FirebaseStorage.getInstance().getReference().child("product/"+System.currentTimeMillis()+".png");
        riversRef.putFile(uri2).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                progressBar2.setVisibility(View.GONE);
                Toast.makeText(add_product.this, exception.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uri2=uri;
                        upload3();
                    }
                });

            }
        });
    }
    private void upload3() {
        final StorageReference riversRef = FirebaseStorage.getInstance().getReference().child("product/"+System.currentTimeMillis()+".png");
        riversRef.putFile(uri3).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                progressBar2.setVisibility(View.GONE);
                Toast.makeText(add_product.this, exception.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uri3=uri;
                      realtime();
                    }
                });

            }
        });
    }





    private void realtime() {
if(flag=="cloths") {
    Long tsLong = System.currentTimeMillis()/1000;
    String ts = tsLong.toString();

    HashMap<String, Object> map = new HashMap<>();
    map.put("id",ts.trim());
    map.put("img1", uri1.toString());
    map.put("img2", uri2.toString());
    map.put("img3", uri3.toString());
    map.put("catagory", flag);
    map.put("Title", title.getText().toString().trim());
    map.put("description", description.getText().toString().trim());
    map.put("actualprice", actualprice.getText().toString().trim());
    map.put("realprice", realprice.getText().toString().trim());
    map.put("xs_size", xs_size.getText().toString());
    map.put("s_size", s_size.getText().toString());
    map.put("m_size", m_size.getText().toString());
    map.put("l_size", l_size.getText().toString());
    map.put("xl_size", xl_size.getText().toString());
    map.put("shop id", FirebaseAuth.getInstance().getUid());
    map.put("shopname", shopname);
    map.put("shopaddress", shopaddress);


//    shopname = dataSnapshot.child("shopname").getValue(String.class);
//    address = dataSnapshot.child("address").getValue(String.class);
//



    FirebaseDatabase.getInstance().getReference()
            .child("product")
//            .child(FirebaseAuth.getInstance().getUid())
            .child(ts)
            .setValue(map)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    uploadcloths(ts);


                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar2.setVisibility(View.GONE);
                    Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
}
else if(flag=="shoes")
{

    Long tsLong = System.currentTimeMillis()/1000;
    String ts = tsLong.toString();
//    EditText titleshoes,descriptionshoe,actualpriceshoe,realpriceshoe,suk7,suk8,suk9,suk10,suk11,suk12;//

    HashMap<String, Object> map = new HashMap<>();
    map.put("id",ts.trim());
    map.put("img1", uri1.toString());
    map.put("img2", uri2.toString());
    map.put("img3", uri3.toString());
    map.put("catagory", flag);
    map.put("Title", titleshoes.getText().toString().trim());
    map.put("description", descriptionshoe.getText().toString().trim());
    map.put("actualprice", actualpriceshoe.getText().toString().trim());
    map.put("realprice", off.getText().toString().trim());
    map.put("uk 7", suk7.getText().toString().trim());
    map.put("uk 8", suk8.getText().toString().trim());
    map.put("uk 9", suk9.getText().toString().trim());
    map.put("uk 10", suk10.getText().toString().trim());
    map.put("uk 11", suk11.getText().toString().trim());
    map.put("uk 12", suk12.getText().toString().trim());
    map.put("shop id", FirebaseAuth.getInstance().getUid());
    map.put("shopname", shopname);
    map.put("shopaddress", shopaddress);


    FirebaseDatabase.getInstance().getReference()
            .child("product")
//            .child(FirebaseAuth.getInstance().getUid())
            .child(ts)
            .setValue(map)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    uploadshoes(ts);

                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar2.setVisibility(View.GONE);
                    Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(add_product.this, MainActivity.class));
                    finish();
                }
            });

}
else if(flag=="elec")
{
//    EditText titleelectronice,descriptionelectronics,actualpriceelectronic,realpriceelectronic,quantity;

    Long tsLong = System.currentTimeMillis()/1000;
    String ts = tsLong.toString();

    HashMap<String, Object> map = new HashMap<>();
    map.put("id",ts.trim());
    map.put("img1", uri1.toString());
    map.put("img2", uri2.toString());
    map.put("img3", uri3.toString());
    map.put("catagory", flag);
    map.put("Title", titleelectronice.getText().toString().trim());
    map.put("description", descriptionelectronics.getText().toString().trim());
    map.put("actualprice", actualpriceelectronic.getText().toString().trim());
    map.put("realprice", realpriceelectronic.getText().toString().trim());
    map.put("quantity", quantity.getText().toString().trim());
    map.put("shop id", FirebaseAuth.getInstance().getUid());
    map.put("shopname", shopname);
    map.put("shopaddress", shopaddress);


    FirebaseDatabase.getInstance().getReference()
            .child("product")
//            .child(FirebaseAuth.getInstance().getUid())
            .child(ts)
            .setValue(map)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    uploadinelec(ts);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar2.setVisibility(View.GONE);
                    Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

}
else {
    Toast.makeText(add_product.this,"Error..", Toast.LENGTH_SHORT).show();
}

    }

    private void uploadinelec(String ts) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("img1", uri1.toString());///
        map.put("catagory", flag);////
        map.put("Title", titleelectronice.getText().toString().trim());///
        map.put("realprice", realpriceelectronic.getText().toString().trim());///

        FirebaseDatabase.getInstance().getReference()
                .child("elec")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(ts)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        selfupload(ts);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar2.setVisibility(View.GONE);
                        Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }

    private void uploadshoes(String ts) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("img1", uri1.toString());///
        map.put("catagory", flag);////
        map.put("Title", titleshoes.getText().toString().trim());///
        map.put("realprice",off.getText().toString().trim());///

        FirebaseDatabase.getInstance().getReference()
                .child("shoe")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(ts)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        progressBar2.setVisibility(View.GONE);
//                        Toast.makeText(add_product.this, "Data inserted", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(add_product.this, MainActivity.class));
//                        finish();
                        selfupload(ts);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar2.setVisibility(View.GONE);
                        Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }

    private void selfupload(String ts) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("img1", uri1.toString());///
        map.put("catagory", flag);////
        map.put("IDfordeleteproduct", ts);////
        map.put("Title", common_title);///
        map.put("realprice",common_price);///

        FirebaseDatabase.getInstance().getReference()
                .child("User")
             .child(FirebaseAuth.getInstance().getUid())
                .child("userproducts")
                .child(ts)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar2.setVisibility(View.GONE);
                        Toast.makeText(add_product.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(add_product.this, MainActivity.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar2.setVisibility(View.GONE);
                        Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void uploadcloths(String ts) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("img1", uri1.toString());///
        map.put("catagory", flag);////
        map.put("Title", title.getText().toString().trim());///
        map.put("realprice", realprice.getText().toString().trim());///

        FirebaseDatabase.getInstance().getReference()
                .child("cloths")
//            .child(FirebaseAuth.getInstance().getUid())
                .child(ts)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        selfupload(ts);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar2.setVisibility(View.GONE);
                        Toast.makeText(add_product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            uri1 = data.getData();
            img1.setImageURI(uri1);
        }
        if (requestCode == 2) {
            uri2 = data.getData();
            img2.setImageURI(uri2);
        }
        if (requestCode == 3) {
            uri3 = data.getData();
            img3.setImageURI(uri3);
        }
    }



}