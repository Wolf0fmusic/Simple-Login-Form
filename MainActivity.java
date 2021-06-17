package com.example.registerform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText edtTxtFullName, edtTxtEmail, edtTxtPassword, edtTxtRePassword;
    private Button btnRegister, btnImage;
    private TextView txtFullName, txtEmail, txtPassword, txtRePassword;
    private Spinner countrySpinner;
    private RadioGroup radioGroupGender;
    private CheckBox cbAgreement;
    private ConstraintLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Image Change", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });

    }

    private void initRegister(){
        Log.d(TAG, "initRegister: Started");
        if (validateData())
        {
            if (cbAgreement.isChecked())
            {
                showSnackBar();
            }else{
                Toast.makeText(this, "You need to agree to the License Agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar(){
        Log.d(TAG, "showSnackBar: Started");

        txtFullName.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);
        txtPassword.setVisibility(View.GONE);
        txtRePassword.setVisibility(View.GONE);

        String name = edtTxtFullName.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String country = countrySpinner.getSelectedItem().toString();
        String gender = "";

        switch (radioGroupGender.getCheckedRadioButtonId())
        {
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbOther:
                gender = "Other";
                break;
            default:
                gender = "Unknown";
                break;
        }

        String snackText =
                "Name " + name + "\n" +
                "Email " + email + "\n" +
                "Gender " + gender + "\n" +
                "Country " + country + "\n";

        Snackbar.make(parent, name + " Registered!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismissed", view -> {
                    txtFullName.setText("");
                    txtEmail.setText("");
                    txtPassword.setText("");
                    txtRePassword.setText("");
                }).show();

    }

    private boolean validateData(){
        Log.d(TAG, "validateData: Started");

        if(edtTxtFullName.getText().toString().equals(""))
        {
            txtFullName.setVisibility(View.VISIBLE);
            return false;
        }
        if(edtTxtEmail.getText().toString().equals(""))
        {
            txtEmail.setVisibility(View.VISIBLE);
            return false;
        }
        if(edtTxtPassword.getText().toString().equals(""))
        {
            txtPassword.setVisibility(View.VISIBLE);
            return false;
        }

        if(!edtTxtPassword.getText().toString().equals(edtTxtRePassword.getText().toString())){
            Toast.makeText(this, "Password Doesn't Match.", Toast.LENGTH_SHORT).show();
            txtRePassword.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    private void initViews(){
        Log.d(TAG,"Started");

        edtTxtFullName = findViewById(R.id.edtTxtFullName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtRePassword = findViewById(R.id.edtTxtRePassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnImage =(Button) findViewById(R.id.btnImage);

        txtFullName = findViewById(R.id.txtFullName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtRePassword = findViewById(R.id.txtRePassword);

        countrySpinner = findViewById(R.id.countrySpinner);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        cbAgreement = findViewById(R.id.cbAgreement);
        parent = findViewById(R.id.parent);


    }
}