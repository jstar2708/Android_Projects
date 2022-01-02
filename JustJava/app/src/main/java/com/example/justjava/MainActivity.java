package com.example.justjava;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.justjava.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = findViewById(R.id.Whipped_check_view);
        CheckBox choclate = findViewById(R.id.chocolate_check_view);
        boolean hasChocolate = choclate.isChecked();
        boolean hasWhippedCream = whippedCream.isChecked();
        EditText edittext = findViewById(R.id.name_edit_view);
        String name = edittext.getText().toString();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.just_java) + name);
        startActivity(intent);
    }

    /**
     * This method is used to form the summary o order
     *
     * @param price is the price of the number of coffees
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String message = getString(R.string.name) + name + "\n" + getString(R.string.whipped) +" : "  + hasWhippedCream + "\n" + getString(R.string.chocolate) + " : "+ hasChocolate + "\n" + getString(R.string.quantity)  +" : "+ quantity + "\n" + getString(R.string.total) + " : ₹" +
                price + "\n"+getString(R.string.thank_you );
        return message;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price of the coffees ordered
     */
    private int calculatePrice(boolean hasWhipped, boolean hasChocolate) {
        if (hasChocolate && hasWhipped) {
            return quantity * 8;
        } else if (hasChocolate) {
            return quantity * 7;
        } else if (hasWhipped) {
            return quantity * 6;
        } else {
            return quantity * 5;
        }

    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(getApplicationContext(), "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(getApplicationContext(), "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}