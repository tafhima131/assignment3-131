package com.example.validation;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone, editTextPassword, editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Assuming your XML is named activity_main.xml

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    showSignupSuccessDialog();
                }
            }
        });
    }

    private boolean validateInput() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();


        if (TextUtils.isEmpty(name) || !name.matches("^[a-zA-Z\\s-]+$")) {
            editTextName.setError("Please enter a valid name");
            return false;
        }


        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Invalid email address");
            return false;
        }


        if (TextUtils.isEmpty(phone) || !phone.matches("^(?:\\+?88|0)?1\\d{9}$")) {
            editTextPhone.setError("Invalid phone number");
            return false;
        }


        if (TextUtils.isEmpty(password) || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            editTextPassword.setError("Password must be at least 8 characters with letters and digits");
            return false;
        }


        if (TextUtils.isEmpty(confirmPassword) || !confirmPassword.equals(password)) {
            editTextConfirmPassword.setError("Passwords do not match");
            return false;
        }

        return true;
    }

    private void showSignupSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Sign Up Successful")
                .setMessage("You have successfully signed up!")
                .setPositiveButton("OK", null)
                .show();
    }
}