/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author mayel-1
 * @param <T>
 */
public class JaxbConverter<T> {
    
    private final String CHARSET = "UTF-8";
    private JAXBContext context = null;
    private Unmarshaller unmarshaller = null;
    private Marshaller marshaller = null;

    T object ;
    public JaxbConverter() {
        
    }

    public T readXML(Class clazz, String output) throws JAXBException{
        T resultObj = null ;
        JAXBContext jAXBContext = JAXBContext.newInstance(clazz.getPackage().getName());
        this.unmarshaller = jAXBContext.createUnmarshaller();
        File out = new File(output);
        if(out.exists()){
            try(InputStream stream = new FileInputStream(output)) {
                
                resultObj = (T)unmarshaller.unmarshal(stream);
                return resultObj;
            } catch (Exception e) {
                //TODO catch exception
            }
        }
        return resultObj;
    }
    
    public boolean writeToFile(T jaxbElement, String output) throws JAXBException, FileNotFoundException, IOException{
        boolean result;
        JAXBContext jAXBContext = JAXBContext.newInstance(jaxbElement.getClass().getPackage().getName());
        this.marshaller = jAXBContext.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File out = new File(output);
        if(!out.exists()){
            Path path = Paths.get(output);
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        try (OutputStream os = new FileOutputStream( output )) {
            marshaller.marshal(jaxbElement, os);
            result = true;
        }
        return result;
    }
    
}
