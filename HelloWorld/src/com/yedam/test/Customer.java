package com.yedam.test;

public class Customer {
    private String cusmId;
    private String cusmName;
    private String cusmPw;

    public Customer() {}
    
    // customer_id, customer_name, customer_pw를 받는 생성자
    public Customer(String cusmId, String cusmName, String cusmPw) {
        this.cusmId = cusmId;
        this.cusmName = cusmName;
        this.cusmPw = cusmPw;
    }

    // getter와 setter 메서드
    public String getCustomerId() {
        return cusmId;
    }

    public void setCustomerId(String customerId) {
        this.cusmId = customerId;
    }

    public String getCustomerName() {
        return cusmName;
    }

    public void setCustomerName(String customerName) {
        this.cusmName = customerName;
    }

    public String getCustomerPw() {
        return cusmPw;
    }

    public void setCustomerPw(String customerPw) {
        this.cusmPw = customerPw;
    }

    @Override
    public String toString() {
        return "Customer{id='" + cusmId + "', name='" + cusmName + "', password='" + cusmPw + "'}";
    }
}