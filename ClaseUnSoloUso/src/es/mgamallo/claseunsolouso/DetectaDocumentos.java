package es.mgamallo.claseunsolouso;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class DetectaDocumentos {

	public final String DIRECTORIO_FIRMADO_INGRESOS = "C:/02 Area Pruebas/PruebaFirmadosXedoc/";
	public final String ORDENES = "Consentimento";
	public final String EVOLUTIVOS = "Interconsulta";
	public final String TSNU = "Evolutivo";
	
	public final String DIRECTORIO_PADRE_DESTINO = "C:/02 Area Pruebas/Carpetas para Firmado/";
	
	public DetectaDocumentos(){
		File directorioPadre = new File(DIRECTORIO_FIRMADO_INGRESOS);

		if(directorioPadre.exists()){
			File[] directorios = directorioPadre.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					return pathname.isDirectory();
				}
			});
			
			if(directorios != null){
				System.out.println(directorios.length);
				for(int i=0;i<directorios.length;i++){
					System.out.println(directorios[i].getName());
					
					File f = new File(directorios[i].getAbsolutePath());
					if(f.exists()){
						File pdfs[] = f.listFiles(new FilenameFilter() {
							
							@Override
							public boolean accept(File dir, String name) {
								// TODO Auto-generated method stub
								if(name.contains(ORDENES) || name.contains(EVOLUTIVOS) || name.contains(TSNU)){
									return true;
								}
								return false;
							}
						});
						
						if(pdfs != null){
							System.out.println("Tamaño carpeta " + pdfs.length);
							
							String nombreCarpeta = directorios[i].getName();
							String carpetaFinal = DIRECTORIO_PADRE_DESTINO + nombreCarpeta;
							
							File carpeta = new File(carpetaFinal);
							carpeta.mkdirs();
							
							for(int j=0;j<pdfs.length;j++){
								
								String rutaFinal = carpetaFinal;
								rutaFinal += ("/" + pdfs[j].getName());
								
								System.out.println(pdfs[j].getName());
								System.out.println(rutaFinal);
								
							 	System.out.println(pdfs[j].renameTo(new File(rutaFinal)));
								
							}
						}
					}
				}
			}
		}
		else{
			directorioPadre.mkdirs();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new DetectaDocumentos();

	}

}
