package services.Interfaces;

import java.io.File;

import services.exceptions.UnpackException;

/**
 * @author marcel
 * 
 *         Interface para descompactacao dos arquivos xml. Cada implementacao
 *         desta interface aborda um tipo de descompactacao de arquivos.
 */
public interface IUnpack {
	public File Unpack(File compressedFile) throws UnpackException;
}
