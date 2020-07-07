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
import android.widget.TextView;

public class SignupPage extends AppCompatActivity {
    final String TAG = "bbt app";
    private TextView toggleTextView;
    private TextView toggleTextView2;
    private EditText passWord;
    private Button loginButton;
    private EditText passWordConfirm;
    private EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        final EditText userName = findViewById(R.id.username_Edit);
        passWord = findViewById(R.id.password_edit);
        passWordConfirm = findViewById(R.id.password_edit2);
        loginButton = findViewById(R.id.signIn_Button);
        toggleTextView = findViewById(R.id.toggle2);
        toggleTextView2 = findViewById(R.id.toggle_re_enter);
        /*hiding the password toggle for both passwords*/
        toggleTextView.setVisibility(View.GONE);
        toggleTextView2.setVisibility(View.GONE);

        /*function for showing/hiding toggle for password 1*/
        passwordToggle(passWord,toggleTextView);

        /*function for showing/hiding toggle for password 2*/
        passwordToggle(passWordConfirm,toggleTextView2);

        /*function for switching between hide and show for toggle and showing password*/
        toggleChange(passWord,toggleTextView);

        /*function for switching between hide and show for toggle and showing password*/
        toggleChange(passWordConfirm,toggleTextView2);

        /*function for switching to sign up page*/
        signUp();

    }

    /*function for switching to sign up page*/
    public void signUp(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent welcome =  new Intent(SignupPage.this,MainMenu.class);
                Log.v(TAG,"Login in");
                startActivity(welcome);
            }
        });


    }

    /*function for switching between hide and show for toggle and showing password*/
    /*check = password and toggle = password toggle*/

    public void toggleChange(final EditText check,final TextView toggle){
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle.getText()== getString(R.string.toggleOn)){
                    toggle.setText(getString(R.string.toggleOff));
                    check.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    check.setSelection( check.length());
                    Log.v(TAG,"password shown");
                }
                else{
                    toggle.setText(getString(R.string.toggleOn));
                    check.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    check.setSelection( check.length());
                    check.setSelection( check.length());
                    Log.v(TAG,"password hidden");
                }
            }
        });

    }

    /*function for showing/hiding toggle for password */
    /*checkPassword = password and toggleChange = password toggle*/
    public void passwordToggle(final EditText checkPassword,final TextView toggleChange){
        checkPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkPassword.getText().length() > 0){
                    toggleChange.setVisibility(View.VISIBLE);
                }
                else{
                    toggleChange.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void passwordChecker(){

    }
}
