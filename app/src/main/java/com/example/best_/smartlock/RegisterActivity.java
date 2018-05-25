package com.example.best_.smartlock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity = RegisterActivity.this;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;

        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, "Enter Valid Email")) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Enter Valid Email")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, "Enter Valid Password")) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, "Password Does Not Match")) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim())) {

            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            Intent Login = new Intent(RegisterActivity.this, LoginActivity.class);
            // Toast to show success message that record saved successfully
            Toast.makeText(this,"Success", Toast.LENGTH_LONG).show();
            emptyInputEditText();
            startActivity(Login);


        } else {
            // Toast to show error message that record already exists
            Toast.makeText(this, "Email is already", Toast.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
