package sg.edu.np.madteam6.assignment;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    final String TAG = "bbt app";
    private TextView toggleTextView;
    private EditText passWord;
    private Button loginButton;
    private TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        final EditText userName = findViewById(R.id.username_Edit);
        passWord = findViewById(R.id.password_edit);
        loginButton = findViewById(R.id.signIn_Button);
        signUp = findViewById(R.id.signup);
        toggleTextView = findViewById(R.id.toggle);
        /*hiding the password toggle*/
        toggleTextView.setVisibility(View.GONE);
        /*function for showing/hiding toggle*/
        passwordToggle();

        /*function for switching between hide and show for toggle and showing password*/
        toggleChange();

        /*function for switching to login page*/
        login();

        /*function for switching to signup page*/
        onClick(signUp);

    }

    /*function for switching to login page*/
    public void login(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            /*intent to go to login page*/
            public void onClick(View view) {
                Intent welcome =  new Intent(LoginPage.this,MainMenu.class);
                Log.v(TAG,"login in");
                startActivity(welcome);
            }
        });

    }
    /*function for switching to signup page*/
    public void onClick(View view){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            /*intent to go to sign up page*/
            public void onClick(View v) {
                Intent signUp =  new Intent(LoginPage.this,SignupPage.class);
                Log.v(TAG,"signing up");
                startActivity(signUp);
            }
        });

    }

    /*function for switching between hide and show for toggle and showing password*/
    public void toggleChange(){
        toggleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*show password*/
                if(toggleTextView.getText()== getString(R.string.toggleOn)){
                    toggleTextView.setText(getString(R.string.toggleOff));
                    passWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passWord.setSelection( passWord.length());
                    Log.v(TAG,"password shown");
                }
                /*hide password*/
                else{
                    toggleTextView.setText(getString(R.string.toggleOn));
                    passWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passWord.setSelection( passWord.length());
                    Log.v(TAG,"password hidden");

                }
            }
        });

    }
    /*function for showing/hiding toggle*/
    public void passwordToggle(){
        passWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            /*toggle will only show if there is more than one char*/
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passWord.getText().length() > 0){
                    toggleTextView.setVisibility(View.VISIBLE);
                    Log.v(TAG,"toggle shown");
                }
                else{
                    toggleTextView.setVisibility(View.GONE);
                    Log.v(TAG,"toggle hidden");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
