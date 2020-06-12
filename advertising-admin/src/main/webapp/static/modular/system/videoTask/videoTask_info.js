/**
 * 初始化视频金币任务表详情对话框
 */
var VideoTaskInfoDlg = {
    videoTaskInfoData : {}
};

/**
 * 清除数据
 */
VideoTaskInfoDlg.clearData = function() {
    this.videoTaskInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoTaskInfoDlg.set = function(key, val) {
    this.videoTaskInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoTaskInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VideoTaskInfoDlg.close = function() {
    parent.layer.close(window.parent.VideoTask.layerIndex);
}

/**
 * 收集数据
 */
VideoTaskInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('award')
    .set('sumAward')
    .set('expire')
    .set('ctime')
    .set('generalizeCount')
    .set('lookCount')
    .set('advertisingId');
}

/**
 * 提交添加
 */
VideoTaskInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoTask/add", function(data){
        Feng.success("添加成功!");
        window.parent.VideoTask.table.refresh();
        VideoTaskInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoTaskInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VideoTaskInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoTask/update", function(data){
        Feng.success("修改成功!");
        window.parent.VideoTask.table.refresh();
        VideoTaskInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoTaskInfoData);
    ajax.start();
}

$(function() {

});
