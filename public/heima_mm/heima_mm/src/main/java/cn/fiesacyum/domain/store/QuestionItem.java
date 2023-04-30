package cn.fiesacyum.domain.store;

public class QuestionItem {
    /*
    * <id column="id" jdbcType="VARCHAR" property="id"/>
      <result column="question_id" jdbcType="VARCHAR" property="questionId"/>
      <result column="content" jdbcType="VARCHAR" property="content"/>
      <result column="is_right" jdbcType="VARCHAR" property="isRight"/>
   * */
    private String id;
    private String questionId;
    private String content;
    private String isRight;
    private String picture;

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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    @Override
    public String toString() {
        return "QuestionItem{" +
                "id='" + id + '\'' +
                ", questionId='" + questionId + '\'' +
                ", content='" + content + '\'' +
                ", isRight='" + isRight + '\'' +
                '}';
    }
}
