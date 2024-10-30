package gt.edu.umg.proyecto.DBThings;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NOMBRE = "umg.db";
    public static final String TABLE_CONTACTOS = "tb_contactos";

    //Constructos
    public DbHelper(@Nullable Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }
    //Crea la tabla la primera vez que se ejecuta la aplicacion
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, telefono TEXT, email TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTOS);
        onCreate(db);
    }

}