/**
 * 发布商品浏览记录表管理初始化
 */
var VideoIssueLooks = {
    id: "VideoIssueLooksTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VideoIssueLooks.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: ' ', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '发布id', field: 'issueId', visible: true, align: 'center', valign: 'middle'},
            {title: '任务分享id', field: 'taskRelationId', visible: true, align: 'center', valign: 'middle'},
            {title: '浏览用户id', field: 'lookUserId', visible: true, align: 'center', valign: 'middle'},
            {title: '浏览时长', field: 'lookTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否点击广告 0-未点击 1-已点击', field: 'isClick', visible: true, align: 'center', valign: 'middle'},
            {title: '是否复制商品链接', field: 'isCopy', visible: true, align: 'center', valign: 'middle'},
            {title: '是否点击联系方式', field: 'isRelation', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VideoIssueLooks.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        VideoIssueLooks.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加发布商品浏览记录表
 */
VideoIssueLooks.openAddVideoIssueLooks = function () {
    var index = layer.open({
        type: 2,
        title: '添加发布商品浏览记录表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/videoIssueLooks/videoIssueLooks_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看发布商品浏览记录表详情
 */
VideoIssueLooks.openVideoIssueLooksDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '发布商品浏览记录表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/videoIssueLooks/videoIssueLooks_update/' + VideoIssueLooks.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除发布商品浏览记录表
 */
VideoIssueLooks.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/videoIssueLooks/delete", function (data) {
            Feng.success("删除成功!");
            VideoIssueLooks.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("videoIssueLooksId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询发布商品浏览记录表列表
 */
VideoIssueLooks.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    VideoIssueLooks.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = VideoIssueLooks.initColumn();
    var table = new BSTable(VideoIssueLooks.id, "/videoIssueLooks/list", defaultColunms);
    table.setPaginationType("client");
    VideoIssueLooks.table = table.init();
});
