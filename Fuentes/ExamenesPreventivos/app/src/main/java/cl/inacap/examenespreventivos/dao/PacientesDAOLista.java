package cl.inacap.examenespreventivos.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.examenespreventivos.dto.Paciente;

public class PacientesDAOLista implements PacientesDAO {

    private List<Paciente> pacientes = new ArrayList<>();
    private static PacientesDAOLista instancia;

    public static PacientesDAOLista getInstance(){
        if(instancia == null){
            instancia = new PacientesDAOLista();
        }
        return instancia;
    }

    @Override
    public List<Paciente> getAll() {
        return pacientes;
    }

    @Override
    public Paciente save(Paciente p) {
        pacientes.add(p);
        return p;
    }
}
