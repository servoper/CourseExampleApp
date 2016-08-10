package bg.codingschool.myapplication1.data.sqlite.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import bg.codingschool.myapplication1.data.model.Dog;
import bg.codingschool.myapplication1.data.sqlite.DatabaseHelper;

/**
 * Created by Nikolay Vasilev on 8/10/2016.
 */
public class DogContentProvider {
    private static final String TABLE_NAME = Dog.class.getName();

    public static final String COLUMN_NAME = Dog.PARAM_NAME;
    public static final String COLUMN_AGE = Dog.PARAM_AGE;
    public static final String COLUMN_IMAGE_URL = Dog.PARAM_IMAGE_URL;

    // Create Statement for Products Table
    public static final String CREATE_TABLE_PRODUCT = "CREATE TABLE "
            + TABLE_NAME + "  (" +
            DatabaseHelper.COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_AGE + " REAL, " +
            COLUMN_IMAGE_URL + " TEXT" +
            ");";

    private final DatabaseHelper mDatabaseHelper;

    public DogContentProvider(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase open() {
        return mDatabaseHelper.getWritableDatabase();
    }

    private void close(SQLiteDatabase database) {
        database.close();
    }

    public void writeDog(Dog dog) {
        final ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, dog.name);
        values.put(COLUMN_AGE, dog.age);
        values.put(COLUMN_IMAGE_URL, dog.imageUrl);

        SQLiteDatabase database = open();

        // This call performs the update
        // The return value is the rowId or primary key value for the new row!
        // If this method returns -1 then the insert has failed.
        long id = database.insert(
                TABLE_NAME, // The table name in which the data will be inserted
                null,       // This a special parameter, can in most cases be null.
                values      // The ContentValues instance which contains the data
        );

        close(database);
    }

    public void writeDogs(ArrayList<Dog> dogs) {

        SQLiteDatabase database = open();

        for (Dog dog : dogs) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, dog.name);
            values.put(COLUMN_AGE, dog.age);
            values.put(COLUMN_IMAGE_URL, dog.imageUrl);

            // This call performs the update
            // The return value is the rowId or primary key value for the new row!
            // If this method returns -1 then the insert has failed.
            long id = database.insert(
                    TABLE_NAME, // The table name in which the data will be inserted
                    null,       // This a special parameter, can in most cases be null.
                    values      // The ContentValues instance which contains the data
            );
        }

        close(database);
    }

    public void deleteAll() {
        SQLiteDatabase db = open();

        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public void delete(String name) {
        SQLiteDatabase db = open();

        String whereClause = COLUMN_NAME + " = ?";
        String[] whereArgs = new String[]{String.valueOf(name)};

        int numRowsDeleted = db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

    public ArrayList<Dog> getDogs(String nameToSearch, int minAge) {

        SQLiteDatabase database = open();

        final Cursor cursor = database.query(
                // Name of the table to read from
                TABLE_NAME,

                // String array of the columns which are supposed to be read
                new String[]{COLUMN_NAME, COLUMN_AGE, COLUMN_IMAGE_URL},

                // The selection argument which specifies which row is read.
                // ? symbols are parameters.
                COLUMN_NAME + " LIKE ? AND " + COLUMN_AGE + " > ?",

                // The actual parameters values for the selection as a String array.
                // ? above take the value from here
                new String[]{"%" + nameToSearch + "%", String.valueOf(minAge)},

                // GroupBy clause. Specify a column name to group similar values
                // in that column together.
                null,

                // Having clause. When using the GroupBy clause this allows you to
                // specify which groups to include.
                null,

                // OrderBy clause. Specify a column name here to order the results
                // according to that column. Optionally append ASC or DESC to specify
                // an ascending or descending order.
                null
        );


        ArrayList<Dog> dogs = redDogsFromCursor(cursor);

        database.close();

        return dogs;
    }

    public ArrayList<Dog> getDogs(String nameToSearch) {

        SQLiteDatabase database = open();

        final Cursor cursor = database.query(
                // Name of the table to read from
                TABLE_NAME,

                // String array of the columns which are supposed to be read
                new String[]{COLUMN_NAME, COLUMN_AGE, COLUMN_IMAGE_URL},

                // The selection argument which specifies which row is read.
                // ? symbols are parameters.
                COLUMN_NAME + " LIKE ?",

                // The actual parameters values for the selection as a String array.
                // ? above take the value from here
                new String[]{"%" + nameToSearch + "%"},

                // GroupBy clause. Specify a column name to group similar values
                // in that column together.
                null,

                // Having clause. When using the GroupBy clause this allows you to
                // specify which groups to include.
                null,

                // OrderBy clause. Specify a column name here to order the results
                // according to that column. Optionally append ASC or DESC to specify
                // an ascending or descending order.
                null
        );


        ArrayList<Dog> dogs = redDogsFromCursor(cursor);

        database.close();

        return dogs;
    }

    private ArrayList<Dog> redDogsFromCursor(Cursor cursor) {

        int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
        int ageIndex = cursor.getColumnIndex(COLUMN_AGE);
        int imgUrlIndex = cursor.getColumnIndex(COLUMN_IMAGE_URL);

        ArrayList<Dog> dogs = new ArrayList<>();

        // If moveToFirst() returns false then cursor is empty
        if (!cursor.moveToFirst()) {
            return dogs;
        }

        do {
            // Read the values of a row in the table using the indexes acquired above
            long id = cursor.getLong(idIndex);
            String name = cursor.getString(nameIndex);
            int description = cursor.getInt(ageIndex);
            String value = cursor.getString(imgUrlIndex);

            dogs.add(new Dog(name, description, value));
        } while (cursor.moveToNext());

        return dogs;
    }
}
