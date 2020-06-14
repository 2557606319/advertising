/*
 Navicat MySQL Data Transfer

 Source Server         : 本地开发
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : advertising_test

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 14/06/2020 15:38:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) DEFAULT NULL COMMENT '父级ids',
  `simplename` varchar(45) DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (24, 1, 0, '[0],', '总公司', '总公司', '', NULL);
INSERT INTO `sys_dept` VALUES (25, 2, 24, '[0],[24],', '开发部', '开发部', '', NULL);
INSERT INTO `sys_dept` VALUES (26, 3, 24, '[0],[24],', '运营部', '运营部', '', NULL);
INSERT INTO `sys_dept` VALUES (27, 4, 24, '[0],[24],', '战略部', '战略部', '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '父级字典',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `code` varchar(255) DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (50, 0, 0, '性别', NULL, 'sys_sex');
INSERT INTO `sys_dict` VALUES (51, 1, 50, '男', NULL, '1');
INSERT INTO `sys_dict` VALUES (52, 2, 50, '女', NULL, '2');
INSERT INTO `sys_dict` VALUES (53, 0, 0, '状态', NULL, 'sys_state');
INSERT INTO `sys_dict` VALUES (54, 1, 53, '启用', NULL, '1');
INSERT INTO `sys_dict` VALUES (55, 2, 53, '禁用', NULL, '2');
INSERT INTO `sys_dict` VALUES (56, 0, 0, '账号状态', NULL, 'account_state');
INSERT INTO `sys_dict` VALUES (57, 1, 56, '启用', NULL, '1');
INSERT INTO `sys_dict` VALUES (58, 2, 56, '冻结', NULL, '2');
INSERT INTO `sys_dict` VALUES (59, 3, 56, '已删除', NULL, '3');
COMMIT;

-- ----------------------------
-- Table structure for sys_expense
-- ----------------------------
DROP TABLE IF EXISTS `sys_expense`;
CREATE TABLE `sys_expense` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` decimal(20,2) DEFAULT NULL COMMENT '报销金额',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `state` int(11) DEFAULT NULL COMMENT '状态: 1.待提交  2:待审核   3.审核通过 4:驳回',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `processId` varchar(255) DEFAULT NULL COMMENT '流程定义id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报销表';

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '管理员id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否执行成功',
  `message` text COMMENT '具体消息',
  `ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8 COMMENT='登录记录';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_login_log` VALUES (217, '登录日志', 1, '2020-03-24 16:28:09', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (218, '退出日志', 1, '2020-03-24 16:29:30', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (219, '登录日志', 1, '2020-03-24 16:30:26', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (220, '登录日志', 1, '2020-03-25 00:24:14', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (221, '登录日志', 1, '2020-03-25 10:48:23', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (222, '退出日志', 1, '2020-03-25 10:48:28', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (223, '登录日志', 1, '2020-03-25 10:48:39', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (224, '退出日志', 1, '2020-03-25 11:25:45', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (225, '登录日志', 1, '2020-03-25 11:25:48', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (226, '登录日志', 1, '2020-03-25 11:32:34', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (227, '登录日志', 1, '2020-03-25 15:57:33', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (228, '登录日志', 1, '2020-03-25 16:00:36', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (229, '登录日志', 1, '2020-03-25 16:13:41', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (230, '登录日志', 1, '2020-03-26 10:31:09', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (231, '登录日志', 1, '2020-03-26 10:32:30', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (232, '登录日志', 1, '2020-03-27 00:27:39', '成功', NULL, '0:0:0:0:0:0:0:1');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `num` int(65) DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) DEFAULT NULL COMMENT '菜单层级',
  `ismenu` int(11) DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `isopen` int(11) DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1242847975768088583 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (105, 'system', '0', '[0],', '系统管理', 'fa-user', '#', 4, 1, 1, NULL, 1, 1);
INSERT INTO `sys_menu` VALUES (106, 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', 1, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (107, 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', NULL, '/mgr/add', 1, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (108, 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', NULL, '/mgr/edit', 2, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (109, 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', NULL, '/mgr/delete', 3, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (110, 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', NULL, '/mgr/reset', 4, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (111, 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', NULL, '/mgr/freeze', 5, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (112, 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', NULL, '/mgr/unfreeze', 6, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (113, 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', NULL, '/mgr/setRole', 7, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (114, 'role', 'system', '[0],[system],', '角色管理', NULL, '/role', 2, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (115, 'role_add', 'role', '[0],[system],[role],', '添加角色', NULL, '/role/add', 1, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (116, 'role_edit', 'role', '[0],[system],[role],', '修改角色', NULL, '/role/edit', 2, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (117, 'role_remove', 'role', '[0],[system],[role],', '删除角色', NULL, '/role/remove', 3, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (118, 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', NULL, '/role/setAuthority', 4, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (119, 'menu', 'system', '[0],[system],', '菜单管理', NULL, '/menu', 4, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (120, 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', NULL, '/menu/add', 1, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (121, 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', NULL, '/menu/edit', 2, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (122, 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', NULL, '/menu/remove', 3, 3, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (128, 'log', 'system', '[0],[system],', '业务日志', NULL, '/log', 6, 2, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (130, 'druid', 'system', '[0],[system],', '监控管理', NULL, '/druid', 7, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (131, 'dept', 'system', '[0],[system],', '部门管理', NULL, '/dept', 3, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (132, 'dict', 'system', '[0],[system],', '字典管理', NULL, '/dict', 4, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (133, 'loginLog', 'system', '[0],[system],', '登录日志', NULL, '/loginLog', 6, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (134, 'log_clean', 'log', '[0],[system],[log],', '清空日志', NULL, '/log/delLog', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (135, 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', NULL, '/dept/add', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (136, 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', NULL, '/dept/update', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (137, 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', NULL, '/dept/delete', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (138, 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', NULL, '/dict/add', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (139, 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', NULL, '/dict/update', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (140, 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', NULL, '/dict/delete', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (141, 'notice', 'system', '[0],[system],', '通知管理', NULL, '/notice', 9, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (142, 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', NULL, '/notice/add', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (143, 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', NULL, '/notice/update', 2, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (144, 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', NULL, '/notice/delete', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (145, 'hello', '0', '[0],', '通知', 'fa-rocket', '/notice/hello', 1, 1, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (148, 'code', '0', '[0],', '代码生成', 'fa-code', '/code', 3, 1, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (149, 'api_mgr', '0', '[0],', '接口文档', 'fa-leaf', '/swagger-ui.html', 2, 1, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (150, 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', 4, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (151, 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (152, 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', 4, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (153, 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (154, 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', 6, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (155, 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', 4, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (156, 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (157, 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', 6, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (158, 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', 2, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (159, 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (160, 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', 1, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (161, 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', 2, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (162, 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', 5, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (163, 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', 6, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (164, 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', 7, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (165, 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', 8, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (166, 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', 9, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (167, 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', 10, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1242608959516880897, 'article', '0', '[0],', '文章素材', '', '/article', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242608959516880898, 'article_list', 'article', '[0],[article],', '文章素材列表', '', '/article/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242608959516880899, 'article_add', 'article', '[0],[article],', '文章素材添加', '', '/article/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242608959516880900, 'article_update', 'article', '[0],[article],', '文章素材更新', '', '/article/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242608959516880901, 'article_delete', 'article', '[0],[article],', '文章素材删除', '', '/article/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242608959516880902, 'article_detail', 'article', '[0],[article],', '文章素材详情', '', '/article/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610176771010561, 'advertising', '0', '[0],', '广告信息表', '', '/advertising', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610176771010562, 'advertising_list', 'advertising', '[0],[advertising],', '广告信息表列表', '', '/advertising/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610176771010563, 'advertising_add', 'advertising', '[0],[advertising],', '广告信息表添加', '', '/advertising/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610176771010564, 'advertising_update', 'advertising', '[0],[advertising],', '广告信息表更新', '', '/advertising/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610176771010565, 'advertising_delete', 'advertising', '[0],[advertising],', '广告信息表删除', '', '/advertising/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610176771010566, 'advertising_detail', 'advertising', '[0],[advertising],', '广告信息表详情', '', '/advertising/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610327220695041, 'articleIssue', '0', '[0],', '文章发布表', '', '/articleIssue', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610327220695042, 'articleIssue_list', 'articleIssue', '[0],[articleIssue],', '文章发布表列表', '', '/articleIssue/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610327220695043, 'articleIssue_add', 'articleIssue', '[0],[articleIssue],', '文章发布表添加', '', '/articleIssue/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610327220695044, 'articleIssue_update', 'articleIssue', '[0],[articleIssue],', '文章发布表更新', '', '/articleIssue/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610327220695045, 'articleIssue_delete', 'articleIssue', '[0],[articleIssue],', '文章发布表删除', '', '/articleIssue/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610327220695046, 'articleIssue_detail', 'articleIssue', '[0],[articleIssue],', '文章发布表详情', '', '/articleIssue/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610448780013570, 'articleIssueLooks', '0', '[0],', '发布的文章表浏览记录', '', '/articleIssueLooks', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610448780013571, 'articleIssueLooks_list', 'articleIssueLooks', '[0],[articleIssueLooks],', '发布的文章表浏览记录列表', '', '/articleIssueLooks/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610448780013572, 'articleIssueLooks_add', 'articleIssueLooks', '[0],[articleIssueLooks],', '发布的文章表浏览记录添加', '', '/articleIssueLooks/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610448780013573, 'articleIssueLooks_update', 'articleIssueLooks', '[0],[articleIssueLooks],', '发布的文章表浏览记录更新', '', '/articleIssueLooks/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610448780013574, 'articleIssueLooks_delete', 'articleIssueLooks', '[0],[articleIssueLooks],', '发布的文章表浏览记录删除', '', '/articleIssueLooks/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610448780013575, 'articleIssueLooks_detail', 'articleIssueLooks', '[0],[articleIssueLooks],', '发布的文章表浏览记录详情', '', '/articleIssueLooks/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610549011296258, 'articleTask', '0', '[0],', '文章金币任务表', '', '/articleTask', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610549011296259, 'articleTask_list', 'articleTask', '[0],[articleTask],', '文章金币任务表列表', '', '/articleTask/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610549011296260, 'articleTask_add', 'articleTask', '[0],[articleTask],', '文章金币任务表添加', '', '/articleTask/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610549011296261, 'articleTask_update', 'articleTask', '[0],[articleTask],', '文章金币任务表更新', '', '/articleTask/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610549011296262, 'articleTask_delete', 'articleTask', '[0],[articleTask],', '文章金币任务表删除', '', '/articleTask/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610549011296263, 'articleTask_detail', 'articleTask', '[0],[articleTask],', '文章金币任务表详情', '', '/articleTask/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610798383640577, 'articleTaskRelation', '0', '[0],', '文章金币任务与发布者关联表', '', '/articleTaskRelation', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610798387834882, 'articleTaskRelation_list', 'articleTaskRelation', '[0],[articleTaskRelation],', '文章金币任务与发布者关联表列表', '', '/articleTaskRelation/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610798387834883, 'articleTaskRelation_add', 'articleTaskRelation', '[0],[articleTaskRelation],', '文章金币任务与发布者关联表添加', '', '/articleTaskRelation/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610798387834884, 'articleTaskRelation_update', 'articleTaskRelation', '[0],[articleTaskRelation],', '文章金币任务与发布者关联表更新', '', '/articleTaskRelation/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610798387834885, 'articleTaskRelation_delete', 'articleTaskRelation', '[0],[articleTaskRelation],', '文章金币任务与发布者关联表删除', '', '/articleTaskRelation/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610798387834886, 'articleTaskRelation_detail', 'articleTaskRelation', '[0],[articleTaskRelation],', '文章金币任务与发布者关联表详情', '', '/articleTaskRelation/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610935654821889, 'goldDetails', '0', '[0],', '金币明细表', '', '/goldDetails', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610935654821890, 'goldDetails_list', 'goldDetails', '[0],[goldDetails],', '金币明细表列表', '', '/goldDetails/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610935654821891, 'goldDetails_add', 'goldDetails', '[0],[goldDetails],', '金币明细表添加', '', '/goldDetails/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610935654821892, 'goldDetails_update', 'goldDetails', '[0],[goldDetails],', '金币明细表更新', '', '/goldDetails/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610935654821893, 'goldDetails_delete', 'goldDetails', '[0],[goldDetails],', '金币明细表删除', '', '/goldDetails/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242610935654821894, 'goldDetails_detail', 'goldDetails', '[0],[goldDetails],', '金币明细表详情', '', '/goldDetails/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611004894392321, 'goldExtract', '0', '[0],', '金币明提现申请表', '', '/goldExtract', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611004894392322, 'goldExtract_list', 'goldExtract', '[0],[goldExtract],', '金币明提现申请表列表', '', '/goldExtract/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611004894392323, 'goldExtract_add', 'goldExtract', '[0],[goldExtract],', '金币明提现申请表添加', '', '/goldExtract/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611004894392324, 'goldExtract_update', 'goldExtract', '[0],[goldExtract],', '金币明提现申请表更新', '', '/goldExtract/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611004894392325, 'goldExtract_delete', 'goldExtract', '[0],[goldExtract],', '金币明提现申请表删除', '', '/goldExtract/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611004894392326, 'goldExtract_detail', 'goldExtract', '[0],[goldExtract],', '金币明提现申请表详情', '', '/goldExtract/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611081708875777, 'payDetails', '0', '[0],', '支付明细表', '', '/payDetails', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611081708875778, 'payDetails_list', 'payDetails', '[0],[payDetails],', '支付明细表列表', '', '/payDetails/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611081708875779, 'payDetails_add', 'payDetails', '[0],[payDetails],', '支付明细表添加', '', '/payDetails/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611081708875780, 'payDetails_update', 'payDetails', '[0],[payDetails],', '支付明细表更新', '', '/payDetails/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611081708875781, 'payDetails_delete', 'payDetails', '[0],[payDetails],', '支付明细表删除', '', '/payDetails/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611081708875782, 'payDetails_detail', 'payDetails', '[0],[payDetails],', '支付明细表详情', '', '/payDetails/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611142664695810, 'rebate', '0', '[0],', '返佣明细表', '', '/rebate', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611142664695811, 'rebate_list', 'rebate', '[0],[rebate],', '返佣明细表列表', '', '/rebate/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611142664695812, 'rebate_add', 'rebate', '[0],[rebate],', '返佣明细表添加', '', '/rebate/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611142664695813, 'rebate_update', 'rebate', '[0],[rebate],', '返佣明细表更新', '', '/rebate/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611142664695814, 'rebate_delete', 'rebate', '[0],[rebate],', '返佣明细表删除', '', '/rebate/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611142664695815, 'rebate_detail', 'rebate', '[0],[rebate],', '返佣明细表详情', '', '/rebate/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611254728110082, 'type', '0', '[0],', '素材类型表', '', '/type', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611254728110083, 'type_list', 'type', '[0],[type],', '素材类型表列表', '', '/type/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611254728110084, 'type_add', 'type', '[0],[type],', '素材类型表添加', '', '/type/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611254728110085, 'type_update', 'type', '[0],[type],', '素材类型表更新', '', '/type/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611254728110086, 'type_delete', 'type', '[0],[type],', '素材类型表删除', '', '/type/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611254728110087, 'type_detail', 'type', '[0],[type],', '素材类型表详情', '', '/type/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611317210656770, 'user', '0', '[0],', '用户表', '', '/user', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611317210656771, 'user_list', 'user', '[0],[user],', '用户表列表', '', '/user/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611317210656772, 'user_add', 'user', '[0],[user],', '用户表添加', '', '/user/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611317210656773, 'user_update', 'user', '[0],[user],', '用户表更新', '', '/user/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611317210656774, 'user_delete', 'user', '[0],[user],', '用户表删除', '', '/user/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611317210656775, 'user_detail', 'user', '[0],[user],', '用户表详情', '', '/user/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611436471496705, 'video', '0', '[0],', '视频素材表', '', '/video', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611436471496706, 'video_list', 'video', '[0],[video],', '视频素材表列表', '', '/video/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611436471496707, 'video_add', 'video', '[0],[video],', '视频素材表添加', '', '/video/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611436471496708, 'video_update', 'video', '[0],[video],', '视频素材表更新', '', '/video/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611436471496709, 'video_delete', 'video', '[0],[video],', '视频素材表删除', '', '/video/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242611436471496710, 'video_detail', 'video', '[0],[video],', '视频素材表详情', '', '/video/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765541231865857, 'videoIssue', '0', '[0],', '文章素材发布表', '', '/videoIssue', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765541231865858, 'videoIssue_list', 'videoIssue', '[0],[videoIssue],', '文章素材发布表列表', '', '/videoIssue/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765541231865859, 'videoIssue_add', 'videoIssue', '[0],[videoIssue],', '文章素材发布表添加', '', '/videoIssue/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765541231865860, 'videoIssue_update', 'videoIssue', '[0],[videoIssue],', '文章素材发布表更新', '', '/videoIssue/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765541231865861, 'videoIssue_delete', 'videoIssue', '[0],[videoIssue],', '文章素材发布表删除', '', '/videoIssue/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765541231865862, 'videoIssue_detail', 'videoIssue', '[0],[videoIssue],', '文章素材发布表详情', '', '/videoIssue/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765636610338817, 'videoIssueLooks', '0', '[0],', '发布商品浏览记录表', '', '/videoIssueLooks', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765636610338818, 'videoIssueLooks_list', 'videoIssueLooks', '[0],[videoIssueLooks],', '发布商品浏览记录表列表', '', '/videoIssueLooks/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765636610338819, 'videoIssueLooks_add', 'videoIssueLooks', '[0],[videoIssueLooks],', '发布商品浏览记录表添加', '', '/videoIssueLooks/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765636610338820, 'videoIssueLooks_update', 'videoIssueLooks', '[0],[videoIssueLooks],', '发布商品浏览记录表更新', '', '/videoIssueLooks/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765636610338821, 'videoIssueLooks_delete', 'videoIssueLooks', '[0],[videoIssueLooks],', '发布商品浏览记录表删除', '', '/videoIssueLooks/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765636610338822, 'videoIssueLooks_detail', 'videoIssueLooks', '[0],[videoIssueLooks],', '发布商品浏览记录表详情', '', '/videoIssueLooks/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765734069186562, 'videoTask', '0', '[0],', '视频金币任务表', '', '/videoTask', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765734069186563, 'videoTask_list', 'videoTask', '[0],[videoTask],', '视频金币任务表列表', '', '/videoTask/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765734069186564, 'videoTask_add', 'videoTask', '[0],[videoTask],', '视频金币任务表添加', '', '/videoTask/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765734069186565, 'videoTask_update', 'videoTask', '[0],[videoTask],', '视频金币任务表更新', '', '/videoTask/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765734069186566, 'videoTask_delete', 'videoTask', '[0],[videoTask],', '视频金币任务表删除', '', '/videoTask/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242765734069186567, 'videoTask_detail', 'videoTask', '[0],[videoTask],', '视频金币任务表详情', '', '/videoTask/detail', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242847975768088577, 'clientUser', '0', '[0],', '客户端用户', '', '/clientUser', 99, 1, 1, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242847975768088578, 'clientUser_list', 'clientUser', '[0],[clientUser],', '客户端用户列表', '', '/clientUser/list', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242847975768088579, 'clientUser_add', 'clientUser', '[0],[clientUser],', '客户端用户添加', '', '/clientUser/add', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242847975768088580, 'clientUser_update', 'clientUser', '[0],[clientUser],', '客户端用户更新', '', '/clientUser/update', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242847975768088581, 'clientUser_delete', 'clientUser', '[0],[clientUser],', '客户端用户删除', '', '/clientUser/delete', 99, 2, 0, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (1242847975768088582, 'clientUser_detail', 'clientUser', '[0],[clientUser],', '客户端用户详情', '', '/clientUser/detail', 99, 2, 0, NULL, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='通知表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
BEGIN;
INSERT INTO `sys_notice` VALUES (6, '世界', 10, '欢迎使用Guns管理系统', '2017-01-11 08:53:20', 1);
INSERT INTO `sys_notice` VALUES (8, '你好', NULL, '你好', '2017-05-10 19:28:57', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logtype` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '用户id',
  `classname` varchar(255) DEFAULT NULL COMMENT '类名称',
  `method` text COMMENT '方法名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否成功',
  `message` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=556 DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_operation_log` VALUES (554, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2020-03-25 11:25:04', '成功', '角色名称=超级管理员,资源名称=系统管理,用户管理,添加用户,修改用户,删除用户,重置密码,冻结用户,解除冻结用户,分配角色,分配角色跳转,编辑用户跳转,用户列表,角色管理,添加角色,修改角色,删除角色,配置权限,修改角色跳转,角色分配跳转,角色列表,菜单管理,添加菜单,修改菜单,删除菜单,菜单编辑跳转,菜单列表,业务日志,清空日志,日志列表,日志详情,监控管理,部门管理,添加部门,修改部门,删除部门,修改部门跳转,部门列表,部门详情,字典管理,添加字典,修改字典,删除字典,修改菜单跳转,字典列表,字典详情,登录日志,清空登录日志,登录日志列表,通知管理,添加通知,修改通知,删除通知,通知,代码生成,接口文档');
INSERT INTO `sys_operation_log` VALUES (555, '业务日志', '配置权限', 1, 'com.stylefeng.guns.modular.system.controller.RoleController', 'setAuthority', '2020-03-26 10:32:13', '成功', '角色名称=超级管理员,资源名称=系统管理,用户管理,添加用户,修改用户,删除用户,重置密码,冻结用户,解除冻结用户,分配角色,分配角色跳转,编辑用户跳转,用户列表,角色管理,添加角色,修改角色,删除角色,配置权限,修改角色跳转,角色分配跳转,角色列表,菜单管理,添加菜单,修改菜单,删除菜单,菜单编辑跳转,菜单列表,业务日志,清空日志,日志列表,日志详情,监控管理,部门管理,添加部门,修改部门,删除部门,修改部门跳转,部门列表,部门详情,字典管理,添加字典,修改字典,删除字典,修改菜单跳转,字典列表,字典详情,登录日志,清空登录日志,登录日志列表,通知管理,添加通知,修改通知,删除通知,通知,代码生成,接口文档');
COMMIT;

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` bigint(11) DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4088 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
BEGIN;
INSERT INTO `sys_relation` VALUES (3377, 105, 5);
INSERT INTO `sys_relation` VALUES (3378, 106, 5);
INSERT INTO `sys_relation` VALUES (3379, 107, 5);
INSERT INTO `sys_relation` VALUES (3380, 108, 5);
INSERT INTO `sys_relation` VALUES (3381, 109, 5);
INSERT INTO `sys_relation` VALUES (3382, 110, 5);
INSERT INTO `sys_relation` VALUES (3383, 111, 5);
INSERT INTO `sys_relation` VALUES (3384, 112, 5);
INSERT INTO `sys_relation` VALUES (3385, 113, 5);
INSERT INTO `sys_relation` VALUES (3386, 114, 5);
INSERT INTO `sys_relation` VALUES (3387, 115, 5);
INSERT INTO `sys_relation` VALUES (3388, 116, 5);
INSERT INTO `sys_relation` VALUES (3389, 117, 5);
INSERT INTO `sys_relation` VALUES (3390, 118, 5);
INSERT INTO `sys_relation` VALUES (3391, 119, 5);
INSERT INTO `sys_relation` VALUES (3392, 120, 5);
INSERT INTO `sys_relation` VALUES (3393, 121, 5);
INSERT INTO `sys_relation` VALUES (3394, 122, 5);
INSERT INTO `sys_relation` VALUES (3395, 150, 5);
INSERT INTO `sys_relation` VALUES (3396, 151, 5);
INSERT INTO `sys_relation` VALUES (3937, 105, 1);
INSERT INTO `sys_relation` VALUES (3938, 106, 1);
INSERT INTO `sys_relation` VALUES (3939, 107, 1);
INSERT INTO `sys_relation` VALUES (3940, 108, 1);
INSERT INTO `sys_relation` VALUES (3941, 109, 1);
INSERT INTO `sys_relation` VALUES (3942, 110, 1);
INSERT INTO `sys_relation` VALUES (3943, 111, 1);
INSERT INTO `sys_relation` VALUES (3944, 112, 1);
INSERT INTO `sys_relation` VALUES (3945, 113, 1);
INSERT INTO `sys_relation` VALUES (3946, 165, 1);
INSERT INTO `sys_relation` VALUES (3947, 166, 1);
INSERT INTO `sys_relation` VALUES (3948, 167, 1);
INSERT INTO `sys_relation` VALUES (3949, 114, 1);
INSERT INTO `sys_relation` VALUES (3950, 115, 1);
INSERT INTO `sys_relation` VALUES (3951, 116, 1);
INSERT INTO `sys_relation` VALUES (3952, 117, 1);
INSERT INTO `sys_relation` VALUES (3953, 118, 1);
INSERT INTO `sys_relation` VALUES (3954, 162, 1);
INSERT INTO `sys_relation` VALUES (3955, 163, 1);
INSERT INTO `sys_relation` VALUES (3956, 164, 1);
INSERT INTO `sys_relation` VALUES (3957, 119, 1);
INSERT INTO `sys_relation` VALUES (3958, 120, 1);
INSERT INTO `sys_relation` VALUES (3959, 121, 1);
INSERT INTO `sys_relation` VALUES (3960, 122, 1);
INSERT INTO `sys_relation` VALUES (3961, 150, 1);
INSERT INTO `sys_relation` VALUES (3962, 151, 1);
INSERT INTO `sys_relation` VALUES (3963, 128, 1);
INSERT INTO `sys_relation` VALUES (3964, 134, 1);
INSERT INTO `sys_relation` VALUES (3965, 158, 1);
INSERT INTO `sys_relation` VALUES (3966, 159, 1);
INSERT INTO `sys_relation` VALUES (3967, 130, 1);
INSERT INTO `sys_relation` VALUES (3968, 131, 1);
INSERT INTO `sys_relation` VALUES (3969, 135, 1);
INSERT INTO `sys_relation` VALUES (3970, 136, 1);
INSERT INTO `sys_relation` VALUES (3971, 137, 1);
INSERT INTO `sys_relation` VALUES (3972, 152, 1);
INSERT INTO `sys_relation` VALUES (3973, 153, 1);
INSERT INTO `sys_relation` VALUES (3974, 154, 1);
INSERT INTO `sys_relation` VALUES (3975, 132, 1);
INSERT INTO `sys_relation` VALUES (3976, 138, 1);
INSERT INTO `sys_relation` VALUES (3977, 139, 1);
INSERT INTO `sys_relation` VALUES (3978, 140, 1);
INSERT INTO `sys_relation` VALUES (3979, 155, 1);
INSERT INTO `sys_relation` VALUES (3980, 156, 1);
INSERT INTO `sys_relation` VALUES (3981, 157, 1);
INSERT INTO `sys_relation` VALUES (3982, 133, 1);
INSERT INTO `sys_relation` VALUES (3983, 160, 1);
INSERT INTO `sys_relation` VALUES (3984, 161, 1);
INSERT INTO `sys_relation` VALUES (3985, 141, 1);
INSERT INTO `sys_relation` VALUES (3986, 142, 1);
INSERT INTO `sys_relation` VALUES (3987, 143, 1);
INSERT INTO `sys_relation` VALUES (3988, 144, 1);
INSERT INTO `sys_relation` VALUES (3989, 145, 1);
INSERT INTO `sys_relation` VALUES (3990, 148, 1);
INSERT INTO `sys_relation` VALUES (3991, 149, 1);
INSERT INTO `sys_relation` VALUES (3992, 1242608959516880897, 1);
INSERT INTO `sys_relation` VALUES (3993, 1242608959516880898, 1);
INSERT INTO `sys_relation` VALUES (3994, 1242608959516880899, 1);
INSERT INTO `sys_relation` VALUES (3995, 1242608959516880900, 1);
INSERT INTO `sys_relation` VALUES (3996, 1242608959516880901, 1);
INSERT INTO `sys_relation` VALUES (3997, 1242608959516880902, 1);
INSERT INTO `sys_relation` VALUES (3998, 1242610176771010561, 1);
INSERT INTO `sys_relation` VALUES (3999, 1242610176771010562, 1);
INSERT INTO `sys_relation` VALUES (4000, 1242610176771010563, 1);
INSERT INTO `sys_relation` VALUES (4001, 1242610176771010564, 1);
INSERT INTO `sys_relation` VALUES (4002, 1242610176771010565, 1);
INSERT INTO `sys_relation` VALUES (4003, 1242610176771010566, 1);
INSERT INTO `sys_relation` VALUES (4004, 1242610327220695041, 1);
INSERT INTO `sys_relation` VALUES (4005, 1242610327220695042, 1);
INSERT INTO `sys_relation` VALUES (4006, 1242610327220695043, 1);
INSERT INTO `sys_relation` VALUES (4007, 1242610327220695044, 1);
INSERT INTO `sys_relation` VALUES (4008, 1242610327220695045, 1);
INSERT INTO `sys_relation` VALUES (4009, 1242610327220695046, 1);
INSERT INTO `sys_relation` VALUES (4010, 1242610448780013570, 1);
INSERT INTO `sys_relation` VALUES (4011, 1242610448780013571, 1);
INSERT INTO `sys_relation` VALUES (4012, 1242610448780013572, 1);
INSERT INTO `sys_relation` VALUES (4013, 1242610448780013573, 1);
INSERT INTO `sys_relation` VALUES (4014, 1242610448780013574, 1);
INSERT INTO `sys_relation` VALUES (4015, 1242610448780013575, 1);
INSERT INTO `sys_relation` VALUES (4016, 1242610549011296258, 1);
INSERT INTO `sys_relation` VALUES (4017, 1242610549011296259, 1);
INSERT INTO `sys_relation` VALUES (4018, 1242610549011296260, 1);
INSERT INTO `sys_relation` VALUES (4019, 1242610549011296261, 1);
INSERT INTO `sys_relation` VALUES (4020, 1242610549011296262, 1);
INSERT INTO `sys_relation` VALUES (4021, 1242610549011296263, 1);
INSERT INTO `sys_relation` VALUES (4022, 1242610935654821889, 1);
INSERT INTO `sys_relation` VALUES (4023, 1242610935654821890, 1);
INSERT INTO `sys_relation` VALUES (4024, 1242610935654821891, 1);
INSERT INTO `sys_relation` VALUES (4025, 1242610935654821892, 1);
INSERT INTO `sys_relation` VALUES (4026, 1242610935654821893, 1);
INSERT INTO `sys_relation` VALUES (4027, 1242610935654821894, 1);
INSERT INTO `sys_relation` VALUES (4028, 1242611004894392321, 1);
INSERT INTO `sys_relation` VALUES (4029, 1242611004894392322, 1);
INSERT INTO `sys_relation` VALUES (4030, 1242611004894392323, 1);
INSERT INTO `sys_relation` VALUES (4031, 1242611004894392324, 1);
INSERT INTO `sys_relation` VALUES (4032, 1242611004894392325, 1);
INSERT INTO `sys_relation` VALUES (4033, 1242611004894392326, 1);
INSERT INTO `sys_relation` VALUES (4034, 1242611081708875777, 1);
INSERT INTO `sys_relation` VALUES (4035, 1242611081708875778, 1);
INSERT INTO `sys_relation` VALUES (4036, 1242611081708875779, 1);
INSERT INTO `sys_relation` VALUES (4037, 1242611081708875780, 1);
INSERT INTO `sys_relation` VALUES (4038, 1242611081708875781, 1);
INSERT INTO `sys_relation` VALUES (4039, 1242611081708875782, 1);
INSERT INTO `sys_relation` VALUES (4040, 1242611142664695810, 1);
INSERT INTO `sys_relation` VALUES (4041, 1242611142664695811, 1);
INSERT INTO `sys_relation` VALUES (4042, 1242611142664695812, 1);
INSERT INTO `sys_relation` VALUES (4043, 1242611142664695813, 1);
INSERT INTO `sys_relation` VALUES (4044, 1242611142664695814, 1);
INSERT INTO `sys_relation` VALUES (4045, 1242611142664695815, 1);
INSERT INTO `sys_relation` VALUES (4046, 1242611254728110082, 1);
INSERT INTO `sys_relation` VALUES (4047, 1242611254728110083, 1);
INSERT INTO `sys_relation` VALUES (4048, 1242611254728110084, 1);
INSERT INTO `sys_relation` VALUES (4049, 1242611254728110085, 1);
INSERT INTO `sys_relation` VALUES (4050, 1242611254728110086, 1);
INSERT INTO `sys_relation` VALUES (4051, 1242611254728110087, 1);
INSERT INTO `sys_relation` VALUES (4052, 1242611317210656770, 1);
INSERT INTO `sys_relation` VALUES (4053, 1242611317210656771, 1);
INSERT INTO `sys_relation` VALUES (4054, 1242611317210656772, 1);
INSERT INTO `sys_relation` VALUES (4055, 1242611317210656773, 1);
INSERT INTO `sys_relation` VALUES (4056, 1242611317210656774, 1);
INSERT INTO `sys_relation` VALUES (4057, 1242611317210656775, 1);
INSERT INTO `sys_relation` VALUES (4058, 1242611436471496705, 1);
INSERT INTO `sys_relation` VALUES (4059, 1242611436471496706, 1);
INSERT INTO `sys_relation` VALUES (4060, 1242611436471496707, 1);
INSERT INTO `sys_relation` VALUES (4061, 1242611436471496708, 1);
INSERT INTO `sys_relation` VALUES (4062, 1242611436471496709, 1);
INSERT INTO `sys_relation` VALUES (4063, 1242611436471496710, 1);
INSERT INTO `sys_relation` VALUES (4064, 1242765541231865857, 1);
INSERT INTO `sys_relation` VALUES (4065, 1242765541231865858, 1);
INSERT INTO `sys_relation` VALUES (4066, 1242765541231865859, 1);
INSERT INTO `sys_relation` VALUES (4067, 1242765541231865860, 1);
INSERT INTO `sys_relation` VALUES (4068, 1242765541231865861, 1);
INSERT INTO `sys_relation` VALUES (4069, 1242765541231865862, 1);
INSERT INTO `sys_relation` VALUES (4070, 1242765636610338817, 1);
INSERT INTO `sys_relation` VALUES (4071, 1242765636610338818, 1);
INSERT INTO `sys_relation` VALUES (4072, 1242765636610338819, 1);
INSERT INTO `sys_relation` VALUES (4073, 1242765636610338820, 1);
INSERT INTO `sys_relation` VALUES (4074, 1242765636610338821, 1);
INSERT INTO `sys_relation` VALUES (4075, 1242765636610338822, 1);
INSERT INTO `sys_relation` VALUES (4076, 1242765734069186562, 1);
INSERT INTO `sys_relation` VALUES (4077, 1242765734069186563, 1);
INSERT INTO `sys_relation` VALUES (4078, 1242765734069186564, 1);
INSERT INTO `sys_relation` VALUES (4079, 1242765734069186565, 1);
INSERT INTO `sys_relation` VALUES (4080, 1242765734069186566, 1);
INSERT INTO `sys_relation` VALUES (4081, 1242765734069186567, 1);
INSERT INTO `sys_relation` VALUES (4082, 1242847975768088577, 1);
INSERT INTO `sys_relation` VALUES (4083, 1242847975768088578, 1);
INSERT INTO `sys_relation` VALUES (4084, 1242847975768088579, 1);
INSERT INTO `sys_relation` VALUES (4085, 1242847975768088580, 1);
INSERT INTO `sys_relation` VALUES (4086, 1242847975768088581, 1);
INSERT INTO `sys_relation` VALUES (4087, 1242847975768088582, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `pid` int(11) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `deptid` int(11) DEFAULT NULL COMMENT '部门名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 1, 0, '超级管理员', 24, 'administrator', 1);
INSERT INTO `sys_role` VALUES (5, 2, 1, '临时', 26, 'temp', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) DEFAULT NULL COMMENT '名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `roleid` varchar(255) DEFAULT NULL COMMENT '角色id',
  `deptid` int(11) DEFAULT NULL COMMENT '部门id',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'girl.gif', 'admin', 'ecfadcde9305f8891bcfe5a1e28c253e', '8pgby', '张三', '2017-05-05 00:00:00', 2, 'sn93@qq.com', '18200000000', '1', 27, 1, '2016-01-29 08:49:53', 25);
INSERT INTO `sys_user` VALUES (44, NULL, 'test', '45abb7879f6a8268f1ef600e6038ac73', 'ssts3', 'test', '2017-05-01 00:00:00', 1, 'abc@123.com', '', '5', 26, 3, '2017-05-16 20:33:37', NULL);
INSERT INTO `sys_user` VALUES (45, NULL, 'boss', '71887a5ad666a18f709e1d4e693d5a35', '1f7bf', '老板', '2017-12-04 00:00:00', 1, '', '', '1', 24, 1, '2017-12-04 22:24:02', NULL);
INSERT INTO `sys_user` VALUES (46, NULL, 'manager', 'b53cac62e7175637d4beb3b16b2f7915', 'j3cs9', '经理', '2017-12-04 00:00:00', 1, '', '', '1', 24, 1, '2017-12-04 22:24:24', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_advertising
-- ----------------------------
DROP TABLE IF EXISTS `tb_advertising`;
CREATE TABLE `tb_advertising` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '',
  `describe` varchar(100) DEFAULT '',
  `cover` varchar(255) DEFAULT '' COMMENT '封面',
  `is_default` int(1) DEFAULT '0' COMMENT '是否默认 0-否 1-是',
  `href` varchar(100) DEFAULT '' COMMENT '点击跳转链接',
  `goods_command` varchar(255) DEFAULT '' COMMENT '商品口令',
  `user_id` bigint(20) DEFAULT '0',
  `award` int(10) DEFAULT '1' COMMENT '奖励金币',
  `sum_award` bigint(20) DEFAULT '0' COMMENT '总奖励金币',
  `expire` datetime DEFAULT NULL COMMENT '到期时间',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='广告表';

-- ----------------------------
-- Records of tb_advertising
-- ----------------------------
BEGIN;
INSERT INTO `tb_advertising` VALUES (2, '123123', '123123', 'https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00194-1317.jpg', 0, 'https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00194-1317.jpg', '', 1, 2, 200, '2020-05-14 22:47:00', '2020-04-05 11:16:53');
INSERT INTO `tb_advertising` VALUES (3, '', '', '20200505/c2fabfa5-7117-4f30-a9ca-a517df9e931f.png', 0, '', '', 0, 1, 0, NULL, '2020-05-05 16:35:54');
INSERT INTO `tb_advertising` VALUES (4, 'awefawef', 'awefawe', '20200505/aaa25d3b-f0bd-4e37-91ac-c328ba9fd6b9.png', 0, 'awefawefaw', 'efawefaw', 0, 1, 0, NULL, '2020-05-05 16:38:30');
INSERT INTO `tb_advertising` VALUES (5, '请问发我份', '安慰发违发', '20200505/30623d76-612d-46cf-a694-90124aa89074.png', 0, '安慰发违发', '安慰发违发', 1, 1, 0, NULL, '2020-05-05 22:31:46');
INSERT INTO `tb_advertising` VALUES (6, 'awef', 'awef', '20200505/9d429d01-d1e6-417b-a658-8b1b10ede8c0.png', 0, 'awef', 'awef', 1, 1, 0, NULL, '2020-05-05 23:22:22');
INSERT INTO `tb_advertising` VALUES (7, '123123', '123123', 'https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00194-1317.jpg', 0, 'https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00194-1317.jpg', '', 1, 1, 0, NULL, '2020-05-09 23:50:23');
INSERT INTO `tb_advertising` VALUES (8, '123123ss', '123123', '20200509/814f2798-b95c-4446-95c8-240ae7350f63.jpeg', 1, 'https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00194-1317.jpg', '', 1, 1, 0, NULL, '2020-05-09 23:50:35');
INSERT INTO `tb_advertising` VALUES (9, 'awefa', 'wefawe', '20200513/7ceced2e-36f1-4200-9d8a-64a990aa66bd.jpeg', 0, 'afweawe', 'efawefawe', 1, 1, 0, NULL, '2020-05-13 23:31:08');
INSERT INTO `tb_advertising` VALUES (10, '啦啦啦啦啦', '啦啦啦啦', '20200606/b27b0de6-9a61-4ed7-ae77-cdb6eb55f35c.png', 1, 'http://joeyjava.ticp.io/advertising/module/add-adver.html', '', 2, 2, 100, '2020-06-23 00:00:00', '2020-06-06 10:37:01');
INSERT INTO `tb_advertising` VALUES (11, '啦啦啦', '啦啦啦', '20200606/838ca8b5-6870-4815-8825-942cbb8aa2b0.jpeg', 0, '啦啦啦', '', 2, 1, 0, NULL, '2020-06-06 14:32:41');
COMMIT;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0' COMMENT '创建用户',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(100) DEFAULT '',
  `describe` varchar(255) DEFAULT '',
  `previews` varchar(255) DEFAULT '' COMMENT '前三张预览图',
  `url` varchar(255) DEFAULT '' COMMENT '文章页面地址',
  `look_count` bigint(20) DEFAULT '0' COMMENT '素材总浏览量',
  `type_id` int(11) DEFAULT '0' COMMENT '素材类型id',
  `comment_count` bigint(20) DEFAULT '0' COMMENT '浏览量',
  `like_count` bigint(20) DEFAULT '0' COMMENT '喜欢量',
  `looking` bigint(20) DEFAULT '0' COMMENT '在看量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `url-unq` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COMMENT='文章素材表';

-- ----------------------------
-- Records of tb_article
-- ----------------------------
BEGIN;
INSERT INTO `tb_article` VALUES (26, 1, '2020-04-02 11:44:22', '中国“盖楼”世界第一，但也应该适可而止了，“后遗症”已经暴发', '', 'http://p9.pstatp.com/list/300x196/pgc-image/c01a92cc2bc949818dfd5bb921fed00b.jpg', '1249fadef908eb503af8cd2612271a4a.html', 0, 1, 0, 0, 0);
INSERT INTO `tb_article` VALUES (27, 1, '2020-04-02 11:56:39', 'NBA巨星兄弟新冠去世亲人昏迷，他在中国炮轰：美国付出惨痛代价', '', 'http://p9.pstatp.com/list/300x196/pgc-image/f8945b59a7954556b0318fe8dc4cae88.jpg', '325b0afc21fd440e577d2ff9b50e78ad.html', 25555, 0, 0, 0, 0);
INSERT INTO `tb_article` VALUES (28, 1, '2020-04-02 12:35:30', '美确诊破18万！股市再跌！特朗普语调阴沉，发出重要呼吁', '', 'http://p9.pstatp.com/list/300x196/pgc-image/3fa732974b964c13bed01c645278d2cc.jpg', 'd4cb668228f15a68b17792d0f64e9e7f.html', 0, 1, 0, 0, 0);
INSERT INTO `tb_article` VALUES (29, 1, '2020-04-02 12:36:52', '“百家号”运营两年多了，写了近千篇文章，谈一谈自己的想法', '', 'https://pics3.baidu.com/feed/b90e7bec54e736d1ffbe12b8ca40fac7d46269e3.jpeg?token=2e233a3a61498110e523ff1cf1a85f1d&s=3053396C12F089DE0E77A10A0200509B', '6ed1b01c4466ab36a80e57a7356dea6e.html', 0, 1, 0, 0, 0);
INSERT INTO `tb_article` VALUES (30, 1, '2020-04-02 12:38:41', '人体最娇嫩的部位，居然一杯热茶就能毁掉它？', '', '/20200402/45d5af8d-076d-464f-baff-c186ca74886f.jpeg', 'aa436ef54554d1e8a211e701cf2fef27.html', 0, 1, 0, 0, 0);
INSERT INTO `tb_article` VALUES (45, 1, '2020-04-03 00:30:15', '1000个并发到底最大能承载多少用户？', '', '', '386fe36076bc746bd5ebf1b175daa4f8.html', 0, 1, 0, 0, 0);
INSERT INTO `tb_article` VALUES (47, 1, '2020-04-03 00:36:51', '权威发布：无症状感染者管理标准与确诊病例基本一致', '', '', 'f3b040befbb5b73da85f1961956eba22.html', 0, 1, 0, 0, 0);
INSERT INTO `tb_article` VALUES (50, 1, '2020-04-04 13:00:26', '全国各地各族人民深切悼念抗击新冠肺炎疫情斗争牺牲烈士和逝世同胞　　习近平李克强栗战书汪洋王沪宁赵乐际韩正王岐山　　在京出席哀悼活动', '', '', 'd1fe9a3548ee5944c7df823e46730e24.html', 27, 2, 0, 0, 0);
INSERT INTO `tb_article` VALUES (51, 1, '2020-04-04 17:31:23', '66 个包过面试锦囊，拿走不谢！', '', '/20200404/ef9ef883-e350-4e48-a8e6-ec2b4439b9f8.jpeg', 'b32e77a16cb665e779ad4aa27f480b9d.html', 0, 2, 0, 0, 0);
INSERT INTO `tb_article` VALUES (52, 1, '2020-04-05 10:18:28', '快给你的Spring Boot做个埋点监控吧！', '', '/20200405/0b41bdb5-70a1-43f4-b331-6d8f0f543dc8.jpeg', 'f88590dcf2ab947bb54d5b95a8aa7005.html', 0, 1, 0, 0, 0);
INSERT INTO `tb_article` VALUES (53, 1, '2020-04-05 14:09:29', '【低配车PK】选奥迪Q3还是雷克萨斯UX入门版？这个抉择有点难……', '', '/20200405/342fb05c-a755-4879-9d02-7b1958ed8277.jpeg', '8457037d1dc23dfa1268afa9f7ab3cba.html', 3333, 1, 0, 0, 66);
INSERT INTO `tb_article` VALUES (63, 1, '2020-04-05 16:10:11', '终于有人问特朗普，怎么看中国在危机中的全球领导力？他说了两大关键词', '', 'http://n1.itc.cn/img7/adapt/wb/common/2020/04/02/158579287281904746.JPEG', 'af9e6550c001eccf7f683b04981bfa6b.html', 3333, 1, 0, 0, 66);
INSERT INTO `tb_article` VALUES (64, 1, '2020-04-05 16:12:04', '瑞幸咖啡道歉了！涉事高管及员工已被停职调查', '', 'http://n1.itc.cn/img8/wb/sohulife/2020/04/05/158606884038345472.JPEG', '637438bd7497865305758cbf400bd659.html', 2121, 1, 0, 0, 42);
INSERT INTO `tb_article` VALUES (65, 1, '2020-04-05 17:05:14', '软件开发“教父”的20年，重构开发模式，重塑开发者思维', '', '/20200405/fc1e10c3-954a-4ace-8123-3d0705bd1744.jpeg', 'c87191702fdb57e6a6aa4c6a77174e64.html', 2727, 1, 0, 0, 54);
INSERT INTO `tb_article` VALUES (66, 1, '2020-04-05 17:10:38', '瑞幸咖啡店里排起长队，网络都瘫痪了，员工指着摄像头：公司有规定，不能说', '', 'http://n1.itc.cn/img7/adapt/wb/common/2020/04/04/158596915654334911.PNG', 'a32763884be9b3b8fb28c248b2bf5de8.html', 3535, 1, 0, 0, 70);
INSERT INTO `tb_article` VALUES (67, 1, '2020-05-24 10:14:18', '英国制定计划将华为5G设备清零；360回应被列入实体清单；白岩松首次回应兼职红会副会长 | 邦早报', '', '/20200524/dde23a19-9629-49f1-a343-6f42a9486df5.jpeg', '27ebcf53ac7d2faa21dc75415d4d3460.html', 4949, 1, 0, 0, 98);
INSERT INTO `tb_article` VALUES (68, 1, '2020-05-28 00:03:23', '火遍全网！6岁小女孩一人承包一支乐队，完美演绎《加州旅馆》', '', '/20200528/3d21c120-1f18-4b25-9bec-506684ecf6ff.jpeg', '00db8f0304885bc329046b56f5963c60.html', 2929, 1, 0, 0, 58);
INSERT INTO `tb_article` VALUES (69, 1, '2020-05-30 11:39:42', '冲上热搜！高校要开螺蛳粉学院？评论区亮了...', '', '/20200530/17c693fa-6564-4644-ad29-a5691b73a7f6.jpeg', '2b18d088205a466fa792cf94d10f6f55.html', 2323, 1, 0, 0, 46);
COMMIT;

-- ----------------------------
-- Table structure for tb_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_comment`;
CREATE TABLE `tb_article_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `relation_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联素材id',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `content` text CHARACTER SET utf8mb4 COMMENT '评论内容',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `pid` bigint(20) DEFAULT NULL COMMENT '追评id',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `nick` varchar(50) DEFAULT '' COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='文章素材评论表';

-- ----------------------------
-- Records of tb_article_comment
-- ----------------------------
BEGIN;
INSERT INTO `tb_article_comment` VALUES (1, 27, 1, '安慰发违发', '2020-05-30 09:09:26', NULL, '20200517/20eb9b4b-a5f6-420b-9286-2e7ae754f663.png', '沧海一声笑');
INSERT INTO `tb_article_comment` VALUES (2, 27, 1, '安慰发违发搜索', '2020-05-30 09:09:37', NULL, '20200517/20eb9b4b-a5f6-420b-9286-2e7ae754f663.png', '沧海一声笑');
INSERT INTO `tb_article_comment` VALUES (3, 27, 1, 'awef😀', '2020-05-30 10:49:13', NULL, '20200517/20eb9b4b-a5f6-420b-9286-2e7ae754f663.png', '沧海一声笑');
INSERT INTO `tb_article_comment` VALUES (4, 27, 1, '😀😀😀😀', '2020-05-30 10:50:43', NULL, '20200517/20eb9b4b-a5f6-420b-9286-2e7ae754f663.png', '沧海一声笑');
INSERT INTO `tb_article_comment` VALUES (5, 27, 1, '😜😜😜😜', '2020-05-30 10:56:24', NULL, '20200517/20eb9b4b-a5f6-420b-9286-2e7ae754f663.png', '沧海一声笑');
INSERT INTO `tb_article_comment` VALUES (8, 27, 2, 'awef', '2020-06-07 01:03:36', NULL, '20200604/7587711c-aab2-42a4-bd82-62fdaedd084b', 'Joey');
INSERT INTO `tb_article_comment` VALUES (9, 27, 2, 'awefawefaw哈哈😀', '2020-06-07 01:05:14', NULL, '20200604/7587711c-aab2-42a4-bd82-62fdaedd084b', 'Joey');
COMMIT;

-- ----------------------------
-- Table structure for tb_article_issue
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_issue`;
CREATE TABLE `tb_article_issue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0' COMMENT '发布用户',
  `article_id` bigint(20) DEFAULT '0' COMMENT '文章素材id',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论数',
  `look_count` int(11) DEFAULT '1' COMMENT '阅读数',
  `like_count` int(11) DEFAULT '0' COMMENT '喜欢数',
  `advertising_id` bigint(20) DEFAULT '0' COMMENT '展示广告id',
  `ctime` datetime DEFAULT NULL,
  `task_id` bigint(20) DEFAULT '0' COMMENT '关联任务id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='文章发布表';

-- ----------------------------
-- Records of tb_article_issue
-- ----------------------------
BEGIN;
INSERT INTO `tb_article_issue` VALUES (1, 1, 27, 0, 1, 0, 8, NULL, 0);
INSERT INTO `tb_article_issue` VALUES (2, 2, 27, 0, 1, 0, 11, NULL, 0);
INSERT INTO `tb_article_issue` VALUES (3, 3, 27, 0, 1, 0, 0, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_article_issue_looks
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_issue_looks`;
CREATE TABLE `tb_article_issue_looks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ' ',
  `issue_id` bigint(20) DEFAULT '0' COMMENT '发布id',
  `task_relation_id` bigint(20) DEFAULT '0' COMMENT '任务分享id',
  `look_user_id` bigint(20) DEFAULT '0' COMMENT '浏览用户id',
  `look_time` int(11) DEFAULT '0' COMMENT '浏览时长',
  `is_click` int(11) DEFAULT '0' COMMENT '是否点击广告 0-未点击 1-已点击',
  `is_copy` int(11) DEFAULT '0' COMMENT '是否复制商品链接',
  `is_relation` int(11) DEFAULT '0' COMMENT '是否点击联系方式',
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发布文章浏览记录表';

-- ----------------------------
-- Table structure for tb_article_task
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_task`;
CREATE TABLE `tb_article_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0' COMMENT '发起用户id',
  `award` int(10) DEFAULT '1' COMMENT '阅读奖励金币',
  `sum_award` bigint(20) DEFAULT '1' COMMENT '总奖励金币',
  `expire` datetime DEFAULT NULL COMMENT '到期时间',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  `generalize_count` int(11) DEFAULT '0' COMMENT '推广人数',
  `look_count` int(11) DEFAULT '0' COMMENT '浏览人数',
  `advertising_id` bigint(20) DEFAULT '0' COMMENT '广告id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='文章金币任务表 ';

-- ----------------------------
-- Records of tb_article_task
-- ----------------------------
BEGIN;
INSERT INTO `tb_article_task` VALUES (1, 1, 2, 200, '2020-05-14 22:47:00', '2020-05-11 23:56:53', 0, 0, 2);
INSERT INTO `tb_article_task` VALUES (2, 2, 2, 100, '2020-06-23 00:00:00', '2020-06-13 19:11:36', 0, 0, 10);
INSERT INTO `tb_article_task` VALUES (3, 2, 1, 100, '2020-06-30 12:59:00', '2020-06-13 19:11:36', 0, 0, 10);
COMMIT;

-- ----------------------------
-- Table structure for tb_article_task_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_task_relation`;
CREATE TABLE `tb_article_task_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) DEFAULT '0' COMMENT '任务id',
  `relation_id` bigint(20) DEFAULT '0' COMMENT '关联id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章金币任务与文章发布表关联表';

-- ----------------------------
-- Table structure for tb_client_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_client_user`;
CREATE TABLE `tb_client_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick` varchar(50) DEFAULT '' COMMENT '昵称',
  `avatar` varchar(255) DEFAULT '',
  `industry` varchar(50) DEFAULT '' COMMENT '行业',
  `professional` varchar(50) DEFAULT '' COMMENT '职业',
  `phone` varchar(20) DEFAULT '',
  `wx_account` varchar(255) DEFAULT '' COMMENT '微信账号',
  `wxqr` varchar(255) DEFAULT '' COMMENT '微信二维码',
  `email` varchar(50) DEFAULT '',
  `gold_balance` bigint(20) DEFAULT '0' COMMENT '金币余额',
  `sum_commission` decimal(20,2) DEFAULT '0.00' COMMENT '总获佣金',
  `sum_lower` decimal(20,2) DEFAULT '0.00' COMMENT '总获下级佣金',
  `vip` int(1) DEFAULT '0' COMMENT '是否为vip 0-不是  1-是',
  `vip_expire` datetime DEFAULT NULL COMMENT 'vip到期时间',
  `agency_level` int(1) DEFAULT '0' COMMENT '代理等级 0-青铜 1-黄金 2-钻石',
  `earnings1` decimal(10,4) DEFAULT '0.4000' COMMENT '一层返佣比例',
  `earnings2` decimal(10,4) DEFAULT '0.0000' COMMENT '二层返佣比例',
  `earnings3` decimal(10,4) DEFAULT '0.0000' COMMENT '三层返佣比例',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  `pid` bigint(20) DEFAULT '0' COMMENT '邀请人',
  `open_id` varchar(50) DEFAULT NULL COMMENT '微信openid',
  `refresh_token` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `open_id_unq` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of tb_client_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_client_user` VALUES (1, '沧海一声笑', '20200517/20eb9b4b-a5f6-420b-9286-2e7ae754f663.png', '金融', '操盘手', '1585858585', 'awefawefa', '20200517/0dd8083e-419d-4621-91ea-d11d6a3bddd4.png', '2557606319@qq.com', 9999, 0.00, 0.00, 0, NULL, 1, 0.4000, 0.0000, 0.0000, '2020-03-26 18:34:24', 0, NULL, NULL, NULL);
INSERT INTO `tb_client_user` VALUES (2, 'Joey', '20200604/7587711c-aab2-42a4-bd82-62fdaedd084b', 'ssss', '', '15874147126', 'awefawefawe', '20200606/6dbd1e99-314a-4318-b0bd-448aa7f86165.png', '', 0, 0.00, 0.00, 0, NULL, 0, 0.4000, 0.0000, 0.0000, '2020-06-04 22:03:10', 0, 'opHD8t_ph8DbCzEPUZl8wKUn-9yg', '34_4LP87k2A6bsthc7QfxTsPXqfIXChWCRRCHirbqSS_0ZCBCo6Goq-mChOFFk7HLvFq16Ing4quppK0X9J7qhEJw', '34_zlJWDiXYhnNwWM2yJBnMTxMOVkh7mz2vRi5oj3oSAfy2yY-sN-XCnhIzZTzPXQzKrdT2XPExy0MLZkcNh_VAlQ');
INSERT INTO `tb_client_user` VALUES (3, '嗯哼×', '20200606/6c101fd6-fe86-46ce-82da-b54735a77e0e', '', '', '', '', '', '', 0, 0.00, 0.00, 0, NULL, 0, 0.4000, 0.0000, 0.0000, '2020-06-06 10:48:02', 0, 'opHD8tzmSyEgbx2ZGPEt4Pb0VzoQ', '34_HHyEvqsjSlVIwkUk8OphYXwxFllsdnxVp7_B5mdSqA_SL-B2pZoXDXZbE0YdHeh30nEPKG_sf669eMZzcdbkSPvt1dtDuFpK9yvzMYuOGWI', '34_-KlqIiAKk37OoWwqbAwFXRtXwIFmlUqdwxi1HrJS96M0owmiwupjAl6CzN1Fe513G1kALALHiZrYII3kzP2AtGDgUozXpaELTABo6ojd9L8');
COMMIT;

-- ----------------------------
-- Table structure for tb_gold_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_gold_details`;
CREATE TABLE `tb_gold_details` (
  `id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT '0',
  `gold_num` int(11) DEFAULT '0' COMMENT '金币数',
  `direction` int(11) DEFAULT '0' COMMENT '收入支出  0-收入 1-支出',
  `remark` varchar(50) DEFAULT '',
  `ctime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='金币明细表';

-- ----------------------------
-- Table structure for tb_gold_extract
-- ----------------------------
DROP TABLE IF EXISTS `tb_gold_extract`;
CREATE TABLE `tb_gold_extract` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0',
  `gold_num` bigint(20) DEFAULT '0' COMMENT '金币数',
  `type` int(1) DEFAULT '0' COMMENT '提现类型 0-支付宝 1-微信',
  `account` varchar(50) DEFAULT '' COMMENT '提现账户',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int(1) DEFAULT '0' COMMENT '审核状态 0-待审核 1-审核成功  2-审核失败',
  `auth_time` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(50) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='金币提现';

-- ----------------------------
-- Table structure for tb_pay_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_pay_details`;
CREATE TABLE `tb_pay_details` (
  `num` varchar(50) NOT NULL DEFAULT '' COMMENT '交易编号',
  `user_id` bigint(20) DEFAULT '0',
  `pay_num` varchar(50) DEFAULT '' COMMENT '支付编号',
  `money` decimal(20,2) DEFAULT '0.00' COMMENT '金额',
  `status` int(1) DEFAULT '0' COMMENT '支付状态  0-待支付  1-支付成功 2-支付失败',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_rebate
-- ----------------------------
DROP TABLE IF EXISTS `tb_rebate`;
CREATE TABLE `tb_rebate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0' COMMENT '收益用户',
  `target_user_id` bigint(20) DEFAULT '0' COMMENT '目标用户',
  `pro_money` decimal(20,2) DEFAULT '0.00' COMMENT '消费金额',
  `commission` decimal(20,2) DEFAULT '0.00' COMMENT '佣金',
  `ratio` decimal(20,4) DEFAULT '0.0000' COMMENT '返佣比例',
  `gold` bigint(20) DEFAULT '0' COMMENT '获得金币',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='返佣明细表';

-- ----------------------------
-- Table structure for tb_setting
-- ----------------------------
DROP TABLE IF EXISTS `tb_setting`;
CREATE TABLE `tb_setting` (
  `key` varchar(255) NOT NULL,
  `val` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `pkey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_setting
-- ----------------------------
BEGIN;
INSERT INTO `tb_setting` VALUES ('agent_level', NULL, '代理等级信息', NULL);
INSERT INTO `tb_setting` VALUES ('level1', '0.4', '青铜代理直推返佣', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level1_2', '0', '青铜代理二级返佣', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level1_3', '0', '青铜代理三级返佣', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level2', '0.6', '黄金代理直推返佣', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level2_2', '0', '黄金代理二级返佣', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level2_3', '0', '黄金代理三级返佣', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level2_price', '899', '黄金会员价格', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level3', '0.8', '钻石代理直推返佣', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level3_2', '0.1', '钻石代理二级返佣', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level3_3', '0.05', '钻石代理三级返佣', 'agent_level');
INSERT INTO `tb_setting` VALUES ('level3_price', '1699', '铂金会员价格', 'agent_level');
COMMIT;

-- ----------------------------
-- Table structure for tb_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  `tname` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='素材类型表';

-- ----------------------------
-- Records of tb_type
-- ----------------------------
BEGIN;
INSERT INTO `tb_type` VALUES (1, '2020-03-26 23:11:38', '娱乐');
INSERT INTO `tb_type` VALUES (2, '2020-03-26 23:11:45', '新闻');
INSERT INTO `tb_type` VALUES (3, '2020-03-26 23:11:51', '明星');
INSERT INTO `tb_type` VALUES (4, '2020-03-26 23:11:57', '体育');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_type_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_type_relation`;
CREATE TABLE `tb_user_type_relation` (
  `user_id` bigint(20) DEFAULT '0',
  `type_id` int(11) DEFAULT '0',
  UNIQUE KEY `unq` (`user_id`,`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户展示类型关联表';

-- ----------------------------
-- Records of tb_user_type_relation
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_type_relation` VALUES (1, 1);
INSERT INTO `tb_user_type_relation` VALUES (1, 2);
COMMIT;

-- ----------------------------
-- Table structure for tb_video
-- ----------------------------
DROP TABLE IF EXISTS `tb_video`;
CREATE TABLE `tb_video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0' COMMENT '创建用户',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(100) DEFAULT '',
  `describe` varchar(255) DEFAULT '',
  `previews` varchar(150) DEFAULT '' COMMENT '预览图',
  `url` varchar(100) DEFAULT '' COMMENT '视频地址',
  `look_count` bigint(20) DEFAULT '0' COMMENT '素材总浏览量',
  `comment_count` bigint(20) DEFAULT '0' COMMENT '浏览量',
  `like_count` bigint(20) DEFAULT '0' COMMENT '喜欢量',
  `type_id` int(11) DEFAULT '0' COMMENT '素材类型id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `video_url_idx` (`url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COMMENT='视频素材表';

-- ----------------------------
-- Records of tb_video
-- ----------------------------
BEGIN;
INSERT INTO `tb_video` VALUES (89, 1, '2020-04-20 22:47:55', '#张予曦 这么主动的对象 #邢昭林确定不要 #无法拥抱的你 @抖音小助手', '', 'https://p1.pstatp.com/large/tos-cn-p-0015/a4e71032db6e441ab9aac55a8197d5b5_1585830027.jpg', '20200420/d7a52110409aeaed1d2724be20b7d809.mp4', 3636, 8170, 176136, 1);
INSERT INTO `tb_video` VALUES (97, 1, '2020-04-25 22:50:27', '在保证安全的前提下，再合理处理家庭内部纠纷 #安全出行', '', 'https://p1.pstatp.com/large/tos-cn-p-0015/768a1514d87e46ac84cd54bf33358f9d_1587270053.jpg', '20200425/0cdc11eff67bba3e056baa4071c567be.mp4', 2727, 174259, 2147800, 1);
INSERT INTO `tb_video` VALUES (108, 1, '2020-05-03 21:01:01', '#泡沫 全都是泡沫！！！@抖音小助手', '', 'https://p1.pstatp.com/large/tos-cn-p-0015/e1991fcd8afe45d6a0655e95b2abcbb1_1587706995.jpg', '20200503/2c68156cb25d15a94ae22fa1b07636ee.mp4', 1818, 24373, 149271, 1);
INSERT INTO `tb_video` VALUES (114, 1, '2020-05-04 11:37:57', '#泡沫 全都是泡沫！！！@抖音小助手', '', 'https://p1.pstatp.com/large/tos-cn-p-0015/e1991fcd8afe45d6a0655e95b2abcbb1_1587706995.jpg', '20200504/2c68156cb25d15a94ae22fa1b07636ee.mp4', 1818, 24732, 152418, 1);
INSERT INTO `tb_video` VALUES (115, 1, '2020-05-09 23:26:16', '喜欢你 你说什么就是什么', '', 'https://p1.pstatp.com/large/tos-cn-p-0015/23a50b36f64942c3a6c528a185d08865_1587720369.jpg', '20200509/8c87b6b5a9ee40186786fe69bf25df90.mp4', 1212, 10783, 395878, 1);
INSERT INTO `tb_video` VALUES (116, 1, '2020-05-09 23:26:32', '喜欢你 你说什么就是什么', '', 'https://p1.pstatp.com/large/tos-cn-p-0015/23a50b36f64942c3a6c528a185d08865_1587720369.jpg', '20200509/c78ee24d2e2a7f0d17ea56fb90e601cb.mp4', 1212, 10783, 395878, 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_video_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_comment`;
CREATE TABLE `tb_video_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `relation_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联素材id',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '评论内容',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `pid` bigint(20) DEFAULT NULL COMMENT '追评id',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `nick` varchar(50) DEFAULT '' COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='视频素材评论表';

-- ----------------------------
-- Records of tb_video_comment
-- ----------------------------
BEGIN;
INSERT INTO `tb_video_comment` VALUES (1, 96, 1, '哇哦安慰发违发为', '2020-04-24 23:57:37', NULL, '20200405/0b41bdb5-70a1-43f4-b331-6d8f0f543dc8.jpeg', '沧海一粟小');
INSERT INTO `tb_video_comment` VALUES (2, 96, 1, '啦啦啦', '2020-04-24 23:57:37', NULL, '20200405/0b41bdb5-70a1-43f4-b331-6d8f0f543dc8.jpeg', '沧海一粟小');
COMMIT;

-- ----------------------------
-- Table structure for tb_video_issue
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_issue`;
CREATE TABLE `tb_video_issue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0' COMMENT '发布用户',
  `video_id` bigint(20) DEFAULT '0' COMMENT '文章素材id',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论数',
  `look_count` int(11) DEFAULT '1' COMMENT '阅读数',
  `like_count` int(11) DEFAULT '0' COMMENT '喜欢数',
  `advertising_id` bigint(20) DEFAULT '0' COMMENT '展示广告id',
  `ctime` datetime DEFAULT NULL,
  `task_id` bigint(20) DEFAULT '0' COMMENT '关联任务id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='文章发布表';

-- ----------------------------
-- Records of tb_video_issue
-- ----------------------------
BEGIN;
INSERT INTO `tb_video_issue` VALUES (23, 1, 108, 0, 1, 0, 2, NULL, 0);
INSERT INTO `tb_video_issue` VALUES (24, 1, 101, 0, 1, 0, 0, NULL, 0);
INSERT INTO `tb_video_issue` VALUES (25, 1, 115, 0, 1, 0, 0, NULL, 0);
INSERT INTO `tb_video_issue` VALUES (26, 1, 116, 0, 1, 0, 0, NULL, 0);
INSERT INTO `tb_video_issue` VALUES (27, 1, 89, 0, 1, 0, 2, NULL, 0);
INSERT INTO `tb_video_issue` VALUES (28, 2, 89, 0, 1, 0, 11, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_video_issue_looks
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_issue_looks`;
CREATE TABLE `tb_video_issue_looks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ' ',
  `issue_id` bigint(20) DEFAULT '0' COMMENT '发布id',
  `task_relation_id` bigint(20) DEFAULT '0' COMMENT '任务分享id',
  `look_user_id` bigint(20) DEFAULT '0' COMMENT '浏览用户id',
  `look_time` int(11) DEFAULT '0' COMMENT '浏览时长',
  `is_click` int(11) DEFAULT '0' COMMENT '是否点击广告 0-未点击 1-已点击',
  `is_copy` int(11) DEFAULT '0' COMMENT '是否复制商品链接',
  `is_relation` int(11) DEFAULT '0' COMMENT '是否点击联系方式',
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发布商品浏览记录表';

-- ----------------------------
-- Table structure for tb_video_like
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_like`;
CREATE TABLE `tb_video_like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `relation_id` bigint(20) DEFAULT NULL COMMENT '关联素材id',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频素材收藏表';

-- ----------------------------
-- Table structure for tb_video_task
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_task`;
CREATE TABLE `tb_video_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0' COMMENT '发起用户id',
  `award` int(10) DEFAULT '1' COMMENT '阅读奖励金币',
  `sum_award` bigint(20) DEFAULT '1' COMMENT '总奖励金币',
  `expire` datetime DEFAULT NULL COMMENT '到期时间',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP,
  `generalize_count` int(11) DEFAULT '0' COMMENT '推广人数',
  `look_count` int(11) DEFAULT '0' COMMENT '浏览人数',
  `advertising_id` bigint(20) DEFAULT '0' COMMENT '广告id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频金币任务表 ';

-- ----------------------------
-- Table structure for tb_video_task_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_task_relation`;
CREATE TABLE `tb_video_task_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) DEFAULT '0' COMMENT '任务id',
  `relation_id` bigint(20) DEFAULT '0' COMMENT '关联id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频金币任务与文章发布表关联表';

SET FOREIGN_KEY_CHECKS = 1;
