package database.s;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context){
        super(context, "studentdatabase",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists student(id integer primary key autoincrement,name varhcar,address varchar,phone varchar )");


    }

    @Override 
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists student");
        onCreate(sqLiteDatabase);

    }

    public void insertdata(DataModel d) {
        SQLiteDatabase dv=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put("name",d.name);
        value.put("address",d.address);
        value.put("phone",d.phone);
        dv.insert("student",null,value);
    }

    public ArrayList<DataModel> readdata() {
        ArrayList<DataModel> data=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c= db.rawQuery("Select * from student", null);
        if(c.moveToFirst()){
            do{
                DataModel d= new DataModel();
                d.id=c.getInt(0);
                d.name=c.getString(1);
                d.address=c.getString(2);
                d.phone=c.getString(3);
                data.add(d);
            }while (c.moveToNext());
        }
        return data;
    }

    public void deletedata(int id) {
        SQLiteDatabase remove=this.getWritableDatabase();
        remove.execSQL("delete from student where id="+id);
    }

    public void updatedata(DataModel d) {
        SQLiteDatabase fill=this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name",d.name);
        content.put("address",d.address);
        content.put("phone",d.phone);
        fill.update("student", content,"id="+d.id,null);
    }
}
