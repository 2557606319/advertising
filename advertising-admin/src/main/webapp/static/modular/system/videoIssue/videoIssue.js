/**
 * 文章素材发布表管理初始化
 */
var VideoIssue = {
    id: "VideoIssueTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VideoIssue.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '发布用户', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '文章素材id', field: 'videoId', visible: true, align: 'center', valign: 'middle'},
            {title: '评论数', field: 'commentCount', visible: true, align: 'center', valign: 'middle'},
            {title: '阅读数', field: 'lookCount', visible: true, align: 'center', valign: 'middle'},
            {title: '喜欢数', field: 'likeCount', visible: true, align: 'center', valign: 'middle'},
            {title: '展示广告id', field: 'advertisingId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VideoIssue.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        VideoIssue.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文章素材发布表
 */
VideoIssue.openAddVideoIssue = function () {
    var index = layer.open({
        type: 2,
        title: '添加文章素材发布表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/videoIssue/videoIssue_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章素材发布表详情
 */
VideoIssue.openVideoIssueDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章素材发布表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/videoIssue/videoIssue_update/' + VideoIssue.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文章素材发布表
 */
VideoIssue.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/videoIssue/delete", function (data) {
            Feng.success("删除成功!");
            VideoIssue.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("videoIssueId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文章素材发布表列表
 */
VideoIssue.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    VideoIssue.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = VideoIssue.initColumn();
    var table = new BSTable(VideoIssue.id, "/videoIssue/list", defaultColunms);
    table.setPaginationType("client");
    VideoIssue.table = table.init();
});
