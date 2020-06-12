/**
 * 初始化支付明细表详情对话框
 */
var PayDetailsInfoDlg = {
    payDetailsInfoData : {}
};

/**
 * 清除数据
 */
PayDetailsInfoDlg.clearData = function() {
    this.payDetailsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayDetailsInfoDlg.set = function(key, val) {
    this.payDetailsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayDetailsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PayDetailsInfoDlg.close = function() {
    parent.layer.close(window.parent.PayDetails.layerIndex);
}

/**
 * 收集数据
 */
PayDetailsInfoDlg.collectData = function() {
    this
    .set('num')
    .set('userId')
    .set('payNum')
    .set('money')
    .set('status')
    .set('ctime');
}

/**
 * 提交添加
 */
PayDetailsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payDetails/add", function(data){
        Feng.success("添加成功!");
        window.parent.PayDetails.table.refresh();
        PayDetailsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payDetailsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PayDetailsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payDetails/update", function(data){
        Feng.success("修改成功!");
        window.parent.PayDetails.table.refresh();
        PayDetailsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payDetailsInfoData);
    ajax.start();
}

$(function() {

});
