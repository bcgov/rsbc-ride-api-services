//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.02 at 09:47:31 PM PDT 
//


package bcgov.jh.etk.jhetkcommon.model.icbc.evt.mcot;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for IndividualPartyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndividualPartyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Surname" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://icbc.net/RSI_InformationManager}string_FreeFormText">
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="GivenName1" type="{http://icbc.net/RSI_InformationManager}string_GivenName" minOccurs="0"/>
 *         &lt;element name="GivenName2" type="{http://icbc.net/RSI_InformationManager}string_GivenName" minOccurs="0"/>
 *         &lt;element name="GivenName3" type="{http://icbc.net/RSI_InformationManager}string_GivenName" minOccurs="0"/>
 *         &lt;element name="Gender" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="1"/>
 *               &lt;enumeration value="M"/>
 *               &lt;enumeration value="F"/>
 *               &lt;enumeration value="U"/>
 *               &lt;enumeration value="X"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BirthDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="YoungPerson" type="{http://icbc.net/RSI_InformationManager}bool_y_n" minOccurs="0"/>
 *         &lt;element name="ChangeOfAddressFlag" type="{http://icbc.net/RSI_InformationManager}bool_y_n" minOccurs="0"/>
 *         &lt;element name="ResidenceAddress" type="{http://icbc.net/RSI_InformationManager}AddressType" minOccurs="0"/>
 *         &lt;element name="DriverLicense" type="{http://icbc.net/RSI_InformationManager}DriverLicenseType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualPartyType", propOrder = {
    "surname",
    "givenName1",
    "givenName2",
    "givenName3",
    "gender",
    "birthDate",
    "youngPerson",
    "changeOfAddressFlag",
    "residenceAddress",
    "driverLicense"
})
public class IndividualPartyType {

    @XmlElementRef(name = "Surname", type = JAXBElement.class, required = false)
    protected JAXBElement<String> surname;
    @XmlElementRef(name = "GivenName1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> givenName1;
    @XmlElementRef(name = "GivenName2", type = JAXBElement.class, required = false)
    protected JAXBElement<String> givenName2;
    @XmlElementRef(name = "GivenName3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> givenName3;
    @XmlElementRef(name = "Gender", type = JAXBElement.class, required = false)
    protected JAXBElement<String> gender;
    @XmlElementRef(name = "BirthDate", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> birthDate;
    @XmlElementRef(name = "YoungPerson", type = JAXBElement.class, required = false)
    protected JAXBElement<BoolYN> youngPerson;
    @XmlElementRef(name = "ChangeOfAddressFlag", type = JAXBElement.class, required = false)
    protected JAXBElement<BoolYN> changeOfAddressFlag;
    @XmlElementRef(name = "ResidenceAddress", type = JAXBElement.class, required = false)
    protected JAXBElement<AddressType> residenceAddress;
    @XmlElementRef(name = "DriverLicense", type = JAXBElement.class, required = false)
    protected JAXBElement<DriverLicenseType> driverLicense;

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSurname(JAXBElement<String> value) {
        this.surname = value;
    }

    /**
     * Gets the value of the givenName1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGivenName1() {
        return givenName1;
    }

    /**
     * Sets the value of the givenName1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGivenName1(JAXBElement<String> value) {
        this.givenName1 = value;
    }

    /**
     * Gets the value of the givenName2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGivenName2() {
        return givenName2;
    }

    /**
     * Sets the value of the givenName2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGivenName2(JAXBElement<String> value) {
        this.givenName2 = value;
    }

    /**
     * Gets the value of the givenName3 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGivenName3() {
        return givenName3;
    }

    /**
     * Sets the value of the givenName3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGivenName3(JAXBElement<String> value) {
        this.givenName3 = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGender(JAXBElement<String> value) {
        this.gender = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setBirthDate(JAXBElement<XMLGregorianCalendar> value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the youngPerson property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BoolYN }{@code >}
     *     
     */
    public JAXBElement<BoolYN> getYoungPerson() {
        return youngPerson;
    }

    /**
     * Sets the value of the youngPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BoolYN }{@code >}
     *     
     */
    public void setYoungPerson(JAXBElement<BoolYN> value) {
        this.youngPerson = value;
    }

    /**
     * Gets the value of the changeOfAddressFlag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BoolYN }{@code >}
     *     
     */
    public JAXBElement<BoolYN> getChangeOfAddressFlag() {
        return changeOfAddressFlag;
    }

    /**
     * Sets the value of the changeOfAddressFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BoolYN }{@code >}
     *     
     */
    public void setChangeOfAddressFlag(JAXBElement<BoolYN> value) {
        this.changeOfAddressFlag = value;
    }

    /**
     * Gets the value of the residenceAddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddressType }{@code >}
     *     
     */
    public JAXBElement<AddressType> getResidenceAddress() {
        return residenceAddress;
    }

    /**
     * Sets the value of the residenceAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddressType }{@code >}
     *     
     */
    public void setResidenceAddress(JAXBElement<AddressType> value) {
        this.residenceAddress = value;
    }

    /**
     * Gets the value of the driverLicense property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DriverLicenseType }{@code >}
     *     
     */
    public JAXBElement<DriverLicenseType> getDriverLicense() {
        return driverLicense;
    }

    /**
     * Sets the value of the driverLicense property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DriverLicenseType }{@code >}
     *     
     */
    public void setDriverLicense(JAXBElement<DriverLicenseType> value) {
        this.driverLicense = value;
    }

}
