/**
 * 管理初始化
 */
var ScoreMoney = {
    id: "ScoreMoneyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ScoreMoney.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '起始分数', field: 'startScore', visible: true, align: 'center', valign: 'middle'},
            {title: '终止分数', field: 'endScore', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'matchingMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ScoreMoney.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ScoreMoney.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
ScoreMoney.openAddScoreMoney = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/scoreMoney/scoreMoney_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
ScoreMoney.openScoreMoneyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/scoreMoney/scoreMoney_update/' + ScoreMoney.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
ScoreMoney.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/scoreMoney/delete", function (data) {
            Feng.success("删除成功!");
            ScoreMoney.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("scoreMoneyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
ScoreMoney.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ScoreMoney.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ScoreMoney.initColumn();
    var table = new BSTable(ScoreMoney.id, "/scoreMoney/list", defaultColunms);
    table.setPaginationType("client");
    ScoreMoney.table = table.init();
});
