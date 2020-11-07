package cl.inacap.examenespreventivos.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesSQLiteHelper extends SQLiteOpenHelper {

    //Aqui se crea la tabla
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

    //Aqui vamos a hacer la creacion de la tabla de productos
    //esto se ejecuta una vez, cuando la bd no existe
    //cuando se abre la aplicacion
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //aqui ejecuto el sqlCreate
        //si no existe la bd, ejecuta la tabla
        sqLiteDatabase.execSQL(sqlCreate);
    }

    //Aqui hay que hacer los cambios necesarios cuando hay cambio de version
    //esto se ejecuta cuando la version de bd se incremente
    //cuando se hacen cambios a la bd, agregar categoria, etc
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //se borra la table y se vuelve a crear
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
