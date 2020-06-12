/**
 * 初始化文章金币任务与发布者关联表详情对话框
 */
var ArticleTaskRelationInfoDlg = {
    articleTaskRelationInfoData : {}
};

/**
 * 清除数据
 */
ArticleTaskRelationInfoDlg.clearData = function() {
    this.articleTaskRelationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleTaskRelationInfoDlg.set = function(key, val) {
    this.articleTaskRelationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleTaskRelationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ArticleTaskRelationInfoDlg.close = function() {
    parent.layer.close(window.parent.ArticleTaskRelation.layerIndex);
}

/**
 * 收集数据
 */
ArticleTaskRelationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('taskId')
    .set('relationId');
}

/**
 * 提交添加
 */
ArticleTaskRelationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleTaskRelation/add", function(data){
        Feng.success("添加成功!");
        window.parent.ArticleTaskRelation.table.refresh();
        ArticleTaskRelationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleTaskRelationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ArticleTaskRelationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleTaskRelation/update", function(data){
        Feng.success("修改成功!");
        window.parent.ArticleTaskRelation.table.refresh();
        ArticleTaskRelationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleTaskRelationInfoData);
    ajax.start();
}

$(function() {

});
