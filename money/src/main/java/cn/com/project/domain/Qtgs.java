package cn.com.project.domain;

public class Qtgs {
    private Integer id;

    private String gsmc;

    /**
     *预算金额
     */
    private String cjsl;

    /**
     *剩余预算
     */
    private Double amount;

    private String cjje;

    private String lname;

    private String lrsj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGsmc() {
        return gsmc;
    }

    public void setGsmc(String gsmc) {
        this.gsmc = gsmc == null ? null : gsmc.trim();
    }

    public String getCjsl() {
        return cjsl;
    }

    public void setCjsl(String cjsl) {
        this.cjsl = cjsl == null ? null : cjsl.trim();
    }

    public String getCjje() {
        return cjje;
    }

    public void setCjje(String cjje) {
        this.cjje = cjje == null ? null : cjje.trim();
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname == null ? null : lname.trim();
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj == null ? null : lrsj.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}