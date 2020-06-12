/**
 * 广告信息表管理初始化
 */
var Advertising = {
    id: "AdvertisingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Advertising.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'describe', visible: true, align: 'center', valign: 'middle'},
            {title: '封面', field: 'cover', visible: true, align: 'center', valign: 'middle'},
            {title: '是否默认 0-否 1-是', field: 'default', visible: true, align: 'center', valign: 'middle'},
            {title: '点击跳转链接', field: 'href', visible: true, align: 'center', valign: 'middle'},
            {title: '商品口令', field: 'goodsCommand', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '奖励金币', field: 'award', visible: true, align: 'center', valign: 'middle'},
            {title: '总奖励金币', field: 'sumAward', visible: true, align: 'center', valign: 'middle'},
            {title: '到期时间', field: 'expire', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Advertising.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Advertising.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加广告信息表
 */
Advertising.openAddAdvertising = function () {
    var index = layer.open({
        type: 2,
        title: '添加广告信息表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/advertising/advertising_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看广告信息表详情
 */
Advertising.openAdvertisingDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '广告信息表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/advertising/advertising_update/' + Advertising.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除广告信息表
 */
Advertising.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/advertising/delete", function (data) {
            Feng.success("删除成功!");
            Advertising.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("advertisingId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询广告信息表列表
 */
Advertising.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Advertising.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Advertising.initColumn();
    var table = new BSTable(Advertising.id, "/advertising/list", defaultColunms);
    table.setPaginationType("client");
    Advertising.table = table.init();
});
