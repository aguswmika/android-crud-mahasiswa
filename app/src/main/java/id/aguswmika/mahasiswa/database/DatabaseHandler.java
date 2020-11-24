package id.aguswmika.mahasiswa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.aguswmika.mahasiswa.model.Mahasiswa;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "mahasiswa";

    private static final String TABLE_MAHASISWA = "mahasiswa";

    private static final String KEY_NIM = "nim";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_PROGRAM_STUDI = "program_studi";
    private static final String KEY_ALAMAT = "alamat";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MAHASISWA_TABLE = "CREATE TABLE " + TABLE_MAHASISWA + "("
                + KEY_NIM + " CHAR(10) PRIMARY KEY,"
                + KEY_NAMA + " VARCHAR(255),"
                + KEY_PROGRAM_STUDI + " VARCHAR(255),"
                + KEY_ALAMAT + " TEXT" + ")";
        db.execSQL(CREATE_MAHASISWA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);

        onCreate(db);
    }

    public void save(Mahasiswa mahasiswa){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NIM, mahasiswa.getNim());
        values.put(KEY_NAMA, mahasiswa.getNama());
        values.put(KEY_PROGRAM_STUDI, mahasiswa.getProgramStudi());
        values.put(KEY_ALAMAT, mahasiswa.getAlamat());

        db.insert(TABLE_MAHASISWA, null, values);
        db.close();
    }

    public Mahasiswa findOne(String nim){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_MAHASISWA, new String[]{KEY_NIM,KEY_NAMA, KEY_PROGRAM_STUDI, KEY_ALAMAT},
                KEY_NIM +"=?", new String[]{nim}, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }


        assert cursor != null;
        return new Mahasiswa(
                cursor.getString(cursor.getColumnIndex(KEY_NIM)) //nim
                ,cursor.getString(cursor.getColumnIndex(KEY_NAMA)) //nama
                ,cursor.getString(cursor.getColumnIndex(KEY_PROGRAM_STUDI)) //program_studi,
                ,cursor.getString(cursor.getColumnIndex(KEY_ALAMAT)) // alamat
        );
    }

    public List<Mahasiswa> findAll(){
        List<Mahasiswa> listMahasiswa = new ArrayList<>();
        String query="SELECT * FROM "+ TABLE_MAHASISWA;

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(cursor.getString(cursor.getColumnIndex(KEY_NIM)));
                mahasiswa.setNama(cursor.getString(cursor.getColumnIndex(KEY_NAMA)));
                mahasiswa.setProgramStudi(cursor.getString(cursor.getColumnIndex(KEY_PROGRAM_STUDI)));
                mahasiswa.setAlamat(cursor.getString(cursor.getColumnIndex(KEY_ALAMAT)));
                listMahasiswa.add(mahasiswa);
            }while(cursor.moveToNext());
        }

        return listMahasiswa;
    }

    public void update(Mahasiswa mahasiswa){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAMA, mahasiswa.getNama());
        values.put(KEY_PROGRAM_STUDI, mahasiswa.getProgramStudi());
        values.put(KEY_ALAMAT, mahasiswa.getAlamat());

        db.update(TABLE_MAHASISWA, values, KEY_NIM +"=?", new String[]{mahasiswa.getNim()});
        db.close();
    }

    public void delete(Mahasiswa mahasiswa){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_MAHASISWA, KEY_NIM +"=?", new String[]{
            mahasiswa.getNim()
        });
        db.close();
    }
}