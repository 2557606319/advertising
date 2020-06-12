/**
 * 文章发布表管理初始化
 */
var ArticleIssue = {
    id: "ArticleIssueTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ArticleIssue.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '发布用户', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '文章素材id', field: 'articleId', visible: true, align: 'center', valign: 'middle'},
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
ArticleIssue.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ArticleIssue.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文章发布表
 */
ArticleIssue.openAddArticleIssue = function () {
    var index = layer.open({
        type: 2,
        title: '添加文章发布表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/articleIssue/articleIssue_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章发布表详情
 */
ArticleIssue.openArticleIssueDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章发布表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/articleIssue/articleIssue_update/' + ArticleIssue.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文章发布表
 */
ArticleIssue.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/articleIssue/delete", function (data) {
            Feng.success("删除成功!");
            ArticleIssue.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("articleIssueId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文章发布表列表
 */
ArticleIssue.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ArticleIssue.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ArticleIssue.initColumn();
    var table = new BSTable(ArticleIssue.id, "/articleIssue/list", defaultColunms);
    table.setPaginationType("client");
    ArticleIssue.table = table.init();
});
