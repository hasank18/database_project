package DB;

public class Client {
    private int cid;
    private String cname,birthdate,gander,address,phonenum;

    public Client(int cid, String cname, String birthdate, String gander, String address, String phonenum) {
        this.cid = cid;
        this.cname = cname;
        this.birthdate = birthdate;
        this.gander = gander;
        this.address = address;
        this.phonenum = phonenum;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGander() {
        return gander;
    }

    public void setGander(String gander) {
        this.gander = gander;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}
