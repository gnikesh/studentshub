package np.com.gnikesh.studentshub;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    EditText email, username, password, phone, address;
    public static final String[] facList={"BCT", "BEX", "BEL", "BME", "BCE", "BGE"};
    Spinner faculty;
    String fac="";
    Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=(EditText)findViewById(R.id.emaiFieldField);
        username=(EditText)findViewById(R.id.usernameinRegisterField);
        password=(EditText)findViewById(R.id.PasswordInRegisterField);
        phone=(EditText)findViewById(R.id.phoneField);
        address=(EditText)findViewById(R.id.addressField);
        faculty=(Spinner)findViewById(R.id.spinner);
        regBtn=(Button)findViewById(R.id.regBtn);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item, facList);
        faculty.setAdapter(adapter);

        MyOnItemSelectedListener ml=new MyOnItemSelectedListener();
        faculty.setOnItemSelectedListener(ml);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dusername = username.getText().toString().trim();
                String dpassword = password.getText().toString().trim();
                String demail = email.getText().toString().trim();
                String dphone = phone.getText().toString().trim();
                String daddress = address.getText().toString().trim();
                String dfaculty = fac;

                ParseUser user = new ParseUser();
                user.setEmail(demail);
                user.setPassword(dpassword);
                user.setUsername(dusername);
                user.put(ParseConstants.KEY_ADDRESS, daddress);
                user.put(ParseConstants.KEY_FACULTY, dfaculty);
                user.put(ParseConstants.KEY_PHONE, dphone);

                user.signUpInBackground(new SignUpCallback() {
                    ProgressDialog pd=ProgressDialog.show(RegisterActivity.this, "Signing Up", "Please wait...");
                    @Override
                    public void done(ParseException e) {
                            if (e==null){
                                Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                pd.dismiss();
                            }else {
                                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
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

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
                        fac=parent.getSelectedItem().toString();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }



}
