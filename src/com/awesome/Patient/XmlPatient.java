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



public class XmlPatient {
    private static Scanner scan = new Scanner(System.in);


    public static void WriteXmlPatient(ArrayList<Patient>allPatients, String filepath){
        try{
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element rootElement = document.createElement("users");      //creating the class node (USERS) and append it to the root.
            document.appendChild(rootElement);

            for(Patient patients: allPatients){
                Element patientElement = document.createElement("patient");
                rootElement.appendChild(patientElement);            //Patient node is added to users node

                Element nameElement = document.createElement("name");
                patientElement.appendChild(nameElement);

                createChildElement(document,nameElement, "first", patients.getFirstName());
                createChildElement(document,nameElement, "last", patients.getLastName());

                createChildElement(document,patientElement, "age", String.valueOf((patients.getAge())));
                createChildElement(document,patientElement, "phoneNumber", String.valueOf(patients.getPhoneNumber()));
                createChildElement(document,patientElement, "address", patients.getAddress());
                //createChildElement(document,patientElement, "bloodType", patients.getBloodType().toString());

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static ArrayList<Patient> readPatientXml(String filepath){
        ArrayList<Patient> allPatients = new ArrayList<>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(filepath));

            // It gets the list of all patients nodes
            NodeList root = document.getElementsByTagName("patient");
            for(int i=0; i< root.getLength(); i++){
                Node node = root.item(i);           // get all the child nodes from this node (will get a node list)
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node; // Here just casting the node into Element, because node does not have

                    String firstname = ((Element) element.getElementsByTagName("name").item(0)).getElementsByTagName("first").item(0).getTextContent();
                    String lastname = ((Element) element.getElementsByTagName("name").item(0)).getElementsByTagName("last").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    int phoneNumber = Integer.parseInt(element.getElementsByTagName("phoneNumber").item(0).getTextContent());

                    //print out patient
                    System.out.println(i+1);
                    System.out.println("First name: " + firstname);
                    System.out.println("Last name: " + lastname);
                    System.out.println("Address: " + address);
                    System.out.println("Age:  " + age);
                    System.out.println("Phone Number: " + phoneNumber);
                    System.out.println(" ");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return allPatients;

    }

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

}
