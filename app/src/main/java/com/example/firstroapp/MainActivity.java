package com.example.firstroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    int count = 0;
    TextView countTextView;
    HashMap catsMap;
    String catsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countTextView = findViewById(R.id.countTextView);
        countTextView.setText(String.valueOf(count));

        userNameEditText = findViewById(R.id.editText);

        createSpinner();
        createMap();



    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("Сиамская");
        spinnerArrayList.add("Персидская");
        spinnerArrayList.add("Невская маскарадная");

        spinnerAdapter = new ArrayAdapter( this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        catsMap = new HashMap();
        catsMap.put("Сиамская", 500.0);
        catsMap.put("Персидская", 600.0);
        catsMap.put("Невская маскарадная", 1200.0);
    }

    public void minus(View view) {
        if (count > 0) {
            count -= 1;
            countTextView.setText(String.valueOf(count));
            TextView priceTextView = findViewById(R.id.priceTextView);
            priceTextView.setText("" + count*price + "$");

        }

    }

    public void plus(View view) {
        count += 1;
        countTextView.setText(String.valueOf(count));
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + count * price + "$");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        catsName = spinner.getSelectedItem().toString();
        price = (double)catsMap.get(catsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + count*  price + "$");

        ImageView catImageView = findViewById(R.id.priceImage);

        switch (catsName) {
            case "Сиамская":
                catImageView.setImageResource(R.drawable.siam);
                break;
            case "Невская маскарадная":
                catImageView.setImageResource(R.drawable.nevsk_mascar);
                break;
            case "Персидская":
                catImageView.setImageResource(R.drawable.pers);
                break;
            default:
//                catImageView.setImageResource(R.drawable.pers);
                break;
        }

//        if (catsName.equals("Сиамская кошка")) {
//            catImageView.setImageResource(R.drawable.cat);
//        } else if (catsName.equals("Persin")) {
//
//        } else if (catsName.equals("Nevsk Mascarad")) {
//
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.catName = catsName;
        order.count = count;
        order.orderPrice = count * price;
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("catName", order.catName);
        orderIntent.putExtra("count", order.count);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        startActivity(orderIntent);



//        Log.d("printsUserName", order.userName + order.count);
    }
}
