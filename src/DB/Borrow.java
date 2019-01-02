package DB;

public class Borrow {
    private String employee_user_name, sdate, tdate;
    private String cid, bid;

    public Borrow(String employee_user_name, String sdate, String tdate, String  cid, String bid) {
        this.employee_user_name = employee_user_name;
        this.sdate = sdate;
        this.tdate = tdate;
        this.cid = cid;
        this.bid = bid;
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