package com.example.android.women_emp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {
    TextView profname, pnameT, profemail, pemailT, pcontact, pcontactT, profskill, pskillT, profbus, pbusT, bustype, pbustypeT;
    Button logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        profname=findViewById(R.id.profname);
        pnameT=findViewById(R.id.pnameT);
        profemail=findViewById(R.id.profemail);
        pemailT=findViewById(R.id.pemailT);
        pcontact=findViewById(R.id.pcontact);
        pcontactT=findViewById(R.id.pcontactT);
        profskill=findViewById(R.id.profskill);
        pskillT=findViewById(R.id.pskillT);
        profbus =findViewById(R.id.profbus);
        pbusT=findViewById(R.id.pbusT);
        bustype =findViewById(R.id.bustype);
        pbustypeT =findViewById(R.id.pbustypeT);
        logoutbtn=findViewById(R.id.logoutbtn);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfile.this, com.example.android.women_emp1.Choose.class ));
            }
        });

    }
}
