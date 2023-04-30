package cn.tanhua.model.enumeration;

public enum CommentType {
    /***
     * 点赞 -1
     */
    LIKE(1),
    /**
     * 评论 -2
     */
    COMMENT(2),
    /**
     * 喜欢 -3
     */
    LOVE(3);


    int type;

    CommentType(int type){this.type=type;}

    public int getType(){return this.type;}
}