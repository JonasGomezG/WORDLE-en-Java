import java.util.Scanner;

public class Wordle {

	// yo Joaquin insistí en usar esto
	static Scanner lector = new Scanner(System.in);
	// fin de la insistencia

	// Joaquin
	public static void main(String[] args) {

		int intentos;
		char continuar;

		String palabraSecreta;
		String copiaSecreta;
		String palabraAdivinada;

		// Jonás 
		String caracteresValidos = "QWERTYUIOPASDFGHJKLZXCVBNMÑ";
		String[] bancoPalabras = { "kebab", "danza", "edita", "bahia", "hacer", "libro", "vivir", "tabla", "pinos",
				"vagon", "raton", "ladra", "redes", "radio", "sabor", "nadal", "madre", "padre", "ideal", "bacon",
				"cache", "abajo", "decir", "canta", "apodo", "besos", "cazas", "coger", "crema", "erizo", "feria",
				"fusil" };
		String[] historialPalabras; // historial de palabras que ingresó el usuario

		// prepara las variables para el juego
		do {

			intentos = 0;

			historialPalabras = nuevoHistorial();

			palabraSecreta = palabraAleatoria(bancoPalabras);

			imprimeReglas();

			// comienza el juego
			do {

				imprimeHistorial(historialPalabras);

				System.out.println("Adivina la palabra!!\n");

				palabraAdivinada = pedirPalabra(caracteresValidos);

				// actualiza la copia borrando las letras correctas
				copiaSecreta = actualizaCopia(palabraAdivinada, palabraSecreta);

				// compara letra por letra
				historialPalabras[intentos] = comparaPalabra(palabraAdivinada, palabraSecreta, copiaSecreta);

				intentos++;

			} while (!palabraAdivinada.equals(palabraSecreta) && intentos < 6);

			if (palabraAdivinada.equals(palabraSecreta)) {

				System.out.println("Ganaste!!!\n");

			} else {

				System.out.println("Mejor suerte la próxima :(");

			}

			imprimeHistorial(historialPalabras);

			System.out.println("Deseas jugar otra vez?");
			continuar = preguntaAlUsuario();

		} while (continuar == 'S');

		lector.close();
	}

	// Joaquin
	public static String[] nuevoHistorial() {

		String[] historial = new String[6];

		// inicializa el historial de palabras
		for (int i = 0; i < 6; i++) {

			historial[i] = " │   │  │   │  │   │  │   │  │   │ ";

		}

		return historial;

	}

	// Jonás
	public static String palabraAleatoria(String[] bancoPalabras) {

		// escoge una palabra al azar del banco de palabras dado
		String palabraEscogida = bancoPalabras[(int) Math.floor(Math.random() * bancoPalabras.length)].toUpperCase();

		return palabraEscogida;

	}

	// Jonás
	public static void imprimeReglas() {
		
		String ansiVerde = "\033[1;92m"; // cambia el color de los caracteres en consola a verde
		String ansiAmarillo = "\033[1;33m"; // cambia el color de los caracteres en consola a amarillo
		String ansiGris = "\033[1;90m"; // cambia el color de los caracteres en consola a gris
		String ansiReseteo = "\033[0m"; // cambia el color de los caracteres en consola a su color por defecto

		// reglas
		System.out.println();
		System.out.println("Si la letra se ve asi:");
		System.out.println();
		System.out.println(" ┌───┐");
		System.out.println(" │ " + ansiVerde + "A" + ansiReseteo + " │ Significa que está en el lugar correcto");
		System.out.println(" └───┘");
		System.out.println();
		System.out.println("Si se ve asi:");
		System.out.println();
		System.out.println(" ┌───┐");
		System.out.println(" │ " + ansiAmarillo + "A" + ansiReseteo
				+ " │ Significa que está en la palabra pero no en el lugar correcto");
		System.out.println(" └───┘");
		System.out.println();
		System.out.println("Si se ve asi:");
		System.out.println();
		System.out.println(" ┌───┐");
		System.out.println(" │ " + ansiGris + "A" + ansiReseteo + " │ Significa que no está en la palabra");
		System.out.println(" └───┘");
		System.out.println();
		System.out.println("Suerte!\n");

	}

	// Joaquin
	public static void imprimeHistorial(String[] historial) {

		// imprime a pantalla el historial
		for (int i = 0; i < 6; i++) {
			System.out.println(" ┌───┐  ┌───┐  ┌───┐  ┌───┐  ┌───┐ ");
			System.out.println(historial[i]);
			System.out.println(" └───┘  └───┘  └───┘  └───┘  └───┘\n");
		}

		System.out.println();

	}

	// Jonás
	public static String pedirPalabra(String caracteresValidos) {

		String palabra;

		do {

			System.out.print("Palabra: ");
			palabra = lector.nextLine().toUpperCase();

		} while (palabra.length() != 5 || palabra.contains(" ") || !estaEnCadena(palabra, caracteresValidos));

		System.out.println();

		return palabra;

	}

	// Jonás
	public static boolean estaEnCadena(String cadenaValidar, String cadena) {

		boolean seEncuentra = true;
		int longitud = cadenaValidar.length();
		int contador = 0;
		String caracter;

		do {

			caracter = Character.toString(cadenaValidar.charAt(contador));

			if (!cadena.contains(caracter)) {

				seEncuentra = false;

			}

			contador++;

		} while (seEncuentra && contador < longitud);

		return seEncuentra;

	}

	// Joaquin
	public static String borraDeCopia(String copia, int indice) {

		// borra de un string el indice dado
		copia = copia.substring(0, indice) + copia.substring(indice + 1);

		return copia;

	}

	// Joaquin 
	public static String actualizaCopia(String adivinada, String secreta) {

		int indiceLetra;
		char letra;
		String copia = secreta;

		for (int i = 0; i < 5; i++) {

			letra = adivinada.charAt(i);
			indiceLetra = copia.indexOf(letra);

			if (adivinada.charAt(i) == secreta.charAt(i)) {

				if (indiceLetra >= 0) {

					copia = borraDeCopia(copia, indiceLetra);

				}

			}

		}

		return copia;

	}

	// Joaquin
	public static String comparaPalabra(String adivinada, String secreta, String copia) {

		int indiceLetra;
		char letra;
		String palabra = "";
		String ansiVerde = "\033[1;92m"; // cambia el color de los caracteres en consola a verde
		String ansiAmarillo = "\033[1;33m"; // cambia el color de los caracteres en consola a amarillo
		String ansiGris = "\033[1;90m"; // cambia el color de los caracteres en consola a gris
		String ansiReseteo = "\033[0m"; // cambia el color de los caracteres en consola a su color por defecto

		for (int i = 0; i < 5; i++) {

			letra = adivinada.charAt(i);
			indiceLetra = copia.indexOf(letra);

			// si la letra está en el lugar correcto la cambia a verde
			if (adivinada.charAt(i) == secreta.charAt(i)) {

				palabra += " │ " + ansiVerde + adivinada.charAt(i) + ansiReseteo + " │ ";

				// si la letra está en la palabra pero no en el lugar correcto la cambia a
				// amarillo
			} else if (copia.contains(Character.toString(letra))) {

				if (indiceLetra >= 0) {

					copia = borraDeCopia(copia, indiceLetra);
					palabra += " │ " + ansiAmarillo + adivinada.charAt(i) + ansiReseteo + " │ ";

				}

				// si la letra no está en la palabra no la cambia
			} else {

				palabra += " │ " + ansiGris + adivinada.charAt(i) + ansiReseteo + " │ ";

			}

		}

		return palabra;

	}

	// Jonás
	public static char preguntaAlUsuario() {

		String letra;

		do {

			System.out.print("(S/N): ");
			letra = lector.nextLine().toUpperCase();
			System.out.println();

			if (letra.trim().isEmpty() || (letra.charAt(0) != 'S' && letra.charAt(0) != 'N')) {
				System.out.println("Ingresa S o N");
			}

		} while (letra.trim().isEmpty() || (letra.charAt(0) != 'S' && letra.charAt(0) != 'N'));

		return letra.charAt(0);

	}

}
