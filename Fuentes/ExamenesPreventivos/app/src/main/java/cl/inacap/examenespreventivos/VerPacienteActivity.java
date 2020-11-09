package cl.inacap.examenespreventivos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import cl.inacap.examenespreventivos.dto.Paciente;

public class VerPacienteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView rutVp;
    private TextView nombreYapellidoVp;
    private TextView fechaVp;
    private TextView areaVp;
    private TextView covidVp;
    private TextView tosVp;
    private TextView tempVp;
    private TextView presionVp;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.rutVp = findViewById(R.id.rut_ver_pac);
        this.nombreYapellidoVp = findViewById(R.id.nombre_apellido_ver_pac);
        this.fechaVp = findViewById(R.id.fecha_ver_pac);
        this.areaVp = findViewById(R.id.area_ver_pac);
        this.covidVp = findViewById(R.id.covid_ver_pac);
        this.tosVp = findViewById(R.id.tos_ver_pac);
        this.tempVp = findViewById(R.id.temperatura_ver_pac);
        this.presionVp = findViewById(R.id.presion_ver_pac);
        if (getIntent() != null){
            Paciente paciente = (Paciente) getIntent().getSerializableExtra("pacientes");
            this.rutVp.setText(paciente.getRut());
            this.nombreYapellidoVp.setText(paciente.getNombre()+" "+paciente.getApellido());
            this.fechaVp.setText(paciente.getFecha());
            this.areaVp.setText(paciente.getAreaTrabajo());
            if (paciente.getEsCovid()){
                this.covidVp.setText("si");
            }else{
                this.covidVp.setText("no");
            }
            if (paciente.getTos()){
                this.tosVp.setText("si");
            }else{
                this.tosVp.setText("no");
            }
            this.tempVp.setText(paciente.getTemperatura().toString());
            this.presionVp.setText(""+paciente.getArterial());

        }
    }
}