package cn.fiesacyum.domain.store;

import java.util.Date;

public class Question {
    /*
    * <id column="id" jdbcType="VARCHAR" property="id"/>
        <result column="company_id" jdbcType="VARCHAR" property="companyId"/>
        <result column="catalog_id" jdbcType="VARCHAR" property="catalogId"/>
        <result column="remark" jdbcType="VARCHAR" property="remark"/>
        <result column="subject" jdbcType="VARCHAR" property="subject"/>
        <result column="analysis" jdbcType="VARCHAR" property="analysis"/>
        <result column="type" jdbcType="VARCHAR" property="type"/>
        <result column="difficulty" jdbcType="VARCHAR" property="difficulty"/>
        <result column="is_classic" jdbcType="VARCHAR" property="isClassic"/>
        <result column="state" jdbcType="VARCHAR" property="state"/>
        <result column="review_status" jdbcType="VARCHAR" property="reviewStatus"/>
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime"/>
        <!--<result column="picture" jdbcType="VARCHAR" property="picture"/>-->

        <association
            property="company"
            column="company_id"
            javaType="cn.fiesacyum.domain.store.Course"
            select="cn.fiesacyum.dao.store.CompanyDao.findById"/>

        <association
            property="catalog"
            column="catalog_id"
            javaType="cn.fiesacyum.domain.store.Course"
            select="cn.fiesacyum.dao.store.CatalogDao.findById"
            />
     */
    private String id;
    private String companyId;
    private String catalogId;
    private String remark;
    private String subject;
    private String analysis;
    private String type;
    private String difficulty;
    private String isClassic;
    private String state;
    private String reviewStatus;
    private Date createTime;
    private String picture;
    private Company company;
    private Catalog catalog;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getIsClassic() {
        return isClassic;
    }

    public void setIsClassic(String isClassic) {
        this.isClassic = isClassic;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    //用于测试
    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", catalogId='" + catalogId + '\'' +
                ", remark='" + remark + '\'' +
                ", subject='" + subject + '\'' +
                ", analysis='" + analysis + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", isClassic='" + isClassic + '\'' +
                ", state='" + state + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", createTime=" + createTime +
                ", picture='" + picture + '\'' +
                ", company=" + company +
                ", catalog=" + catalog +
                '}';
    }
}
