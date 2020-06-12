/**
 * 视频金币任务表管理初始化
 */
var VideoTask = {
    id: "VideoTaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VideoTask.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '发起用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '阅读奖励金币', field: 'award', visible: true, align: 'center', valign: 'middle'},
            {title: '总奖励金币', field: 'sumAward', visible: true, align: 'center', valign: 'middle'},
            {title: '到期时间', field: 'expire', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'},
            {title: '推广人数', field: 'generalizeCount', visible: true, align: 'center', valign: 'middle'},
            {title: '浏览人数', field: 'lookCount', visible: true, align: 'center', valign: 'middle'},
            {title: '广告id', field: 'advertisingId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VideoTask.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        VideoTask.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加视频金币任务表
 */
VideoTask.openAddVideoTask = function () {
    var index = layer.open({
        type: 2,
        title: '添加视频金币任务表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/videoTask/videoTask_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看视频金币任务表详情
 */
VideoTask.openVideoTaskDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '视频金币任务表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/videoTask/videoTask_update/' + VideoTask.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除视频金币任务表
 */
VideoTask.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/videoTask/delete", function (data) {
            Feng.success("删除成功!");
            VideoTask.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("videoTaskId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询视频金币任务表列表
 */
VideoTask.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    VideoTask.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = VideoTask.initColumn();
    var table = new BSTable(VideoTask.id, "/videoTask/list", defaultColunms);
    table.setPaginationType("client");
    VideoTask.table = table.init();
});
