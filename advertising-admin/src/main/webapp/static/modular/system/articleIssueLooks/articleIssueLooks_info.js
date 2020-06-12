/**
 * 初始化发布的文章表浏览记录详情对话框
 */
var ArticleIssueLooksInfoDlg = {
    articleIssueLooksInfoData : {}
};

/**
 * 清除数据
 */
ArticleIssueLooksInfoDlg.clearData = function() {
    this.articleIssueLooksInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleIssueLooksInfoDlg.set = function(key, val) {
    this.articleIssueLooksInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleIssueLooksInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ArticleIssueLooksInfoDlg.close = function() {
    parent.layer.close(window.parent.ArticleIssueLooks.layerIndex);
}

/**
 * 收集数据
 */
ArticleIssueLooksInfoDlg.collectData = function() {
    this
    .set('id')
    .set('issueId')
    .set('taskRelationId')
    .set('lookUserId')
    .set('lookTime')
    .set('isClick')
    .set('isCopy')
    .set('isRelation')
    .set('ctime');
}

/**
 * 提交添加
 */
ArticleIssueLooksInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleIssueLooks/add", function(data){
        Feng.success("添加成功!");
        window.parent.ArticleIssueLooks.table.refresh();
        ArticleIssueLooksInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleIssueLooksInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ArticleIssueLooksInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleIssueLooks/update", function(data){
        Feng.success("修改成功!");
        window.parent.ArticleIssueLooks.table.refresh();
        ArticleIssueLooksInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleIssueLooksInfoData);
    ajax.start();
}

$(function() {

});
