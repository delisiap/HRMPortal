package com.hrm.rest.io;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;

public class XMLReader
{
	private String fileName;
	private JAXBContext jaxbContext;

	public XMLReader(String fileName, Class<?> cls) throws Exception
	{
		this.fileName = fileName;
		this.jaxbContext = JAXBContext.newInstance(cls);
	}

	public Object readXML() throws Exception
	{
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream xmlInputStream = classLoader.getResourceAsStream(fileName);

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(xmlInputStream);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		xmlStreamReader = new StreamReaderDelegate(xmlStreamReader);
		return unmarshaller.unmarshal(xmlStreamReader);
	}
}