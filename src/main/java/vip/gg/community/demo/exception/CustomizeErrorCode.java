package vip.gg.community.demo.exception;

/**
 * Creat by GG
 * Date on 2020/2/21  3:54 下午
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在呢～");

    private String message;
    CustomizeErrorCode(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
