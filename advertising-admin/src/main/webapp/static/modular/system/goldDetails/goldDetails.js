/**
 * 金币明细表管理初始化
 */
var GoldDetails = {
    id: "GoldDetailsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GoldDetails.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '金币数', field: 'goldNum', visible: true, align: 'center', valign: 'middle'},
            {title: '收入支出  0-收入 1-支出', field: 'direction', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
GoldDetails.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        GoldDetails.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加金币明细表
 */
GoldDetails.openAddGoldDetails = function () {
    var index = layer.open({
        type: 2,
        title: '添加金币明细表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goldDetails/goldDetails_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看金币明细表详情
 */
GoldDetails.openGoldDetailsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '金币明细表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goldDetails/goldDetails_update/' + GoldDetails.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除金币明细表
 */
GoldDetails.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/goldDetails/delete", function (data) {
            Feng.success("删除成功!");
            GoldDetails.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("goldDetailsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询金币明细表列表
 */
GoldDetails.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    GoldDetails.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = GoldDetails.initColumn();
    var table = new BSTable(GoldDetails.id, "/goldDetails/list", defaultColunms);
    table.setPaginationType("client");
    GoldDetails.table = table.init();
});
