package com.example.android.women_emp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class EmpReg extends AppCompatActivity {
    ImageView Eimage;
    EditText rEname, rEemail, rEpass, rEcpass, rEnumber;
    Button Eregister, Esignin;
    ProgressBar EpbLoading;
    private static String Register_URL = "https://womenemploy.000webhostapp.com/register.php";

    boolean isEmail(String text) {
        CharSequence email = text.toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emp_reg);
        Eimage = findViewById(R.id.Eimage);
        rEname = findViewById(R.id.rEname);
        rEemail = findViewById(R.id.rEemail);
        rEpass = findViewById(R.id.rEpass);
        rEcpass = findViewById(R.id.rEcpass);
        rEnumber = findViewById(R.id.rEnumber);
        Eregister = findViewById(R.id.Eregister);
        Esignin = findViewById(R.id.Esignin);
        EpbLoading = findViewById(R.id.EpbLoading);

        Esignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmpReg.this,com.example.android.women_emp1.Login.class));
            }
        });


        Eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = rEname.getText().toString().trim();
                String email = rEemail.getText().toString().trim();
                String number = rEnumber.getText().toString().trim();
                String pass = rEpass.getText().toString().trim();
                String cpass = rEcpass.getText().toString().trim();

                if (name.isEmpty())
                    rEname.setError("Enter your name");
                else if (email.isEmpty())
                    rEemail.setError("Enter your Email address");
                else if (isEmail(email) == false)
                    rEemail.setError("Enter Valid Email Address");
                else if (isValidMobile(number) == false)
                    rEnumber.setError("Enter Valid Mobile Number");
                else if (pass.isEmpty())
                    rEpass.setError("Enter Your Password");
                else if (cpass.isEmpty())
                    rEcpass.setError("Re-enter Your Password");
                else if (!pass.equals(cpass))
                    rEcpass.setError("Passwords Don't Match");
                else {
                    Toast.makeText(getApplicationContext(), "Registration in progress!", Toast.LENGTH_SHORT).show();
                    registration(name, email, number, pass);
                }

            }

            private void registration(final String name,final String email,final String number,final String pass) {
                EpbLoading.setVisibility(View.VISIBLE);
                Eregister.setVisibility(View.GONE);
                Esignin.setVisibility(View.VISIBLE);
            }
        });


    }
}
