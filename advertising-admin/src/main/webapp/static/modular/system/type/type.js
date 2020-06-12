/**
 * 素材类型表管理初始化
 */
var Type = {
    id: "TypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Type.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'tname', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Type.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Type.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加素材类型表
 */
Type.openAddType = function () {
    var index = layer.open({
        type: 2,
        title: '添加素材类型表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/type/type_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看素材类型表详情
 */
Type.openTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '素材类型表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/type/type_update/' + Type.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除素材类型表
 */
Type.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/type/delete", function (data) {
            Feng.success("删除成功!");
            Type.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("typeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询素材类型表列表
 */
Type.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Type.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Type.initColumn();
    var table = new BSTable(Type.id, "/type/list", defaultColunms);
    table.setPaginationType("client");
    Type.table = table.init();
});
