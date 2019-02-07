package DB;

public class Borrow {
    private String borrowID,employee_user_name, sdate, tdate,bname,cname;
    private String cid, bid;

    public Borrow(String borrowID, String employee_user_name, String sdate, String tdate, String bid, String bname, String cid, String cname) {
        this.borrowID = borrowID;
        this.employee_user_name = employee_user_name;
        this.sdate = sdate;
        this.tdate = tdate;
        this.bname = bname;
        this.cname = cname;
        this.cid = cid;
        this.bid = bid;
    }

    public String getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(String borrowID) {
        this.borrowID = borrowID;
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

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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