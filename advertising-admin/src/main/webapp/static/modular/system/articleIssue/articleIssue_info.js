/**
 * 初始化文章发布表详情对话框
 */
var ArticleIssueInfoDlg = {
    articleIssueInfoData : {}
};

/**
 * 清除数据
 */
ArticleIssueInfoDlg.clearData = function() {
    this.articleIssueInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleIssueInfoDlg.set = function(key, val) {
    this.articleIssueInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleIssueInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ArticleIssueInfoDlg.close = function() {
    parent.layer.close(window.parent.ArticleIssue.layerIndex);
}

/**
 * 收集数据
 */
ArticleIssueInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('articleId')
    .set('commentCount')
    .set('lookCount')
    .set('likeCount')
    .set('advertisingId')
    .set('ctime');
}

/**
 * 提交添加
 */
ArticleIssueInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleIssue/add", function(data){
        Feng.success("添加成功!");
        window.parent.ArticleIssue.table.refresh();
        ArticleIssueInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleIssueInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ArticleIssueInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleIssue/update", function(data){
        Feng.success("修改成功!");
        window.parent.ArticleIssue.table.refresh();
        ArticleIssueInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleIssueInfoData);
    ajax.start();
}

$(function() {

});
