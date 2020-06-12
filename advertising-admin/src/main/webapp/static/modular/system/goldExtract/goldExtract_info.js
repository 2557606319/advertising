/**
 * 初始化金币明提现申请表详情对话框
 */
var GoldExtractInfoDlg = {
    goldExtractInfoData : {}
};

/**
 * 清除数据
 */
GoldExtractInfoDlg.clearData = function() {
    this.goldExtractInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GoldExtractInfoDlg.set = function(key, val) {
    this.goldExtractInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GoldExtractInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GoldExtractInfoDlg.close = function() {
    parent.layer.close(window.parent.GoldExtract.layerIndex);
}

/**
 * 收集数据
 */
GoldExtractInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('goldNum')
    .set('type')
    .set('account')
    .set('ctime')
    .set('status')
    .set('authTime')
    .set('remark');
}

/**
 * 提交添加
 */
GoldExtractInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goldExtract/add", function(data){
        Feng.success("添加成功!");
        window.parent.GoldExtract.table.refresh();
        GoldExtractInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.goldExtractInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GoldExtractInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goldExtract/update", function(data){
        Feng.success("修改成功!");
        window.parent.GoldExtract.table.refresh();
        GoldExtractInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.goldExtractInfoData);
    ajax.start();
}

$(function() {

});
