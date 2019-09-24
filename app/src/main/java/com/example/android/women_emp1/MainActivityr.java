package com.example.android.women_emp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.time.chrono.Chronology;
import java.util.HashMap;
import java.util.Map;

public class MainActivityr extends AppCompatActivity {

    TextView textView3;
    ImageView wimage;
    EditText rname, remail, rpass, rcpass, rnumber,rbusname,rskill;
    Button register, signin;
    RadioGroup radiogroup;
    RadioButton  homebtn,smallbtn,mlbtn,selected;
    ProgressBar pbLoading;
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
        setContentView(R.layout.register);
        textView3 = findViewById(R.id.textView3);
        radiogroup =(RadioGroup) findViewById(R.id.busn);
        wimage = findViewById(R.id.wimage);
        rname = findViewById(R.id.rname);
        remail = findViewById(R.id.remail);
        rpass = findViewById(R.id.rpass);
        rcpass = findViewById(R.id.rcpass);
        rnumber = findViewById(R.id.rnumber);
        rbusname = findViewById(R.id.rbusname);
        rskill = findViewById(R.id.rskill);
        register = findViewById(R.id.register);
        signin = findViewById(R.id.signin);
        pbLoading = findViewById(R.id.pbLoading);
        homebtn= findViewById(R.id.homebtn);
        smallbtn= findViewById(R.id.smallbtn);
        mlbtn= findViewById(R.id.mlbtn);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityr.this,com.example.android.women_emp1.Login.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            class StringRequest {
            }

            @Override
            public void onClick(View view) {
                String name = rname.getText().toString().trim();
                String email = remail.getText().toString().trim();
                String number = rnumber.getText().toString().trim();
                String pass = rpass.getText().toString().trim();
                String cpass = rcpass.getText().toString().trim();
                String skill = rskill.getText().toString().trim();
                String busname = rbusname.getText().toString().trim();
                int selectedid = radiogroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedid);
                String selectedtob= (String) selected.getText();

                if (name.isEmpty())
                    rname.setError("Enter your name");
                else if (email.isEmpty())
                    remail.setError("Enter your Email address");
                else if (isEmail(email) == false)
                    remail.setError("Enter Valid Email Address");
                else if (isValidMobile(number) == false)
                    rnumber.setError("Enter Valid Mobile Number");
                else if (skill.isEmpty())
                    rpass.setError("Enter Your Password");
                else if (busname.isEmpty())
                    rpass.setError("Enter Your Password");
                else if (pass.isEmpty())
                    rpass.setError("Enter Your Password");
                else if (cpass.isEmpty())
                    rcpass.setError("Re-enter Your Password");
                else if (selectedtob.isEmpty())
                    Toast.makeText(getApplicationContext(), "Select tob", Toast.LENGTH_SHORT).show();
                else if (!pass.equals(cpass)) {
                    rcpass.setError("Passwords Don't Match");
                    Toast.makeText(getApplicationContext(), "" + selectedtob, Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Registration in progress!", Toast.LENGTH_SHORT).show();
                    registration(name, email, pass, number,skill,busname,selectedtob);
                }

            }

        });

    }

    private void registration(final String u_name, final String u_email, final String u_password,final String u_mob,final String u_skill,final String u_busname,final String u_tob)
    {
        pbLoading.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);
        //ar.setVisibility(View.GONE);

        StringRequest stringRequest= new StringRequest(Request.Method.POST, Register_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(getApplicationContext(),
                                    response, Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject = new JSONObject(response);

                            String msg = jsonObject.getString("success");


                            if(msg.equals("3"))
                            {Toast.makeText(MainActivityr.this, "Email-ID Already Registered", Toast.LENGTH_SHORT).show();
                               // pbLoading.setVisibility(View.GONE);
                                register.setVisibility(View.VISIBLE);
                              //  ar.setVisibility(View.VISIBLE);
                            }
                            else if(msg.equals("4"))
                            {Toast.makeText(MainActivityr.this, "Mobile Number Already Registered", Toast.LENGTH_SHORT).show();
                              pbLoading.setVisibility(View.GONE);
                                register.setVisibility(View.VISIBLE);
                              //  ar.setVisibility(View.VISIBLE);
                            }
                            else if (msg.equals("1")) {
                                Toast.makeText(MainActivityr.this, "Registration Done !", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivityr.this, Login.class));
                                finish();
                            }
                        }
                        catch(JSONException e)

                        {


                            e.printStackTrace();
                            Toast.makeText(MainActivityr.this, "Error in Registration !"+e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            pbLoading.setVisibility(View.GONE);
                            register.setVisibility(View.VISIBLE);
                            //ar.setVisibility(View.VISIBLE);

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pbLoading.setVisibility(View.GONE);
                        register.setVisibility(View.VISIBLE);
                       //ar.setVisibility(View.VISIBLE);

                        Toast.makeText(MainActivityr.this, "Error in Registration !"+error.toString(),
                                Toast.LENGTH_SHORT).show();

                    }
                }
        )
        {
            @Override
            protected Map<String,String> getParams() {


                Map<String,String> params = new HashMap<>();
                params.put("name",u_name);
                params.put("email",u_email);
                params.put("password",u_password);
                params.put("mobno",u_mob);
                params.put("skill",u_skill);
                params.put("busname",u_busname);
                params.put("tob",u_tob);
                return params;


            }
        };





        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);




    }

}

