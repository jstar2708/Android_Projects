package com.example.coffeecafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 1;
    int price = 0;

    public void submitOrder(View view){
        EditText name = findViewById(R.id.name_edit_text);
        CheckBox whippedCream = findViewById(R.id.whipped_check_box);
        CheckBox chocolate = findViewById(R.id.chocolate_check_box);
        boolean hasWhipped = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        calPrice(hasChocolate,hasWhipped);
        String message = orderSummary(name,hasWhipped,hasChocolate);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java app order for "+ name.getText());
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    private String orderSummary(EditText name, boolean hasWhipped, boolean hasChocolate){
        String message = "Name : " +  name.getText() + "\n" + "Whipped Cream : " + hasWhipped + "\n" + "Chocolate : " + hasChocolate + "\n" +
                "Quantity : " + quantity + "\nTotal : $" + price + "\n" + "Thank you";
        return message;
    }

    public void calPrice(boolean hasChocolate, boolean hasWhipped){
        if(hasChocolate && hasWhipped){
            price = quantity * 8;
        }
        else if(hasChocolate){
            price = quantity * 7;
        }
        else if(hasWhipped){
            price = quantity * 6;
        }
        else{
            price = quantity * 5;
        }
    }

    public void increment(View view){
        if(quantity == 100){
            Toast.makeText(this, "You cannot buy more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display();
    }
    public void calPrice(){
    }

    public void decrement(View view){
        if(quantity == 1){
            Toast.makeText(this, "You cannot buy less than 1 coffee" ,Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display();
    }

    public void display(){
        TextView displayQuantity = (TextView) findViewById(R.id.quantity_text_view);
        displayQuantity.setText( ""+ quantity);
    }

}