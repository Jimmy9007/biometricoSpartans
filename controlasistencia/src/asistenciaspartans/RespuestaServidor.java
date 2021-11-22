package asistenciaspartans;

import java.util.Date;

public class RespuestaServidor {

    private int huellaEnrrolada;
    private int usuarioExiste;
    private String documentoUsuario;
    private String nombreUsuario;
    private String idClienteEmpleado;
    private String fechaNacimiento;
    private int responseCode;

    public RespuestaServidor(int huellaEnrrolada, int usuarioExiste, String documentoUsuario, String nombreUsuario, String idClienteEmpleado, String fechaNacimiento, int responseCode) {
        this.huellaEnrrolada = huellaEnrrolada;
        this.usuarioExiste = usuarioExiste;
        this.documentoUsuario = documentoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idClienteEmpleado = idClienteEmpleado;
        this.fechaNacimiento = fechaNacimiento;
        this.responseCode = responseCode;
    }

    public int getHuellaEnrrolada() {
        return huellaEnrrolada;
    }

    public void setHuellaEnrrolada(int huellaEnrrolada) {
        this.huellaEnrrolada = huellaEnrrolada;
    }

    public int getUsuarioExiste() {
        return usuarioExiste;
    }

    public void setUsuarioExiste(int usuarioExiste) {
        this.usuarioExiste = usuarioExiste;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getIdClienteEmpleado() {
        return idClienteEmpleado;
    }

    public void setIdClienteEmpleado(String idClienteEmpleado) {
        this.idClienteEmpleado = idClienteEmpleado;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

   

    
   

}
