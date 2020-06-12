/**
 * 文章金币任务表管理初始化
 */
var ArticleTask = {
    id: "ArticleTaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ArticleTask.initColumn = function () {
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
ArticleTask.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ArticleTask.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文章金币任务表
 */
ArticleTask.openAddArticleTask = function () {
    var index = layer.open({
        type: 2,
        title: '添加文章金币任务表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/articleTask/articleTask_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章金币任务表详情
 */
ArticleTask.openArticleTaskDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章金币任务表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/articleTask/articleTask_update/' + ArticleTask.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文章金币任务表
 */
ArticleTask.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/articleTask/delete", function (data) {
            Feng.success("删除成功!");
            ArticleTask.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("articleTaskId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文章金币任务表列表
 */
ArticleTask.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ArticleTask.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ArticleTask.initColumn();
    var table = new BSTable(ArticleTask.id, "/articleTask/list", defaultColunms);
    table.setPaginationType("client");
    ArticleTask.table = table.init();
});
