package cl.inacap.examenespreventivos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.inacap.examenespreventivos.dao.PacientesDAO;
import cl.inacap.examenespreventivos.dao.PacientesDAOSQLite;
import cl.inacap.examenespreventivos.dto.Paciente;

public class RegistrarPacienteActivity extends AppCompatActivity {

    private int dia, mes, anio;
    private TextView rutTxt;
    private TextView nombretxt;
    private TextView apellidoTxt;
    private TextView fechaTxt;
    private Switch tosSw;
    private Switch sinSw;
    private TextView tempTxt;
    private TextView presionTxt;
    private Button fechaBtn;
    private Toolbar toolbar;
    private Button registrarBtn;
    private Spinner areaTrabajo;
    private PacientesDAO pacDAO = new PacientesDAOSQLite(this);
    private MainActivity main;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.rutTxt = findViewById(R.id.rut_pac_txt);
        this.nombretxt = findViewById(R.id.nombre_pac_txt);
        this.apellidoTxt = findViewById(R.id.apellido_pac_txt);
        this.fechaTxt = findViewById(R.id.fecha_pac_txt);
        this.areaTrabajo = findViewById(R.id.areaSp);
        String[] areasTrabajos = {"Atención a publico","otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, areasTrabajos);
        areaTrabajo.setAdapter(adapter);
        areaTrabajo.isShown();
        this.sinSw = findViewById(R.id.sintomas_sw);
        this.tosSw = findViewById(R.id.tosSw);
        this.tempTxt = findViewById(R.id.tempe_pac_txt);
        this.presionTxt = findViewById(R.id.presion_pac_txt);
        this.registrarBtn = findViewById(R.id.registrarBtn);
        this.fechaBtn = findViewById(R.id.fechaBtn);
        this.fechaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fechaBtn) {
                    final Calendar c = Calendar.getInstance();
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio = c.get(Calendar.YEAR);

                    final Calendar calendario = Calendar.getInstance();
                    calendario.set(dia, mes, anio);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext()
                            , new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int d, int m, int y) {
                            Calendar calendario = Calendar.getInstance();
                            calendario.set(d, m, y);
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            String strDate = format.format(calendario.getTime());

                            fechaTxt.setText(strDate);
                        }
                    }
                            , anio, mes, dia);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePickerDialog.show();
                }
            }
        });

            this.registrarBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Paciente p = new Paciente();
                    List<String> errores = new ArrayList<>();
                    String rutStr = rutTxt.getText().toString().trim();
                    if (validarRut(rutStr)==false)
                        errores.add("Debe Ingresar un Rut valido");
                    String nomStr = nombretxt.getText().toString().trim();
                    if (nomStr.isEmpty()){
                        errores.add("Debe ingresar un nombre");
                    }
                    String apellStr = apellidoTxt.getText().toString().trim();
                    if (apellStr.isEmpty()){
                        errores.add("Debe ingresar un apellido");
                    }

                    String tempStr = tempTxt.getText().toString().trim();
                    float temper = 0;
                    try {
                        temper = Float.parseFloat(tempStr);
                        if (temper < 20){
                            throw new NumberFormatException();
                        }
                    }catch (Exception ex){
                        errores.add("Debe Ingresar una temperatura mayor a 20");
                    }
                    if(fechaTxt.getText().toString().isEmpty()){
                        errores.add("Debe seleccionar una fecha");
                    }

                    if (errores.isEmpty()){
                        p.setRut(rutTxt.getText().toString());
                        p.setNombre(nombretxt.getText().toString());
                        p.setApellido(apellidoTxt.getText().toString());
                        p.setFecha(fechaTxt.getText().toString());
                        p.setAreaTrabajo(areaTrabajo.getSelectedItem().toString());
                        if(sinSw.isChecked()){
                            p.setEsCovid(true);
                        }else{
                            p.setEsCovid(false);
                        }
                        if (tosSw.isChecked()){
                            p.setTos(true);
                        }else{
                            p.setTos(false);
                        }

                        p.setTemperatura(Float.parseFloat(tempTxt.getText().toString()));
                        p.setArterial(Integer.parseInt(presionTxt.getText().toString()));


                        pacDAO.save(p);

                        startActivity(new Intent(RegistrarPacienteActivity.this,PrincipalActivity.class));
                    }else{
                        mostrarErrores(errores);
                    }

                }
                private void mostrarErrores(List<String> errores) {
                    String mensaje = "";
                    for (String e : errores) {
                        mensaje += "" + e + "\n";
                    }
                    AlertDialog.Builder aleBuilder = new AlertDialog.Builder(RegistrarPacienteActivity.this);
                    aleBuilder.setTitle("Error de validación")
                            .setMessage(mensaje)
                            .setPositiveButton("Aceptar", null)
                            .create()
                            .show();
                }
            });
    }
    public static boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

}