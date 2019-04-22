package com.example.android.datatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.datatest.Operations.DataOperations;
import com.example.android.datatest.model.ProductModel;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataButtons extends AppCompatActivity
    {
        DataOperations dat;





    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_buttons);
        dat=new DataOperations();
        }

    public void addItem(View view)
        {
        startActivity(new Intent(getApplicationContext(),AddItem.class));
        }

    public void showData(View view)
        {

        TextView displayID =findViewById(R.id.textViewID);
        dat.retrieveDatabase();
        ArrayList<ProductModel> products=dat.getProducts();
        for(ProductModel product:products)
            displayID.setText(product.getName());

//        displayID.setText(dat.getItems().get(0).getCategory());
        //displayID.setText(dat.getItems().get(0).getName());
        //displayID.setText(dat.getCategories().get(0));
        }

    public void showTotals(View view)
        {
            dat.init();
            dat.retrieveDatabase();
            //ArrayList<ProductModel> products=dat.getProducts();
            //Log.i("DisplayProduct",products.get(0).getName());


            float totals[]=dat.total(dat.getProducts());


            TextView icaTotal,willysTotal,coopTotal;

            icaTotal=findViewById(R.id.textViewICAtotal);
            willysTotal=findViewById(R.id.textViewWILLYStotal);
            coopTotal=findViewById(R.id.textViewCOOPtotal);

            icaTotal.setText(String.valueOf(totals[0]));
       willysTotal.setText(String.valueOf(totals[1]));
       coopTotal.setText(String.valueOf(totals[2]));
        }
    }
