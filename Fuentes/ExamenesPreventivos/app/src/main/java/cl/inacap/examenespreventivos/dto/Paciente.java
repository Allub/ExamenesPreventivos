package cl.inacap.examenespreventivos.dto;

import java.io.Serializable;

public class Paciente implements Serializable {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private String fecha;
    private String areaTrabajo;
    private Boolean esCovid;
    private Float temperatura;
    private Boolean tos;
    private int arterial;
    private String icono;

    @Override
    public String toString() {
        return "Paciente{" +
                "rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEsCovid() {
        return esCovid;
    }

    public void setEsCovid(Boolean esCovid) {
        this.esCovid = esCovid;
    }

    public Boolean getTos() {
        return tos;
    }

    public void setTos(Boolean tos) {
        this.tos = tos;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public Float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Float temperatura) {
        this.temperatura = temperatura;
    }

    public int getArterial() {
        return arterial;
    }

    public void setArterial(int arterial) {
        this.arterial = arterial;
    }
}
