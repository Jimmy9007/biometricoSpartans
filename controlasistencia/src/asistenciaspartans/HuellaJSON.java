/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistenciaspartans;

import java.util.Date;

/**
 *
 * @author PC-Programador4
 */
public class HuellaJSON {

    private String documentoUsuario;
    private String idClienteEmpleado;
    private String nombreUsuario;
    private String fechaNacimiento;
    private String imgBase64;
    private String imgVerificacion;
    private int opcion;

    public HuellaJSON(String documentoUsuario, String idClienteEmpleado, String nombreUsuario, String fechaNacimiento, String imgBase64, String imgVerificacion, int opcion) {
        this.documentoUsuario = documentoUsuario;
        this.idClienteEmpleado = idClienteEmpleado;
        this.nombreUsuario = nombreUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.imgBase64 = imgBase64;
        this.imgVerificacion = imgVerificacion;
        this.opcion = opcion;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getIdClienteEmpleado() {
        return idClienteEmpleado;
    }

    public void setIdClienteEmpleado(String idClienteEmpleado) {
        this.idClienteEmpleado = idClienteEmpleado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public String getImgVerificacion() {
        return imgVerificacion;
    }

    public void setImgVerificacion(String imgVerificacion) {
        this.imgVerificacion = imgVerificacion;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    

}
