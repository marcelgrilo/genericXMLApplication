package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class ServerFileReader {
	private String server = "localhost";
	private int port = 21;
	private String user = "user";
	private String pass = "pass";

	public ServerFileReader(String server, int port, String user, String pass) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.pass = pass;
	}

	public List<File> SaveCompactedXML() {
		List<File> loadedFiles = new ArrayList<File>();
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			// login
			if (!ftpClient.login("username", "password")) {
				ftpClient.logout();
			}
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			}
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			FTPFile[] ftpFiles = ftpClient.listFiles();

			if (ftpFiles != null && ftpFiles.length > 0) {
				// loop pelos arquivos do servidor.
				for (FTPFile file : ftpFiles) {
					if (!file.isFile()) {
						continue;
					}

					// System.out.println("File is " + file.getName());
					OutputStream output;
					output = new FileOutputStream("FtpOtputFiles" + File.separator + file.getName());
					// faz o download do arquivo e salva em FtpOtputFiles
					ftpClient.retrieveFile(file.getName(), output);
					output.close();
					loadedFiles.add(new File("FtpOtputFiles" + File.separator + file.getName()));
				}
			}
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return loadedFiles;
	}

}
