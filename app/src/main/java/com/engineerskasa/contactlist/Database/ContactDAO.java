package com.engineerskasa.contactlist.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.engineerskasa.contactlist.Model.Contact;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    public void insert(Contact... contacts);

    @Update
    public void update(Contact... contacts);

    @Delete
    public void delete(Contact contact);

    @Query("select * from contact")
    public List<Contact> getContacts();

    @Query("select * from contact where phoneNumber = :number")
    public Contact getContactWithId(String number);
}
