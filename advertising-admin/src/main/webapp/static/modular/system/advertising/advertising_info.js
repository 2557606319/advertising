/**
 * 初始化广告信息表详情对话框
 */
var AdvertisingInfoDlg = {
    advertisingInfoData : {}
};

/**
 * 清除数据
 */
AdvertisingInfoDlg.clearData = function() {
    this.advertisingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AdvertisingInfoDlg.set = function(key, val) {
    this.advertisingInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AdvertisingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AdvertisingInfoDlg.close = function() {
    parent.layer.close(window.parent.Advertising.layerIndex);
}

/**
 * 收集数据
 */
AdvertisingInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('describe')
    .set('cover')
    .set('default')
    .set('href')
    .set('goodsCommand')
    .set('userId')
    .set('award')
    .set('sumAward')
    .set('expire')
    .set('ctime');
}

/**
 * 提交添加
 */
AdvertisingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/advertising/add", function(data){
        Feng.success("添加成功!");
        window.parent.Advertising.table.refresh();
        AdvertisingInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.advertisingInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AdvertisingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/advertising/update", function(data){
        Feng.success("修改成功!");
        window.parent.Advertising.table.refresh();
        AdvertisingInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.advertisingInfoData);
    ajax.start();
}

$(function() {

});
