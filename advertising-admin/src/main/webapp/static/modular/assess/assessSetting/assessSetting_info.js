/**
 * 初始化详情对话框
 */
var AssessSettingInfoDlg = {
    assessSettingInfoData : {}
};

/**
 * 清除数据
 */
AssessSettingInfoDlg.clearData = function() {
    this.assessSettingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AssessSettingInfoDlg.set = function(key, val) {
    this.assessSettingInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AssessSettingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AssessSettingInfoDlg.close = function() {
    parent.layer.close(window.parent.AssessSetting.layerIndex);
}

/**
 * 收集数据
 */
AssessSettingInfoDlg.collectData = function() {
    this
    .set('id')
    .set('assessType')
    .set('matching')
    .set('valType')
    .set('val')
    .set('assessScore')
    .set('createTime')
    .set('createUser');
}

/**
 * 提交添加
 */
AssessSettingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/assessSetting/add", function(data){
        Feng.success("添加成功!");
        window.parent.AssessSetting.table.refresh();
        AssessSettingInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.assessSettingInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AssessSettingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/assessSetting/update", function(data){
        Feng.success("修改成功!");
        window.parent.AssessSetting.table.refresh();
        AssessSettingInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.assessSettingInfoData);
    ajax.start();
}

$(function() {

});
