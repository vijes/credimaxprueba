/**
 * 
 */
package ec.com.smx;

import java.util.Scanner;

import org.junit.Test;

import ec.com.smx.framework.common.util.EncryptionUtil;

/**
 * @author vjaramillo
 *
 */
public class EncriptarPassword {

	@Test
	public void validarPassword() {
		String textoCifrado = "3OtUwote04zMnuXAuak9Yw==";
		try {
			System.out.println("Password validado: ".concat(EncryptionUtil.decifrar(textoCifrado)));
		} catch (Exception e) {
			System.err.println("Error al momento de validar password: " + e.getMessage());
			System.err.println(e);
		}
	}

	@Test
	public void calcularMedianteString(){
		try {
			System.out.println("Ingrese los valores: ");
			Scanner ingresoTexto = new Scanner(System.in);
			String valorIngresado = ingresoTexto.nextLine();
			System.out.println("valor ingresado" + valorIngresado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
