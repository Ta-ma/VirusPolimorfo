package virus;

import java.io.File;
import java.io.IOException;

public class Main
{
	
	public static void main (String[] args) throws IOException
	{
		File dir = new File(".//IN//");
		
		for (File archivo : dir.listFiles())
		{
			Antivirus antivirus = new Antivirus(archivo);
			antivirus.buscarVirus();
			antivirus.guardarResultados(new File(".//OUT//" + archivo.getName().replace(".in", ".out")));
		}
	}

}
