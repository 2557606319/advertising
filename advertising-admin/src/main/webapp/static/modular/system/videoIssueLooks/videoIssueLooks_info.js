/**
 * 初始化发布商品浏览记录表详情对话框
 */
var VideoIssueLooksInfoDlg = {
    videoIssueLooksInfoData : {}
};

/**
 * 清除数据
 */
VideoIssueLooksInfoDlg.clearData = function() {
    this.videoIssueLooksInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoIssueLooksInfoDlg.set = function(key, val) {
    this.videoIssueLooksInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoIssueLooksInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VideoIssueLooksInfoDlg.close = function() {
    parent.layer.close(window.parent.VideoIssueLooks.layerIndex);
}

/**
 * 收集数据
 */
VideoIssueLooksInfoDlg.collectData = function() {
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
VideoIssueLooksInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoIssueLooks/add", function(data){
        Feng.success("添加成功!");
        window.parent.VideoIssueLooks.table.refresh();
        VideoIssueLooksInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoIssueLooksInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VideoIssueLooksInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoIssueLooks/update", function(data){
        Feng.success("修改成功!");
        window.parent.VideoIssueLooks.table.refresh();
        VideoIssueLooksInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoIssueLooksInfoData);
    ajax.start();
}

$(function() {

});
