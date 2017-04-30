package model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * Classe InfoXml, obtem o mapeamento do nodo xml 'info' para um objeto java.
 * <info> <ip>242.157.159.46</ip> <port>2581</port> <data>lorem ipsum</data>
 * </info>
 */
@XmlRootElement(name = "info")
@XmlAccessorType(XmlAccessType.FIELD)
public class InfoXml {
	@XmlElement(name = "ip")
	private String ip;
	@XmlElement(name = "data")
	private String data;
	@XmlElement(name = "port")
	private String port;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
