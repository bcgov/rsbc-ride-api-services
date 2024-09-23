//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.02 at 09:47:31 PM PDT 
//

/*
 * Use the following command to regen the JAVA code from RSI_InformationManager.xsd
 * cd c:\Users\177040\RSI\GitHub\jh-etk\jh-etk-common\src\main\java
 * xjc -p bcgov.jh.etk.jhetkcommon.model.icbc.evt.mcot C:\Users\177040\RSI\GitHub\jh-etk\jh-etk-common\src\main\resources\schema\ICBC\RSI_InformationManager.xsd
 * 
 */

package bcgov.jh.etk.jhetkcommon.model.icbc.evt.mcot;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createContraventionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createContraventionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MREMinorVersion" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://icbc.net/RSI_InformationManager}string_FreeFormText">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SchemaStyleVersion" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://icbc.net/RSI_InformationManager}string_FreeFormText">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Submission" type="{http://icbc.net/RSI_InformationManager}SubmissionType"/>
 *         &lt;element name="Party" type="{http://icbc.net/RSI_InformationManager}PartyType"/>
 *         &lt;element name="Violation" type="{http://icbc.net/RSI_InformationManager}ViolationType"/>
 *         &lt;element name="Vehicle" type="{http://icbc.net/RSI_InformationManager}VehicleType" minOccurs="0"/>
 *         &lt;element name="Enforcement" type="{http://icbc.net/RSI_InformationManager}EnforcementType"/>
 *         &lt;element name="CertificateOfService" type="{http://icbc.net/RSI_InformationManager}CertificateOfServiceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createContraventionRequest", propOrder = {
    "mreMinorVersion",
    "schemaStyleVersion",
    "submission",
    "party",
    "violation",
    "vehicle",
    "enforcement",
    "certificateOfService"
})
public class CreateContraventionRequest {

    @XmlElementRef(name = "MREMinorVersion", type = JAXBElement.class, required = false)
    protected JAXBElement<String> mreMinorVersion;
    @XmlElementRef(name = "SchemaStyleVersion", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schemaStyleVersion;
    @XmlElement(name = "Submission", required = true)
    protected SubmissionType submission;
    @XmlElement(name = "Party", required = true)
    protected PartyType party;
    @XmlElement(name = "Violation", required = true)
    protected ViolationType violation;
    @XmlElementRef(name = "Vehicle", type = JAXBElement.class, required = false)
    protected JAXBElement<VehicleType> vehicle;
    @XmlElement(name = "Enforcement", required = true)
    protected EnforcementType enforcement;
    @XmlElementRef(name = "CertificateOfService", type = JAXBElement.class, required = false)
    protected JAXBElement<CertificateOfServiceType> certificateOfService;

    /**
     * Gets the value of the mreMinorVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMREMinorVersion() {
        return mreMinorVersion;
    }

    /**
     * Sets the value of the mreMinorVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMREMinorVersion(JAXBElement<String> value) {
        this.mreMinorVersion = value;
    }

    /**
     * Gets the value of the schemaStyleVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchemaStyleVersion() {
        return schemaStyleVersion;
    }

    /**
     * Sets the value of the schemaStyleVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchemaStyleVersion(JAXBElement<String> value) {
        this.schemaStyleVersion = value;
    }

    /**
     * Gets the value of the submission property.
     * 
     * @return
     *     possible object is
     *     {@link SubmissionType }
     *     
     */
    public SubmissionType getSubmission() {
        return submission;
    }

    /**
     * Sets the value of the submission property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmissionType }
     *     
     */
    public void setSubmission(SubmissionType value) {
        this.submission = value;
    }

    /**
     * Gets the value of the party property.
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getParty() {
        return party;
    }

    /**
     * Sets the value of the party property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setParty(PartyType value) {
        this.party = value;
    }

    /**
     * Gets the value of the violation property.
     * 
     * @return
     *     possible object is
     *     {@link ViolationType }
     *     
     */
    public ViolationType getViolation() {
        return violation;
    }

    /**
     * Sets the value of the violation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViolationType }
     *     
     */
    public void setViolation(ViolationType value) {
        this.violation = value;
    }

    /**
     * Gets the value of the vehicle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link VehicleType }{@code >}
     *     
     */
    public JAXBElement<VehicleType> getVehicle() {
        return vehicle;
    }

    /**
     * Sets the value of the vehicle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link VehicleType }{@code >}
     *     
     */
    public void setVehicle(JAXBElement<VehicleType> value) {
        this.vehicle = value;
    }

    /**
     * Gets the value of the enforcement property.
     * 
     * @return
     *     possible object is
     *     {@link EnforcementType }
     *     
     */
    public EnforcementType getEnforcement() {
        return enforcement;
    }

    /**
     * Sets the value of the enforcement property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnforcementType }
     *     
     */
    public void setEnforcement(EnforcementType value) {
        this.enforcement = value;
    }

    /**
     * Gets the value of the certificateOfService property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CertificateOfServiceType }{@code >}
     *     
     */
    public JAXBElement<CertificateOfServiceType> getCertificateOfService() {
        return certificateOfService;
    }

    /**
     * Sets the value of the certificateOfService property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CertificateOfServiceType }{@code >}
     *     
     */
    public void setCertificateOfService(JAXBElement<CertificateOfServiceType> value) {
        this.certificateOfService = value;
    }

}
