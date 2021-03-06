package com.aleste.alertas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aleste.alertas.utils.ConnectionDetector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void probarConexion(View view) {

        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

        Boolean isConnected = cd.isConnectedToInternet(); // true or false

        if (isConnected) {
            // Internet Connection is Present
            // make HTTP requests
            showAlertDialog(MainActivity.this, "Internet Connection",
                    "You have internet connection", true);
        } else {
            // Internet connection is not present
            // Ask user to connect to Internet
            showAlertDialog(MainActivity.this, "No Internet Connection",
                    "You don't have internet connection.", false);
        }

        Log.i("MainActivity", "hay conexion;" + isConnected);

    }

    public void enviarAlerta(View view) {
        Log.i("MainActivity", "Enviar alerta click");

        SmsManager smsManager =     SmsManager.getDefault();
        smsManager.sendTextMessage("22210", null, "AUBASA", null, null);

    }

    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     * */
    public void showAlertDialog(Context context, String title, String message, Boolean status) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(message)
                .setTitle(title);

        builder.setIcon((status) ? R.drawable.success : R.drawable.fail);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();


        // Showing Alert Message
        dialog.show();
    }

}
