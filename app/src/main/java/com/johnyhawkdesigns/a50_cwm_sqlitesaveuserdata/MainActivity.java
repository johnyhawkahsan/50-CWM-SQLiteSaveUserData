package com.johnyhawkdesigns.a50_cwm_sqlitesaveuserdata;

import android.app.AlertDialog;
import android.database.Cursor;
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


        //This is something new for me. All those methods, there is onClick method
        addData();
        viewData();
        updateData();
        deleteData();
    }




    //Method for Add Data button to add Data to our Database
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





    //Method to View data
    public void viewData() {
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = peopleDB.viewData();

                if (data.getCount() == 0){
                    displayMessage("Error", "No Data Found, data.getCount() == 0");
                }

                StringBuffer stringBuffer = new StringBuffer();
                //Iterate through our data
                while (data.moveToNext()){
                    stringBuffer.append("ID: " + data.getString(0) + "\n");
                    stringBuffer.append("Name: " + data.getString(1) + "\n");
                    stringBuffer.append("Email: " + data.getString(2) + "\n");
                    stringBuffer.append("Favourite TV Show: " + data.getString(3) + "\n");

                    //Display message
                    displayMessage("All Stored Data: ", stringBuffer.toString());
                }

            }
        });

    }



    //Method to update data
    public void updateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                if (temp > 0){

                    String id = etID.getText().toString();
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String tvShow = etTVShow.getText().toString();

                    boolean update = peopleDB.updateData(id, name, email, tvShow);
                    if (update){
                        Toast.makeText(MainActivity.this, "Data updated successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Something went wrong while updating", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "You must enter an ID to Update", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private void deleteData() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                String id = etID.getText().toString();
                if (temp > 0){
                    Integer deletedRow = peopleDB.deleteData(id);
                    if (deletedRow > 0){
                        Toast.makeText(MainActivity.this, deletedRow + " rows deleted successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, deletedRow + " ,something went wrong", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "You must enter an ID to Delete", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    //Alert Dialog Builder Method
    public void displayMessage(String title, String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.show();

    }










}
