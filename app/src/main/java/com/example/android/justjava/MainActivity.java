package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    int quantity=99;
    public void increment(View view) {
        if (quantity==100){
            Toast.makeText(this,"you can not have more than 100 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity==0){
            Toast.makeText(this,"you can not have less than 1 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        displayQuantity(quantity);
    }

    public void submitOrder(View view){
        CheckBox WippedCrC_Box = (CheckBox) findViewById(R.id.hasWippedCream);
        boolean hasWippedCream=WippedCrC_Box.isChecked();
        Log.v("MainActivity","Has wipped cream"+hasWippedCream);
        CheckBox choch = (CheckBox) findViewById(R.id.Choclate);
        boolean hasChoc=choch.isChecked();
        Log.v("MainActivity","Has wipped cream"+hasChoc);

        EditText text=(EditText)findViewById(R.id.edit_view);
        String value=text.getText().toString();

        String priceMessage=createOrderSummary(hasWippedCream,hasChoc,value);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for "+ value);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //displayMessage(priceMessage);
        //intent for google map
      /*  Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/
    }

    private int calculatePrice(boolean addWippedCream,boolean addChoclate) {
        int basePrice=5;
        if (addWippedCream==true){
            basePrice=basePrice+1;
        }
        if (addChoclate==true){
            basePrice=basePrice+2;
        }

        return quantity * basePrice;
    }

    private String createOrderSummary(boolean addWippedCream,boolean addChoclate,String value){

        int price =calculatePrice(addWippedCream, addChoclate);
        String Message ="Name: "+value+"\n"+"Add Wipped Cream? "+addWippedCream+"\n"+"Add Choclate? "+addChoclate+"\n"+"Quantity: "+quantity+"\n"+"Total: "+"Rs "+price+"\n"+"Thank You!" ;
        return Message;
    }

    protected void displayQuantity(int number){
        TextView quantityTextView = (TextView ) findViewById(R.id.text_view2);
        quantityTextView.setText(""+number);
    }

    /*private void displayMessage(String Message) {
        TextView orderSummaryTextView = (TextView) findViewById( R.id.order_summary_text_view);
        orderSummaryTextView.setText(Message);
    }*/
}
