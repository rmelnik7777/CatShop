package com.example.firstroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"rmelnik7777@gmail.com"};
    String subject = "Заказ котана";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        this.setTitle(R.string.your_order);
        Intent orderIntent = getIntent();
        String userName = orderIntent.getStringExtra("userName");
        String catName = orderIntent.getStringExtra("catName");
        int count = orderIntent.getIntExtra("count", 0);
        double orderPrice = orderIntent.getDoubleExtra("orderPrice", 0);
        TextView orderTextView = findViewById(R.id.orderTextView);
        emailText = "Имя клиента: " + userName + "\n" + "Порода кота: " + catName + "\n" + "Количество: " + count + "\n" + "Цена: " + orderPrice/count + "\n" + "Сумма: " + orderPrice;
        orderTextView.setText(emailText);
        ImageView catImageView = findViewById(R.id.orderCat);
        switch (catName) {
            case "Сиамская":
                catImageView.setImageResource(R.drawable.siam);
                break;
            case "Невская маскарадная":
                catImageView.setImageResource(R.drawable.nevsk_mascar);
                break;
            case "Персидская":
                catImageView.setImageResource(R.drawable.pers);
                break;
        }
    }

    public void submitOrder(View view) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
