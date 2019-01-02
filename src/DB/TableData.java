package DB;

public class TableData {
    private String bname,bid,fdate,tdate,phone,address;

    public TableData(String bname, String bid, String fdate, String tdate, String phone, String address) {
        this.bname = bname;
        this.bid = bid;
        this.fdate = fdate;
        this.tdate = tdate;
        this.phone = phone;
        this.address = address;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
