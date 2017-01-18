//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.01.17 um 08:12:41 PM CET 
//


package com.helmidev.database.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "url",
    "dbname",
    "initdb",
    "usernane",
    "password",
    "jdbcdriver"
})
@XmlRootElement(name = "PersitenceUnit")
public class PersitenceUnit {

    @XmlElement(name = "URL", required = true)
    protected String url;
    @XmlElement(name = "DBNAME", required = true)
    private String dbName;
    @XmlElement(name = "INITDB", required = true)
    private boolean initDb;
    @XmlElement(name = "USERNANE", required = true)
    protected String usernane;
    @XmlElement(name = "PASSWORD", required = true)
    protected String password;
    @XmlElement(name = "JDBCDRIVER", required = true)
    protected String jdbcdriver;

    /**
     * Ruft den Wert der url-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURL() {
        return url;
    }

    /**
     * Legt den Wert der url-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * Ruft den Wert der usernane-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERNANE() {
        return usernane;
    }

    /**
     * Legt den Wert der usernane-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERNANE(String value) {
        this.usernane = value;
    }

    /**
     * Ruft den Wert der password-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPASSWORD() {
        return password;
    }

    /**
     * Legt den Wert der password-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPASSWORD(String value) {
        this.password = value;
    }

    /**
     * Ruft den Wert der jdbcdriver-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJDBCDRIVER() {
        return jdbcdriver;
    }

    /**
     * Legt den Wert der jdbcdriver-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJDBCDRIVER(String value) {
        this.jdbcdriver = value;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public boolean isInitDb() {
        return initDb;
    }

    public void setInitDb(boolean initDb) {
        this.initDb = initDb;
    }
    
    

}
