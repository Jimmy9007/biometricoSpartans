package asistenciaspartans;

import com.digitalpersona.onetouch.DPFPTemplate;
import com.google.gson.Gson;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Biometrico {

    private static final String USER_AGENT = "Mozilla/5.0";
//    private static final String SERVER_PATH = "http://192.168.164.245/spartanswebservice/AsistenciaRestApi.php";
    private static final String SERVER_PATH = "https://biometrico.spartansgym.space/AsistenciaRestApi.php";
//    private static final String SERVER_PATH = "http://192.168.0.253/spartanswebservice/AsistenciaRestApi.php";

    public Biometrico() {
    }

    public RespuestaServidor onSave(String documento, String idClienteEmpleado, String nombre, String fechaNacimiento, DPFPTemplate template, Image huella) throws MalformedURLException, IOException {
        String huellaVerificion = Base64.getEncoder().encodeToString(template.serialize());
        ImageIcon huellaIcon = new ImageIcon(huella);
        BufferedImage bufferedHuella = new BufferedImage(450, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedHuella.createGraphics();
        g2d.drawImage(huella, 0, 0, huellaIcon.getImageObserver());

        byte[] imageInByte = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedHuella, "jpg", bos);
        bos.flush();
        imageInByte = bos.toByteArray();
        String huellaBase64 = Base64.getEncoder().encodeToString(imageInByte);
        StringBuilder stringBuilder = new StringBuilder(SERVER_PATH);
        stringBuilder.append(URLEncoder.encode("", "UTF-8"));

        URL obj = new URL(stringBuilder.toString());

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Charset", "*/*");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        HuellaJSON datos = new HuellaJSON("", "", "", "", "", "", 0);
        datos.setDocumentoUsuario(documento);
        datos.setIdClienteEmpleado(idClienteEmpleado);
        datos.setNombreUsuario(nombre);
        datos.setFechaNacimiento(fechaNacimiento);
        datos.setImgVerificacion(huellaVerificion);
        datos.setImgBase64(huellaBase64);
        datos.setOpcion(1);
        String json = new Gson().toJson(datos);
        con.getOutputStream().write(json.getBytes());
        System.out.println("Mensaje de respuesta: " + con.getResponseMessage());
        int responseCode = con.getResponseCode();
        System.out.println("responseCode: " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder respuesta = new StringBuilder();
        while ((line = in.readLine()) != null) {
            respuesta.append(line);
        }
        con.disconnect();
        System.out.println("respuesta: " + respuesta);
        return new Gson().fromJson(respuesta.toString(), RespuestaServidor.class);
    }

    public RespuestaServidor buscarUsuario(String documento) throws MalformedURLException, IOException {
        StringBuilder stringBuilder = new StringBuilder(SERVER_PATH);
        stringBuilder.append(URLEncoder.encode("", "UTF-8"));

        URL obj = new URL(stringBuilder.toString());

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Charset", "*/*");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        HuellaJSON datos = new HuellaJSON("", "", "", "", "", "", 0);
        datos.setDocumentoUsuario(documento);
        datos.setOpcion(2);
        String json = new Gson().toJson(datos);
        con.getOutputStream().write(json.getBytes());
        System.out.println("Mensaje de respuesta: " + con.getResponseMessage());
        int responseCode = con.getResponseCode();
        System.out.println("responseCode: " + responseCode);

//        System.out.println("ERROR: " + con.getErrorStream().toString());
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder respuesta = new StringBuilder();
        while ((line = in.readLine()) != null) {
            respuesta.append(line);
        }
        con.disconnect();
        System.out.println("respuesta: " + respuesta);
        RespuestaServidor respuestaOBJ = new Gson().fromJson(respuesta.toString(), RespuestaServidor.class);
        respuestaOBJ.setResponseCode(responseCode);
        return respuestaOBJ;
    }

    public String listaHuellas() throws UnsupportedEncodingException, MalformedURLException, IOException {
        StringBuilder stb = new StringBuilder(SERVER_PATH);

        URL url = new URL(stb.toString());

        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

//        httpCon.setRequestProperty("User-Agent", USER_AGENT);
        httpCon.setRequestProperty("Acept-Charset", "UTF-8");
        httpCon.setRequestMethod("GET");

        System.out.println("Response Code: " + httpCon.getResponseCode());
        System.out.println("Response Code: " + httpCon.getResponseMessage());

        StringBuilder respuesta;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()))) {
            String linea;
            respuesta = new StringBuilder();

            while ((linea = in.readLine()) != null) {
                respuesta.append(linea);
            }
        }

        return respuesta.toString();

    }

    public void registroEntrada(String documento, String nombre, String idClienteEmpleado) throws UnsupportedEncodingException, MalformedURLException, IOException {
        StringBuilder stringBuilder = new StringBuilder(SERVER_PATH);
        stringBuilder.append(URLEncoder.encode("", "UTF-8"));

        URL obj = new URL(stringBuilder.toString());

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Charset", "*/*");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        HuellaJSON datos = new HuellaJSON("", "", "", "", "", "", 0);
        datos.setDocumentoUsuario(documento);
        datos.setIdClienteEmpleado(idClienteEmpleado);
        datos.setNombreUsuario(nombre);
        datos.setOpcion(3);
        String json = new Gson().toJson(datos);
        con.getOutputStream().write(json.getBytes());
        System.out.println("Mensaje de respuesta: " + con.getResponseMessage());
        int responseCode = con.getResponseCode();
        System.out.println("responseCode: " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder respuesta = new StringBuilder();
        while ((line = in.readLine()) != null) {
            respuesta.append(line);
        }
        con.disconnect();
        System.out.println("respuesta: " + respuesta);
    }

    public void registroSalida(String documento, String nombre, String cargo) throws UnsupportedEncodingException, MalformedURLException, IOException {
        StringBuilder stringBuilder = new StringBuilder(SERVER_PATH);
        stringBuilder.append(URLEncoder.encode("", "UTF-8"));

        URL obj = new URL(stringBuilder.toString());

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Charset", "*/*");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        HuellaJSON datos = new HuellaJSON("", "", "", "", "", "", 0);
        datos.setDocumentoUsuario(documento);
        datos.setNombreUsuario(nombre);
        datos.setOpcion(4);
        String json = new Gson().toJson(datos);
        con.getOutputStream().write(json.getBytes());
        System.out.println("Mensaje de respuesta: " + con.getResponseMessage());
        int responseCode = con.getResponseCode();
        System.out.println("responseCode: " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder respuesta = new StringBuilder();
        while ((line = in.readLine()) != null) {
            respuesta.append(line);
        }
        con.disconnect();
        System.out.println("respuesta: " + respuesta);
    }

}
