package com.example.android.datatest.Operations;


import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.datatest.model.ProductModel;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class DataOperations
    {

    DatabaseReference reference;

    public ArrayList<ProductModel> getProducts()
        {
        return products;
        }

    public ArrayList<ProductModel> products=new ArrayList<>();
    ArrayList<String> categories=new ArrayList<>();
    ArrayList<String> subcategories =new ArrayList<>();
    ArrayList<String> idents=new ArrayList<>();
    //ArrayList<ProductModel> items=new ArrayList<>();
  /*  public  ArrayList<ProductModel> getItems()
        {
        return items;
        }



    public ArrayList<String> getCategories()
        {
        return categories;

        }*/



    /**
     * Find different total price in different supermarkets
     * @param shopList the shopping list intended by the shopper
     * @return an array that contains the total shoplist prices for the different supermarkets
     *         0=ICA ,1=Willys , 2=Coop
     */
    public  float[] total(ArrayList<ProductModel> shopList)
        {

        float sum[]={0,0,0};
        for(ProductModel product:shopList)
        {
            sum[0]+=product.getPriceICA();
            sum[1]+=product.getPriceWillys();
            sum[2]+=product.getPriceCoop();
        }

        return sum;
        }

        public void init()
            {
                products=new ArrayList<>();
            }


        public  void retrieveDatabase()
            {
            reference = FirebaseDatabase.getInstance().getReference();
            //init();
            reference.addValueEventListener(new ValueEventListener()
                {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
                {


                for (DataSnapshot ds1 : dataSnapshot.getChildren())
                {
                    String category = ds1.getKey();
                    categories.add(category);

                }



                for (String cat : categories) {


                    for (DataSnapshot ds2 : dataSnapshot.child(cat).getChildren()) {
                        String subcategory = ds2.getKey();
                        subcategories.add(subcategory);

                    }
                }

                for (String cat : categories) {
                    for (String subcat : subcategories) {

                        for (DataSnapshot ds3 : dataSnapshot.child(cat).child(subcat).getChildren()) {
                            String id = ds3.getKey();
                            ProductModel product =ds3.getValue(ProductModel.class);

                            products.add(product);
                            //Log.i("Product Name:",products.get(products.size()-1).getName());

                        }
                    }
                }


                }














            @Override
            public void onCancelled(DatabaseError databaseError) {
            //Toast.makeText(this,"Error with database",Toast.LENGTH_SHORT).show();
            }
            });

            }


    }