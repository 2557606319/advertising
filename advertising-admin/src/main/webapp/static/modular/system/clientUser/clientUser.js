/**
 * 客户端用户管理初始化
 */
var ClientUser = {
    id: "ClientUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ClientUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '昵称', field: 'nick', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'avatar', visible: true, align: 'center', valign: 'middle'},
            {title: '行业', field: 'industry', visible: true, align: 'center', valign: 'middle'},
            {title: '职业', field: 'professional', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '微信二维码', field: 'wxqr', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '金币余额', field: 'goldBalance', visible: true, align: 'center', valign: 'middle'},
            {title: '总获佣金', field: 'sumCommission', visible: true, align: 'center', valign: 'middle'},
            {title: '总获下级佣金', field: 'sumLower', visible: true, align: 'center', valign: 'middle'},
            {title: '是否为vip 0-不是  1-是', field: 'vip', visible: true, align: 'center', valign: 'middle'},
            {title: 'vip到期时间', field: 'vipExpire', visible: true, align: 'center', valign: 'middle'},
            {title: '代理登记 0-青铜 1-黄金 2-钻石', field: 'agencyLevel', visible: true, align: 'center', valign: 'middle'},
            {title: '一层返佣比例', field: 'earnings1', visible: true, align: 'center', valign: 'middle'},
            {title: '二层返佣比例', field: 'earnings2', visible: true, align: 'center', valign: 'middle'},
            {title: '三层返佣比例', field: 'earnings3', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'ctime', visible: true, align: 'center', valign: 'middle'},
            {title: '邀请人', field: 'pid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ClientUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ClientUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加客户端用户
 */
ClientUser.openAddClientUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加客户端用户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/clientUser/clientUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看客户端用户详情
 */
ClientUser.openClientUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '客户端用户详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/clientUser/clientUser_update/' + ClientUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除客户端用户
 */
ClientUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/clientUser/delete", function (data) {
            Feng.success("删除成功!");
            ClientUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("clientUserId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询客户端用户列表
 */
ClientUser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ClientUser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ClientUser.initColumn();
    var table = new BSTable(ClientUser.id, "/clientUser/list", defaultColunms);
    table.setPaginationType("client");
    ClientUser.table = table.init();
});
