/**
 * 初始化assessType详情对话框
 */
var AssessTypeInfoDlg = {
    assessTypeInfoData : {}
};

/**
 * 清除数据
 */
AssessTypeInfoDlg.clearData = function() {
    this.assessTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AssessTypeInfoDlg.set = function(key, val) {
    this.assessTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AssessTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AssessTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.AssessType.layerIndex);
}

/**
 * 收集数据
 */
AssessTypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('remark')
    .set('createTime');
}

/**
 * 提交添加
 */
AssessTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/assessType/add", function(data){
        Feng.success("添加成功!");
        window.parent.AssessType.table.refresh();
        AssessTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.assessTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AssessTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/assessType/update", function(data){
        Feng.success("修改成功!");
        window.parent.AssessType.table.refresh();
        AssessTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.assessTypeInfoData);
    ajax.start();
}

$(function() {

});
