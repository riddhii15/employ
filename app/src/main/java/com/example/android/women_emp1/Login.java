package com.example.android.women_emp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    TextView textView3;
    EditText lemail, lpassword;
    Button login, button2;
    String email, password;
    private static String Login_URL = "https://womenemploy.000webhostapp.com/login.php";

    boolean isEmail(String text) {
        CharSequence email = text.toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        textView3 = findViewById(R.id.textView3);
        lemail = findViewById(R.id.lemail);
        lpassword = findViewById(R.id.lpassword);
        login = findViewById(R.id.login);
        button2 = findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = lemail.getText().toString().trim();
                String password = lpassword.getText().toString().trim();

                if (email.isEmpty()){
                    lemail.setError("Please enter the email address");
                }
                else if (isEmail(email)== false){
                    lemail.setError("Please enter the valid email address");
                }
                else if (password.isEmpty()){
                    lpassword.setError("Enter Your Password");
                }
                else {

                    loginfn(email,password);

//                    Intent intent = new Intent(Login.this, com.example.android.women_emp1.ViewProfile.class);
//                    startActivity(intent);
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity((new Intent(Login.this ,
                        com.example.android.women_emp1.Choose.class )));
            }
        });
    }

    void loginfn(final String email, final  String password ) {

        //pbLoading.setVisibility(View.VISIBLE);
       // register.setVisibility(View.GONE);
        //ar.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Login_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(getApplicationContext(),
                                    response, Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject = new JSONObject(response);

                            String msg = jsonObject.getString("success");


                            if (msg.equals("3")) {
                                Toast.makeText(Login.this, "Email-ID Already Registered", Toast.LENGTH_SHORT).show();
                                // pbLoading.setVisibility(View.GONE);
                                //register.setVisibility(View.VISIBLE);
                                //  ar.setVisibility(View.VISIBLE);
                            } else if (msg.equals("4")) {
                                Toast.makeText(Login.this, "Mobile Number Already Registered", Toast.LENGTH_SHORT).show();
                               // pbLoading.setVisibility(View.GONE);
                                //register.setVisibility(View.VISIBLE);
                                //  ar.setVisibility(View.VISIBLE);
                            } else if (msg.equals("1")) {
                                Toast.makeText(Login.this, "Logged IN !", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, ViewProfile.class));
                                finish();
                            }
                        } catch (JSONException e) {


                            e.printStackTrace();
                            Toast.makeText(Login.this, "Error in Registration !" + e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            //pbLoading.setVisibility(View.GONE);
                           // register.setVisibility(View.VISIBLE);
                            //ar.setVisibility(View.VISIBLE);

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // pbLoading.setVisibility(View.GONE);
                       // register.setVisibility(View.VISIBLE);
                        //ar.setVisibility(View.VISIBLE);

                        Toast.makeText(Login.this, "Error in Registration !" + error.toString(),
                                Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {


                Map<String, String> params = new HashMap<>();

                params.put("email", email);
                params.put("password", password);

                return params;


            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    }




