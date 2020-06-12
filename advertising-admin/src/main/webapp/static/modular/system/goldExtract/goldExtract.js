/**
 * 金币明提现申请表管理初始化
 */
var GoldExtract = {
    id: "GoldExtractTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GoldExtract.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '金币数', field: 'goldNum', visible: true, align: 'center', valign: 'middle'},
            {title: '提现类型 0-支付宝 1-微信', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '提现账户', field: 'account', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'},
            {title: '审核状态 0-待审核 1-审核成功  2-审核失败', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '审核时间', field: 'authTime', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
GoldExtract.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        GoldExtract.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加金币明提现申请表
 */
GoldExtract.openAddGoldExtract = function () {
    var index = layer.open({
        type: 2,
        title: '添加金币明提现申请表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/goldExtract/goldExtract_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看金币明提现申请表详情
 */
GoldExtract.openGoldExtractDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '金币明提现申请表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/goldExtract/goldExtract_update/' + GoldExtract.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除金币明提现申请表
 */
GoldExtract.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/goldExtract/delete", function (data) {
            Feng.success("删除成功!");
            GoldExtract.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("goldExtractId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询金币明提现申请表列表
 */
GoldExtract.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    GoldExtract.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = GoldExtract.initColumn();
    var table = new BSTable(GoldExtract.id, "/goldExtract/list", defaultColunms);
    table.setPaginationType("client");
    GoldExtract.table = table.init();
});
