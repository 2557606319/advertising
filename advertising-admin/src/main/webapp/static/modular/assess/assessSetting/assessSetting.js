/**
 * 管理初始化
 */
var AssessSetting = {
    id: "AssessSettingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AssessSetting.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '规则类型', field: 'assessType', visible: true, align: 'center', valign: 'middle'},
            {title: '0-小于  1-大于  3-等于  4-小于等于  5-大于等于', field: 'matching', visible: true, align: 'center', valign: 'middle'},
            {title: '值类型  0-数值 1-字符', field: 'valType', visible: true, align: 'center', valign: 'middle'},
            {title: '匹配值', field: 'val', visible: true, align: 'center', valign: 'middle'},
            {title: '匹配分值', field: 'assessScore', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createUser', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AssessSetting.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AssessSetting.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
AssessSetting.openAddAssessSetting = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/assessSetting/assessSetting_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
AssessSetting.openAssessSettingDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/assessSetting/assessSetting_update/' + AssessSetting.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
AssessSetting.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/assessSetting/delete", function (data) {
            Feng.success("删除成功!");
            AssessSetting.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("assessSettingId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
AssessSetting.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AssessSetting.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AssessSetting.initColumn();
    var table = new BSTable(AssessSetting.id, "/assessSetting/list", defaultColunms);
    table.setPaginationType("client");
    AssessSetting.table = table.init();
});
