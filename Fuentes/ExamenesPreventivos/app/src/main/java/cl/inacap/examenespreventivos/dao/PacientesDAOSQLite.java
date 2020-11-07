package cl.inacap.examenespreventivos.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.examenespreventivos.dto.Paciente;
import cl.inacap.examenespreventivos.helpers.PacientesSQLiteHelper;

public class PacientesDAOSQLite implements PacientesDAO {

    private PacientesSQLiteHelper pacHelper;
    //cuando se crea esta instancia, se crea el helper
    //este helper tiene el acceso a las bd
    //asi puedo conectarme, obtener datos, ver datos, etc.
    public PacientesDAOSQLite(Context context){
        this.pacHelper = new PacientesSQLiteHelper(context, "DBPacientes"
                ,null,1);
    }

    @Override
    public List<Paciente> getAll() {
        //con esto se tiene referencia a la lectura de bd
        SQLiteDatabase reader = this.pacHelper.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();
        try {
            if(reader != null){
                Cursor c = reader.rawQuery("SELECT id,rut,nombre,apellido" +
                        ",fechaExamen,areaTrabajo,sintomas" +
                        ",temperatura,tos,presionArterial" +
                        " FROM pacientes",null);
                if(c.moveToFirst()){
                    do{
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setRut(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setApellido(c.getString(3));
                        p.setFecha(c.getString(4));
                        p.setAreaTrabajo(c.getString(5));
                        p.setEsCovid(c.getString(6));
                        p.setTemperatura(c.getFloat(7));
                        p.setTos(c.getString(8));
                        p.setArterial(c.getInt(9));
                        pacientes.add(p);
                    }while (c.moveToNext());
                }
                reader.close();
            }

        }catch (Exception ex){
            Log.e("PACIENTESDAO",ex.toString());
            pacientes = null;
        }

        return pacientes;
    }

    @Override
    public Paciente save(Paciente p) {
        SQLiteDatabase writer = this.pacHelper.getWritableDatabase();
        //aca se genera la query
        String sql = String.format("INSERT INTO pacientes(rut,nombre,apellido,fechaExamen,areaTrabajo,sintomas" +
                ",temperatura,tos,presionArterial)" +
                " VALUES ('%s','%s','%s','%s','%s','%s',%.2f,'%s',%d)",p.getRut(),p.getNombre()
                ,p.getApellido(),p.getFecha(),p.getAreaTrabajo(),p.getEsCovid(),p.getTemperatura(),p.getTos(),p.getArterial());
        //para ejecutar
        writer.execSQL(sql);
        writer.close();

        return p;
    }
}
