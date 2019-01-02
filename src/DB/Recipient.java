package DB;

import java.util.ArrayList;

public class Recipient {
    private String bname,employee_user_name, sdate, tdate;
    private String cid;
    private String bid;
    private String number;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Recipient(String bname, String employee_user_name, String sdate, String tdate, String cid, String bid, String number, String address) {
        this.bname = bname;
        this.employee_user_name = employee_user_name;
        this.sdate = sdate;
        this.tdate = tdate;
        this.cid = cid;

        this.bid = bid;
        this.number = number;
        this.address = address;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getEmployee_user_name() {
        return employee_user_name;
    }

    public void setEmployee_user_name(String employee_user_name) {
        this.employee_user_name = employee_user_name;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
