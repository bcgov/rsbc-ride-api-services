<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/XML_FILE">

		<EVTCanonical>
			<EVTDetails>
				<amountOfPenalty><xsl:value-of select="REPORT/SET_FINE_AMOUNT"/></amountOfPenalty>
				<changeOfAddressFlag><xsl:value-of select="REPORT/CUSTOMDATA/TK_CHANGE_ADDRESS"/></changeOfAddressFlag>
				<carrierJurisdiction><xsl:value-of select="REPORT/CUSTOMDATA/TK_NSC_PUJ"/></carrierJurisdiction>
				<carrierRegNumber><xsl:value-of select="REPORT/CUSTOMDATA/TK_NSC_NBR"/></carrierRegNumber>
				<certificateOfServiceCompleted><xsl:value-of select="REPORT/CUSTOMDATA/TK_CERTIFICATE"/></certificateOfServiceCompleted>
				<certificateOfServiceServedTo><xsl:value-of select="REPORT/CUSTOMDATA/TK_CERTIFICATE_SERVED_TO"/></certificateOfServiceServedTo>
				<certificateOfServiceDateOfService><xsl:value-of select="REPORT/CUSTOMDATA/TK_CERTIFICATE_DATE_OF_SERVICE"/></certificateOfServiceDateOfService>
				<certificateOfServiceEntityPerson><xsl:value-of select="REPORT/CUSTOMDATA/TK_CERTIFICATE_ENTITY_PERSON"/></certificateOfServiceEntityPerson>
				<courtLocationCode><xsl:value-of select="REPORT/CUSTOMDATA/TK_COURT_HEARING_LOCATION_TRANS"/></courtLocationCode>
				<dateOfBirth><xsl:value-of select="REPORT/PERSON/DATE_OF_BIRTH"/></dateOfBirth>
				<disputeAddress><xsl:value-of select="REPORT/CUSTOMDATA/TK_DISPUTE_LOCATION_ADDRESS_TRANS"/></disputeAddress>
				<driversLicenseExpiryDate><xsl:value-of select="REPORT/CUSTOMDATA/TK_DL_EXPIRY"/></driversLicenseExpiryDate>
				<driversLicenseNumber><xsl:value-of select="REPORT/PERSON/DRIVERS_LICENCE_NUMBER"/></driversLicenseNumber>
				<driversLicenseProducedFlag><xsl:value-of select="REPORT/CUSTOMDATA/TK_DL_PRODUCED"/></driversLicenseProducedFlag>
				<driversLicenseProvinceCode><xsl:value-of select="REPORT/PERSON/DRIVERS_LICENCE_PROV_STATE"/></driversLicenseProvinceCode>
				<driversLicenseProvinceName><xsl:value-of select="REPORT/PERSON/DRIVERS_LICENCE_PROV_STATE_TRANS"/></driversLicenseProvinceName>
				<enforcementOfficerName><xsl:value-of select="REPORT/OFFICER_ID_TRANS"/></enforcementOfficerName>
				<xsl:choose>
					<xsl:when test="REPORT/CUSTOMDATA/TK_OFFICER_LASTNAME != ''">
						<enforcementOfficerLastName><xsl:value-of select="REPORT/CUSTOMDATA/TK_OFFICER_LASTNAME"/></enforcementOfficerLastName>
					</xsl:when>
					<xsl:otherwise>
						<enforcementOfficerLastName><xsl:value-of select="REPORT/CUSTOMDATA/TK_TEMP_OFFICER_LASTNAME"/></enforcementOfficerLastName>
					</xsl:otherwise>
				</xsl:choose>
				<enforcementOfficerNumber><xsl:value-of select="REPORT/OFFICER_ID"/></enforcementOfficerNumber>
				<enforcementOfficerPartnerNumber><xsl:value-of select="REPORT/CUSTOMDATA/TK_WITNESSING_OFFICER"/></enforcementOfficerPartnerNumber>
				<enforcementOfficerPartnerName><xsl:value-of select="REPORT/CUSTOMDATA/TK_WITNESSING_OFFICER_TRANS"/></enforcementOfficerPartnerName>
				<xsl:choose>
					<xsl:when test="REPORT/CUSTOMDATA/TK_WITNESSING_OFFICER_LASTNAME != ''">
						<enforcementOfficerPartnerLastName><xsl:value-of select="REPORT/CUSTOMDATA/TK_WITNESSING_OFFICER_LASTNAME"/></enforcementOfficerPartnerLastName>
					</xsl:when>
					<xsl:otherwise>
						<enforcementOfficerPartnerLastName><xsl:value-of select="REPORT/CUSTOMDATA/TK_TEMP_WITNESSING_OFFICER_LASTNAME"/></enforcementOfficerPartnerLastName>
					</xsl:otherwise>
				</xsl:choose>
				<enforcementOrganizationJurisdiction><xsl:value-of select="REPORT/JURISDICTION_TRANS"/></enforcementOrganizationJurisdiction>
				<enforcementOrganizationJurisdictionCode><xsl:value-of select="REPORT/JURISDICTION"/></enforcementOrganizationJurisdictionCode>
				<evtPrinted><xsl:value-of select="REPORT/PRINTED"/></evtPrinted>
				<givenNameOne><xsl:value-of select="REPORT/PERSON/GIVEN_ONE"/></givenNameOne>
				<givenNameTwo><xsl:value-of select="REPORT/PERSON/GIVEN_TWO"/></givenNameTwo>
				<givenNameThree><xsl:value-of select="REPORT/PERSON/GIVEN_THREE"/></givenNameThree>
				<MREMinorVersion><xsl:value-of select="MINOR_VERSION"/></MREMinorVersion>
				<offenceDescription><xsl:value-of select="REPORT/CHARGE_TEXT"/></offenceDescription>
				<offenderType><xsl:value-of select="REPORT/TK_ROLE"/></offenderType>
				<organizationUnit><xsl:value-of select="ORG_UNIT"/></organizationUnit>
				<personAddress><xsl:value-of select="REPORT/PERSON/STREET_NAME"/></personAddress>
				<personResidenceCity><xsl:value-of select="REPORT/PERSON/MUNICIPALITY_NAME"/></personResidenceCity>
				<personResidenceProvinceCode><xsl:value-of select="REPORT/PERSON/PROV_STATE_CODE"/></personResidenceProvinceCode>
				<personResidenceProvinceName><xsl:value-of select="REPORT/PERSON/PROV_STATE_CODE_TRANS"/></personResidenceProvinceName>
				<personResidencePostalCode><xsl:value-of select="REPORT/PERSON/POSTAL_ZIPCODE"/></personResidencePostalCode>
				<registeredUserName><xsl:value-of select="REPORT/CUSTOMDATA/TK_REG_OWNER_NAME"/></registeredUserName>
				<reportDate><xsl:value-of select="SUBMIT_DATE"/></reportDate>
				<reportTime><xsl:value-of select="SENT_TIME"/></reportTime>
				<sex><xsl:value-of select="REPORT/PERSON/GENDER_CODE"/></sex>
				<surname><xsl:value-of select="REPORT/PERSON/INDIVIDUAL_SURNAME"/></surname>
				<statuteAct><xsl:value-of select="REPORT/STATUTE_ACT"/></statuteAct>
				<statuteSection><xsl:value-of select="REPORT/LEGAL_STATUTE_SECTION"/></statuteSection>
				<ticketClass><xsl:value-of select="REPORT/TICKET_CLASS"/></ticketClass>
				<ticketNumber><xsl:value-of select="REPORT/TICKET_NUM"/></ticketNumber>
				<ticketStatus><xsl:value-of select="REPORT/TICKET_STATUS"/></ticketStatus>
				<ticketCOSFormNumber><xsl:value-of select="REPORT/TICKET_ORIGIN_TRANS"/></ticketCOSFormNumber>
				<ticketEVTFormNumber><xsl:value-of select="REPORT/PAYMENT_METHOD_TRANS"/></ticketEVTFormNumber>
				<violationDate><xsl:value-of select="REPORT/OFFENCE_DATE"/></violationDate>
				<violationTime><xsl:value-of select="REPORT/OFFENCE_TIME"/></violationTime>
				<violationLocation><xsl:value-of select="REPORT/STREET_NAME"/></violationLocation>
				<violationCityCode><xsl:value-of select="REPORT/MUNICIPALITY"/></violationCityCode>
				<violationCityName><xsl:value-of select="REPORT/MUNICIPALITY_TRANS"/></violationCityName>
				<vehicleAccidentFlag><xsl:value-of select="REPORT/CUSTOMDATA/TK_ACCIDENT"/></vehicleAccidentFlag>

				<!-- vehicle -->
				<vehicleColour><xsl:value-of select="REPORT/VEHICLE/ORIGINAL_COLOUR"/></vehicleColour>
				<vehicleLicenseProvinceCode><xsl:value-of select="REPORT/VEHICLE/LICENCE_PROVINCE"/></vehicleLicenseProvinceCode>
				<vehicleLicenseProvinceName><xsl:value-of select="REPORT/VEHICLE/LICENCE_PROVINCE_TRANS"/></vehicleLicenseProvinceName>
				<vehiclePlateNo><xsl:value-of select="REPORT/VEHICLE/VEHICLE_LICENCE_NUMBER"/></vehiclePlateNo>
				<vehicleMakeCode><xsl:value-of select="REPORT/VEHICLE/VEHICLE_MAKE"/></vehicleMakeCode>
				<vehicleMakeDescription><xsl:value-of select="REPORT/VEHICLE/VEHICLE_MAKE_TRANS"/></vehicleMakeDescription>
				<vehicleTypeCode><xsl:value-of select="REPORT/VEHICLE/STYLE"/></vehicleTypeCode>
				<vehicleTypeDescription><xsl:value-of select="REPORT/VEHICLE/STYLE_TRANS"/></vehicleTypeDescription>
				<vehicleYear><xsl:value-of select="REPORT/VEHICLE/MODEL_YEAR"/></vehicleYear>
				<youngPersonFlag><xsl:value-of select="REPORT/CUSTOMDATA/TK_YOUNG_PERSON"/></youngPersonFlag>

				<counts>
					<countNumber>1</countNumber>
					<act><xsl:value-of select="REPORT/STATUTE_ACT"/></act>
					<section><xsl:value-of select="REPORT/LEGAL_STATUTE_SECTION"/></section>
					<offenseDescription><xsl:value-of select="REPORT/CHARGE_TEXT"/></offenseDescription>
					<amount><xsl:value-of select="REPORT/SET_FINE_AMOUNT"/></amount>
				</counts>
				<!-- count -->
				<xsl:for-each select="REPORT/PERSON/CHARGE">
					<counts>
						<countNumber><xsl:value-of select="ID"/></countNumber>
						<act><xsl:value-of select="STATUTE_NAME"/></act>
						<section><xsl:value-of select="LEGAL_STATUTE_SECTION"/></section>
						<offenseDescription><xsl:value-of select="CHARGE_TEXT"/></offenseDescription>
						<amount><xsl:value-of select="AMOUNT_OF_PENALTY"/></amount>
					</counts>
				</xsl:for-each>
			</EVTDetails>
		</EVTCanonical>

	</xsl:template>
</xsl:stylesheet>
