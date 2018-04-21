package brayan0428.notasapp.com.notasapp.Clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ConexionHelper extends SQLiteOpenHelper {
    final String CREAR_TABLA_NOTA = "CREATE TABLE notas (id INTEGER PRIMARY KEY AUTOINCREMENT,titulo text not null,descripcion text not null,fecha text not null )";
    public ConexionHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAR_TABLA_NOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists notas");
        onCreate(sqLiteDatabase);
    }
}
