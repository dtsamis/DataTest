package com.example.android.datatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.datatest.model.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddItem extends AppCompatActivity
    {
    ProductModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        }

    public void addToDatabase(View view)
        {
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference();

            String ID,category,subcategory,name;
            float quantity,priceICA,priceWillys,priceCoop;
            boolean isBulk;


            item =new ProductModel();

        EditText inputID,inputCategory,inputSubcategory,inputName,inputQuantity,inputPriceICA,inputPriceWillys,inputPriceCoop;
        //RadioButton yesBulk,noBulk;

        inputID =findViewById(R.id.editTextID);
        inputCategory=findViewById(R.id.editTextCategory);
        inputSubcategory=findViewById(R.id.editTextSubcategory);
        inputName=findViewById(R.id.editTextName);
        inputQuantity=findViewById(R.id.editTextQuantity);
        inputPriceICA=findViewById(R.id.editTextICA);
        inputPriceWillys=findViewById(R.id.editTextWillys);
        inputPriceCoop=findViewById(R.id.editTextCoop);

        ID=inputID.getText().toString();
        




        }

    public void setBulkTrue(View view)
        {
            item.setBulk(true);
        }

    public void setBulkFalse(View view)
        {
            item.setBulk(false);
        }
    }
