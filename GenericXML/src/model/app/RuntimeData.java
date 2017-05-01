package model.app;

public class RuntimeData {	
	private String  cores;
	private String threadData;
	private String memoryData;
	private String filesProcessedData;;
	private String filesFailedData;
	
	
	
	public String getCores() {
		return cores;
	}
	public void setCores(String cores) {
		this.cores = cores;
	}
	public String getThreadData() {
		return threadData;
	}
	public void setThreadData(String threadData) {
		this.threadData = threadData;
	}
	public String getMemoryData() {
		return memoryData;
	}
	public void setMemoryData(String memoryData) {
		this.memoryData = memoryData;
	}
	public String getFilesProcessedData() {
		return filesProcessedData;
	}
	public void setFilesProcessedData(String filesProcessedData) {
		this.filesProcessedData = filesProcessedData;
	}
	public String getFilesFailedData() {
		return filesFailedData;
	}
	public void setFilesFailedData(String filesFailedData) {
		this.filesFailedData = filesFailedData;
	}
	
	
	
}
