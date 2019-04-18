package com.example.android.datatest.Operations;


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

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
  /*  public  ArrayList<ProductModel> getItems()
        {
        return items;
        }

    //ArrayList<ProductModel> items=new ArrayList<>();

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

        float sum[]={0f,0f,0f};
        for(ProductModel product:shopList)
        {
            sum[0]+=product.getPriceICA();
            sum[1]+=product.getPriceWillys();
            sum[2]+=product.getPriceCoop();
        }

        return sum;
        }


        public  ArrayList<ProductModel> retrieveDatabase(final TextView tv)
            {
            final ArrayList<String> categories=new ArrayList<>();
            final ArrayList<String> subcategories =new ArrayList<>();
            final ArrayList<String> idents=new ArrayList<>();
            final ArrayList<ProductModel> items =new ArrayList<>();

            reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
                {
                //String category = dataSnapshot.getKey();
                //if(category)
                // tv.setText("Root");
                // else
                //tv.setText(category);

                for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
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

                            idents.add(id);
                        }
                    }
                }

              for (String cat:categories) {
                    for (String subcat:subcategories) {
                        for (String ident : idents)

                            {

                                DataSnapshot ds4=dataSnapshot.child(cat).child(subcat).child(ident);
                                    ProductModel product = ds4.getValue(ProductModel.class);
                                    items.add(product);
                                    tv.setText(items.get(0).getName()+" "+items.get(0).getCategory());


                            }
                        }


                    }

                }














            @Override
            public void onCancelled(DatabaseError databaseError) {
            //Toast.makeText("Error with database",Toast.LENGTH_SHORT).show();
            }
            });
return items;
            }


    }