/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Main;

import Entity.Artist;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Administrator
 */
public abstract class XMLManager {

    /**
     * This will export ALL artist shows
     */
    public static void ExportXML(){
        List<Artist> arrl = new ArrayList<Artist>();
        ResultSet qry = iMuzaMusic.getDB().query("SELECT ArtistID from Artists");
        
        try {
            while(qry.next()){
                arrl.add(new Artist(qry.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ExportArtistsXML(arrl.toArray(new Artist[arrl.size()]));
        ExportSessionXML(arrl.toArray(new Artist[arrl.size()]));
    }
    public static void ExportXML(Artist art){
        Artist[] tmp = new Artist[1];
        tmp[0] = art;
        ExportSessionXML(tmp);
    }
    /**
     * On a given artist it will export to xml the show dates of the artist
     *
     * @param art
     */
    public static void ExportArtistsXML(Artist[] arts) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder
                    = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("HandsInAirXML");
            doc.appendChild(rootElement);

            //  supercars element
            Element time = doc.createElement("DateCreated");
            rootElement.appendChild(time);
            time.appendChild(doc.createTextNode((new Date()).toString()));

            for (Artist art : arts) {
                Element artistXML = doc.createElement("Artist");
                rootElement.appendChild(artistXML);
                
                // setting attribute to element
                Attr attr = doc.createAttribute("ID");
                attr.setValue(art.getID());
                artistXML.setAttributeNode(attr);

                //Run the query and get session dates
                ResultSet qry = iMuzaMusic.getDB().query("SELECT * FROM Artists where ArtistID=\"" + art.getID() + "\"");
                while (qry.next()) {
                    Integer columnCount = qry.getMetaData().getColumnCount();
                    for(int i=1; i<=columnCount;i++){
                        String columnName = qry.getMetaData().getColumnName(i);
                        iMuzaMusic.log("Column: "+columnName);
                        iMuzaMusic.log("Data Type: "+qry.getMetaData().getColumnType(i));
                        String columnData = "";
                        if(qry.getObject(i)!=null)
                            columnData = qry.getObject(i).toString();
                        
                        Element tmp = doc.createElement(columnName);
                        tmp.appendChild(doc.createTextNode(columnData));
                        artistXML.appendChild(tmp);
                    }
                   
                }
            }
            // carname element

            // write the content into xml file
            TransformerFactory transformerFactory
                    = TransformerFactory.newInstance();
            Transformer transformer
                    = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            PrintStream prntstrm = new PrintStream(new File("HIA-Artist-Data.xml"));
            iMuzaMusic.log("Aquiring stream result");
            StreamResult result
                    = new StreamResult(prntstrm);
            iMuzaMusic.log("Transforming result");
            transformer.transform(source, result);
     iMuzaMusic.log("Successfully exported HIA-Artist-Data.xml file for iRecord System.");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
     * On a given artist it will export to xml the show dates of the artist
     *
     * @param art
     */
    public static void ExportSessionXML(Artist[] arts) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder
                    = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("HandsInAirXML");
            doc.appendChild(rootElement);

            //  supercars element
            Element time = doc.createElement("DateCreated");
            rootElement.appendChild(time);
            time.appendChild(doc.createTextNode((new Date()).toString()));

            for (Artist art : arts) {
                Element artistXML = doc.createElement("Artist");
                rootElement.appendChild(artistXML);
                
                // setting attribute to element
                Attr attr = doc.createAttribute("ID");
                attr.setValue(art.getID());
                artistXML.setAttributeNode(attr);

                //Run the query and get session dates
                ResultSet qry = iMuzaMusic.getDB().query("SELECT pStartDate FROM Shows where iMainArtist=\"" + art.getID() + "\"");
                while (qry.next()) {
                    Element session = doc.createElement("Session");
                    session.appendChild(
                            doc.createTextNode(qry.getDate("pStartDate").toGMTString()));

                    session.setAttribute("timestamp", String.valueOf(qry.getDate("pStartDate").getTime()));
                    artistXML.appendChild(session);
                }
            }
            // carname element

            // write the content into xml file
            TransformerFactory transformerFactory
                    = TransformerFactory.newInstance();
            Transformer transformer
                    = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            PrintStream prntstrm = new PrintStream(new File("HIA-Artist-Sessions.xml"));
            iMuzaMusic.log("Aquiring stream result");
            StreamResult result
                    = new StreamResult(prntstrm);
            iMuzaMusic.log("Transforming result");
            transformer.transform(source, result);
     iMuzaMusic.log("Successfully exported HIA-Artist-Sessions.xml file for iRecord System.");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
