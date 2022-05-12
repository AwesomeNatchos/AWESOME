package com.awesome.Patient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Diary {
    String entryDate;
    String diaryEntry;

    private static Scanner scan = new Scanner(System.in);

    public Diary(String entryDate, String diaryEntry) {
        this.entryDate = entryDate;
        this.diaryEntry = diaryEntry;
    }

    public static void saveDiaryToXml(ArrayList<Diary>patiensDiary, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element rootElement = document.createElement("patientDiary");      //Root node patienDiary
            document.appendChild(rootElement);

            for (Diary entries1 : patiensDiary) {
                Element entryElement = document.createElement("entry");
                rootElement.appendChild(entryElement);

                createChildElement(document,entryElement,"date", entries1.getEntryDate());
                createChildElement(document,entryElement,"message", entries1.getDiaryEntry());
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("");
    }

    public static ArrayList<Diary> readPatientDiary(String filepath){
        ArrayList<Diary> patientDiary = new ArrayList<>();
        Diary diary;
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(filepath));

            // It gets the list of all Entries
            NodeList root = document.getElementsByTagName("entry");

            for(int i=0; i<root.getLength(); i++){
                Node node1 = root.item(i);           // get all the child nodes from this node (will get a node list)
                if(node1.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node1; // Here just casting the node into Element, because node does not have

                    String date = (element.getElementsByTagName("date").item(0).getTextContent());
                    String message = (element.getElementsByTagName("message").item(0).getTextContent());

                    //print out patient
                    System.out.println(i+1);
                    System.out.println("Entry date " + date);
                    System.out.println("    " + message);

                    diary = new Diary(date,message);
                    patientDiary.add(diary);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return patientDiary;
    }

    public static void main(String[] args) {

        ArrayList<Diary> patientDiary = new ArrayList<>();
        Diary diary;
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream("src/com/awesome/Patient/patientDiary.xml"));

            // It gets the list of all Entries
            NodeList root = document.getElementsByTagName("entry");

            for(int j=0; j<root.getLength(); j++){
                Node node1 = root.item(j);           // get all the child nodes from this node (will get a node list)
                if(node1.getNodeType() == Node.ELEMENT_NODE){
                    Element element1 = (Element) node1; // Here just casting the node into Element, because node does not have

                    String date = (element1.getElementsByTagName("date").item(0).getTextContent());
                    String message = (element1.getElementsByTagName("message").item(0).getTextContent());

                    //print out patient
                    System.out.println(j+1);
                    System.out.println("Entry date " + date);
                    System.out.println("    " + message);
                    System.out.println(node1.getNodeType());

                    diary = new Diary(date,message);
                    patientDiary.add(diary);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Diary writeDiary(){
        String askDate;
        String diaryEntry;

        System.out.println("Please enter the date: ");
        askDate = scan.nextLine();
        System.out.println("You can now write your diary entry: ");
        diaryEntry = scan.nextLine();
        System.out.println("");

        Diary diary = new Diary(askDate,diaryEntry);
        System.out.println("You have now successfully added a new entry to your diary");

        return diary;
    };

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getDiaryEntry() {
        return diaryEntry;
    }

    public void setDiaryEntry(String diaryEntry) {
        this.diaryEntry = diaryEntry;
    }
}
