package com.example.firstroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countTextView = findViewById(R.id.countTextView);
        countTextView.setText(String.valueOf(count));
        createSpinner();
        createMap();



    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("Сиамская кошка");
        spinnerArrayList.add("Persin");
        spinnerArrayList.add("Невская маскарадная кошка");

        spinnerAdapter = new ArrayAdapter( this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        catsMap = new HashMap();
        catsMap.put("Сиамская кошка", 500.0);
        catsMap.put("Persin", 600.0);
        catsMap.put("Невская маскарадная кошка", 1200.0);
    }

    public void minus(View view) {
//        TextView countTextView = findViewById(R.id.countTextView);
//        TextView countTextView = findViewById(R.id.countTextView);
//        count = Integer.parseInt(countTextView.getText().toString());
        if (count > 0) {
            count -= 1;
            countTextView.setText(String.valueOf(count));
            TextView priceTextView = findViewById(R.id.priceTextView);
            priceTextView.setText("" + count*price + "$");

        }

    }

    public void plus(View view) {
//        TextView countTextView = findViewById(R.id.countTextView);
        count += 1;
        countTextView.setText(String.valueOf(count));
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + count*price + "$");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        catsName = spinner.getSelectedItem().toString();
        price = (double)catsMap.get(catsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + count*price + "$");

        ImageView catImageView = findViewById(R.id.priceImage);

        switch (catsName) {
            case "Сиамская кошка":
                catImageView.setImageResource(R.drawable.siam);
                break;
            case "Невская маскарадная кошка":
                catImageView.setImageResource(R.drawable.nevsk_mascar);
                break;
            default:
                catImageView.setImageResource(R.drawable.cat);
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
}
