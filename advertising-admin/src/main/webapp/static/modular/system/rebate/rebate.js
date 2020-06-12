/**
 * 返佣明细表管理初始化
 */
var Rebate = {
    id: "RebateTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Rebate.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '收益用户', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '目标用户', field: 'targetUserId', visible: true, align: 'center', valign: 'middle'},
            {title: '消费金额', field: 'proMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '佣金', field: 'commission', visible: true, align: 'center', valign: 'middle'},
            {title: '返佣比例', field: 'ratio', visible: true, align: 'center', valign: 'middle'},
            {title: '获得金币', field: 'gold', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Rebate.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Rebate.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加返佣明细表
 */
Rebate.openAddRebate = function () {
    var index = layer.open({
        type: 2,
        title: '添加返佣明细表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/rebate/rebate_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看返佣明细表详情
 */
Rebate.openRebateDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '返佣明细表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/rebate/rebate_update/' + Rebate.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除返佣明细表
 */
Rebate.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/rebate/delete", function (data) {
            Feng.success("删除成功!");
            Rebate.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("rebateId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询返佣明细表列表
 */
Rebate.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Rebate.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Rebate.initColumn();
    var table = new BSTable(Rebate.id, "/rebate/list", defaultColunms);
    table.setPaginationType("client");
    Rebate.table = table.init();
});
