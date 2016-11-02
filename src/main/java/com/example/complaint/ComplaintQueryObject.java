package com.example.complaint;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ComplaintQueryObject {

    @Id
    private String complaintId;
    private String description;
    private String company;

    public ComplaintQueryObject(String complaintId, String description, String company) {
        this.complaintId = complaintId;
        this.description = description;
        this.company = company;
    }

    public ComplaintQueryObject() {
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
