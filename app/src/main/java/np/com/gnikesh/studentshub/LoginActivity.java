package np.com.gnikesh.studentshub;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    EditText usernameField;
    EditText passwordField;
    TextView createAcc;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameField=(EditText)findViewById(R.id.usernameField);
        passwordField=(EditText)findViewById(R.id.PasswordField);
        loginbtn=(Button)findViewById(R.id.loginBtn);
        createAcc=(TextView)findViewById(R.id.createAccount);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);

            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernameField.getText().toString().trim();
                String password=passwordField.getText().toString().trim();
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    ProgressDialog pd=ProgressDialog.show(LoginActivity.this, "Logging In", "Please wait...");
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (e==null) {
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            pd.dismiss();

                        }else {

                            AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                            AlertDialog alert=builder.create();
                            alert.setMessage("Please check your internet connection!");
                            alert.show();
                            alert.setCancelable(true);
                        }
                    }
                });
            }
        });
    }


}
