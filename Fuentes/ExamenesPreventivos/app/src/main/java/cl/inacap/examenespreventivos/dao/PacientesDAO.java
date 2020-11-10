package cl.inacap.examenespreventivos.dao;

import java.util.List;

import cl.inacap.examenespreventivos.dto.Paciente;

public interface PacientesDAO {
    List<Paciente> getAll();

    Paciente save(Paciente p);
}
