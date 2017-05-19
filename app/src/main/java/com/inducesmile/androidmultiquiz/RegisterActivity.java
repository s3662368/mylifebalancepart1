package com.inducesmile.androidmultiquiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.inducesmile.androidmultiquiz.database.DBHandler;
import com.inducesmile.androidmultiquiz.database.DatabaseQuery;
import com.inducesmile.androidmultiquiz.entities.Client;
import com.inducesmile.androidmultiquiz.helper.MySharedPreference;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private MySharedPreference sharedPreference;
    private DBHandler dbh;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#e1c8d6'>My Life Balance | Register</font>"));

        dbh = new DBHandler(RegisterActivity.this);
        sharedPreference = new MySharedPreference(RegisterActivity.this);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);

        Button register = (Button) findViewById(R.id.register_button);
        assert register != null;
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client = new Client(name.getText().toString(), email.getText().toString());
                dbh.addClient(client);
                sharedPreference.setSessionState(true);
                Intent quizMenuIntent = new Intent(RegisterActivity.this, QuizMenuActivity.class);
                startActivity(quizMenuIntent);
            }
        });

        Button asGuest = (Button)findViewById(R.id.guest_button);
        assert asGuest != null;
        asGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizMenuIntent = new Intent(RegisterActivity.this, QuizMenuActivity.class);
                startActivity(quizMenuIntent);
            }
        });
    }
}
