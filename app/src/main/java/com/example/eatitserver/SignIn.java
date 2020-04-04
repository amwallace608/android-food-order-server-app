package com.example.eatitserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eatitserver.Model.User;
import com.example.eatitserver.Common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    MaterialEditText phoneEditText, passwordEditText;
    Button signInBtn;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Edit Text inputs for phone number and password
        phoneEditText = (MaterialEditText) findViewById(R.id.phoneEditText);
        passwordEditText = (MaterialEditText) findViewById(R.id.passwordEditText);

        //Button input for Sign In button
        signInBtn = (Button) findViewById(R.id.signInBtn);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("User");

        //Onclick listener for Sign In Button
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                signInUser(phoneEditText.getText().toString(),passwordEditText.getText().toString());
            }
        });

    }

    private void signInUser(String phone, String password){
        //could show progress dialog bar/message here
        final String localPhone = phone;
        final String localPassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(localPhone).exists())
                {
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);
                    //check if user is staff
                    if(Boolean.parseBoolean(user.getIsStaff()))
                    {
                        //check password to login
                        if(user.getPassword().equals(localPassword))
                        {
                            Intent signIn = new Intent(SignIn.this,Home.class);
                            Common.currentUser = user;
                            startActivity(signIn);
                            finish();
                        }
                        else {
                            Toast.makeText(SignIn.this,
                                    "Password Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignIn.this,
                                "Login available for Staff accounts only",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignIn.this,
                            "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
