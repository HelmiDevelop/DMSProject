/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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

    public JaxbConverter( Class clazz) {
        try {
            context = JAXBContext.newInstance(clazz);
        } catch (JAXBException e) {
            Logger.getLogger(JaxbConverter.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * marshall
     * @param data : array of bytes
     * @return 
     * @throws javax.xml.stream.XMLStreamException
     * @throws javax.xml.bind.JAXBException
     * */
    public T marshal(byte[] data) throws XMLStreamException, JAXBException {

        XMLInputFactory fac = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;

        if (unmarshaller == null) {
            unmarshaller = context.createUnmarshaller();
        }
        try {
            reader = fac.createXMLStreamReader(new ByteArrayInputStream(data), CHARSET);
            Object obj = unmarshaller.unmarshal(reader);
            if (obj != null ) {
                T t = (T) obj;
                return t;
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }

    /**
     * marshall an input stream
     * @param data : an Input stream
     * @return T
     * @throws javax.xml.stream.XMLStreamException
     * @throws javax.xml.bind.JAXBException
     * @throws java.sql.SQLException
     * */
    public T marshal(InputStream data) throws XMLStreamException, JAXBException, SQLException {

        XMLInputFactory fac = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;

        if (unmarshaller == null) {
            unmarshaller = context.createUnmarshaller();
        }
        try {
            reader = fac.createXMLStreamReader(data, CHARSET);
            Object obj = unmarshaller.unmarshal(reader);
            if (obj != null) {
                T t = (T) obj;
                return t;
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }

    /**
     * unmarshal an object and return a byte array
     *
     * @param obj
     * @return 
     * @throws javax.xml.stream.XMLStreamException
     * @throws javax.xml.bind.JAXBException */
    public byte[] unmarshal(T obj) throws XMLStreamException, JAXBException {

        if(context == null){
            context = JAXBContext.newInstance(obj.getClass());
        }
        if (marshaller == null) {
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, CHARSET);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(obj, baos);

        return baos.toByteArray();
    }
    
}
