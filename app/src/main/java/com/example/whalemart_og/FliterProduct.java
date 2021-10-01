//package com.example.whalemart_og;
//
//import android.widget.Filter;
//
//import java.util.ArrayList;
//
//public class FliterProduct extends Filter {
//    private AdapterProductSeller adapter;
//    private ArrayList<ModelProduct> filterList;
//
//    public FliterProduct(AdapterProductSeller adapter, ArrayList<ModelProduct> productList) {
//        this.adapter = adapter;
//        this.filterList = productList;
//    }
//
//    @Override
//    protected FilterResults performFiltering(CharSequence constraint) {
//        FilterResults results=new FilterResults();
//        //////////////////////////validate data for search query
//        if(constraint !=null&& constraint.length()>0)
//        {
//////////////////////////change to uppercase
//            constraint=constraint.toString().toUpperCase();
//            ////////////////
//            ArrayList<ModelProduct> filterModels=new ArrayList<>();
//      for (int i=0;i<filterList.size();i++)
//      {
//          if (filterList.get(i).getTitle().toUpperCase().contains(constraint))
//          {
//              filterModels.add(filterList.get(i));
//          }
//      }
//            results.count = filterModels.size();
//            results.values = filterModels;
//        }
//        else {
//
//            //////////////////search filter empty
//
//            results.count = filterList.size();
//            results.values = filterList;
//        }
//        return results;
//    }
//
//    @Override
//    protected void publishResults(CharSequence constraint, FilterResults results) {
//adapter.productList=(ArrayList<ModelProduct>) results.values;
//////////////////refresh adapter
//        adapter.notifyDataSetChanged();
//    }
//}
