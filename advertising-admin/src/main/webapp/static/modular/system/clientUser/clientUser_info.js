/**
 * 初始化客户端用户详情对话框
 */
var ClientUserInfoDlg = {
    clientUserInfoData : {}
};

/**
 * 清除数据
 */
ClientUserInfoDlg.clearData = function() {
    this.clientUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClientUserInfoDlg.set = function(key, val) {
    this.clientUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClientUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClientUserInfoDlg.close = function() {
    parent.layer.close(window.parent.ClientUser.layerIndex);
}

/**
 * 收集数据
 */
ClientUserInfoDlg.collectData = function() {
    this
    .set('id')
    .set('nick')
    .set('avatar')
    .set('industry')
    .set('professional')
    .set('phone')
    .set('wxqr')
    .set('email')
    .set('goldBalance')
    .set('sumCommission')
    .set('sumLower')
    .set('vip')
    .set('vipExpire')
    .set('agencyLevel')
    .set('earnings1')
    .set('earnings2')
    .set('earnings3')
    .set('ctime')
    .set('pid');
}

/**
 * 提交添加
 */
ClientUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clientUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.ClientUser.table.refresh();
        ClientUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clientUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClientUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clientUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.ClientUser.table.refresh();
        ClientUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clientUserInfoData);
    ajax.start();
}

$(function() {

});
