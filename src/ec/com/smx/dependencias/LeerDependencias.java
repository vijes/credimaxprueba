/**
 * 
 */
package ec.com.smx.dependencias;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import ec.com.smx.framework.common.util.dto.Duplex;

/**
 * @author vjaramillo
 *
 */
public class LeerDependencias {

	@Test
	public void leerDependenciasGradle() {
		File archivo;
		Map<String, String> dependenciasBase, grupoDependenciasBase;
		Duplex<Map<String, String>, Map<String, String>> duplexDependencias;
		
		try {
			//D:\proyectos\repos\Varios\proyectoVarios\src\ec\com\smx\EncriptarPassword.java
			//D:\proyectos\repos\Varios\proyectoVarios\resources\ec\com\smx\archivoGradle1.gradle
			archivo = new File("resources\\ec\\com\\smx\\archivoGradleBase.gradle");
			duplexDependencias = obtenerDependencias(archivo);
			if ( duplexDependencias != null) {
				grupoDependenciasBase = duplexDependencias.getFirstObject();
				dependenciasBase = duplexDependencias.getSecondObject();
			}else {
				throw new Exception("No se encontro archivos para la definicion base");
			}			
			
			// Recorrer dependencias base para validar cambios:
			for (String dependencia : dependenciasBase.keySet()) {
				if ( grupoDependenciasBase.containsKey(dependenciasBase.get(dependencia))) {
				    System.out.println(dependencia.concat(" ; ").concat( grupoDependenciasBase.get(dependenciasBase.get(dependencia))));
				}else {
				    System.out.println(dependencia.concat(" ; ").concat(dependenciasBase.get(dependencia)));
				}
			}
			
		} catch (Exception e) {
			System.err.println("Error al momento de leer las dependencias "+ e.getMessage());
			System.err.println(e);
		}
	}
	
	@Test
	public void obtenerDependenciasPorTipo() {
		File archivo;
		Map<String, String> dependenciasBase, grupoDependenciasBase;
		Duplex<Map<String, String>, Map<String, String>> duplexDependencias;
		Boolean versionRelease = Boolean.FALSE;
		try {
			//D:\proyectos\repos\Varios\proyectoVarios\src\ec\com\smx\EncriptarPassword.java
			//D:\proyectos\repos\Varios\proyectoVarios\resources\ec\com\smx\archivoGradle1.gradle
			archivo = new File("resources\\ec\\com\\smx\\archivoGradleBase.gradle");
			duplexDependencias = obtenerDependencias(archivo);
			if ( duplexDependencias != null) {
				grupoDependenciasBase = duplexDependencias.getFirstObject();
				dependenciasBase = duplexDependencias.getSecondObject();
			}else {
				throw new Exception("No se encontro archivos para la definicion base");
			}			
			
			// Recorrer dependencias base para validar cambios:
			String version;
			for (String dependencia : dependenciasBase.keySet()) {
				if ( grupoDependenciasBase.containsKey(dependenciasBase.get(dependencia))) {
					version =  grupoDependenciasBase.get(dependenciasBase.get(dependencia));
				}else {
					version =  dependenciasBase.get(dependencia);
				}
				if ( BooleanUtils.isTrue(versionRelease) && version.contains("RELEASE")) {
					System.out.println(dependencia.concat(" ; ").concat(version));
				}else if ( BooleanUtils.isNotTrue(versionRelease) && version.contains("SNAPSHOT") ) {
				    System.out.println(dependencia.concat(" ; ").concat(version));
				}else if ( !version.contains("RELEASE") && !version.contains("SNAPSHOT")) {
				    System.out.println(dependencia.concat(" ; ").concat(version));
				}
			}
		} catch (Exception e) {
			System.err.println("Error al momento de leer las dependencias "+ e.getMessage());
			System.err.println(e);
		}
	}
	
	@Test
	public void compararVersionesGradle() {
		File archivo;
		Map<String, String> dependenciasBase, dependenciasComparar, grupoDependenciasBase, grupoDependenciasComparar;
		Duplex<Map<String, String>, Map<String, String>> duplexDependencias;
		
		try {
			//D:\proyectos\repos\Varios\proyectoVarios\src\ec\com\smx\EncriptarPassword.java
			//D:\proyectos\repos\Varios\proyectoVarios\resources\ec\com\smx\archivoGradle1.gradle
			archivo = new File("resources\\ec\\com\\smx\\archivoGradleBase.gradle");
			duplexDependencias = obtenerDependencias(archivo);
			if ( duplexDependencias != null) {
				grupoDependenciasBase = duplexDependencias.getFirstObject();
				dependenciasBase = duplexDependencias.getSecondObject();
			}else {
				throw new Exception("No se encontro archivos para la definicion base");
			}			
			// Obtener segundo archivo
			archivo = new File("resources\\ec\\com\\smx\\archivoGradleComparar.gradle");
			duplexDependencias = obtenerDependencias(archivo);
			if ( duplexDependencias != null) {
				grupoDependenciasComparar = duplexDependencias.getFirstObject();
				dependenciasComparar = duplexDependencias.getSecondObject();
			}else {
				throw new Exception("No se encontro archivos para la definicion a comparar");
			}
			
			// Recorrer dependencias base para validar cambios:
			for (String dependencia : dependenciasBase.keySet()) {
				String versionBase, versionComparar;
				if ( dependenciasComparar.containsKey(dependencia) ) {
					// Obtener version base
					if ( grupoDependenciasBase.containsKey(dependenciasBase.get(dependencia)) ) {
						versionBase =  grupoDependenciasBase.get(dependenciasBase.get(dependencia));
					}else {
						versionBase = dependenciasBase.get(dependencia);
					}
					
					// Obtener version comparar
					if ( grupoDependenciasComparar.containsKey(dependenciasComparar.get(dependencia)) ) {
						versionComparar =  grupoDependenciasComparar.get(dependenciasComparar.get(dependencia));
					}else {
						versionComparar = dependenciasComparar.get(dependencia);
					}
					
					if ( !StringUtils.equals(versionBase, versionComparar) ) {
						System.out.println(dependencia);
						System.out.println("		versiones: ".concat(versionBase).concat("	").concat(versionComparar));
					}else {
						System.out.println(dependencia.concat(" version: ").concat(versionBase));
					}
				}else {
					System.out.println(dependencia.concat(" N/A "));
				}
			}
			
		} catch (Exception e) {
			System.err.println("Error al momento de leer las dependencias "+ e.getMessage());
			System.err.println(e);
		}
	}
	@Test
	public void compararVersionesGradlevsOtros() {
		File archivo; 
		Duplex<Map<String, String>, Map<String, String>> duplexDependencias ;
		Map<String, String> grupoDependenciasBase, dependenciasBase, dependenciasOtros;
		String versionBase, versionOtro;
		try {
			// Leer dependencias gradle
			archivo = new File("resources\\ec\\com\\smx\\archivoGradleBase.gradle");
			duplexDependencias = obtenerDependencias(archivo);
			if ( duplexDependencias != null) {
				grupoDependenciasBase = duplexDependencias.getFirstObject();
				dependenciasBase = duplexDependencias.getSecondObject();
			}else {
				throw new Exception("No se encontro archivos para la definicion base");
			}
			
			// leer dependencias otros
			archivo = new File("resources\\ec\\com\\smx\\archivoGradleMAX.gradle");
			dependenciasOtros = obtenerDependenciasOtros(archivo);
			if ( dependenciasOtros == null ) {
				throw new Exception("No se encontro archivos para las dependencias otros");
			}
			
			for (String dependenciaBase : dependenciasBase.keySet()) {
				if ( dependenciasOtros.containsKey(dependenciaBase)) {
					versionOtro  = dependenciasOtros.get(dependenciaBase);
				}else {
					String[] dependenciaArray2Nivel = dependenciaBase.split(":");
					if( dependenciaArray2Nivel.length > 1 &&  dependenciasOtros.containsKey(dependenciaArray2Nivel[1])) {
						versionOtro  = dependenciasOtros.get(dependenciaArray2Nivel[1]);
					}else {
						versionOtro  = "N/A";
					}
				}
				
				if ( grupoDependenciasBase.containsKey(dependenciasBase.get(dependenciaBase)) ) {
					versionBase =  grupoDependenciasBase.get(dependenciasBase.get(dependenciaBase)) ;
				}else {
					versionBase =  dependenciasBase.get(dependenciaBase) ;
				}
				
				// Imprimir valores de las dependencias
				/*if ( StringUtils.equals(versionOtro, "N/A")) {
					System.out.println(dependenciaBase.concat("	").concat(versionBase));
				}else*/ if (!StringUtils.equals(versionBase, versionOtro)) {
					System.out.println(dependenciaBase.concat("\n	").concat(versionBase).concat("		").concat(versionOtro));
				}
			}
			
		} catch (Exception e) {
			System.err.println("Error al momento de leer las dependencias "+ e.getMessage());
			System.err.println(e);
		}
	}
	/**
	 * 
	 * @param archivo
	 * @return
	 * @throws Exception
	 */
	private Duplex<Map<String, String>, Map<String, String>> obtenerDependencias(File archivo) throws Exception {
		FileReader fileReader;
		BufferedReader bufferReader;
		Map<String, String> dependencias, grupoDependencias;
		String cadena;
		String[] arregloDependencia;
		try {
			dependencias = new HashMap<>();
			grupoDependencias = new HashMap<>();
			
			System.out.println(archivo.getAbsolutePath());
			fileReader = new FileReader(archivo);
			bufferReader = new BufferedReader(fileReader);
			
			while ((cadena = bufferReader.readLine()) != null) {
				if ( !cadena.contains("libraries") && !cadena.contains("ext") && !cadena.contains("/") ) {
					//springVersion = '3.2.15.RELEASE'
					if ( cadena.contains("=") ) {
						arregloDependencia = cadena.split("=");
						grupoDependencias.put(arregloDependencia[0].trim(), arregloDependencia[1].replace("'", "").trim());
//						System.out.println("Grupo: " + arregloDependencia[0].trim()+ " version: "+ arregloDependencia[1].replace("'", "").trim());
					}//"ec.com.smx.corpv2:corp-ftp":"1.9.0-RELEASE",
					else if (cadena.contains(":")) {
						arregloDependencia = cadena.split(":");
						dependencias.put(arregloDependencia[0].concat(":").concat(arregloDependencia[1]).replaceAll("\"", "").trim(), arregloDependencia[2].replaceAll("\"", "").replace(",", "").replace("$", "").replace("{", "").replace("}", "").trim());
//						System.out.println("dependencia: " + arregloDependencia[0].concat(arregloDependencia[1]).replaceAll("\"", "").trim()+ " version: "+ arregloDependencia[2].replaceAll("\"", "").replace(",", "").replace("$", "").replace("{", "").replace("}", "").trim());
					}
					
				}
			}
			bufferReader.close();
			return new Duplex<Map<String,String>, Map<String,String>>(grupoDependencias, dependencias);
		} catch (Exception e) {
			System.err.println("Error al momento de obtener las dependencias "+ e.getMessage());
			System.err.println(e);
		}
		return null;
	}
	/**
	 * 
	 * @param archivo
	 * @return
	 */
	private Map<String, String> obtenerDependenciasOtros(File archivo) {
		FileReader fileReader;
		BufferedReader bufferReader;
		Map<String, String> dependencias;
		String cadena, dependenciaString;
		String[] arregloDependencia, dependenciaArray;
		try {
			dependencias = new HashMap<>();
			
			System.out.println(archivo.getAbsolutePath());
			fileReader = new FileReader(archivo);
			bufferReader = new BufferedReader(fileReader);
			while ((cadena = bufferReader.readLine()) != null) {
				arregloDependencia = cadena.split(";");
				dependenciaArray = arregloDependencia[0].split(":");
				// Obtener la dependencia 
				if (dependenciaArray != null && dependenciaArray.length>1) {
					dependenciaString = dependenciaArray[0].concat(":").concat(dependenciaArray[1]);
				}else {
					dependenciaString = dependenciaArray[0];
				}
				
				// Unificar la dependencia con su version
				if (arregloDependencia != null && arregloDependencia.length > 2 ) {
					dependencias.put(dependenciaString.trim(), arregloDependencia[2].trim());
				}else if (arregloDependencia != null && arregloDependencia.length == 2 ) {
					dependencias.put(dependenciaString.trim(), arregloDependencia[1].trim());
				}else {
					dependencias.put(dependenciaString, "");
				}
				
			}
			bufferReader.close();
			return dependencias;
		} catch (Exception e) {
			System.err.println("Error al momento de obtener las dependencias "+ e.getMessage());
			System.err.println(e);
		}
		return null;
	}
	
}
