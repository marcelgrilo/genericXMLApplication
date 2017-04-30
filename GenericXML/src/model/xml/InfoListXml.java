package model.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * Classe InfoListXml, obtem o mapeamento do nodo principal 'infoList' para um
 * objeto java.
 * 
 * <infoList> <info> <ip>242.157.159.46</ip> <port>2581</port> <data>lorem
 * ipsum</data> </info> <info> <ip>242.157.159.46</ip> <port>2581</port>
 * <data>lorem ipsum</data> </info> . . . </infoList>
 */
@XmlRootElement(name = "infoList")
@XmlAccessorType(XmlAccessType.FIELD)
public class InfoListXml {
	@XmlElement(name = "info")
	protected List<InfoXml> infoXmls;

	public List<InfoXml> getInfoXmls() {
		return infoXmls;
	}

	public void setInfoXmls(List<InfoXml> infoXmls) {
		this.infoXmls = infoXmls;
	}

}