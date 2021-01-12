package aed.accesoficheros;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Random {

	public static void main(String[] args) throws IOException {
		//Insertar();
		Visualizar();

	}

	public static void Modificar() throws IOException {
		RandomAccessFile fichero = new RandomAccessFile("test.txt", "rw");
		int id = 0;
		int Copas = 0;
		Charset charset = StandardCharsets.UTF_8;

		char Separador = ',';
		String s1 = "";
		if (fichero.length() == 0)
			System.out.println("No hay datos a modificar");
		else {
			id = (id - 1) * 231;
			fichero.seek(id + 231);

			fichero.writeInt(Copas);
		}

	}

	public void VisualizaEquipoID() throws IOException {
		int id = 0;

		RandomAccessFile fichero = new RandomAccessFile("test.txt", "rw");

		Charset charset = StandardCharsets.UTF_8;

		char separador = ',';
		String s1 = "";

		if (fichero.length() == 0)
			System.out.println("No hay datos a visualizar");
		else {
			id = (id - 1) * 231;
			fichero.seek(id);

			System.out.println("ID: " + fichero.readInt());

			separador = fichero.readChar();

			byte[] arr1 = new byte[40];
			fichero.readFully(arr1);
			s1 = new String(arr1, charset);
			System.out.println("Nombre Equipo: " + s1);

			separador = fichero.readChar();

			byte[] arr2 = new byte[5];
			fichero.readFully(arr2);
			s1 = new String(arr2, charset);
			System.out.println("Codigo Liga: " + s1);

			separador = fichero.readChar();

			byte[] arr3 = new byte[60];
			fichero.readFully(arr3);
			s1 = new String(arr3, charset);
			System.out.println("Localidad: " + s1);

			separador = fichero.readChar();

			System.out.println("Copas ganadas: " + fichero.readInt());

			separador = fichero.readChar();

			System.out.println("Internacional " + fichero.readBoolean());

			separador = fichero.readChar();

		}

	}

	public static void Visualizar() throws IOException {
		int ID;

		RandomAccessFile fichero = new RandomAccessFile("Creado.txt", "r");

		Charset charset = StandardCharsets.UTF_8;

		char separador = ',';
		String s1 = "";

		if (fichero.length() == 0)
			System.out.println("No hay datos a visualizar");
		else {
			while (fichero.getFilePointer() < fichero.length()) {

				System.out.println("ID: " + fichero.readInt());

				separador = fichero.readChar();

				byte[] arr1 = new byte[40];
				fichero.readFully(arr1);
				s1 = new String(arr1, charset);
				System.out.println("Nombre Equipo: " + s1);

				separador = fichero.readChar();

				byte[] arr2 = new byte[5];
				fichero.readFully(arr2);
				s1 = new String(arr2, charset);
				System.out.println("Codigo Liga: " + s1);

				separador = fichero.readChar();

				byte[] arr3 = new byte[60];
				fichero.readFully(arr3);
				s1 = new String(arr3, charset);
				System.out.println("Localidad: " + s1);

				separador = fichero.readChar();

				System.out.println("Copas ganadas: " + fichero.readInt());

				separador = fichero.readChar();

				System.out.println("Internacional " + fichero.readBoolean());

				separador = fichero.readChar();
			}
		}
	}

	public static void Insertar() throws IOException {
		String[] datos = new String[5];
		datos[0] = ("Los angele" + "                                                  ").substring(0,
				40);
		datos[1] = ("YUKA" + "     ").substring(0, 5);
		datos[2] = ("UN PUEBLO" + "                                                                                     ")
				.substring(0, 60);
		datos[3] = "5";
		datos[4] = "true";
		RandomAccessFile fichero = new RandomAccessFile("hola.txt", "rw");
		int id = 0;
		char separador = ',';

		if (fichero.length() == 0)
			id = 1;
		else {
			fichero.seek(fichero.length() - 229);
			id = fichero.readInt() + 1;
			fichero.seek(fichero.length());
		}
		
		fichero.writeInt(id);
		fichero.writeChar(separador);
		//nombre
		fichero.writeUTF(datos[0]);
		fichero.writeChar(separador);
		//liga
		fichero.writeUTF(datos[1]);
		fichero.writeChar(separador);
		//localidad
		fichero.writeUTF(datos[2]);
		fichero.writeChar(separador);
		//Copas
		fichero.writeInt(Integer.parseInt(datos[3]));
		fichero.writeChar(separador);
		//Internacional
		fichero.writeBoolean(Boolean.parseBoolean(datos[4]));
		fichero.writeChar(separador);
	}
}
