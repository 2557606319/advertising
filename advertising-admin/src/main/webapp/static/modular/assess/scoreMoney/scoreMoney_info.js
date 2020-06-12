/**
 * 初始化详情对话框
 */
var ScoreMoneyInfoDlg = {
    scoreMoneyInfoData : {}
};

/**
 * 清除数据
 */
ScoreMoneyInfoDlg.clearData = function() {
    this.scoreMoneyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScoreMoneyInfoDlg.set = function(key, val) {
    this.scoreMoneyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScoreMoneyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ScoreMoneyInfoDlg.close = function() {
    parent.layer.close(window.parent.ScoreMoney.layerIndex);
}

/**
 * 收集数据
 */
ScoreMoneyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('startScore')
    .set('endScore')
    .set('matchingMoney')
    .set('createTime');
}

/**
 * 提交添加
 */
ScoreMoneyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scoreMoney/add", function(data){
        Feng.success("添加成功!");
        window.parent.ScoreMoney.table.refresh();
        ScoreMoneyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scoreMoneyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ScoreMoneyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scoreMoney/update", function(data){
        Feng.success("修改成功!");
        window.parent.ScoreMoney.table.refresh();
        ScoreMoneyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scoreMoneyInfoData);
    ajax.start();
}

$(function() {

});
