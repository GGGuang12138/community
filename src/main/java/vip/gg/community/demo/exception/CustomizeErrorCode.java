package vip.gg.community.demo.exception;

/**
 * Creat by GG
 * Date on 2020/2/21  3:54 下午
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不在呢～"),
    TARGET_PARAM_NOT_FOUND(2002,"您未选中任何问题或评论～"),
    TYPE_PARAM_WRONG(2003,"评论类型错误或不存在呢～"),
    COMMENT_NOT_FOUND(2005,"回复的评论不存在了呢～"),
    COMMENT_IS_EMPTY(2006,"回复的内容不能为空呢～"),

    NO_LOGIN(1001,"当前未登陆，请先登陆呢～"),
    SYS_ERROR(1002,"系统出错了呢～");


    private String message;
    private Integer code;
    CustomizeErrorCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
