package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import services.Interfaces.IUnpack;
import services.exceptions.UnpackException;

/**
 * @author marcel
 * 
 *         Implementacao de IUnpack para descompactar arquivos xml do tipo .zip.
 */
public class UnzipService implements IUnpack 
{
	@Override
	public File Unpack(File compressedFile) throws UnpackException 
	{
		ArrayList<File> files = new ArrayList<File>();
		byte[] buffer = new byte[1024];
		try 
		{
			if (!compressedFile.getName().endsWith(".zip")) 
			{
				throw new UnpackException("file is not a zip");
			}
			// conteudo do arquivo zip.
			ZipInputStream zis = new ZipInputStream(new FileInputStream(compressedFile));
			ZipEntry ze = zis.getNextEntry();

			// percorre todas as entradas do zip. espera-se que se tenha apenas
			// um arquivo do tipo xml.
			while (ze != null)
			{
				String fileName = ze.getName();
				if (!fileName.endsWith(".xml")) 
				{
					continue;
				}

				File newFile = new File("UnpackedFiles" + File.separator + fileName);

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) 
				{
					fos.write(buffer, 0, len);
				}

				fos.close();
				files.add(newFile);
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}

		if (files.size() != 1) 
		{
			throw new UnpackException("Compacted Files should have ONE and only ONE XML file");
		}

		return files.get(0);
	}

}