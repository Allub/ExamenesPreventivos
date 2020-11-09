package cl.inacap.examenespreventivos.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesSQLiteHelper extends SQLiteOpenHelper {
    private final String sqlCreate = "CREATE TABLE " +
            "pacientes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
            ",rut TEXT" +
            ",nombre TEXT" +
            ",apellido TEXT" +
            ",fechaExamen TEXT" +
            ",areaTrabajo TEXT" +
            ",sintomas TEXT" +
            ",temperatura REAL" +
            ",tos TEXT" +
            ",presionArterial INTEGER)";

    public PacientesSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
