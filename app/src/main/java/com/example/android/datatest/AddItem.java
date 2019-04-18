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
    ProductModel item=new ProductModel();

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        }

    public void addToDatabase(View view)
        {
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference();

            String ID,category,quantity,subcategory,name;
            float priceICA,priceWillys,priceCoop;
            //boolean isBulk;




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
        category=inputCategory.getText().toString();
        subcategory=inputSubcategory.getText().toString();
        name=inputName.getText().toString();
        quantity=inputQuantity.getText().toString();
        priceICA=Float.parseFloat(inputPriceICA.getText().toString());
        priceWillys=Float.parseFloat(inputPriceWillys.getText().toString());
        priceCoop=Float.parseFloat(inputPriceCoop.getText().toString());



        item.setID(ID);
        item.setCategory(category);
        item.setSubCategory(subcategory);
        item.setName(name);
        item.setQuantity(quantity);
        item.setPriceICA(priceICA);
        item.setPriceWillys(priceWillys);
        item.setPriceCoop(priceCoop);

        reference.child(category).child(subcategory).child(ID).setValue(item);

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
