/**
 * 初始化文章素材发布表详情对话框
 */
var VideoIssueInfoDlg = {
    videoIssueInfoData : {}
};

/**
 * 清除数据
 */
VideoIssueInfoDlg.clearData = function() {
    this.videoIssueInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoIssueInfoDlg.set = function(key, val) {
    this.videoIssueInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoIssueInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VideoIssueInfoDlg.close = function() {
    parent.layer.close(window.parent.VideoIssue.layerIndex);
}

/**
 * 收集数据
 */
VideoIssueInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('videoId')
    .set('commentCount')
    .set('lookCount')
    .set('likeCount')
    .set('advertisingId')
    .set('ctime');
}

/**
 * 提交添加
 */
VideoIssueInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoIssue/add", function(data){
        Feng.success("添加成功!");
        window.parent.VideoIssue.table.refresh();
        VideoIssueInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoIssueInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VideoIssueInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoIssue/update", function(data){
        Feng.success("修改成功!");
        window.parent.VideoIssue.table.refresh();
        VideoIssueInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoIssueInfoData);
    ajax.start();
}

$(function() {

});
