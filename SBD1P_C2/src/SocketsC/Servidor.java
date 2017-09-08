package SocketsC;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.SwingUtilities;

import java.net.ServerSocket;


public class Servidor {

	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
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
		
		entrada= new ObjectInputStream(conexion.getInputStream());
		mostrarMensaje("Se obtuvieron los flujos de E/S");
	}
	
	private void procesarConexion() throws IOException{
		String mensaje="Conexion exitosa";
		enviarDatos(mensaje);
					
		do {
			try {
				mensaje=(String)entrada.readObject();
				mostrarMensaje(mensaje);
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
	
	private void enviarDatos(String mensaje) {
		try {
			salida.writeObject("SERVIDOR>>> "+ mensaje);
			salida.flush();
			mostrarMensaje("SERVIDOR>>> "+mensaje);
		}catch(IOException excepcionES) {
			mostrarMensaje("Error al escribir objeto");
		}	
	}
	
	private void mostrarMensaje(String mensaje){
		
		System.out.println(mensaje);
		
	}
}
