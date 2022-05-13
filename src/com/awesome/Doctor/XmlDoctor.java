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

public class XmlDoctor {

    private static Scanner scan = new Scanner(System.in);
    public static void WriteXmlDoctor(ArrayList<Doctor> alldoctors, String filepath){
        try{
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element rootElement = document.createElement("users");      //creating the class node (USERS) and append it to the root.
            document.appendChild(rootElement);

            for(Doctor doctor: alldoctors){
                Element doctorElement = document.createElement("doctor");
                rootElement.appendChild(doctorElement);

                Element nameElement = document.createElement("name");
                doctorElement.appendChild(nameElement);

                createChildElement(document,nameElement, "first", doctor.getFirstName());
                createChildElement(document,nameElement, "last", doctor.getLastName());
                createChildElement(document,doctorElement, "address", doctor.getAddress());
                createChildElement(document,doctorElement, "age", String.valueOf((doctor.getAge())));
                createChildElement(document,doctorElement, "phoneNumber", String.valueOf(doctor.getPhoneNumber()));
                createChildElement(document,doctorElement, "doctorID", String.valueOf(doctor.getDoctorID()));


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

    public static ArrayList<Doctor> readDoctortXml(String filepath){
        ArrayList<Doctor> allDoctors = new ArrayList<>();
        Doctor doctor;
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(filepath));

            // It gets the list of all patients nodes
            NodeList root = document.getElementsByTagName("doctor");

            for(int i=0; i< root.getLength(); i++){
                Node node = root.item(i);           // get all the child nodes from this node (will get a node list)
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node; // Here just casting the node into Element, because node does not have

                    String firstname = ((Element) element.getElementsByTagName("name").item(0)).getElementsByTagName("first").item(0).getTextContent();
                    String lastname = ((Element) element.getElementsByTagName("name").item(0)).getElementsByTagName("last").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    int phoneNumber = Integer.parseInt(element.getElementsByTagName("phoneNumber").item(0).getTextContent());
                    int doctorID = Integer.parseInt(element.getElementsByTagName("doctorID").item(0).getTextContent());

                    //print out patient
                    System.out.println(i+1);
                    System.out.println("First name: " + firstname);
                    System.out.println("Last name: " + lastname);
                    System.out.println("Address: " + address);
                    System.out.println("Age:  " + age);
                    System.out.println("Phone Number: " + phoneNumber);
                    System.out.println("Doctor ID: " + doctorID);
                    System.out.println(" ");

                    doctor = new Doctor(firstname,lastname,address,age,doctorID,phoneNumber);
                    allDoctors.add(doctor);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return allDoctors;

    }

    public static ArrayList<Doctor> justReadDoctortXml(String filepath){
        ArrayList<Doctor> allDoctors = new ArrayList<>();
        Doctor doctor;
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(filepath));

            // It gets the list of all doctor nodes
            NodeList root = document.getElementsByTagName("doctor");

            for(int i=0; i< root.getLength(); i++){
                Node node = root.item(i);           // get all the child nodes from this node (will get a node list)
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node; // Here just casting the node into Element, because node does not have

                    String firstname = ((Element) element.getElementsByTagName("name").item(0)).getElementsByTagName("first").item(0).getTextContent();
                    String lastname = ((Element) element.getElementsByTagName("name").item(0)).getElementsByTagName("last").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    int phoneNumber = Integer.parseInt(element.getElementsByTagName("phoneNumber").item(0).getTextContent());
                    int doctorID = Integer.parseInt(element.getElementsByTagName("doctorID").item(0).getTextContent());


                    doctor = new Doctor(firstname,lastname,address,age,doctorID,phoneNumber);
                    allDoctors.add(doctor);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return allDoctors;

    }

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}
