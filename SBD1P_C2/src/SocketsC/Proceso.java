package SocketsC;


import java.io.DataInputStream;
/////
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import uiGf.ventana1;
//////

public class Proceso extends Thread{
	public Proceso(String msg) {
		super(msg);
		///////
		//ejecutarServidor();
		///////
	}
	
	public void run() {
		ejecutarServidor();
	}
	
	
	private ObjectInputStream entrada;
	private static ObjectOutputStream salida;
	private ServerSocket servidor;
	private Socket conexion;
	private int contador=1;
	
	public void ejecutarServidor() {
		
		try {
			servidor= new ServerSocket(12345,100); 
			
			while(true) {
				try {
					esperarConexion();
					obtenerFlujos();
					procesarConexion();
				}catch (EOFException excepcionEOF) {
					mostrarMensaje("Servidor termino la conexion");
					
				}finally {
					cerrarConexion();
					contador++;
				}
				
			}
		}
		catch(IOException exepcionES){
			exepcionES.printStackTrace();
		}		
	}
	
	private void esperarConexion() throws IOException {
		mostrarMensaje("Esperando una Conexion");
		conexion=servidor.accept();
		mostrarMensaje("Conexion "+contador+" recibida de "+conexion.getInetAddress().getHostName());
	}
	
	private void obtenerFlujos() throws IOException {
		salida= new ObjectOutputStream(conexion.getOutputStream());
		salida.flush();
		///
		System.out.println("va a entrar ObjectInputStream");
		entrada= new ObjectInputStream(conexion.getInputStream());
		mostrarMensaje("Se obtuvieron los flujos de E/S");
	}
	
	private void procesarConexion() throws IOException{
		Object mensaje="Conexion exitosa";
		System.out.println("va a entrar a enviarDatos");
		enviarDatos(mensaje.toString());
		System.out.println("paso enviarDatos");
		do {
			try {
				System.out.println("va a entrar lectura");
				mensaje=entrada.readObject();
				System.out.println("paso la lectura");
				mostrarMensaje(mensaje.toString());
			}catch(ClassNotFoundException excepcionClaseNoEncontrada) {
				mostrarMensaje("Se recibio un objeto desconocido");
			}
			
		}while(!mensaje.equals("CLIENTE>>> TERMINAR"));
	}
	
	
	private void cerrarConexion() {
		mostrarMensaje("cerrarConexion");
		try {
			salida.close();
			entrada.close();
			conexion.close();
		}catch(IOException exepcionesEs) {
			exepcionesEs.printStackTrace();
		}
	}
	
	public static void enviarDatos(String mensaje) {
		try {
			salida.writeObject("SERVIDOR>>> "+ mensaje);
			salida.flush();
			mostrarMensaje("SERVIDOR>>> "+mensaje);
		}catch(IOException excepcionES) {
			mostrarMensaje("Error al escribir objeto");
		}	
	}
	
	private static void mostrarMensaje(String mensaje){
		
		System.out.println(mensaje);
		ventana1.editorPane.setText(mensaje);
	}
	////////
}
