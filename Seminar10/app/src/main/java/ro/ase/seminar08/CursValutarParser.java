package ro.ase.seminar08;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CursValutarParser {

    public static CursValutar parsareXml(String xml) {
        return parsare(xml);
    }

    private static CursValutar parsare(String xml) {
        CursValutar cursValutar = null;
        String eur = "", usd = "";
        try {
            ByteArrayInputStream input = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDoc = db.parse(input);
            domDoc.getDocumentElement().normalize();
            cursValutar = new CursValutar();
            Node cube = getNodeByName("Cube", domDoc.getDocumentElement());
            if (cube != null) {
                String data = getAttributeValue(cube, "date");
                NodeList childNodes = cube.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node node = childNodes.item(i);
                    String attributeValue = getAttributeValue(node, "currency");
                    if (attributeValue.equals("EUR"))
                        eur = node.getTextContent();
                    if (attributeValue.equals("USD"))
                        usd = node.getTextContent();

                }
                cursValutar = new CursValutar(data, eur, usd);
            } else
                Log.e("eroare", "Eroare parsare xml curs BNR!");


        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Log.e("exception", Objects.requireNonNull(ex.getMessage()));
        }
        return cursValutar;
    }

    public static String getAttributeValue(Node node, String value) {
        try {
            return ((Element) node).getAttribute(value);
        } catch (Exception e) {
            return "";
        }

    }

    public static Node getNodeByName(String nodeName, Node parentNode) {
        if (parentNode.getNodeName().equals(nodeName))
            return parentNode;
        NodeList childNodes = parentNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = getNodeByName(nodeName, childNodes.item(i));
            if (node != null)
                return node;
        }
        return null;
    }
}
