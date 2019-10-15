package com.engineerskasa.contactlist.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.engineerskasa.contactlist.Model.Contact;
import com.engineerskasa.contactlist.TypeConverters.DateTypeConverter;

@Database(entities = {Contact.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}
