package com.example.android.women_emp1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Choose extends AppCompatActivity {
    Button register, login;
    RadioButton womenbtn, Empbtn;
    private RadioGroup radiogroupc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainchoose);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        womenbtn= findViewById(R.id.womenbtn);
        Empbtn= findViewById(R.id.Empbtn);
        radiogroupc= findViewById(R.id.radiogroupc);
        radiogroupc.clearCheck();

        radiogroupc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = (RadioButton)radiogroupc.findViewById(checkedId);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radiogroupc.getCheckedRadioButtonId();
                if (selectedId== -1){
                    Toast.makeText(Choose.this, "No answer has been selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    RadioButton radioButton= (RadioButton)radiogroupc.findViewById(selectedId);
                    if (selectedId == womenbtn.getId() ){
                        startActivity(new Intent(Choose.this,
                                com.example.android.women_emp1.MainActivityr.class));
                    }
                    else{
                        startActivity(new Intent(Choose.this, com.example.android.women_emp1.EmpReg.class));
                    }

                }



            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(Choose.this,
                        com.example.android.women_emp1.Login.class  )));

            }
        });
    }
}
















