package cl.inacap.examenespreventivos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private String usuario;
    private String clave;
    private EditText nombre;
    private EditText contraseña;
    private Button sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nombre = findViewById(R.id.nombre);
        this.contraseña = findViewById(R.id.contraseña);
        this.sesion = findViewById(R.id.sesion);
        this.sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (nombre.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "Debe ingresar nombre de usuario", Toast.LENGTH_SHORT).show();
                        nombre.setBackground(getDrawable(R.drawable.border));
                    }
                    if (validaRut(nombre.getText().toString()) == true) {
                        usuario = nombre.getText().toString();
                    } else {
                        Toast.makeText(MainActivity.this, "Nombre de usuario invalido", Toast.LENGTH_SHORT).show();
                    }
                    clave = contraseña.getText().toString();
                    if(clave.isEmpty()){
                        Toast.makeText(MainActivity.this, "Debe ingresar contraseña", Toast.LENGTH_SHORT).show();
                    }
                    if (usuario.length() == 10) {
                        if (clave.equals(usuario.substring(4, 8))) {
                            //intent
                            startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Contraseña Incorrecta" , Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (usuario.length() == 9) {
                        if (clave.equals(usuario.substring(3, 7))) {
                            //intent
                            startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch (Exception ex){

                }

            }
        });

    }

    //validador de rut
    public static Boolean validaRut(String rut) {
        Pattern pattern = Pattern.compile("[0-9]{7,8}-[0-9kK]{1}");
        Matcher matcher = pattern.matcher(rut);
        if (matcher.matches() == false) {
            return false;
        }
        return true;
    }


}