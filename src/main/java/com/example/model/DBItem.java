package com.example.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "items", schema="public")
public class DBItem {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "brand")
    private String brand;
    private String type;
    private String guid;
    private LocalDateTime createdDateTime;
    private String email;
    private String authenticationCode;
    private String price;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    private String fuelType;
    private String speedometerCondition;
    private Long productionYear;
    private String mobileNumber;

    public DBItem() {
    }

    public DBItem(
            String brand,
            String type,
            String guid,
            LocalDateTime createdDateTime,
            String email,
            String authenticationCode,
            String price,
            String description,
            String fuelType,
            String speedometerCondition,
            Long productionYear,
            String mobileNumber
    ) {
        super();
        this.brand = brand;
        this.type = type;
        this.guid = guid;
        this.createdDateTime = createdDateTime;
        this.email = email;
        this.authenticationCode = authenticationCode;
        this.price = price;
        this.description = description;
        this.fuelType = fuelType;
        this.speedometerCondition = speedometerCondition;
        this.productionYear = productionYear;
        this.mobileNumber = mobileNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        this.authenticationCode = authenticationCode;
    }    

    public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Long productionYear) {
        this.productionYear = productionYear;
    }

	public String getSpeedometerCondition() {
		return speedometerCondition;
	}

	public void setSpeedometerCondition(String speedometerCondition) {
		this.speedometerCondition = speedometerCondition;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "DBItem [id=" + id + ", brand=" + brand + ", type=" + type + ", guid=" + guid + ", createdDateTime="
				+ createdDateTime + ", email=" + email + ", authenticationCode=" + authenticationCode + ", price="
				+ price + ", description=" + description + ", fuelType=" + fuelType + ", speedometerCondition="
				+ speedometerCondition + ", productionYear=" + productionYear + ", mobileNumber=" + mobileNumber + "]";
	}       
	
}
