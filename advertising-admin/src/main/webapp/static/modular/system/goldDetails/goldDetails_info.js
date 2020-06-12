/**
 * 初始化金币明细表详情对话框
 */
var GoldDetailsInfoDlg = {
    goldDetailsInfoData : {}
};

/**
 * 清除数据
 */
GoldDetailsInfoDlg.clearData = function() {
    this.goldDetailsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GoldDetailsInfoDlg.set = function(key, val) {
    this.goldDetailsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GoldDetailsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GoldDetailsInfoDlg.close = function() {
    parent.layer.close(window.parent.GoldDetails.layerIndex);
}

/**
 * 收集数据
 */
GoldDetailsInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('goldNum')
    .set('direction')
    .set('remark')
    .set('ctime');
}

/**
 * 提交添加
 */
GoldDetailsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goldDetails/add", function(data){
        Feng.success("添加成功!");
        window.parent.GoldDetails.table.refresh();
        GoldDetailsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.goldDetailsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GoldDetailsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/goldDetails/update", function(data){
        Feng.success("修改成功!");
        window.parent.GoldDetails.table.refresh();
        GoldDetailsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.goldDetailsInfoData);
    ajax.start();
}

$(function() {

});
