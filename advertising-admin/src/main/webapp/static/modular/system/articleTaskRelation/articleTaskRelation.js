/**
 * 文章金币任务与发布者关联表管理初始化
 */
var ArticleTaskRelation = {
    id: "ArticleTaskRelationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ArticleTaskRelation.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '任务id', field: 'taskId', visible: true, align: 'center', valign: 'middle'},
            {title: '关联id', field: 'relationId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ArticleTaskRelation.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ArticleTaskRelation.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文章金币任务与发布者关联表
 */
ArticleTaskRelation.openAddArticleTaskRelation = function () {
    var index = layer.open({
        type: 2,
        title: '添加文章金币任务与发布者关联表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/articleTaskRelation/articleTaskRelation_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章金币任务与发布者关联表详情
 */
ArticleTaskRelation.openArticleTaskRelationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章金币任务与发布者关联表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/articleTaskRelation/articleTaskRelation_update/' + ArticleTaskRelation.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文章金币任务与发布者关联表
 */
ArticleTaskRelation.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/articleTaskRelation/delete", function (data) {
            Feng.success("删除成功!");
            ArticleTaskRelation.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("articleTaskRelationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文章金币任务与发布者关联表列表
 */
ArticleTaskRelation.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ArticleTaskRelation.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ArticleTaskRelation.initColumn();
    var table = new BSTable(ArticleTaskRelation.id, "/articleTaskRelation/list", defaultColunms);
    table.setPaginationType("client");
    ArticleTaskRelation.table = table.init();
});
