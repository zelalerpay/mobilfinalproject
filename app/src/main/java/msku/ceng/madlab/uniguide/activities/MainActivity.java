package msku.ceng.madlab.uniguide.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import msku.ceng.madlab.uniguide.R;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etEmail;
    private Button btnLogin, btnSignUp;
    private TextView tvNewAccount;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "UserPref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvNewAccount = findViewById(R.id.tvNewAccount);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Daha önce giriş yapılmışsa doğrudan CategoryActivity'ye yönlendir
        if (sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)) {
            navigateToCategoryActivity();
        }

        // Giriş işlemi
        btnLogin.setOnClickListener(view -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                showToast("Please enter username and password");
            } else if (isValidLogin(username, password)) {
                setLoggedIn(true);
                navigateToCategoryActivity();
            } else {
                showToast("Invalid username or password");
            }
        });

        // Yeni hesap oluşturma görünür hale gelir
        tvNewAccount.setOnClickListener(v -> {
            etEmail.setVisibility(View.VISIBLE);
            btnSignUp.setVisibility(View.VISIBLE);
            tvNewAccount.setVisibility(View.GONE);
        });

        // Kayıt işlemi
        btnSignUp.setOnClickListener(view -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email)) {
                showToast("Please fill in all fields");
            } else if (!isValidEmail(email)) {
                showToast("Invalid email format");
            } else {
                registerUser(username, password, email);
                showToast("Registration successful! Please log in.");
                resetLoginForm();
            }
        });
    }

    private void navigateToCategoryActivity() {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isValidLogin(String username, String password) {
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
        return savedUsername.equals(username) && savedPassword.equals(password);
    }

    private void registerUser(String username, String password, String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply();
    }

    private void setLoggedIn(boolean isLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void resetLoginForm() {
        etEmail.setVisibility(View.GONE);
        btnSignUp.setVisibility(View.GONE);
        tvNewAccount.setVisibility(View.VISIBLE);
        etUsername.setText("");
        etPassword.setText("");
        etEmail.setText("");
    }
}
