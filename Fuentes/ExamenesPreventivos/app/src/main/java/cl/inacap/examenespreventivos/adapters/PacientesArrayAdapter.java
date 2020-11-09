package cl.inacap.examenespreventivos.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.examenespreventivos.R;
import cl.inacap.examenespreventivos.dto.Paciente;

public class PacientesArrayAdapter extends ArrayAdapter<Paciente> {
    private Activity activity;
    private List<Paciente> pacientes;
    public PacientesArrayAdapter(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.pacientes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.pacientes_list,null,true);
        ImageView icono = fila.findViewById(R.id.iconoImg);
        TextView rutTv = fila.findViewById(R.id.rutTxt);
        TextView nombreTv = fila.findViewById(R.id.nombreTxt);
        TextView apellidoTv = fila.findViewById(R.id.apellidoTxt);
        TextView fechaTv = fila.findViewById(R.id.fechaTxt);


        Paciente actual = pacientes.get(position);
        rutTv.setText("Rut: "+actual.getRut());
        nombreTv.setText("Nombre: "+actual.getNombre());
        apellidoTv.setText("Apellido: "+actual.getApellido());
        fechaTv.setText("Fecha: "+actual.getFecha());
        if(actual.getEsCovid().toString().equals("true")){
            actual.setIcono("https://genotipia.com/wp-content/uploads/2020/04/virus-1024x975.jpeg");
            Picasso.get().load(actual.getIcono())
                    .resize(300,300)
                    .centerCrop()
                    .into(icono);
        }
        return fila;
    }
}
