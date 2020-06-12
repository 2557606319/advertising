/**
 * 初始化返佣明细表详情对话框
 */
var RebateInfoDlg = {
    rebateInfoData : {}
};

/**
 * 清除数据
 */
RebateInfoDlg.clearData = function() {
    this.rebateInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RebateInfoDlg.set = function(key, val) {
    this.rebateInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RebateInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RebateInfoDlg.close = function() {
    parent.layer.close(window.parent.Rebate.layerIndex);
}

/**
 * 收集数据
 */
RebateInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('targetUserId')
    .set('proMoney')
    .set('commission')
    .set('ratio')
    .set('gold')
    .set('ctime');
}

/**
 * 提交添加
 */
RebateInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rebate/add", function(data){
        Feng.success("添加成功!");
        window.parent.Rebate.table.refresh();
        RebateInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rebateInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RebateInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/rebate/update", function(data){
        Feng.success("修改成功!");
        window.parent.Rebate.table.refresh();
        RebateInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.rebateInfoData);
    ajax.start();
}

$(function() {

});
