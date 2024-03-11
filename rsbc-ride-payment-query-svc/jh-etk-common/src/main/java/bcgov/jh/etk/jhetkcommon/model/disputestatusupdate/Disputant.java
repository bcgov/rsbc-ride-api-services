/**
 * Auto generated class. Using http://www.jsonschema2pojo.org/ 
 * Parameters:
 * *Package: 
 * *Class name:
 * *Target language: Java
 * *Source type: JSON
 * *Annotation style: none
 * *Include getters and setters
 * *Property word delimiters: empty string
 */
package bcgov.jh.etk.jhetkcommon.model.disputestatusupdate;

import io.swagger.annotations.ApiModel;

/**
 * The Class Disputant.
 * @author HLiang
 */
@ApiModel("Disputant_disputeStatusUpdate")
public class Disputant {

    /** The client type code. */
    private String client_type_code;
    
    /** The organization name. */
    private String organization_name;
    
    /** The surname. */
    private String surname;
    
    /** The given name 1. */
    private String given_name1;
    
    /** The given name 2. */
    private String given_name2;
    
    /** The given name 3. */
    private String given_name3;
    
    /** The dl number. */
    private String dl_number;
    
    /** The mvb client number. */
    private String mvb_client_number;

    /**
     * Gets the client type code.
     *
     * @return the client type code
     */
    public String getClient_type_code() {
        return client_type_code;
    }

    /**
     * Sets the client type code.
     *
     * @param client_type_code the new client type code
     */
    public void setClient_type_code(String client_type_code) {
        this.client_type_code = client_type_code;
    }

    /**
     * Gets the organization name.
     *
     * @return the organization name
     */
    public String getOrganization_name() {
        return organization_name;
    }

    /**
     * Sets the organization name.
     *
     * @param organization_name the new organization name
     */
    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    /**
     * Gets the surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     *
     * @param surname the new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the given name 1.
     *
     * @return the given name 1
     */
    public String getGiven_name1() {
        return given_name1;
    }

    /**
     * Sets the given name 1.
     *
     * @param given_name1 the new given name 1
     */
    public void setGiven_name1(String given_name1) {
        this.given_name1 = given_name1;
    }

    /**
     * Gets the given name 2.
     *
     * @return the given name 2
     */
    public String getGiven_name2() {
        return given_name2;
    }

    /**
     * Sets the given name 2.
     *
     * @param given_name2 the new given name 2
     */
    public void setGiven_name2(String given_name2) {
        this.given_name2 = given_name2;
    }

    /**
     * Gets the given name 3.
     *
     * @return the given name 3
     */
    public String getGiven_name3() {
        return given_name3;
    }

    /**
     * Sets the given name 3.
     *
     * @param given_name3 the new given name 3
     */
    public void setGiven_name3(String given_name3) {
        this.given_name3 = given_name3;
    }

    /**
     * Gets the dl number.
     *
     * @return the dl number
     */
    public String getDl_number() {
        return dl_number;
    }

    /**
     * Sets the dl number.
     *
     * @param dl_number the new dl number
     */
    public void setDl_number(String dl_number) {
        this.dl_number = dl_number;
    }

    /**
     * Gets the mvb client number.
     *
     * @return the mvb client number
     */
    public String getMvb_client_number() {
        return mvb_client_number;
    }

    /**
     * Sets the mvb client number.
     *
     * @param mvb_client_number the new mvb client number
     */
    public void setMvb_client_number(String mvb_client_number) {
        this.mvb_client_number = mvb_client_number;
    }

}
