package com.johnyhawkdesigns.a50_cwm_sqlitesaveuserdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper peopleDB;

    Button btnAddData, btnViewData,btnUpdateData,btnDelete;
    EditText etName,etEmail,etTVShow,etID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleDB = new DatabaseHelper(this);

        etName = (EditText) findViewById(R.id.etNewName);
        etEmail = (EditText) findViewById(R.id.etNewEmail);
        etTVShow = (EditText) findViewById(R.id.etNewTvShow);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnViewData = (Button) findViewById(R.id.btnViewData);
        btnUpdateData = (Button) findViewById(R.id.btnUpdateData);
        etID = (EditText) findViewById(R.id.etID);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        addData();

    }


    public void addData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String tvShow = etTVShow.getText().toString();

                Boolean insertData = peopleDB.addData(name, email, tvShow);

                if (insertData) {
                    Toast.makeText(MainActivity.this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
