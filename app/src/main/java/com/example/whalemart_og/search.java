package com.example.whalemart_og;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class search extends Fragment {

    LinearLayout linearLayout,bandhkam;
    CardView shoes;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_search, container, false);

        linearLayout=view.findViewById(R.id.mainbox);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openshop = new Intent(getActivity(), showclothes.class);
                startActivity(openshop);
            }
        });

        shoes=view.findViewById(R.id.shoes);
        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openshoes=new Intent(getActivity(),showshoes.class);
                startActivity(openshoes);
            }
        });

        bandhkam=view.findViewById(R.id.bandhkam);
        bandhkam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openshoes=new Intent(getActivity(),showelectronics.class);
                startActivity(openshoes);
            }
        });



        return view;
    }

}