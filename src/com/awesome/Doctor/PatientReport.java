package com.awesome.Doctor;

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

public class PatientReport {
    String entryDate;
    String medicalReport;

    private static Scanner scan = new Scanner(System.in);

    public PatientReport(String entryDate, String medicalReport) {
        this.entryDate = entryDate;
        this.medicalReport = medicalReport;
    }

    public static ArrayList<PatientReport> readMedicalReport(String filepath){
            ArrayList<PatientReport> allMedicalReports = new ArrayList<>();
            PatientReport medicalReports;

            try{
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(new FileInputStream(filepath));

                // It gets the list of all medicalReport nodes
                NodeList root = document.getElementsByTagName("entry");

                for(int i=0; i< root.getLength(); i++){
                    Node node = root.item(i);           // get all the child nodes from this node (will get a node list)
                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element) node; // Here just casting the node into Element, because node does not have


                        //try
                        String date = (element.getElementsByTagName("date").item(0).getTextContent());
                        String entry = (element.getElementsByTagName("report").item(0).getTextContent());

                        System.out.println(i+1);
                        System.out.println("Entry date " + date);
                        System.out.println("    " + entry);

                        medicalReports = new PatientReport(date,entry);
                        allMedicalReports.add(medicalReports);

                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return allMedicalReports;
    }


    public static void saveMedicalReportToXml(ArrayList<PatientReport>report, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element rootElement = document.createElement("medicalReport");      //Root node Medical report
            document.appendChild(rootElement);

            for (PatientReport entries : report) {
                Element entryElement = document.createElement("entry");
                rootElement.appendChild(entryElement);

                createChildElement(document,entryElement,"date", entries.getMedialEntryDate());
                createChildElement(document,entryElement,"report", entries.getMedicalReport());

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

    public static ArrayList<PatientReport> readPatientDiary(String filepath){
        ArrayList<PatientReport> patientDiary = new ArrayList<>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(filepath));

            // It gets the list of all Entries
            NodeList root = document.getElementsByTagName("patientDiary");

            for(int i=0; i< root.getLength(); i++){
                Node node = root.item(i);           // get all the child nodes from this node (will get a node list)
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node; // Here just casting the node into Element, because node does not have

                    String date = (element.getElementsByTagName("date").item(0).getTextContent());
                    String message = (element.getElementsByTagName("message").item(0).getTextContent());

                    //print out patient
                    System.out.println(i+1);
                    System.out.println("Entry date " + date);
                    System.out.println("    " + message);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return patientDiary;

    }

    public static PatientReport writeMedicalReport(){
        String askDate;
        String diaryEntry;

        System.out.println("Please enter the date: ");
        askDate = scan.nextLine();
        System.out.println("Write report: ");
        diaryEntry = scan.nextLine();
        System.out.println("");

        PatientReport report = new PatientReport(askDate,diaryEntry);
        System.out.println("You have now successfully added a new entry to your diary");

        return report;
    };

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

    public String getMedialEntryDate() {
        return entryDate;
    }

    public void setMedicalReportDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getMedicalReport() {
        return medicalReport;
    }

    public void setMedicalReport(String diaryEntry) {
        this.medicalReport = diaryEntry;
    }
}
