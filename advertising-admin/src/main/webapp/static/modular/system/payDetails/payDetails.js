/**
 * 支付明细表管理初始化
 */
var PayDetails = {
    id: "PayDetailsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PayDetails.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '交易编号', field: 'num', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '支付编号', field: 'payNum', visible: true, align: 'center', valign: 'middle'},
            {title: '金额', field: 'money', visible: true, align: 'center', valign: 'middle'},
            {title: '支付状态  0-待支付  1-支付成功 2-支付失败', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PayDetails.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PayDetails.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加支付明细表
 */
PayDetails.openAddPayDetails = function () {
    var index = layer.open({
        type: 2,
        title: '添加支付明细表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/payDetails/payDetails_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看支付明细表详情
 */
PayDetails.openPayDetailsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '支付明细表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/payDetails/payDetails_update/' + PayDetails.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除支付明细表
 */
PayDetails.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/payDetails/delete", function (data) {
            Feng.success("删除成功!");
            PayDetails.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("payDetailsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询支付明细表列表
 */
PayDetails.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PayDetails.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PayDetails.initColumn();
    var table = new BSTable(PayDetails.id, "/payDetails/list", defaultColunms);
    table.setPaginationType("client");
    PayDetails.table = table.init();
});
