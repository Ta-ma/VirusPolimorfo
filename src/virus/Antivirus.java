package virus;

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Antivirus
{
	int[] partes;
	int m;
	LinkedList<Integer> resultados;
	
	public Antivirus (File archivo) throws IOException
	{
		resultados = new LinkedList<Integer>();
		Scanner sc = new Scanner(archivo);
		partes = new int[sc.nextInt()];
		m = sc.nextInt();
		
		for (int i = 0; i < partes.length; i++)
			partes[i] = sc.nextInt();
		
		sc.close();
	}
	
	public void buscarVirus ()
	{
		// comienzo a buscar permutaciones
		for (int i = 0; i < partes.length; i++)
		{
			// pregunto si el número es negativo, de ser así lo ignoro
			// esto es lo que no entiendo del problema, por qué hay números negativos?
			// un número negativo nunca va a poder formar parte de una permutación de
			// 1 a M porque M siempre va a ser positivo.
			if (partes[i] > 0)
			{
				// uso la posición sub 0 como contador y el resto para
				// verificar si estoy encontrando los elementos de la permutación.
				int[] vecPermutaciones = new int[m + 1];
				
				// me sirve para cortar el while si encuentra que hay elementos repetidos o 
				// elementos que no formen parte de la permutación.
				boolean permutaciónTrucha = false;
				
				// tal vez la parte menos eficiente del algoritmo es esta
				// tener que rearmar el vector cada vez que analizo una posición distinta.
				for (int j = 1; j <= m; j++)
					vecPermutaciones[j] = j;
				
				int k = i;
				// si vecPermutaciones[0] es igual a m entonces encontré todas las partes de la
				// permutación por lo que significa que el virus puede estar ahí.
				while (vecPermutaciones[0] != m && permutaciónTrucha == false)
				{
					if (partes[k] > 0 && partes[k] <= m && vecPermutaciones[partes[k]] != 0)
					{
						vecPermutaciones[partes[k]] = 0;
						vecPermutaciones[0]++;
						k++;
					}
					else
						permutaciónTrucha = true;
				}
				
				if (vecPermutaciones[0] == m && permutaciónTrucha == false)
					resultados.add(i + 1);
			}
		}
	}
	
	public void mostrarResultados ()
	{
		int tam = resultados.size();
		System.out.println(tam);
		
		for (int i = 0; i < tam; i++)
		{
			System.out.print(resultados.get(i) + " ");
		}
	}
	
	public void guardarResultados (File destino) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(destino));
		int tam = resultados.size();
		out.println(tam);
		
		for (int i = 0; i < tam; i++)
		{
			out.print(resultados.get(i) + " ");
		}
		
		
		out.close();
	}
}
