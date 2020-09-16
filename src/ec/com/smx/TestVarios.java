/**
 * 
 */
package ec.com.smx;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * @author vjaramillo
 *
 */
public class TestVarios {

	@Test
	public void testContarDiasFecha() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 
			Date fechaInicial=dateFormat.parse("2019-02-20");
			Date fechaFinal=new Date();
			//fechaFinal=dateFormat.parse(fechaFinal.toString());
	 
			int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
	 
			System.out.println("Hay "+dias+" dias de diferencia");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	@Test
	public void testCompletarCeros() {
	    String valor = "4";
	    Integer  cantidadCeros = 7;
	    for (int i = 0 ; i < cantidadCeros ; i++) {
            if (valor.length() < cantidadCeros) {
                valor = "0".concat(valor);
                System.out.println(valor);
            }
        }
	    System.out.println("Resultado final: ".concat(valor));
	}
	
	@Test
	public void validarFormatoFecha() {
		String fecha = "2019-01-01";
		SimpleDateFormat formatoFecha;
		try {
			formatoFecha = new SimpleDateFormat("YYYY-mm-dd");
//			formatoFecha = new SimpleDateFormat("YYYY/mm/dd");
			formatoFecha.setLenient(Boolean.FALSE);
			System.out.println(formatoFecha.parse(fecha));
			
		} catch (Exception e) {
			System.err.println("Error al momento de validar la fecha ".concat(e.getMessage()));
		}
	}
}
