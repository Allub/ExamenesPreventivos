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
    public PacientesDAOSQLite(Context context){
        this.pacHelper = new PacientesSQLiteHelper(context, "DBPacientes"
                ,null,1);
    }

    @Override
    public List<Paciente> getAll() {
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
                        if (c.getString(6).equals("true")){
                            p.setEsCovid(true);
                        }else{
                            p.setEsCovid(false);
                        }
                        p.setTemperatura(c.getFloat(7));
                        if (c.getString(8).equals("true")){
                            p.setTos(true);
                        }else{
                            p.setTos(false);
                        }
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
        String sql = String.format("INSERT INTO pacientes(rut,nombre,apellido,fechaExamen,areaTrabajo,sintomas" +
                ",temperatura,tos,presionArterial)" +
                " VALUES ('%s','%s','%s','%s','%s','%s',%.1f,'%s',%d)",p.getRut(),p.getNombre()
                ,p.getApellido(),p.getFecha(),p.getAreaTrabajo(),p.getEsCovid(),p.getTemperatura(),p.getTos(),p.getArterial());

        writer.execSQL(sql);
        writer.close();

        return p;
    }
}
