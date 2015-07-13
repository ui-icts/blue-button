package edu.uiowa.icts.bluebutton.domain;

/*
 * #%L
 * blue-button Spring MVC Web App
 * %%
 * Copyright (C) 2014 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.format.annotation.DateTimeFormat;

/** 
 * Generated by Protogen - www.icts.uiowa.edu/protogen 
 * @since 07/31/2014 09:37:03 CDT
 */

@Entity( name = "edu_uiowa_icts_bluebutton_domain_person" )
@Table( name = "person" , schema = "bluebutton" )
public class Person {

	private static final Log log = LogFactory.getLog( Person.class );

	private Integer personId;
	private String firstName;
	private String middleName;
	private String lastName;
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date dateOfBirth;
	private String addressLineOne;
	private String addressLineTwo;
	private String city;
	private String state;
	private Integer zipcode;
	private String country;
	private String email;
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date firstLogin;
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date lastLogin;
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date lastUpdated;
	private String signature;
	private Set<ClinicalDocument> clinicalDocuments = new HashSet<ClinicalDocument>( 0 );

	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date registrationDate;

	public Person() {
		this.personId = null;
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.dateOfBirth = null;
		this.addressLineOne = "";
		this.addressLineTwo = "";
		this.city = "";
		this.state = "";
		this.zipcode = null;
		this.country = "";
		this.email = "";
		this.firstLogin = null;
		this.lastLogin = null;
		this.lastUpdated = null;
		this.signature = "";
		this.clinicalDocuments = new HashSet<ClinicalDocument>( 0 );
	}

	@Id
	@Column( name = "person_id" , unique = true , nullable = false )
	@GeneratedValue( strategy = GenerationType.AUTO , generator = "gen" )
	@javax.persistence.SequenceGenerator( name = "gen" , sequenceName = "bluebutton.seqnum" , allocationSize = 1 )
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId( Integer personId ) {
		this.personId = personId;
	}

	/*****firstName*****/
	@Column( name = "first_name" )
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	/*****middleName*****/
	@Column( name = "middle_name" )
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName( String middleName ) {
		this.middleName = middleName;
	}

	/*****lastName*****/
	@Column( name = "last_name" )
	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	/*****dateOfBirth*****/
	@Column( name = "date_of_birth" )
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth( Date dateOfBirth ) {
		this.dateOfBirth = dateOfBirth;
	}

	/*****addressLineOne*****/
	@Column( name = "address_line_one" )
	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne( String addressLineOne ) {
		this.addressLineOne = addressLineOne;
	}

	/*****addressLineTwo*****/
	@Column( name = "address_line_two" )
	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo( String addressLineTwo ) {
		this.addressLineTwo = addressLineTwo;
	}

	/*****city*****/
	@Column( name = "city" )
	public String getCity() {
		return city;
	}

	public void setCity( String city ) {
		this.city = city;
	}

	/*****state*****/
	@Column( name = "state" )
	public String getState() {
		return state;
	}

	public void setState( String state ) {
		this.state = state;
	}

	/*****zipcode*****/
	@Column( name = "zipcode" )
	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode( Integer zipcode ) {
		this.zipcode = zipcode;
	}

	/*****country*****/
	@Column( name = "country" )
	public String getCountry() {
		return country;
	}

	public void setCountry( String country ) {
		this.country = country;
	}

	/*****email*****/
	@Column( name = "email" )
	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	/*****firstLogin*****/
	@Column( name = "first_login" )
	public Date getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin( Date firstLogin ) {
		this.firstLogin = firstLogin;
	}

//	public void setFirstLogin( String firstLogin ) {
//		try {
//			DateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
//			formatter.setLenient( true );
//			this.firstLogin = formatter.parse( firstLogin );
//		} catch ( ParseException e ) {
//			log.error( " ParseException setting date for FirstLogin", e );
//		}
//	}

	/*****lastLogin*****/
	@Column( name = "last_login" )
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin( Date lastLogin ) {
		this.lastLogin = lastLogin;
	}

//	public void setLastLogin( String lastLogin ) {
//		try {
//			DateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
//			formatter.setLenient( true );
//			this.lastLogin = formatter.parse( lastLogin );
//		} catch ( ParseException e ) {
//			log.error( " ParseException setting date for LastLogin", e );
//		}
//	}

	/*****lastUpdated*****/
	@Column( name = "last_updated" )
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated( Date lastUpdated ) {
		this.lastUpdated = lastUpdated;
	}

//	public void setLastUpdated( String lastUpdated ) {
//		try {
//			DateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
//			formatter.setLenient( true );
//			this.lastUpdated = formatter.parse( lastUpdated );
//		} catch ( ParseException e ) {
//			log.error( " ParseException setting date for LastUpdated", e );
//		}
//	}

	/*****signature*****/
	@Column( name = "signature" )
	public String getSignature() {
		return signature;
	}

	public void setSignature( String signature ) {
		this.signature = signature;
	}


	/*****clinicalDocuments*****/
	@OneToMany( fetch = FetchType.LAZY , mappedBy = "person" , targetEntity = ClinicalDocument.class , cascade = CascadeType.REMOVE )
	public Set<ClinicalDocument> getClinicalDocuments() {
		return clinicalDocuments;
	}

	public void setClinicalDocuments( Set<ClinicalDocument> clinicalDocuments ) {
		this.clinicalDocuments = clinicalDocuments;
	}

	@Column( name = "registration_date" )
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate( Date registrationDate ) {
		this.registrationDate = registrationDate;
	}

//	public void setRegistrationDate( String registrationDate ) {
//		try {
//			DateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
//			formatter.setLenient( true );
//			this.registrationDate = formatter.parse( registrationDate );
//		} catch ( ParseException e ) {
//			log.error( " ParseException setting date for registration date", e );
//		}
//	}
}