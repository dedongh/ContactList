package com.engineerskasa.contactlist.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.engineerskasa.contactlist.Database.AppDatabase;
import com.engineerskasa.contactlist.Database.ContactDAO;
import com.engineerskasa.contactlist.Model.Contact;
import com.engineerskasa.contactlist.R;

import java.util.Date;

public class CreatContactActivity extends AppCompatActivity {
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneNumberEditText;
    private Button mSaveButton;
    private ContactDAO mContactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_contact);

        mContactDAO = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getContactDAO();

        mFirstNameEditText = findViewById(R.id.firstNameEditText);
        mLastNameEditText = findViewById(R.id.lastNameEditText);
        mPhoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        mSaveButton = findViewById(R.id.saveButton);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstNameEditText.getText().toString();
                String lastName = mLastNameEditText.getText().toString();
                String phoneNumber = mPhoneNumberEditText.getText().toString();

                if (firstName.length() == 0 || lastName.length() == 0 || phoneNumber.length() == 0) {
                    Toast.makeText(CreatContactActivity.this, "Please make sure all details are correct", Toast.LENGTH_SHORT).show();
                    return;
                }
                Contact contact = new Contact();
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setPhoneNumber(phoneNumber);
                contact.setCreatedDate(new Date());

                // insert to database
                try {
                    mContactDAO.insert(contact);
                    setResult(RESULT_OK);
                    finish();
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(CreatContactActivity.this, "A contact with same phone number already exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
