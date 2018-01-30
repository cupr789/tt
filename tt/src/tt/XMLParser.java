package tt;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	public static Logger log = Logger.getLogger(XMLParser.class);
	
	public static Object newInstance(String cn) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class c = Class.forName(cn);
		return c.newInstance();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {
		
		InputStream inputStream = null;
		try {
			inputStream = XMLParser.class.getResourceAsStream("/beans.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(inputStream);
			//System.out.println(document.getChildNodes());
			document.getDocumentElement().normalize();
			NodeList nl = document.getElementsByTagName("bean");
			for(int i=0; i<nl.getLength(); i++) {
				Node n = nl.item(i);
				Element e =(Element)n;
				//log.debug(e.getAttribute("id"));
				/*System.out.println(e.getAttribute("id"));
				System.out.println(e.getAttribute("class"));*/
				if(e.getAttribute("id").equals("hw1")) {					
					HelloWorld1 hw1 = (HelloWorld1)newInstance(e.getAttribute("class"));
					hw1.printSome();
				}
				if(e.getAttribute("id").equals("hw2")) {					
					HelloWorld2 hw2 = (HelloWorld2)newInstance(e.getAttribute("class"));
					hw2.printSome();
				}
				if(e.getAttribute("id").equals("hw3")) {					
					HelloWorld3 hw3 = (HelloWorld3)newInstance(e.getAttribute("class"));
					hw3.printSome();
				}
				//HelloWorld2 hw2 = (HelloWorld2)newInstance(e.getAttribute("class"));
			}

		} catch (ParserConfigurationException | IOException | SAXException e ) {
			e.printStackTrace();
		}
		
	}
}
