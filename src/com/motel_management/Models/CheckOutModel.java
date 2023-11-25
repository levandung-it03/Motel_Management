package com.motel_management.Models;

import java.sql.Date;

public class CheckOutModel {
    private String checkOutId;
    private String contractId;
    private Date checkOutDate;
    private String reason;

    public CheckOutModel(String checkOutId, String contractId, Date checkOutDate, String reason) {
        this.checkOutId = checkOutId;
        this.contractId = contractId;
        this.checkOutDate = checkOutDate;
        this.reason = reason;
    }

    public String getCheckOutId() {
        return checkOutId;
    }

    public void setCheckOutId(String checkOutId) {
        this.checkOutId = checkOutId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getReason () {return reason;}

    public void setReason (String reason) {this.reason = reason;}
}
