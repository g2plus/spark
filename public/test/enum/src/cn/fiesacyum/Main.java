package cn.fiesacyum;

public class Main {
    public static void main(String[] args) {

        CommentType like=CommentType.LIKE;
        System.out.println(like.getType());//1
        like.print();//"Hello World!

        CommentType commentType = CommentType.LEMON;
        System.out.println(commentType.getType());//4
        commentType.print();
    }
}
