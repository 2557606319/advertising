/**
 * 初始化素材类型表详情对话框
 */
var TypeInfoDlg = {
    typeInfoData : {}
};

/**
 * 清除数据
 */
TypeInfoDlg.clearData = function() {
    this.typeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TypeInfoDlg.set = function(key, val) {
    this.typeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TypeInfoDlg.close = function() {
    parent.layer.close(window.parent.Type.layerIndex);
}

/**
 * 收集数据
 */
TypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('ctime')
    .set('tname');
}

/**
 * 提交添加
 */
TypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/type/add", function(data){
        Feng.success("添加成功!");
        window.parent.Type.table.refresh();
        TypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.typeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/type/update", function(data){
        Feng.success("修改成功!");
        window.parent.Type.table.refresh();
        TypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.typeInfoData);
    ajax.start();
}

$(function() {

});
