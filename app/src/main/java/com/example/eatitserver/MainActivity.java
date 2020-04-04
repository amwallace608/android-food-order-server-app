package com.example.eatitserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button signInBtn;
    TextView txtSlogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sign in button
        signInBtn = (Button) findViewById(R.id.signInBtn);
        //slogan
        txtSlogan = (TextView) findViewById(R.id.txtSlogan);
        //Start Signin Activity on button click
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view){
                Intent signIn = new Intent(MainActivity.this, SignIn.class);
                startActivity(signIn);
            }
        });


    }
}
