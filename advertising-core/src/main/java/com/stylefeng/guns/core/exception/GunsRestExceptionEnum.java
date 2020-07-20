package com.stylefeng.guns.core.exception;

/**
 * Guns异常枚举
 *
 * @author fengshuonan
 * @Date 2017/12/28 下午10:33
 */
public enum GunsRestExceptionEnum implements ServiceExceptionEnum {


    NO_SUPPORT_ARTICLE(400, "暂不支持该类文章"),
    NO_SUPPORT_VIDEO(400, "暂不支持该平台视频"),
    ARTICLE_NO_EXIST(400, "该文章不存在"),
    VIDEO_NO_EXIST(400, "该视频不存在"),
    VIDEO_NO_ISSUE(400, "该视频未发布"),
    UPLOAD_ERROR(400, "上传异常"),
    UPD_TASK_ERR(400, "发布广告任务失败"),
    ARGS_ERR(400, "参数填写错误"),
    PAY_TYPE_NOT_EXIST(400, "支付类型不存在"),

    SERVER_ERROR(500, "服务器异常");

    GunsRestExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
