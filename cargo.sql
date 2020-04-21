/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : cargo

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 21/04/2020 17:21:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型【0:目录，1:菜单。2：按钮】',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `text_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国际化文本key',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记【 0：未删除，1：已删除】',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('', '1061495901922672641', '通知重发(按钮)', '', 'operate:notification:reissue', 2, '', NULL, '', NULL, NULL, '2020-02-11 11:12:28', NULL, '2020-02-11 11:12:28', 1);
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '', 'system:inxex', 0, '', 1, 'text.menu.system.manage', NULL, NULL, '2018-09-28 10:58:16', NULL, '2019-07-24 16:00:42', 0);
INSERT INTO `sys_menu` VALUES ('1052495110186582018', '0', '关于我们', '/manager/toAbout', 'session:index', 1, '', 2, '1', NULL, NULL, '2018-10-17 17:42:43', NULL, '2020-04-21 16:24:04', 0);
INSERT INTO `sys_menu` VALUES ('1052495641386795010', '1062255694142828546', '发送广播', '/broadcast/index', 'broadcast:index', 1, '', 3, 'text.menu.send.broadcast', NULL, NULL, '2018-10-17 17:44:49', NULL, '2018-11-13 16:08:08', 1);
INSERT INTO `sys_menu` VALUES ('1052496683667144705', '0', 'IM用户管理', '/user/index', 'user:index', 1, '', 4, 'text.menu.user.manage', NULL, NULL, '2018-10-17 17:48:58', NULL, '2018-11-11 16:21:14', 1);
INSERT INTO `sys_menu` VALUES ('1052496973111869441', '0', '群组管理', '', 'group:index', 0, '', 5, 'text.menu.group.manage', NULL, NULL, '2018-10-17 17:50:07', NULL, '2018-12-28 20:19:47', 1);
INSERT INTO `sys_menu` VALUES ('1052497156537171969', '0', '文章管理', '/article/index', 'article:index', 1, NULL, 6, 'text.menu.article.manage', NULL, NULL, '2018-10-17 17:50:51', NULL, '2018-10-17 17:50:51', 0);
INSERT INTO `sys_menu` VALUES ('1052497636940169218', '0', '任务管理', '/manager/toSave', 'quartz:index', 1, '', 9, 'text.menu.task.manage', NULL, NULL, '2018-10-17 17:52:45', NULL, '2020-04-18 23:44:47', 0);
INSERT INTO `sys_menu` VALUES ('1052498004730298369', '0', '消息查询', '/message/index', 'message:index', 1, NULL, 7, 'text.menu.news.query', NULL, NULL, '2018-10-17 17:54:13', NULL, '2018-10-17 17:54:13', 0);
INSERT INTO `sys_menu` VALUES ('1053176754846191618', '0', '动态配置刷新', '/service/index', 'service:index', 1, '', 10, 'text.menu.configuration.refresh', NULL, NULL, '2018-10-19 14:51:20', NULL, '2018-10-19 14:53:12', 0);
INSERT INTO `sys_menu` VALUES ('1055764151121088514', '1', '操作日志', '/sys/log/index', 'sys:log:index', 1, '', 2, 'text.menu.operate.log', NULL, NULL, '2018-10-26 18:12:43', NULL, '2018-10-26 18:12:43', 0);
INSERT INTO `sys_menu` VALUES ('1061495901922672641', '0', '钱包管理', '', 'wallet:index', 0, 'layui-icon layui-icon-rmb', NULL, 'text.menu.wallet.manage', NULL, NULL, '2018-11-11 13:48:39', NULL, '2018-11-11 14:39:24', 1);
INSERT INTO `sys_menu` VALUES ('1061496252637790210', '1061495901922672641', '币种管理', '/coin/index', 'coin:index', 1, '', NULL, 'text.menu.coin.manage', NULL, NULL, '2018-11-11 13:50:02', NULL, '2018-11-11 13:50:02', 1);
INSERT INTO `sys_menu` VALUES ('1061507598204973057', '1061495901922672641', '交易流水', '/capital/index', 'capital:index', 1, '', NULL, 'text.menu.transaction.flow', NULL, NULL, '2018-11-11 14:35:07', NULL, '2018-11-11 14:35:07', 1);
INSERT INTO `sys_menu` VALUES ('1061528375203463170', '0', '通知管理', '', 'notice:admin', 0, '', NULL, 'text.menu.notice.manage', NULL, NULL, '2018-11-11 15:57:41', NULL, '2018-11-11 16:11:15', 1);
INSERT INTO `sys_menu` VALUES ('1061530487668867074', '1061528375203463170', '币种交易监控', '/dict/coin/notice/index', 'dict:coin:notice:index', 1, '', NULL, 'text.menu.coin.transaction.monitor', NULL, NULL, '2018-11-11 16:06:05', NULL, '2018-11-11 16:06:05', 1);
INSERT INTO `sys_menu` VALUES ('1061530619751694337', '1061528375203463170', '币种新增通知', '/dict/notice/coin/add', 'dict:notice:coin:add', 1, '', NULL, 'text.menu.coin.add.notice', NULL, NULL, '2018-11-11 16:06:36', NULL, '2018-11-11 16:06:36', 1);
INSERT INTO `sys_menu` VALUES ('1061556917617836033', '1061495901922672641', '空投管理', '/airdrop/index', 'airdrop:index', 1, '', NULL, 'text.menu.airdrop.manage', NULL, NULL, '2018-11-11 17:51:06', NULL, '2018-11-11 17:52:21', 1);
INSERT INTO `sys_menu` VALUES ('1061812829668909057', '1076060712870014977', 'OTC订单审核', '/otc/order/index', 'otc:order:index', 1, '', NULL, 'text.menu.otc.order.check', NULL, NULL, '2018-11-12 10:48:00', NULL, '2018-12-21 18:25:03', 0);
INSERT INTO `sys_menu` VALUES ('1061822253297025025', '0', 'APP版本管理', '/appVersion/index', 'appVersion:index', 1, '', 11, 'text.menu.app.version.manage', NULL, NULL, '2018-11-12 11:25:27', NULL, '2018-11-12 20:04:50', 0);
INSERT INTO `sys_menu` VALUES ('1061859915933102081', '1061495901922672641', '会员管理', '/member/index', 'member:index', 1, '', NULL, 'text.menu.member.manage', NULL, NULL, '2018-11-12 13:55:07', NULL, '2018-11-12 15:01:08', 1);
INSERT INTO `sys_menu` VALUES ('1061876766012579841', '1061495901922672641', '钱包详情', '/user/coin/index', 'user:coin:index', 1, '', NULL, 'text.menu.wallet.detail', NULL, NULL, '2018-11-12 15:02:04', NULL, '2019-03-19 19:36:55', 1);
INSERT INTO `sys_menu` VALUES ('1061884621591609345', '1061495901922672641', '资产统计', '/statistics/coin/index', 'statistics:coin:index', 1, '', NULL, 'text.menu.assets.statistics', NULL, NULL, '2018-11-12 15:33:17', NULL, '2018-11-12 15:33:17', 1);
INSERT INTO `sys_menu` VALUES ('1061898561675534337', '1061495901922672641', '资金对账记录', '/check/checkIndex', 'check:index', 1, '', NULL, 'text.menu.fund.reconciliation.record', NULL, NULL, '2018-11-12 16:28:40', NULL, '2018-11-12 16:28:40', 1);
INSERT INTO `sys_menu` VALUES ('1062185206064861186', '1061495901922672641', '手续费管理', '/coinCharge/index', 'coinCharge:index', 1, '', NULL, 'text.menu.fee.manage', NULL, NULL, '2018-11-13 11:27:42', NULL, '2018-12-05 09:38:56', 1);
INSERT INTO `sys_menu` VALUES ('1062193073656397826', '1061495901922672641', '异常处理', '/exception/toPage', 'exception:index', 1, '', NULL, 'text.menu.exception.handle', NULL, NULL, '2018-11-13 11:58:58', NULL, '2018-11-13 11:58:58', 1);
INSERT INTO `sys_menu` VALUES ('1062255694142828546', '0', '广播管理', '', 'broadcast:manager', 0, '', NULL, 'text.menu.broadcast.manage', NULL, NULL, '2018-11-13 16:07:47', NULL, '2018-11-13 16:07:47', 1);
INSERT INTO `sys_menu` VALUES ('1062256740873031681', '1062255694142828546', '发送指令', '/broadcast/order', 'broadcast:order', 1, '', NULL, 'text.menu.send.instruction', NULL, NULL, '2018-11-13 16:11:57', NULL, '2018-11-13 16:33:12', 1);
INSERT INTO `sys_menu` VALUES ('1062316393659269121', '0', '游戏管理', '/game/index', 'game:index', 1, '', 99, 'text.menu.game.manage', NULL, NULL, '2018-11-13 20:08:59', NULL, '2019-07-19 16:16:32', 0);
INSERT INTO `sys_menu` VALUES ('1062584666210459649', '1061495901922672641', '手续费统计', '/statistics/coinCharge/index', 'statistics:coinCharge:index', 1, '', NULL, 'text.menu.fee.statistics', NULL, NULL, '2018-11-14 13:55:00', NULL, '2018-11-23 11:09:21', 1);
INSERT INTO `sys_menu` VALUES ('1063366293700980738', '0', '国际简码管理', '/globalCode/index', 'globalCode:index', 1, '', NULL, 'text.menu.global.code.manage', NULL, NULL, '2018-11-16 17:40:55', NULL, '2018-11-16 17:40:55', 0);
INSERT INTO `sys_menu` VALUES ('1069429922901590017', '0', '开发者菜单', '', 'dev:index', 0, '', NULL, 'text.menu.developer.menu', NULL, NULL, '2018-12-03 11:15:37', NULL, '2018-12-03 13:50:12', 0);
INSERT INTO `sys_menu` VALUES ('1069432982742790146', '1069429922901590017', '开发者工具', '/file/thumbIndex', 'thumbIndex:index', 1, '', NULL, 'text.menu.developer.tool', NULL, NULL, '2018-12-03 11:27:46', NULL, '2018-12-25 15:49:56', 0);
INSERT INTO `sys_menu` VALUES ('1073455165609377793', '1061495901922672641', '手续费报表', '/chargeReport/index', 'chargeReport:index', 1, '', NULL, 'text.menu.fee.report', NULL, NULL, '2018-12-14 13:50:29', NULL, '2018-12-14 13:50:29', 1);
INSERT INTO `sys_menu` VALUES ('1074937661525301250', '1074939161525301250', '表情包新增(按钮)', '/sticker/toAdd', 'sticker:toAdd', 2, '', NULL, '', NULL, NULL, '2020-02-07 10:46:26', NULL, '2020-02-07 10:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1074937661553501250', '1074939161525301250', '表情包编辑(按钮)', '/sticker/jump/edit', 'sticker:jump:edit', 2, '', NULL, '', NULL, NULL, '2020-02-07 10:46:26', NULL, '2020-02-07 10:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1074937661667501250', '1074939161525301250', '表情包删除(按钮)', '/sticker/delete', 'sticker:delete', 2, '', NULL, '', NULL, NULL, '2020-02-07 10:46:26', NULL, '2020-02-07 10:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1074937661667501257', '1074939161525301250', '表情包下架(按钮)', '/sticker/update', 'sticker:update', 2, '', NULL, '', NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1074939161525301250', '0', '表情包管理', '/sticker/index', 'sticker:index', 1, '', NULL, 'text.menu.emoji.manage', NULL, NULL, '2018-12-18 16:07:22', NULL, '2018-12-21 09:28:34', 0);
INSERT INTO `sys_menu` VALUES ('1074966054498848769', '1076060712870014977', '平台提款管理', '/platform/withdraw/apply/index', 'platform:withdraw:apply:index', 1, '', NULL, 'text.menu.platform.withdrawal.manage', NULL, NULL, '2018-12-18 17:54:13', NULL, '2018-12-21 18:28:02', 0);
INSERT INTO `sys_menu` VALUES ('1075621422876999682', '1076060712870014977', '平台提款配置', '/platform/withdraw/config/index', 'platform:withdraw:config:index', 1, '', NULL, 'text.menu.platform.withdrawal.config', NULL, NULL, '2018-12-20 13:18:25', NULL, '2018-12-21 18:28:08', 0);
INSERT INTO `sys_menu` VALUES ('1076060712870014977', '0', 'OTC管理', '', 'otc:manage', 0, '', NULL, 'text.menu.otc.manage', NULL, NULL, '2018-12-21 18:24:00', NULL, '2018-12-21 18:24:00', 0);
INSERT INTO `sys_menu` VALUES ('1078131664302788609', '1061495901922672641', '资金流水', '/user/coin/asset/index', 'user:coin:asset:index', 1, '', NULL, 'text.menu.cash.flow', NULL, NULL, '2018-12-27 11:33:14', NULL, '2018-12-27 16:10:52', 1);
INSERT INTO `sys_menu` VALUES ('1078626895800258562', '1052496973111869441', '群列表', '/group/index', 'group:list', 1, '', 1, 'text.menu.group.list', NULL, NULL, '2018-12-28 20:21:06', NULL, '2018-12-28 20:22:27', 1);
INSERT INTO `sys_menu` VALUES ('1078627700116770818', '1052496973111869441', '群活跃度查询', '/group/active/index', 'group:active:index', 1, '', 2, 'text.menu.group.activity.query', NULL, NULL, '2018-12-28 20:24:18', NULL, '2018-12-29 15:03:45', 1);
INSERT INTO `sys_menu` VALUES ('1079726895800258562', '1078626895800258562', '群解散(按钮)', '/group/disband', 'disband:group', 2, '', NULL, '', NULL, NULL, '2020-02-07 10:46:26', NULL, '2020-02-07 10:46:26', 1);
INSERT INTO `sys_menu` VALUES ('1079735895800258562', '1078626895800258562', '私聊开放(按钮)', '/group/information/flag', 'group:information:flag', 2, '', NULL, '', NULL, NULL, '2020-02-07 10:46:26', NULL, '2020-02-07 10:46:26', 1);
INSERT INTO `sys_menu` VALUES ('1083268937756827650', '1061495901922672641', '游戏返佣报表', '/rebateReport/index', 'rebateReport:index', 1, '', NULL, 'text.menu.game.rebate.report', NULL, NULL, '2019-01-10 15:46:55', NULL, '2019-01-10 15:46:55', 1);
INSERT INTO `sys_menu` VALUES ('1083642124340371458', '1085417587316400129', '邀请关系管理', '/inviteRelation/index', 'inviteRelation:index', 1, '', NULL, 'text.menu.invite.relation.manage', NULL, NULL, '2019-01-11 16:29:50', NULL, '2019-01-16 14:05:30', 0);
INSERT INTO `sys_menu` VALUES ('1083643364340371458', '1083642124340371458', '转移关系(按钮)', '/inviteRelation/relationChange', 'inviteRelation:relationChange', 2, '', NULL, '', NULL, NULL, '2020-02-07 10:46:26', NULL, '2020-02-07 10:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1083647734340371458', '1083642124340371458', '添加下级(按钮)', '/inviteRelation/addRelation', 'inviteRelation:addRelation', 2, '', NULL, '', NULL, NULL, '2020-02-07 10:46:26', NULL, '2020-02-07 15:06:34', 0);
INSERT INTO `sys_menu` VALUES ('1085049912924889090', '1085417587316400129', '查询直系关系', '/inviteRelation/direct/index', 'inviteRelation:direct', 1, '', NULL, 'text.menu.query.immediate.relation', NULL, NULL, '2019-01-15 13:43:53', NULL, '2019-01-16 14:06:13', 0);
INSERT INTO `sys_menu` VALUES ('1085417587316400129', '0', '邀请注册管理', '', 'invite:index', 0, '', NULL, 'text.menu.invite.regist.manage', NULL, NULL, '2019-01-16 14:04:53', NULL, '2019-01-16 14:04:53', 0);
INSERT INTO `sys_menu` VALUES ('1087957653572763650', '1061495901922672641', '游戏返佣', '/rebate/index', 'rebate:index', 1, '', 1, 'text.menu.game.rebate', NULL, NULL, '2019-01-23 14:18:12', NULL, '2019-01-23 14:21:15', 1);
INSERT INTO `sys_menu` VALUES ('1097382185573605378', '1076060712870014977', '银行卡管理', '/otc/bankcard/index', 'otc:bankcard:index', 1, '', NULL, 'text.menu.bank.card.manage', NULL, NULL, '2019-02-18 14:27:56', NULL, '2019-07-19 16:00:14', 0);
INSERT INTO `sys_menu` VALUES ('1100233955551268865', '1052496973111869441', '群游戏列表', '/group/game/index', 'group:game:index', 1, '', NULL, 'text.menu.group.game.list', NULL, NULL, '2019-02-26 11:19:51', NULL, '2019-02-26 11:19:51', 1);
INSERT INTO `sys_menu` VALUES ('1100244155551268865', '1100233955551268865', '结束游戏(按钮)', '/group/game/stop', 'group:game:stop', 2, '', NULL, '', NULL, NULL, '2020-02-07 10:46:26', NULL, '2020-02-07 10:46:26', 1);
INSERT INTO `sys_menu` VALUES ('1102779564330127361', '1061495901922672641', '用户资产交易任务管理', '/user/coin/trade/task/index', 'user:coin:trade:task:index', 1, '', NULL, 'text.menu.user.asset.transaction.task.manage', NULL, NULL, '2019-03-05 11:55:11', NULL, '2019-03-05 11:55:11', 1);
INSERT INTO `sys_menu` VALUES ('1103858046367748097', '1076060712870014977', 'OTC买卖比值报表', '/otc/order/day/statistics/index', 'otc:order:day:statistics:index', 1, '', NULL, 'text.menu.buy.sale.ratio.report', NULL, NULL, '2019-03-08 11:20:41', NULL, '2019-03-08 11:20:41', 0);
INSERT INTO `sys_menu` VALUES ('1104991955854442497', '0', '商家管理', '/merchant/index', 'merchant:index', 1, '', NULL, 'text.menu.business.manage', NULL, NULL, '2019-03-11 14:26:26', NULL, '2019-03-20 11:04:45', 0);
INSERT INTO `sys_menu` VALUES ('1105023023662612482', '0', '对账管理', '', 'billcheck:index', 0, '', NULL, 'text.menu.reconciliation.manage', NULL, NULL, '2019-03-11 16:29:53', NULL, '2019-03-11 16:54:08', 0);
INSERT INTO `sys_menu` VALUES ('1105024783022145538', '1105023023662612482', '对账单列表', '/billcheck/index', 'billcheck:index', 1, '', NULL, 'text.menu.reconciliation.list', NULL, NULL, '2019-03-11 16:36:53', NULL, '2019-03-11 16:47:43', 0);
INSERT INTO `sys_menu` VALUES ('1107540332593782785', '0', '短信管理', '', 'sms:manage', 0, '', NULL, 'text.menu.sms.manage', NULL, NULL, '2019-03-18 15:12:47', NULL, '2019-03-18 18:08:11', 0);
INSERT INTO `sys_menu` VALUES ('1107544062772879362', '1107540332593782785', '短信列表', '/sms/index', 'sms:index', 1, '', NULL, 'text.menu.sms.list', NULL, NULL, '2019-03-18 15:27:36', NULL, '2019-03-18 18:08:22', 0);
INSERT INTO `sys_menu` VALUES ('1108628105326092289', '1076060712870014977', 'OTC提款每日限制', '/platform/withdraw/config/otc/limit/index', 'platform:withdraw:config:otc:limit:index', 1, '', NULL, 'text.menu.otc.daily.withdrawal.limit', NULL, NULL, '2019-03-21 15:15:12', NULL, '2019-03-21 15:15:12', 0);
INSERT INTO `sys_menu` VALUES ('1111520743557193730', '1085417587316400129', '查询绑定关系', '/inviteRelation/bind/relation', 'inviteRelation:bind:relation', 1, '', NULL, 'text.menu.query.bind.relation', NULL, NULL, '2019-03-29 14:49:30', NULL, '2019-03-29 14:53:38', 0);
INSERT INTO `sys_menu` VALUES ('1112631475929661441', '1085417587316400129', '绑定关系转移操作日志', '/invite/relation/change/log/index', 'invite:relation:change:log:index', 1, '', NULL, 'text.menu.bind.relation.transfer.operate.log', NULL, NULL, '2019-04-01 16:23:10', NULL, '2019-04-01 16:23:10', 0);
INSERT INTO `sys_menu` VALUES ('1113014640997343233', '1076060712870014977', 'OTC商家分组', '/otc/merchant/group/index', 'otc:merchant:group:index', 1, '', NULL, 'text.menu.otc.business.group', NULL, NULL, '2019-04-02 17:45:43', NULL, '2019-04-02 17:45:43', 0);
INSERT INTO `sys_menu` VALUES ('1113357848533086210', '0', '钱包模式管理', '', 'walletMode:index', 0, '', NULL, 'text.menu.wallet.mode.manage', NULL, NULL, '2019-04-03 16:29:30', NULL, '2019-04-03 16:29:30', 0);
INSERT INTO `sys_menu` VALUES ('1113358272057126913', '1113357848533086210', '法币管理', '/legal/coin/index', 'legal:coin:index', 1, '', NULL, 'text.menu.legal.coin.manage', NULL, NULL, '2019-04-03 16:31:11', NULL, '2019-04-03 16:31:11', 0);
INSERT INTO `sys_menu` VALUES ('1113358272057173213', '1113358272057126913', '法币新增(按钮)', '/legal/coin/add', 'legal:coin:add', 2, '', NULL, '', NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1113358272057175545', '1113358272057126913', '法币删除(按钮)', '/legal/coin/del', 'legal:coin:del', 2, '', NULL, '', NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1113624311800684546', '1113357848533086210', ' 国家及地区管理', '/pattern/country/index', 'patternCountry:index', 1, '', NULL, 'text.menu.country.area.manage', NULL, NULL, '2019-04-04 10:08:20', NULL, '2019-04-04 11:34:29', 0);
INSERT INTO `sys_menu` VALUES ('1113624367800684546', '1113624311800684546', '自动加入白名单按钮(按钮)', '/dict/modifyValueByDomainAndKey', 'dict:modifyValueByDomainAndKey', 2, '', NULL, '', NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1113624367800686904', '1113624311800684546', '修改钱包模式(按钮)', '/pattern/country/modifyPattern', 'pattern:country:modifyPattern', 2, '', NULL, '', NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1113728880098549757', '1113728880098549762', '开启关闭白名单(按钮)', '/pattern/whiteList/modifyWhiteList', 'pattern:whiteList:modifyWhiteList', 2, '', NULL, '', NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1113728880098549762', '1113357848533086210', '编辑用户钱包白名单', '/pattern/whiteList/index', 'patternWhiteList:index', 1, '', NULL, 'text.menu.edit.user.wallet.whitelist', NULL, NULL, '2019-04-04 17:03:51', NULL, '2019-04-09 11:16:16', 0);
INSERT INTO `sys_menu` VALUES ('1116970180933210114', '0', '余额宝', '', 'yeb:index', 0, '', NULL, 'text.menu.yeb', NULL, NULL, '2019-04-13 15:43:38', NULL, '2019-04-13 15:43:38', 0);
INSERT INTO `sys_menu` VALUES ('1116970601428963329', '1116970180933210114', '余额宝资产', '/yeb/property', 'yeb:index', 1, '', NULL, 'text.menu.yeb.assets', NULL, NULL, '2019-04-13 15:45:18', NULL, '2019-04-15 11:16:11', 0);
INSERT INTO `sys_menu` VALUES ('1116970850860027905', '1116970180933210114', '余额宝订单', '/yeb/order', 'yeb:dd', 1, '', NULL, 'text.menu.yeb.order', NULL, NULL, '2019-04-13 15:46:17', NULL, '2019-04-13 15:46:17', 0);
INSERT INTO `sys_menu` VALUES ('1116971000152084481', '1116970180933210114', '日年化收益率', '/yeb/earnings', 'yeb:earnings', 1, '', NULL, 'text.menu.daily.return.rate', NULL, NULL, '2019-04-13 15:46:53', NULL, '2019-04-16 18:37:42', 0);
INSERT INTO `sys_menu` VALUES ('1116971000152084489', '1116970180933210114', '用户第三方关联数据', '/yeb/userThirdPartyRel', 'yeb:userThirdPartyRel', 1, '', NULL, 'text.menu.user.third.Party.linked.data', NULL, NULL, '2019-04-13 15:46:53', NULL, '2019-04-13 16:30:32', 0);
INSERT INTO `sys_menu` VALUES ('1119172100774752258', '0', '换汇管理', '', 'offsite:exchange', 0, '', NULL, 'text.menu.exchange.manage', NULL, NULL, '2019-04-19 17:33:16', NULL, '2019-04-19 17:33:16', 1);
INSERT INTO `sys_menu` VALUES ('1119172357243858946', '1119172100774752258', '信用值管理', '/merchant/deposit/index', 'merchant:deposit:index', 1, '', NULL, 'text.menu.credit.value.manage', NULL, NULL, '2019-04-19 17:34:17', NULL, '2019-04-19 17:34:17', 1);
INSERT INTO `sys_menu` VALUES ('1120176015352016897', '1119172100774752258', '订单管理', '/offsite/exchange/order/index', 'offsite:exchange:order:index', 1, '', NULL, 'text.menu.order.manage', NULL, NULL, '2019-04-22 12:02:28', NULL, '2019-04-23 11:47:12', 1);
INSERT INTO `sys_menu` VALUES ('1120510130735374338', '1119172100774752258', '换汇商家管理', '/exchange/merchant/index', 'exchange:merchant:index', 1, '', NULL, 'text.menu.exchange.business.manage', NULL, NULL, '2019-04-23 10:10:07', NULL, '2019-04-23 10:10:07', 1);
INSERT INTO `sys_menu` VALUES ('1120537689416949761', '0', '银行卡管理', '', 'bcb:bankcard', 0, '', NULL, 'text.menu.bank.card.manage', NULL, NULL, '2019-04-23 11:59:38', NULL, '2019-06-20 10:54:53', 1);
INSERT INTO `sys_menu` VALUES ('1120973356448112642', '1119172100774752258', '商品管理', '/offsite/exchange/goods/index', 'offsite:exchange:goods:index', 1, '', 4, 'text.menu.goods.manage', NULL, NULL, '2019-04-24 16:50:49', NULL, '2019-04-24 16:51:56', 1);
INSERT INTO `sys_menu` VALUES ('1121230565047607297', '1119172100774752258', '投诉管理', '/offsite/exchange/complaint/index', 'offsite:exchange:complaint:index', 1, '', NULL, 'text.menu.complaint.manage', NULL, NULL, '2019-04-25 09:52:52', NULL, '2019-04-25 09:52:52', 1);
INSERT INTO `sys_menu` VALUES ('1131799354359922690', '1119172100774752258', '汇率管理', '/offsite/exchange/wave/rate/index', 'offsite:exchange:wave:rate:index', 1, '', NULL, 'text.menu.exchange.rate.manage', NULL, NULL, '2019-05-24 13:49:28', NULL, '2019-05-24 13:49:28', 1);
INSERT INTO `sys_menu` VALUES ('1133256750202601473', '1119172100774752258', '支付方式', '/offsite/exchange/rebate/index', 'offsite:exchange:rebate:index', 1, '', NULL, 'text.menu.pay.type', NULL, NULL, '2019-05-28 14:20:38', NULL, '2019-05-28 14:20:38', 1);
INSERT INTO `sys_menu` VALUES ('1134299132872032258', '0', '资金快照', '', 'user:coin:snapshot', 0, '', NULL, 'text.menu.funds.snapshot', NULL, NULL, '2019-05-31 11:22:42', NULL, '2019-05-31 11:22:42', 0);
INSERT INTO `sys_menu` VALUES ('1134299470106656769', '1134299132872032258', '快照列表', '/user/coin/snapshot/list', 'user:coin:snapshot:list', 1, '', NULL, 'text.menu.snapshot.list', NULL, NULL, '2019-05-31 11:24:02', NULL, '2019-07-19 16:19:02', 0);
INSERT INTO `sys_menu` VALUES ('1135837257299267586', '1061495901922672641', '提现申请失败记录管理', '/withdraw/apply/fail/index', 'withdraw:apply:fail:index', 1, '', 5, 'text.menu.withdrawal.application.failure.record.manage', NULL, NULL, '2019-06-04 17:14:39', NULL, '2019-06-04 17:14:51', 1);
INSERT INTO `sys_menu` VALUES ('1137965336910016514', '1119172100774752258', '资金账号统计', '/offsite/exchange/fundAccount/statistics/index', 'offsite:exchange:fundAccount:statistics:index', 1, '', NULL, 'text.menu.fund.account.statistics', NULL, NULL, '2019-06-10 14:10:53', NULL, '2019-06-10 14:11:17', 1);
INSERT INTO `sys_menu` VALUES ('1138737922126794754', '1061495901922672641', '通知重发', '/notification/reissue/toPage', 'notification:reissue:toPage', 1, '', NULL, 'text.menu.notice.resend', NULL, NULL, '2019-06-12 17:20:52', NULL, '2019-06-12 17:20:52', 1);
INSERT INTO `sys_menu` VALUES ('1141246645681946626', '0', '报警管理', '/alertAssets/index', 'alertAssets:index', 1, '', NULL, 'text.menu.alarm.manage', NULL, NULL, '2019-06-19 15:29:38', NULL, '2019-07-19 16:00:22', 1);
INSERT INTO `sys_menu` VALUES ('1141246645681947732', '1141246645681946626', '报警任务新增(按钮)', '/alertAssets/add', 'alertAssets:add', 2, '', NULL, '', NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 1);
INSERT INTO `sys_menu` VALUES ('1141246645681948843', '1141246645681946626', '报警禁用(按钮)', '/alertAssets/update', 'alertAssets:update', 2, '', NULL, '', NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 1);
INSERT INTO `sys_menu` VALUES ('1141540055932149761', '1120537689416949761', '银行卡申请列表', '/bcb/bankcard/index', 'bcb:bankcard:applylist', 1, '', NULL, 'text.menu.bank.card.application.list', NULL, NULL, '2019-06-20 10:55:32', NULL, '2019-06-20 10:56:52', 1);
INSERT INTO `sys_menu` VALUES ('1141540705038442498', '1120537689416949761', '银行卡列表', '/bcb/bankcard/index/cardnum', 'bcb:bankcard:cardlist', 1, '', NULL, 'text.menu.bank.card.list', NULL, NULL, '2019-06-20 10:58:07', NULL, '2019-06-20 10:58:23', 1);
INSERT INTO `sys_menu` VALUES ('1143093726672748545', '1076060712870014977', 'OTC提现配置', '/otc/withdraw/config/index', 'otc:withdraw:config:index', 1, '', NULL, 'text.menu.otc.withdrawal.config', NULL, NULL, '2019-06-24 17:49:16', NULL, '2019-07-02 18:15:29', 0);
INSERT INTO `sys_menu` VALUES ('1145887260638056450', '1120537689416949761', '银行卡卡种列表', '/bcb/bankcard/type', 'bcb:bankcard:type', 1, '', NULL, 'text.menu.bank.card.type.list', NULL, NULL, '2019-07-02 10:49:47', NULL, '2019-07-02 10:49:47', 1);
INSERT INTO `sys_menu` VALUES ('1146985990204325890', '0', '反馈管理', '', 'adviceFeedback:index', 0, '', NULL, 'text.menu.feedback.manage', NULL, NULL, '2019-07-05 11:35:44', NULL, '2019-07-05 11:35:44', 0);
INSERT INTO `sys_menu` VALUES ('1146986333860429826', '1146985990204325890', '意见反馈列表', '/adviceFeedback/index', 'advice:feedback:list', 1, '', NULL, 'text.menu.feedback.list', NULL, NULL, '2019-07-05 11:37:06', NULL, '2019-07-05 11:37:06', 0);
INSERT INTO `sys_menu` VALUES ('1152130920073297921', '1134299132872032258', '资产统计快照', '/asset/coin/statistics/list', 'asset:statstics:list', 1, '', NULL, 'text.menu.asset.statistics.snapshot', NULL, NULL, '2019-07-19 16:19:51', NULL, '2019-07-19 16:19:51', 0);
INSERT INTO `sys_menu` VALUES ('1154039032844324865', '1', '字典管理', '/dict/index', 'dict:index', 1, '', NULL, 'text.menu.dict.manage', NULL, NULL, '2019-07-24 22:42:01', NULL, '2019-07-24 22:42:01', 1);
INSERT INTO `sys_menu` VALUES ('1164793385609392129', '1116970180933210114', '余额宝订单', '/yeb/order/index', 'yeb:order:index', 1, '', NULL, 'text.menu.yeb.order', NULL, NULL, '2019-08-23 14:55:58', NULL, '2019-08-23 14:55:58', 0);
INSERT INTO `sys_menu` VALUES ('1173518565538021377', '1116970180933210114', '余额宝资产统计', '/yeb/assets/statistics/index', 'yeb:assets:statistics:index', 1, '', NULL, 'text.menu.yeb.assets.statistics', NULL, NULL, '2019-09-16 16:46:43', NULL, '2019-09-16 16:46:43', 0);
INSERT INTO `sys_menu` VALUES ('1196779312083111937', '0', '交易记录', '', 'transaction', 0, '', 2, 'text.menu.transaction.record', NULL, NULL, '2019-11-19 21:16:37', NULL, '2019-11-20 08:56:14', 1);
INSERT INTO `sys_menu` VALUES ('1196780901598498817', '1196779312083111937', '好友转账', '/transaction/transfer/friends/index', 'transaction:friends:index', 1, '', NULL, 'text.menu.friend.transfer', NULL, NULL, '2019-11-19 21:22:56', NULL, '2019-11-22 20:51:58', 1);
INSERT INTO `sys_menu` VALUES ('1196957129467023362', '1196779312083111937', '钱包转账', '/transaction/transfer/wallet/index', 'transaction:wallet:index', 1, '', NULL, 'text.menu.wallet.transfer', NULL, NULL, '2019-11-20 09:03:12', NULL, '2019-11-22 20:52:35', 1);
INSERT INTO `sys_menu` VALUES ('1196957567641767938', '1196779312083111937', '资金转入', '/transaction/recharge/index', 'transaction:recharge:index', 1, '', NULL, 'text.menu.funds.transfer.in', NULL, NULL, '2019-11-20 09:04:57', NULL, '2019-11-22 14:12:31', 1);
INSERT INTO `sys_menu` VALUES ('1196957922559578114', '1196779312083111937', 'OTC充值', '/tradingRecord/otcRecharge/index', 'transaction:otc:recharge:index', 1, '', NULL, 'text.menu.otc.recharge', NULL, NULL, '2019-11-20 09:06:21', NULL, '2019-12-05 09:07:45', 1);
INSERT INTO `sys_menu` VALUES ('1196958494775889921', '1196779312083111937', 'OTC提现', '/tradingRecord/otcWithdraw/index', 'transaction:otc:withdraw:index', 1, '', NULL, 'text.menu.otc.withdraw', NULL, NULL, '2019-11-20 09:08:38', NULL, '2019-12-04 15:30:16', 1);
INSERT INTO `sys_menu` VALUES ('1197435953996734466', '1196779312083111937', '红包发送', '/red/packet/send/index', 'transaction:red:packet:send:index', 1, '', NULL, 'text.menu.red.pocket.send', NULL, NULL, '2019-11-21 16:45:53', NULL, '2019-12-05 09:31:28', 1);
INSERT INTO `sys_menu` VALUES ('1197436432948502529', '1196779312083111937', '红包领取', '/red/packet/receive/index', 'transaction:red:packet:receive:index', 1, '', NULL, 'text.menu.red.pocket.receive', NULL, NULL, '2019-11-21 16:47:47', NULL, '2019-12-05 09:31:46', 1);
INSERT INTO `sys_menu` VALUES ('1197436731075436545', '1196779312083111937', '闪兑', '/tradingRecord/fastExchange/index', 'transaction:fast:exchange:index', 1, '', NULL, 'text.menu.fast.exchange', NULL, NULL, '2019-11-21 16:48:58', NULL, '2019-12-04 15:30:48', 1);
INSERT INTO `sys_menu` VALUES ('1197437255686397954', '1196779312083111937', '扫码付款', '/transaction/transfer/scan/pay/index', 'transaction:scan:pay:index', 1, '', NULL, 'text.menu.scan.pay', NULL, NULL, '2019-11-21 16:51:03', NULL, '2019-12-05 09:29:45', 1);
INSERT INTO `sys_menu` VALUES ('1197437717097586690', '1196779312083111937', '理财宝', '/transaction/yeb/order/index', 'transaction:yeb:order:index', 1, '', NULL, 'text.menu.licaibao', NULL, NULL, '2019-11-21 16:52:53', NULL, '2019-11-25 19:00:40', 1);
INSERT INTO `sys_menu` VALUES ('1197702762301231106', '0', '用户认证', '/user/authentication/index', 'user:authentication:index', 1, '', NULL, 'text.menu.user.authentication', NULL, NULL, '2019-11-22 10:26:05', NULL, '2019-11-22 10:26:05', 0);
INSERT INTO `sys_menu` VALUES ('1205028637044678657', '1119172100774752258', '用户换汇统计', '/exchange/user/statistics/index', 'exchange:user:statistics:index', 1, '', NULL, 'text.menu.offsite.exchange.user.statistics', NULL, NULL, '2019-12-12 15:36:30', NULL, '2019-12-25 14:31:38', 1);
INSERT INTO `sys_menu` VALUES ('1205036568836706305', '1196779312083111937', '代发工资转出交易', '/transaction/transfer/salary/out/index', 'transaction:salary:out:index', 1, '', 36, 'text.menu.salary.transfer.out', NULL, NULL, '2019-12-12 16:08:01', NULL, '2019-12-13 15:15:37', 1);
INSERT INTO `sys_menu` VALUES ('1207138852118654978', '1196779312083111937', 'topgate充值', '/transaction/zbpay/order/index', 'transaction:zbpay:order:index', 1, '', 11, 'text.menu.zbpay.recharge', NULL, NULL, '2020-01-04 20:00:00', NULL, '2020-01-04 20:00:00', 1);
INSERT INTO `sys_menu` VALUES ('1207916114625581058', '1196779312083111937', '代发工资转入交易', '/transaction/transfer/salary/in/index', 'transaction:salary:in:index', 1, '', NULL, 'text.menu.salary.transfer.in', NULL, NULL, '2019-12-20 14:50:18', NULL, '2019-12-20 14:50:18', 1);
INSERT INTO `sys_menu` VALUES ('1209731057977122818', '1119172100774752258', '商家成交统计', '/exchange/merchant/transaction/statistics/index', 'offsite:exchange:merchant:transaction:statistics:index', 1, '', NULL, 'text.menu.offsite.exchange.merchant.transaction.statistics', NULL, NULL, '2019-12-25 15:02:14', NULL, '2019-12-25 15:17:00', 1);
INSERT INTO `sys_menu` VALUES ('1210128628082151426', '1119172100774752258', '商家挂单统计', '/exchange/merchant/goods/statistics/index', 'offsite:exchange:merchant:goods:statistics:index', 1, '', NULL, 'text.menu.offsite.exchange.goods.statistics', NULL, NULL, '2019-12-26 17:22:02', NULL, '2019-12-26 17:22:02', 1);
INSERT INTO `sys_menu` VALUES ('1212698438463143937', '1061495901922672641', 'topgate充值管理', '/topgate/recharge/payway/index', 'topgate:recharge:payway:index', 1, '', NULL, 'text.topgate.recharge.manager', NULL, NULL, '2020-01-02 19:33:33', NULL, '2020-01-06 18:41:20', 1);
INSERT INTO `sys_menu` VALUES ('1212698824766930945', '1061495901922672641', 'topgate提现管理', '/topgate/withdraw/payway/index', 'topgate:withdraw:payway:index', 1, '', NULL, 'text.topgate.withdraw.manager', NULL, NULL, '2020-01-02 19:35:05', NULL, '2020-01-06 18:41:41', 1);
INSERT INTO `sys_menu` VALUES ('1213060715928981506', '1196779312083111937', 'Topgate闪兑', '/tradingRecord/fastExchangeTopgate/index', 'transaction:fast:exchange:topgate:index', 1, '', NULL, 'text.menu.fast.exchange.topgate', NULL, NULL, '2020-01-04 20:00:00', NULL, '2020-01-04 20:00:00', 1);
INSERT INTO `sys_menu` VALUES ('1213074633471987713', '1061495901922672641', 'MQ队列消息', '/mq/msg/send/toPage', 'mq:msg:send:toPage', 1, '', NULL, 'text.menu.mq.msg.send.manage', NULL, NULL, '2020-01-03 20:28:25', NULL, '2020-01-04 09:30:10', 1);
INSERT INTO `sys_menu` VALUES ('1213298587940491265', '1196779312083111937', 'Topgate提现', '/tradingRecord/withdrawTopgate/index', 'transaction:withdraw:topgate:index', 1, '', NULL, 'text.menu.withdraw.topgate', NULL, NULL, '2020-01-04 20:00:00', NULL, '2020-01-04 20:00:00', 1);
INSERT INTO `sys_menu` VALUES ('1216562606450061314', '1052496683667144705', '修改', '/user/modify', 'im:user:modify', 2, '', NULL, '', NULL, NULL, '2020-01-13 11:28:22', NULL, '2020-01-13 11:56:35', 1);
INSERT INTO `sys_menu` VALUES ('1216602018224525314', '1061495901922672641', '会员管理功能禁用(按钮)', '/member/status/turn', 'status:turn:member', 2, '', NULL, '', NULL, NULL, '2020-01-13 14:04:59', NULL, '2020-01-15 16:10:26', 1);
INSERT INTO `sys_menu` VALUES ('1216644399601729537', '1052496683667144705', '重置密码', '/user/resetPsd', 'im:user:reset:password', 2, '', NULL, '', NULL, NULL, '2020-01-13 16:53:23', NULL, '2020-01-13 16:53:23', 1);
INSERT INTO `sys_menu` VALUES ('1216644817794809857', '1052496683667144705', 'IM用户启用禁用', '/user/operateState', 'im:user:operate', 2, '', NULL, '', NULL, NULL, '2020-01-13 16:55:03', NULL, '2020-01-13 16:55:03', 1);
INSERT INTO `sys_menu` VALUES ('1217382433134706690', '1212698824766930945', 'topgate提现新增(按钮)', '/topgate/withdraw/payway/add', 'add:topgate:withdraw:payway', 2, '', NULL, '', NULL, NULL, '2020-01-15 17:46:04', NULL, '2020-01-15 17:46:04', 1);
INSERT INTO `sys_menu` VALUES ('1217382969053511681', '1212698824766930945', 'topgate提现修改(按钮)', '/topgate/withdraw/payway/edit', 'edit:topgate:withdraw:payway', 2, '', NULL, '', NULL, NULL, '2020-01-15 17:48:12', NULL, '2020-01-15 17:48:12', 1);
INSERT INTO `sys_menu` VALUES ('1217383327897190402', '1212698824766930945', 'topgate提现删除(按钮)', '/topgate/withdraw/payway/del', 'del:topgate:withdraw:payway', 2, '', NULL, '', NULL, NULL, '2020-01-15 17:49:37', NULL, '2020-01-15 17:49:37', 1);
INSERT INTO `sys_menu` VALUES ('1217383721650061314', '1212698824766930945', 'topgate提现开关(按钮)', '/topgate/withdraw/payway/onOrOff', 'onOrOff:topgate:withdraw:payway', 2, '', NULL, '', NULL, NULL, '2020-01-15 17:51:11', NULL, '2020-01-15 17:51:11', 1);
INSERT INTO `sys_menu` VALUES ('1217631419846291457', '1212698824766930945', 'topgate提现字典删除(按钮)', '/topgate/withdraw/payway/delDict', 'delDict:topgate:withdraw:payway', 2, '', NULL, '', NULL, NULL, '2020-01-16 10:15:27', NULL, '2020-01-16 10:15:27', 1);
INSERT INTO `sys_menu` VALUES ('1217635324974002178', '1212698824766930945', 'topgate提现字典新增(按钮)', '/topgate/withdraw/payway/addDict', 'addDict:topgate:withdraw:payway', 2, '', NULL, '', NULL, NULL, '2020-01-16 10:30:58', NULL, '2020-01-16 10:30:58', 1);
INSERT INTO `sys_menu` VALUES ('1217635836754587649', '1212698824766930945', 'topgate提现字典修改(按钮)', '/topgate/withdraw/payway/editDict', 'editDict:topgate:withdraw:payway', 2, '', NULL, '', NULL, NULL, '2020-01-16 10:33:00', NULL, '2020-01-16 10:33:00', 1);
INSERT INTO `sys_menu` VALUES ('1217654314794360834', '1212698438463143937', 'topgate充值新增(按钮)', '/topgate/recharge/payway/add', 'add:topgate:recharge:payway', 2, '', NULL, '', NULL, NULL, '2020-01-16 11:46:26', NULL, '2020-01-16 11:46:26', 1);
INSERT INTO `sys_menu` VALUES ('1217654664188272642', '1212698438463143937', 'topgate充值修改(按钮)', '/topgate/recharge/payway/edit', 'edit:topgate:recharge:payway', 2, '', NULL, '', NULL, NULL, '2020-01-16 11:47:49', NULL, '2020-01-16 11:47:49', 1);
INSERT INTO `sys_menu` VALUES ('1217655039070969857', '1212698438463143937', 'topgate充值开关(按钮)', '/topgate/recharge/payway/onOrOff', 'onOrOff:topgate:recharge:payway', 2, '', NULL, '', NULL, NULL, '2020-01-16 11:49:18', NULL, '2020-01-16 11:49:18', 1);
INSERT INTO `sys_menu` VALUES ('1217655951537610753', '1212698438463143937', 'topgate充值删除', '/topgate/recharge/payway/del', 'del:topgate:recharge:payway', 2, '', NULL, '', NULL, NULL, '2020-01-16 11:52:56', NULL, '2020-01-16 11:52:56', 1);
INSERT INTO `sys_menu` VALUES ('1225631064301400065', '1119172100774752258', '换汇管理商家信用值新增（按钮）', '/merchant/deposit/add', 'merchant:deposit:add', 2, '', NULL, '', NULL, NULL, '2020-02-07 12:03:11', NULL, '2020-02-07 12:03:11', 1);
INSERT INTO `sys_menu` VALUES ('1225634922629066754', '1119172100774752258', '换汇管理商家信用值编辑（按钮）', '/merchant/deposit/edit', 'merchant:deposit:edit', 2, '', NULL, '', NULL, NULL, '2020-02-07 12:18:31', NULL, '2020-02-07 12:19:10', 1);
INSERT INTO `sys_menu` VALUES ('1225635389048254466', '1119172100774752258', '换汇管理商家信用值删除（按钮）', '/merchant/deposit/del', 'merchant:deposit:del', 2, '', NULL, '', NULL, NULL, '2020-02-07 12:20:22', NULL, '2020-02-07 12:26:56', 1);
INSERT INTO `sys_menu` VALUES ('1225690979753684994', '1119172100774752258', '换汇管理订单列表下载（按钮）', '/offsite/exchange/order/download', 'offsite:exchange:order:download', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:01:16', NULL, '2020-02-07 16:01:16', 1);
INSERT INTO `sys_menu` VALUES ('1225691391701446657', '1119172100774752258', '换汇管理商家账号开关（按钮）', '/exchange/merchant/update/status', 'exchange:merchant:update:status', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:02:54', NULL, '2020-02-07 16:02:54', 1);
INSERT INTO `sys_menu` VALUES ('1225691630374121474', '1119172100774752258', '换汇管理商家汇率更新（按钮）', '/exchange/merchant/update/merchantwaverate', 'exchange:merchant:waverate:update', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:03:51', NULL, '2020-02-07 16:03:51', 1);
INSERT INTO `sys_menu` VALUES ('1225691865681068033', '1097382185573605378', 'OTC新增银行卡（按钮）', '/otc/bankcard/bind', 'otc:bankcard:bind', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:04:47', NULL, '2020-02-07 16:04:47', 0);
INSERT INTO `sys_menu` VALUES ('1225691956976185345', '1119172100774752258', '换汇管理商家注销（按钮）', '/exchange/merchant/cancel', 'exchange:merchant:cancel', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:05:09', NULL, '2020-02-07 16:05:09', 1);
INSERT INTO `sys_menu` VALUES ('1225692185821605889', '1119172100774752258', '换汇管理商品文件下载（按钮）', '/offsite/exchange/goods/download', 'download:offsite:exchange:goods', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:06:04', NULL, '2020-02-07 16:06:04', 1);
INSERT INTO `sys_menu` VALUES ('1225692387989356545', '1097382185573605378', 'OTC解绑银行卡（按钮）', '/otc/bankcard/unbind', 'otc:bankcard:unbind', NULL, '', NULL, '', NULL, NULL, '2020-02-07 16:06:52', NULL, '2020-02-07 16:06:52', 0);
INSERT INTO `sys_menu` VALUES ('1225692402067337217', '1119172100774752258', '换汇管理商品上架（按钮）', '/offsite/exchange/goods/up', 'offsite:exchange:goods:up', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:06:55', NULL, '2020-02-07 16:06:55', 1);
INSERT INTO `sys_menu` VALUES ('1225692617931386881', '1119172100774752258', '换汇管理商品下架（按钮）', '/offsite/exchange/goods/down', 'offsite:exchange:goods:down', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:07:47', NULL, '2020-02-07 16:07:47', 1);
INSERT INTO `sys_menu` VALUES ('1225692792519290882', '1119172100774752258', '换汇管理投诉处理（按钮）', '/offsite/exchange/complaint/processing', 'processing:offsite:exchange:complaint', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:08:28', NULL, '2020-02-20 17:34:29', 1);
INSERT INTO `sys_menu` VALUES ('1225692988967907329', '1119172100774752258', '换汇管理支付方式新增（按钮）', '/offsite/exchange/rebate/add', 'add:offsite:exchange:rebate', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:09:15', NULL, '2020-02-20 14:13:17', 1);
INSERT INTO `sys_menu` VALUES ('1225693203334590466', '1119172100774752258', '换汇管理支付方式编辑（按钮）', '/offsite/exchange/rebate/edit', 'edit:offsite:exchange:rebate', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:10:06', NULL, '2020-02-20 14:13:07', 1);
INSERT INTO `sys_menu` VALUES ('1225693380820758530', '1119172100774752258', '换汇管理支付方式删除（按钮）', '/offsite/exchange/rebate/del', 'del:offsite:exchange:rebate', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:10:48', NULL, '2020-02-20 14:12:52', 1);
INSERT INTO `sys_menu` VALUES ('1225693574803124225', '1119172100774752258', '换汇管理汇率新增（按钮）', '/offsite/exchange/wave/rate/add', 'add:offsite:exchange:wave:rate', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:11:35', NULL, '2020-02-20 14:11:33', 1);
INSERT INTO `sys_menu` VALUES ('1225693754248032257', '1119172100774752258', '换汇管理汇率编辑（按钮）', '/offsite/exchange/wave/rate/edit', 'edit:offsite:exchange:wave:rate', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:12:17', NULL, '2020-02-20 14:10:38', 1);
INSERT INTO `sys_menu` VALUES ('1225693933369978882', '1119172100774752258', '换汇管理汇率删除（按钮）', '/offsite/exchange/wave/rate/del', 'del:offsite:exchange:wave:rate', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:13:00', NULL, '2020-02-20 14:09:57', 1);
INSERT INTO `sys_menu` VALUES ('1225694119190228994', '1119172100774752258', '换汇管理地区汇率设置（按钮）', '/offsite/exchange/wave/rate/areaEdit', 'edit:offsite:exchange:wave:rate:area', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:13:44', NULL, '2020-02-20 14:09:32', 1);
INSERT INTO `sys_menu` VALUES ('1225703228285431809', '1113014640997343233', 'OTC商家分组新增（按钮）', '/otc/merchant/group/add', 'otc:merchant:group:add', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:49:56', NULL, '2020-02-07 16:49:56', 0);
INSERT INTO `sys_menu` VALUES ('1225703541130178561', '1113014640997343233', 'OTC商家分组编辑（按钮）', '/otc/merchant/group/edit', 'otc:merchant:group:edit', 2, '', NULL, '', NULL, NULL, '2020-02-07 16:51:11', NULL, '2020-02-07 16:51:11', 0);
INSERT INTO `sys_menu` VALUES ('1225713935588569090', '1143093726672748545', 'OTC提现配置启用（按钮）', '/otc/withdraw/config/enable', 'otc:withdraw:config:enable', 2, '', NULL, '', NULL, NULL, '2020-02-07 17:32:29', NULL, '2020-02-07 17:32:29', 0);
INSERT INTO `sys_menu` VALUES ('1225714154287968258', '1143093726672748545', 'OTC提现配置删除（按钮）', '/otc/withdraw/config/delete', 'otc:withdraw:config:delete', 2, '', NULL, '', NULL, NULL, '2020-02-07 17:33:21', NULL, '2020-02-07 17:33:21', 0);
INSERT INTO `sys_menu` VALUES ('1225717446980186113', '1074966054498848769', '平台提款管理新增编辑（按钮）', '/platform/withdraw/config/modifyPage', 'platform:withdraw:config:modifyPage', 2, '', NULL, '', NULL, NULL, '2020-02-07 17:46:26', NULL, '2020-02-07 17:46:26', 0);
INSERT INTO `sys_menu` VALUES ('1225717638592770050', '1074966054498848769', '平台提款管理删除（按钮）', '/platform/withdraw/config/delete', 'platform:withdraw:config:delete', 2, '', NULL, '', NULL, NULL, '2020-02-07 17:47:12', NULL, '2020-02-07 17:47:12', 0);
INSERT INTO `sys_menu` VALUES ('1225722493152751617', '1213074633471987713', 'MQ重发(按钮)', '', 'operate:mq:msg:send', 2, '', NULL, '', NULL, NULL, '2020-02-07 18:06:29', NULL, '2020-02-07 18:06:29', 1);
INSERT INTO `sys_menu` VALUES ('1225725228648460290', '1135837257299267586', '提现申请处理(按钮)', '', 'operate:withdraw:apply:fail', 2, '', NULL, '', NULL, NULL, '2020-02-07 18:17:22', NULL, '2020-02-07 18:17:22', 1);
INSERT INTO `sys_menu` VALUES ('1226713550600040450', '1061496252637790210', '币种管理编辑（按钮）', '/coin/edit', 'coin:edit', 2, '', NULL, '', NULL, NULL, '2020-02-10 11:44:36', NULL, '2020-02-10 11:44:36', 1);
INSERT INTO `sys_menu` VALUES ('1226713902556672001', '1061496252637790210', '币种管理新增（按钮）', '/coin/add', 'coin:add', 2, '', NULL, '', NULL, NULL, '2020-02-10 11:46:00', NULL, '2020-02-10 11:46:00', 1);
INSERT INTO `sys_menu` VALUES ('1226755538783334402', '1062193073656397826', '异常处理（按钮）', '/exception/handle', 'exception:handle', 2, '', NULL, '', NULL, NULL, '2020-02-10 14:31:27', NULL, '2020-02-10 14:31:27', 1);
INSERT INTO `sys_menu` VALUES ('1226755962982658049', '1061556917617836033', '空投管理新增或编辑（按钮）', '/airdrop/save', 'airdrop:save', 2, '', NULL, '', NULL, NULL, '2020-02-10 14:33:08', NULL, '2020-02-10 14:33:08', 1);
INSERT INTO `sys_menu` VALUES ('1226756118704582658', '1061556917617836033', '空投管理删除（按钮）', '/airdrop/delete', 'airdrop:delete', 2, '', NULL, '', NULL, NULL, '2020-02-10 14:33:45', NULL, '2020-02-10 14:33:45', 1);
INSERT INTO `sys_menu` VALUES ('1226757497426833409', '1062584666210459649', '同步余额宝转出费用(按钮)', '/statistics/syncOldYebOutWithdrawFee', 'statistics:coin:syncOldYebOutWithdrawFee', 2, '', NULL, '', NULL, NULL, '2020-02-10 14:39:14', NULL, '2020-02-10 14:39:14', 1);
INSERT INTO `sys_menu` VALUES ('1226760665208483842', '1062185206064861186', '手续费管理新增编辑（按钮）', '/coinCharge/add', 'coinCharge:add', 2, '', NULL, '', NULL, NULL, '2020-02-10 14:51:49', NULL, '2020-02-10 14:51:49', 1);
INSERT INTO `sys_menu` VALUES ('1226761334728454145', '1062185206064861186', '手续费管理删除（按钮）', '/coinCharge/delete', 'coinCharge:delete', 2, '', NULL, '', NULL, NULL, '2020-02-10 14:54:28', NULL, '2020-02-10 14:54:28', 1);
INSERT INTO `sys_menu` VALUES ('1227067851459518466', '1061495901922672641', '通知重发(按钮)', '', 'operate:notification:reissue', 2, '', NULL, '', NULL, NULL, '2020-02-11 11:12:28', NULL, '2020-02-11 11:12:28', 1);
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', '/sys/user/index', 'sys:user:list', 1, NULL, 1, 'text.menu.manager.list', NULL, NULL, '2018-09-28 10:58:19', NULL, '2018-09-28 10:59:12', 0);
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '/sys/role/index', 'sys:role:list', 1, NULL, 2, 'text.menu.role.manage', NULL, NULL, '2018-09-28 10:58:23', NULL, '2018-09-28 10:59:16', 0);
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '/sys/menu/index', 'sys:menu:list', 1, NULL, 3, 'text.menu.manu.manage', NULL, NULL, '2018-09-28 10:58:26', NULL, '2018-09-28 10:59:19', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `role_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `status` smallint(6) NULL DEFAULT 0 COMMENT '状态  0：禁用   1：正常',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记【 0：未删除，1：已删除】',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1049572543650844673', '管理员', 'admin', 0, NULL, NULL, '2018-10-09 16:09:29', '1061806203427790850', '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role` VALUES ('1152129637249613826', 'test', 'test', 0, NULL, '1061806203427790850', '2019-07-19 16:14:45', '1061806203427790850', '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role` VALUES ('1211542090513313793', 'KHAdmin', 'KHAdmin', 0, NULL, '1209303564290019329', '2019-12-30 14:58:38', '1061806203427790850', '2020-01-19 15:56:06', 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标记【 0：未删除，1：已删除】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1227075493858893832 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1055710672860209154, 1225631064301400065, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055711188147232769, 1225692402067337217, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055711395127746562, 1225690979753684994, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055711434944274433, 1225635389048254466, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055711469975101441, 1225691630374121474, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055712576046600193, 1225691391701446657, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055713134920830977, 1225691956976185345, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055714575152570369, 1225692185821605889, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055715312595050497, 1225693754248032257, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055716538925637634, 1225692792519290882, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1055766529849348098, 1225692617931386881, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1057257386159054849, 1225692988967907329, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1057270750058840065, 1225693574803124225, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1057270960365436929, 1225693380820758530, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1057271033065308161, 1225693203334590466, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1057283738283216898, 1225693933369978882, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1057574633609474050, 1225694119190228994, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243262066689, 1, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243270455298, 1052497450503356417, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243270455299, 1055764151121088514, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243270455300, 2, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243270455301, 3, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243270455302, 4, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243270455303, 1052495110186582018, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243270455304, 1052496683667144705, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243270455305, 1052496973111869441, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243274649601, 1052497156537171969, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243274649602, 1052497636940169218, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243274649603, 1052498004730298369, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243274649604, 1053176754846191618, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243274649605, 1061495901922672641, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243274649606, 1061496252637790210, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243274649607, 1061507598204973057, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843905, 1061556917617836033, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843906, 1061859915933102081, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843907, 1061876766012579841, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843908, 1061884621591609345, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843909, 1061898561675534337, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843910, 1062185206064861186, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843911, 1062193073656397826, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843912, 1062584666210459649, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843913, 1073455165609377793, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243278843914, 1061528375203463170, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243283038209, 1061530487668867074, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243283038210, 1061530619751694337, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243283038211, 1061822253297025025, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243283038212, 1062255694142828546, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243283038213, 1052495641386795010, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243350147073, 1062256740873031681, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243350147074, 1062316393659269121, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243354341378, 1063366293700980738, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243354341379, 1069429922901590017, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243354341380, 1069432982742790146, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243354341381, 1074939161525301250, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243354341382, 1061812829668909057, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243354341383, 1119172100774752258, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769243358535681, 1120973356448112642, 1057574543364829186, NULL, NULL, '2019-05-10 16:41:36', NULL, '2019-05-10 16:41:36', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922562, 1, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922563, 1052497450503356417, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922564, 1055764151121088514, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922565, 2, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922566, 3, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922567, 4, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922568, 1052495110186582018, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922569, 1052496683667144705, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922570, 1052496973111869441, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922571, 1078626895800258562, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922572, 1078627700116770818, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922573, 1100233955551268865, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922574, 1052497156537171969, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922575, 1052497636940169218, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922576, 1052498004730298369, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922577, 1053176754846191618, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922578, 1061495901922672641, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922579, 1061496252637790210, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922580, 1061507598204973057, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922581, 1061556917617836033, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922582, 1061859915933102081, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922583, 1061876766012579841, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922584, 1061884621591609345, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922585, 1061898561675534337, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922586, 1062185206064861186, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922587, 1062193073656397826, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456617922588, 1062584666210459649, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456626311170, 1073455165609377793, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456626311171, 1078131664302788609, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456626311172, 1087957653572763650, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456626311173, 1102779564330127361, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456693420033, 1061528375203463170, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456693420034, 1061530487668867074, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614337, 1061530619751694337, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614338, 1061822253297025025, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614339, 1062255694142828546, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614340, 1052495641386795010, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614341, 1062256740873031681, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614342, 1062316393659269121, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614343, 1063366293700980738, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614344, 1069429922901590017, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614345, 1069432982742790146, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614346, 1074939161525301250, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614347, 1076060712870014977, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614348, 1061812829668909057, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614349, 1074966054498848769, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614350, 1075621422876999682, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614351, 1097382185573605378, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614352, 1103858046367748097, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614353, 1108628105326092289, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614354, 1113014640997343233, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614355, 1085417587316400129, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456697614356, 1083642124340371458, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456706002945, 1085049912924889090, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456706002946, 1111520743557193730, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456706002947, 1112631475929661441, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456706002948, 1104991955854442497, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456706002949, 1105023023662612482, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456706002950, 1105024783022145538, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456706002951, 1107540332593782785, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769456706002952, 1107544062772879362, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457465171970, 1113357848533086210, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457469366273, 1113358272057126913, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457469366274, 1113624311800684546, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457469366275, 1113728880098549762, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457473560578, 1116970180933210114, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457473560579, 1116970601428963329, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457473560580, 1116970850860027905, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457473560581, 1116971000152084481, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457473560582, 1116971000152084489, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457473560583, 1119172100774752258, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457473560584, 1119172357243858946, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457477754882, 1120176015352016897, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457477754883, 1120510130735374338, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457477754884, 1120973356448112642, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457477754885, 1121230565047607297, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1126769457477754886, 1120537689416949761, 1126769456601145345, NULL, '1061806203427790850', '2019-05-10 16:42:27', '1061806203427790850', '2019-05-10 16:42:27', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086892593153, 1, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086900981762, 1055764151121088514, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086905176065, 2, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086905176066, 3, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086905176067, 4, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086905176068, 1052495110186582018, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086905176069, 1052496683667144705, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086909370370, 1052496973111869441, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086909370371, 1078626895800258562, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086909370372, 1078627700116770818, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086909370373, 1100233955551268865, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086909370374, 1052497636940169218, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086909370375, 1053176754846191618, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086909370376, 1061495901922672641, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086909370377, 1061496252637790210, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086909370378, 1061507598204973057, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758978, 1061556917617836033, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758979, 1061859915933102081, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758980, 1061876766012579841, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758981, 1061884621591609345, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758982, 1062185206064861186, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758983, 1062193073656397826, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758984, 1062584666210459649, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758985, 1073455165609377793, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758986, 1078131664302788609, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758987, 1087957653572763650, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758988, 1102779564330127361, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758989, 1135837257299267586, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758990, 1138737922126794754, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758991, 1061528375203463170, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086917758992, 1061530487668867074, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086989062145, 1061530619751694337, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450753, 1061822253297025025, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450754, 1062255694142828546, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450755, 1052495641386795010, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450756, 1062256740873031681, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450757, 1062316393659269121, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450758, 1063366293700980738, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450759, 1074939161525301250, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450760, 1076060712870014977, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450761, 1061812829668909057, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450762, 1074966054498848769, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450763, 1075621422876999682, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450764, 1097382185573605378, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450765, 1103858046367748097, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450766, 1108628105326092289, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450767, 1113014640997343233, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450768, 1143093726672748545, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450769, 1085417587316400129, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450770, 1083642124340371458, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438086997450771, 1085049912924889090, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839362, 1111520743557193730, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839363, 1112631475929661441, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839364, 1104991955854442497, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839365, 1105023023662612482, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839366, 1105024783022145538, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839367, 1107540332593782785, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839368, 1107544062772879362, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839369, 1113357848533086210, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839370, 1113358272057126913, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087005839371, 1113624311800684546, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948226, 1113728880098549762, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948227, 1116970180933210114, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948228, 1116970601428963329, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948229, 1116970850860027905, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948230, 1116971000152084481, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948231, 1116971000152084489, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948232, 1119172100774752258, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948233, 1119172357243858946, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948234, 1120176015352016897, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948235, 1120510130735374338, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948236, 1120973356448112642, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948237, 1121230565047607297, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087072948238, 1131799354359922690, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336833, 1133256750202601473, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336834, 1137965336910016514, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336835, 1120537689416949761, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336836, 1141540055932149761, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336837, 1141540705038442498, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336838, 1145887260638056450, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336839, 1134299132872032258, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336840, 1134299470106656769, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336841, 1141246645681946626, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336842, 1146985990204325890, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336843, 1146986333860429826, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336844, 1196779312083111937, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336845, 1196780901598498817, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336846, 1196957129467023362, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336847, 1196957567641767938, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336848, 1196957922559578114, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087081336849, 1196958494775889921, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:21', NULL, '2019-11-21 16:54:21', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087433658369, 1197435953996734466, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:22', NULL, '2019-11-21 16:54:22', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087433658370, 1197436432948502529, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:22', NULL, '2019-11-21 16:54:22', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087433658371, 1197436731075436545, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:22', NULL, '2019-11-21 16:54:22', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087433658372, 1197437255686397954, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:22', NULL, '2019-11-21 16:54:22', 0);
INSERT INTO `sys_role_menu` VALUES (1197438087433658373, 1197437717097586690, 1152129637249613826, NULL, NULL, '2019-11-21 16:54:22', NULL, '2019-11-21 16:54:22', 0);
INSERT INTO `sys_role_menu` VALUES (1216654755321528322, 1216644817794809857, 1211542090513313793, NULL, NULL, '2020-01-13 17:34:32', NULL, '2020-01-13 17:34:32', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054125641729, 1, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054129836034, 1055764151121088514, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054129836035, 1154039032844324865, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054129836036, 2, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054129836037, 3, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054134030337, 4, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054134030338, 1052495110186582018, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054134030339, 1052496683667144705, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054134030340, 1052496973111869441, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054134030341, 1078626895800258562, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054134030342, 1078627700116770818, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054134030343, 1100233955551268865, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054134030344, 1052497636940169218, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054138224642, 1053176754846191618, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054138224643, 1061495901922672641, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054138224644, 1061496252637790210, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054138224645, 1061507598204973057, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054138224646, 1061556917617836033, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054138224647, 1061859915933102081, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054138224648, 1061876766012579841, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418946, 1061884621591609345, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418947, 1062185206064861186, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418948, 1062193073656397826, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418949, 1062584666210459649, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418950, 1073455165609377793, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418951, 1078131664302788609, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418952, 1087957653572763650, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418953, 1102779564330127361, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418954, 1135837257299267586, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054142418955, 1138737922126794754, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054146613250, 1061528375203463170, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054205333506, 1061530487668867074, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054209527809, 1061530619751694337, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054209527810, 1061822253297025025, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054209527811, 1062255694142828546, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054209527812, 1052495641386795010, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054209527813, 1062256740873031681, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054213722114, 1062316393659269121, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054213722115, 1063366293700980738, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054213722116, 1069429922901590017, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054213722117, 1069432982742790146, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054213722118, 1074939161525301250, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054213722119, 1076060712870014977, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054213722120, 1061812829668909057, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054213722121, 1074966054498848769, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054213722122, 1075621422876999682, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916418, 1097382185573605378, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916419, 1103858046367748097, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916420, 1108628105326092289, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916421, 1113014640997343233, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916422, 1143093726672748545, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916423, 1085417587316400129, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916424, 1083642124340371458, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916425, 1085049912924889090, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916426, 1111520743557193730, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916427, 1112631475929661441, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054217916428, 1104991955854442497, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054222110722, 1105023023662612482, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054222110723, 1105024783022145538, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054222110724, 1107540332593782785, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054222110725, 1107544062772879362, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054276636673, 1113357848533086210, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830977, 1113358272057126913, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830978, 1113624311800684546, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830979, 1113728880098549762, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830980, 1116970180933210114, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830981, 1116970601428963329, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830982, 1116970850860027905, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830983, 1116971000152084481, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830984, 1116971000152084489, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830985, 1164793385609392129, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830986, 1173518565538021377, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830987, 1119172100774752258, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054280830988, 1119172357243858946, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025282, 1120176015352016897, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025283, 1120510130735374338, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025284, 1120973356448112642, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025285, 1121230565047607297, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025286, 1131799354359922690, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025287, 1133256750202601473, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025288, 1137965336910016514, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025289, 1205028637044678657, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025290, 1209731057977122818, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025291, 1210128628082151426, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025292, 1120537689416949761, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025293, 1141540055932149761, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054285025294, 1141540705038442498, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054289219586, 1145887260638056450, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054289219587, 1134299132872032258, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054289219588, 1134299470106656769, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054289219589, 1152130920073297921, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054343745537, 1141246645681946626, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054343745538, 1146985990204325890, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054343745539, 1146986333860429826, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054343745540, 1196779312083111937, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054347939842, 1196780901598498817, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054347939843, 1196957129467023362, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054347939844, 1197435953996734466, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054347939845, 1197436432948502529, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054347939846, 1197437255686397954, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1216984054347939847, 1197702762301231106, 1211542090513313793, NULL, NULL, '2020-01-14 15:23:03', NULL, '2020-01-14 15:23:03', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126009040897, 1, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126021623810, 1055764151121088514, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012418, 1154039032844324865, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012419, 2, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012420, 3, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012421, 4, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012422, 1052495110186582018, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012423, 1052496683667144705, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012424, 1216562606450061314, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012425, 1216644399601729537, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012426, 1216644817794809857, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012427, 1052496973111869441, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012428, 1078626895800258562, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126030012429, 1078627700116770818, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126038401026, 1100233955551268865, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126038401027, 1052497636940169218, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126038401028, 1053176754846191618, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126038401029, 1061495901922672641, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789633, 1061496252637790210, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789634, 1061507598204973057, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789635, 1061556917617836033, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789636, 1061859915933102081, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789637, 1061876766012579841, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789638, 1061884621591609345, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789639, 1062185206064861186, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789640, 1062193073656397826, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789641, 1062584666210459649, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789642, 1073455165609377793, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789643, 1078131664302788609, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789644, 1087957653572763650, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126046789645, 1102779564330127361, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126382333953, 1135837257299267586, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126382333954, 1138737922126794754, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126382333955, 1212698438463143937, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126382333956, 1217654314794360834, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126382333957, 1217654664188272642, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126382333958, 1217655039070969857, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126382388578, 1079726895800258562, 1049572543650844673, NULL, NULL, '2020-02-10 15:04:22', NULL, '2020-02-10 15:04:22', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382388579, 1079735895800258562, 1049572543650844673, NULL, NULL, '2020-02-10 15:04:22', NULL, '2020-02-10 15:04:22', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382388661, 1100244155551268865, 1049572543650844673, NULL, NULL, '2020-02-10 15:04:22', NULL, '2020-02-10 15:04:22', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382388672, 1074937661525301250, 1049572543650844673, NULL, NULL, '2020-02-10 15:04:22', NULL, '2020-02-10 15:04:22', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382388673, 1074937661553501250, 1049572543650844673, NULL, NULL, '2020-02-10 15:04:22', NULL, '2020-02-10 15:04:22', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382388674, 1074937661667501250, 1049572543650844673, NULL, NULL, '2020-02-10 15:04:22', NULL, '2020-02-10 15:04:22', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382388675, 1083643364340371458, 1049572543650844673, NULL, NULL, '2020-02-10 15:04:22', NULL, '2020-02-10 15:04:22', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382388676, 1083647734340371458, 1049572543650844673, NULL, NULL, '2020-02-10 15:04:22', NULL, '2020-02-10 15:04:22', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382391234, 1113358272057173213, 1049572543650844673, NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382397648, 1141246645681948843, 1049572543650844673, NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382399672, 1074937661667501257, 1049572543650844673, NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126382399893, 1141246645681947732, 1049572543650844673, NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126390722562, 1217655951537610753, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111169, 1212698824766930945, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111170, 1217382433134706690, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111171, 1217382969053511681, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111172, 1217383327897190402, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111173, 1217383721650061314, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111174, 1217631419846291457, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111175, 1217635324974002178, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111176, 1217635836754587649, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111177, 1213074633471987713, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111178, 1061528375203463170, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111179, 1061530487668867074, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111180, 1061530619751694337, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111181, 1061822253297025025, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111182, 1062255694142828546, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126399111183, 1052495641386795010, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126407499778, 1062256740873031681, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126407499779, 1062316393659269121, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126407499780, 1063366293700980738, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126407499781, 1069429922901590017, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126407499782, 1069432982742790146, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126407499783, 1074939161525301250, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126407499784, 1076060712870014977, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126407499785, 1061812829668909057, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126734655490, 1074966054498848769, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126734655491, 1075621422876999682, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126734655492, 1097382185573605378, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044098, 1103858046367748097, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044099, 1108628105326092289, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044100, 1113014640997343233, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044101, 1143093726672748545, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044102, 1085417587316400129, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044103, 1083642124340371458, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044104, 1085049912924889090, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044105, 1111520743557193730, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044106, 1112631475929661441, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044107, 1104991955854442497, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044108, 1105023023662612482, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044109, 1105024783022145538, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044110, 1107540332593782785, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044111, 1107544062772879362, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044112, 1113357848533086210, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044113, 1113358272057126913, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126743044114, 1113624311800684546, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432706, 1113728880098549762, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432707, 1116970180933210114, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432708, 1116970601428963329, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432709, 1116970850860027905, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432710, 1116971000152084481, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432711, 1116971000152084489, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432712, 1164793385609392129, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432713, 1173518565538021377, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432714, 1119172100774752258, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126751432715, 1119172357243858946, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704126902391234, 1113358272057175545, 1049572543650844673, NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_role_menu` VALUES (1217704126902394321, 1113624367800684546, 1049572543650844673, NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_role_menu` VALUES (1217704127082782721, 1120176015352016897, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127082782722, 1120510130735374338, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171330, 1120973356448112642, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171331, 1121230565047607297, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171332, 1131799354359922690, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171333, 1133256750202601473, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171334, 1137965336910016514, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171335, 1205028637044678657, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171336, 1209731057977122818, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171337, 1210128628082151426, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171338, 1120537689416949761, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127091171339, 1141540055932149761, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559938, 1141540705038442498, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559939, 1145887260638056450, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559940, 1134299132872032258, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559941, 1134299470106656769, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559942, 1152130920073297921, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559943, 1141246645681946626, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559944, 1146985990204325890, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559945, 1146986333860429826, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559946, 1196779312083111937, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559947, 1196780901598498817, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559948, 1196957129467023362, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559949, 1196957567641767938, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559950, 1196957922559578114, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127099559951, 1196958494775889921, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127107948546, 1197435953996734466, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127107948547, 1197436432948502529, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127107948548, 1197436731075436545, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127107948549, 1197437255686397954, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127426715650, 1197437717097586690, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127426715651, 1205036568836706305, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127426715652, 1207138852118654978, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127426715653, 1207916114625581058, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127435104257, 1213060715928981506, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127435104258, 1213298587940491265, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217704127435104259, 1197702762301231106, 1049572543650844673, NULL, NULL, '2020-01-16 15:04:22', NULL, '2020-01-16 15:04:22', 1);
INSERT INTO `sys_role_menu` VALUES (1217705786902394321, 1113624367800686904, 1049572543650844673, NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_role_menu` VALUES (1217705786902396601, 1113728880098549757, 1049572543650844673, NULL, NULL, '2020-02-13 16:46:26', NULL, '2020-02-13 16:46:26', 0);
INSERT INTO `sys_role_menu` VALUES (1218802394646552578, 1, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394663329794, 1055764151121088514, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394667524098, 1154039032844324865, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394675912705, 2, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394680107010, 3, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394688495618, 4, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394696884226, 1052495110186582018, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394701078530, 1052496683667144705, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394713661442, 1216562606450061314, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394717855746, 1216644399601729537, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394726244353, 1216644817794809857, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394730438657, 1052496973111869441, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394738827265, 1078626895800258562, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394743021570, 1078627700116770818, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394751410177, 1100233955551268865, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394755604481, 1052497636940169218, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394763993090, 1053176754846191618, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394768187394, 1061495901922672641, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394772381697, 1061496252637790210, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394780770305, 1061507598204973057, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394789158913, 1061556917617836033, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394797547521, 1061859915933102081, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394805936129, 1061876766012579841, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394814324738, 1061884621591609345, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394822713346, 1062185206064861186, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394831101953, 1062193073656397826, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394835296258, 1062584666210459649, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394843684865, 1073455165609377793, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394852073474, 1078131664302788609, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394860462081, 1087957653572763650, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394864656385, 1102779564330127361, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394986291202, 1135837257299267586, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802394998874114, 1138737922126794754, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395007262721, 1212698438463143937, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395011457025, 1212698824766930945, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395019845633, 1213074633471987713, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395024039938, 1061528375203463170, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395036622849, 1061530487668867074, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395040817154, 1061530619751694337, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395049205761, 1061822253297025025, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395053400066, 1062255694142828546, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395061788674, 1052495641386795010, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395065982978, 1062256740873031681, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395074371585, 1062316393659269121, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395082760193, 1063366293700980738, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395086954498, 1069429922901590017, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395099537409, 1069432982742790146, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395107926017, 1074939161525301250, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395112120321, 1076060712870014977, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395124703234, 1061812829668909057, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395128897537, 1074966054498848769, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395137286146, 1075621422876999682, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395141480450, 1097382185573605378, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395149869057, 1103858046367748097, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395158257665, 1108628105326092289, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395162451970, 1113014640997343233, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395170840577, 1143093726672748545, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395179229186, 1085417587316400129, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395187617793, 1083642124340371458, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395196006401, 1085049912924889090, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395200200705, 1111520743557193730, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395309252610, 1112631475929661441, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395321835521, 1104991955854442497, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395334418434, 1105023023662612482, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395342807041, 1105024783022145538, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395351195650, 1107540332593782785, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395363778562, 1107544062772879362, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395372167169, 1113357848533086210, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395380555777, 1113358272057126913, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395393138689, 1113624311800684546, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395401527297, 1113728880098549762, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395409915905, 1116970180933210114, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395422498817, 1116970601428963329, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395430887425, 1116970850860027905, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395443470337, 1116971000152084481, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395451858945, 1116971000152084489, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395464441857, 1164793385609392129, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395477024770, 1173518565538021377, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395485413377, 1119172100774752258, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395493801985, 1119172357243858946, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395497996290, 1120176015352016897, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:29', NULL, '2020-01-19 15:48:29', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395506384898, 1120510130735374338, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395514773505, 1120973356448112642, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395523162113, 1121230565047607297, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395527356417, 1131799354359922690, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395535745025, 1133256750202601473, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395539939329, 1137965336910016514, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395552522242, 1205028637044678657, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395556716546, 1209731057977122818, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395569299457, 1210128628082151426, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395577688066, 1120537689416949761, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395690934273, 1141540055932149761, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395707711489, 1141540705038442498, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395716100097, 1145887260638056450, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395724488706, 1134299132872032258, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395737071618, 1134299470106656769, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395745460226, 1152130920073297921, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395753848834, 1141246645681946626, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395762237441, 1146985990204325890, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395770626050, 1146986333860429826, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395779014657, 1196779312083111937, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395787403265, 1196780901598498817, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395795791873, 1196957129467023362, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395804180481, 1196957567641767938, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395812569089, 1196957922559578114, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395820957697, 1196958494775889921, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395829346305, 1197435953996734466, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395837734913, 1197436432948502529, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395846123521, 1197436731075436545, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395850317826, 1197437255686397954, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395858706434, 1197437717097586690, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395867095042, 1205036568836706305, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395871289345, 1207138852118654978, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395879677954, 1207916114625581058, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395888066561, 1213060715928981506, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395896455169, 1213298587940491265, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218802395909038081, 1197702762301231106, 1049572543650844673, NULL, NULL, '2020-01-19 15:48:30', NULL, '2020-01-19 15:48:30', 1);
INSERT INTO `sys_role_menu` VALUES (1218804311300874241, 1, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311309262850, 1055764151121088514, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311313457153, 1154039032844324865, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311321845762, 2, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311330234369, 3, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311334428674, 4, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311342817281, 1052495110186582018, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311347011586, 1052496683667144705, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311355400194, 1216644817794809857, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311359594498, 1052496973111869441, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311367983106, 1078626895800258562, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311376371713, 1078627700116770818, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311380566017, 1100233955551268865, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311388954625, 1052497636940169218, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311393148930, 1053176754846191618, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311401537537, 1061495901922672641, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311409926146, 1061496252637790210, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311418314754, 1061507598204973057, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311426703361, 1061556917617836033, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311435091969, 1061859915933102081, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311443480577, 1061876766012579841, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311447674881, 1061884621591609345, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311456063490, 1062185206064861186, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311464452098, 1062193073656397826, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311472840705, 1062584666210459649, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311481229314, 1073455165609377793, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311489617921, 1078131664302788609, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311493812225, 1087957653572763650, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311502200834, 1102779564330127361, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311510589442, 1135837257299267586, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311518978050, 1138737922126794754, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311628029954, 1212698438463143937, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311636418562, 1212698824766930945, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311640612865, 1061528375203463170, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311649001474, 1061530487668867074, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311653195777, 1061530619751694337, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311661584385, 1061822253297025025, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311665778690, 1062255694142828546, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311669972994, 1052495641386795010, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311678361601, 1062256740873031681, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311682555906, 1062316393659269121, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311686750209, 1063366293700980738, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311695138817, 1069429922901590017, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311699333121, 1069432982742790146, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311707721730, 1074939161525301250, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311711916033, 1076060712870014977, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311720304641, 1061812829668909057, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311724498945, 1074966054498848769, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311732887553, 1075621422876999682, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311737081857, 1097382185573605378, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311741276162, 1103858046367748097, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311749664769, 1108628105326092289, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311753859073, 1113014640997343233, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311762247682, 1143093726672748545, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311766441985, 1085417587316400129, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311774830593, 1083642124340371458, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311779024898, 1085049912924889090, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311783219202, 1111520743557193730, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311791607810, 1112631475929661441, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311795802113, 1104991955854442497, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311804190721, 1105023023662612482, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311896465409, 1105024783022145538, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311909048321, 1107540332593782785, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311913242626, 1107544062772879362, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311917436929, 1113357848533086210, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311925825538, 1113358272057126913, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311930019841, 1113624311800684546, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311934214145, 1113728880098549762, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311942602754, 1116970180933210114, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311946797058, 1116970601428963329, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311950991361, 1116970850860027905, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311959379970, 1116971000152084481, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311967768578, 1116971000152084489, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311971962882, 1164793385609392129, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311976157186, 1173518565538021377, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311984545793, 1119172100774752258, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311988740098, 1119172357243858946, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804311997128706, 1120176015352016897, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312001323010, 1120510130735374338, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312009711617, 1120973356448112642, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312013905921, 1121230565047607297, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312018100226, 1131799354359922690, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312026488833, 1133256750202601473, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312030683138, 1137965336910016514, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312034877442, 1205028637044678657, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312043266049, 1209731057977122818, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312047460353, 1210128628082151426, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312055848962, 1120537689416949761, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312060043265, 1141540055932149761, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312068431874, 1141540705038442498, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312072626177, 1145887260638056450, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312164900866, 1134299132872032258, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312173289473, 1134299470106656769, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312181678081, 1152130920073297921, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312185872385, 1141246645681946626, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312198455297, 1146985990204325890, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312206843906, 1146986333860429826, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312211038210, 1196779312083111937, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312219426817, 1196780901598498817, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312223621121, 1196957129467023362, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312232009730, 1197435953996734466, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312236204034, 1197436432948502529, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312240398338, 1197437255686397954, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1218804312248786945, 1197702762301231106, 1211542090513313793, NULL, NULL, '2020-01-19 15:56:06', NULL, '2020-01-19 15:56:06', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237731655682, 1, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044289, 1055764151121088514, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044290, 1154039032844324865, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044291, 2, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044292, 3, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044293, 4, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044294, 1052495110186582018, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044295, 1052496683667144705, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044296, 1216562606450061314, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044297, 1216644399601729537, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044298, 1216644817794809857, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044299, 1052496973111869441, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044300, 1078626895800258562, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044301, 1078627700116770818, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237740044302, 1100233955551268865, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432898, 1052497636940169218, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432899, 1053176754846191618, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432900, 1061495901922672641, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432901, 1061496252637790210, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432902, 1061507598204973057, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432903, 1061556917617836033, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432904, 1061859915933102081, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432905, 1061876766012579841, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432906, 1061884621591609345, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432907, 1062185206064861186, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432908, 1062193073656397826, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432909, 1062584666210459649, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432910, 1073455165609377793, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432911, 1078131664302788609, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432912, 1087957653572763650, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237748432913, 1102779564330127361, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237807153153, 1135837257299267586, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237807153154, 1138737922126794754, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237807153155, 1212698438463143937, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541761, 1212698824766930945, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541762, 1213074633471987713, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541763, 1061528375203463170, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541764, 1061530487668867074, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541765, 1061530619751694337, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541766, 1061822253297025025, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541767, 1062255694142828546, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541768, 1052495641386795010, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541769, 1062256740873031681, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541770, 1062316393659269121, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541771, 1063366293700980738, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541772, 1069429922901590017, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541773, 1069432982742790146, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541774, 1074939161525301250, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541775, 1076060712870014977, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541776, 1061812829668909057, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541777, 1074966054498848769, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541778, 1075621422876999682, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541779, 1097382185573605378, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541780, 1225692387989356545, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237815541781, 1103858046367748097, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237823930369, 1108628105326092289, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237823930370, 1113014640997343233, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237823930371, 1225703228285431809, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237823930372, 1143093726672748545, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237823930373, 1085417587316400129, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237823930374, 1083642124340371458, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237865873409, 1085049912924889090, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237865873410, 1111520743557193730, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237865873411, 1112631475929661441, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237865873412, 1104991955854442497, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237865873413, 1105023023662612482, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237865873414, 1105024783022145538, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237865873415, 1107540332593782785, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262017, 1107544062772879362, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262018, 1113357848533086210, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262019, 1113358272057126913, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262020, 1113624311800684546, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262021, 1113728880098549762, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262022, 1116970180933210114, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262023, 1116970601428963329, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262024, 1116970850860027905, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262025, 1116971000152084481, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262026, 1116971000152084489, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262027, 1164793385609392129, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262028, 1173518565538021377, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262029, 1119172100774752258, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262030, 1119172357243858946, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262031, 1120176015352016897, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262032, 1120510130735374338, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262033, 1120973356448112642, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262034, 1121230565047607297, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262035, 1131799354359922690, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262036, 1133256750202601473, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262037, 1137965336910016514, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262038, 1205028637044678657, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720237874262039, 1209731057977122818, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238876700673, 1210128628082151426, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238876700674, 1120537689416949761, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238876700675, 1141540055932149761, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089281, 1141540705038442498, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089282, 1145887260638056450, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089283, 1134299132872032258, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089284, 1134299470106656769, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089285, 1152130920073297921, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089286, 1141246645681946626, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089287, 1146985990204325890, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089288, 1146986333860429826, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089289, 1196779312083111937, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089290, 1196780901598498817, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089291, 1196957129467023362, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089292, 1196957567641767938, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089293, 1196957922559578114, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089294, 1196958494775889921, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089295, 1197435953996734466, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089296, 1197436432948502529, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089297, 1197436731075436545, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089298, 1197437255686397954, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089299, 1197437717097586690, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089300, 1205036568836706305, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238885089301, 1207138852118654978, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238893477889, 1207916114625581058, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238893477890, 1213060715928981506, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238893477891, 1213298587940491265, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1225720238893477892, 1197702762301231106, 1049572543650844673, NULL, NULL, '2020-02-07 17:57:32', NULL, '2020-02-07 17:57:32', 0);
INSERT INTO `sys_role_menu` VALUES (1226762975485673478, 1225634922629066754, 1049572543650844673, NULL, NULL, '2020-02-10 15:01:00', NULL, '2020-02-10 15:01:00', 0);
INSERT INTO `sys_role_menu` VALUES (1227075493858893831, 1227067851459518466, 1049572543650844673, NULL, NULL, '2020-02-11 11:42:50', NULL, '2020-02-11 11:42:50', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标记【 0：未删除，1：已删除】',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1061806203427790850', 'smanager', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('10618062034277908501', 'smanager2', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('106180620342779085012', 'smanager22', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('106180vbb277908501', 'smaddnadf2', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('106182323sd9085012', 'smanagf22er22', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('10618asdas77908501', 'smanag22er2', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('10618xxx9085012', 'smanagxxxf22er22', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('10618xxx90850123', 'smanagxxxf22er223', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('1061vvv77908501', 'smanagrrr22er2', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('1061vvv779085012', 'smanagrrr22er22', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('1061vvv7908501', 'smaddnavvvdf2', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);
INSERT INTO `sys_user` VALUES ('1061vvv790850121', 'smaddnavvvdf221', 'E10ADC3949BA59ABBE56E057F20F883E', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标记【 0：未删除，1：已删除】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1227538614729957379 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1055346259214319618, 1, 1049572543650844673, NULL, NULL, '2018-10-25 14:32:10', NULL, '2018-10-25 14:32:10', 0);
INSERT INTO `sys_user_role` VALUES (1057283738887196674, 1057271540471234562, 1049572543650844673, NULL, NULL, '2018-10-30 22:51:01', NULL, '2018-10-30 22:51:01', 0);
INSERT INTO `sys_user_role` VALUES (1070511597650948098, 1070511597512536065, 1049572543650844673, NULL, NULL, '2018-12-06 10:53:48', NULL, '2018-12-06 10:53:48', 0);
INSERT INTO `sys_user_role` VALUES (1128948235769905153, 1057270960558374914, 1049572543650844673, NULL, NULL, '2019-05-16 17:00:08', NULL, '2019-05-16 17:00:08', 0);
INSERT INTO `sys_user_role` VALUES (1152064709373407233, 1152064430691266562, 1049572543650844673, NULL, NULL, '2019-07-19 11:56:45', NULL, '2019-07-19 11:56:45', 0);
INSERT INTO `sys_user_role` VALUES (1152128709415043073, 1057574633949212674, 1057574543364829186, NULL, NULL, '2019-07-19 16:11:04', NULL, '2019-07-19 16:11:04', 0);
INSERT INTO `sys_user_role` VALUES (1153854839556345858, 1153854839543762946, 1152129637249613826, NULL, NULL, '2019-07-24 10:30:05', NULL, '2019-07-24 10:30:05', 0);
INSERT INTO `sys_user_role` VALUES (1153854990647758850, 1153854990639370242, 1049572543650844673, NULL, NULL, '2019-07-24 10:30:42', NULL, '2019-07-24 10:30:42', 0);
INSERT INTO `sys_user_role` VALUES (1153858543136796673, 1153858543073882114, 1049572543650844673, NULL, NULL, '2019-07-24 10:44:48', NULL, '2019-07-24 10:44:48', 0);
INSERT INTO `sys_user_role` VALUES (1153904126986481666, 1055765052154056706, 1049572543650844673, NULL, NULL, '2019-07-24 13:45:57', NULL, '2019-07-24 13:45:57', 0);
INSERT INTO `sys_user_role` VALUES (1153904127150059522, 1055765052154056706, 1152129637249613826, NULL, NULL, '2019-07-24 13:45:57', NULL, '2019-07-24 13:45:57', 0);
INSERT INTO `sys_user_role` VALUES (1153913573184004097, 1153913573150449665, 1049572543650844673, NULL, NULL, '2019-07-24 14:23:29', NULL, '2019-07-24 14:23:29', 0);
INSERT INTO `sys_user_role` VALUES (1153918528411619330, 1153859075423334402, 1152129637249613826, NULL, NULL, '2019-07-24 14:43:10', NULL, '2019-07-24 14:43:10', 0);
INSERT INTO `sys_user_role` VALUES (1153934808015429634, 1153901729460776962, 1049572543650844673, NULL, NULL, '2019-07-24 15:47:51', NULL, '2019-07-24 15:47:51', 0);
INSERT INTO `sys_user_role` VALUES (1153945266633977857, 1061806203427790850, 1049572543650844673, NULL, NULL, '2019-07-24 16:29:25', NULL, '2019-07-24 16:29:25', 0);
INSERT INTO `sys_user_role` VALUES (1153945595379331073, 1153945595337388033, 1049572543650844673, NULL, NULL, '2019-07-24 16:30:43', NULL, '2019-07-24 16:30:43', 0);
INSERT INTO `sys_user_role` VALUES (1153947891098071041, 1153946436538609665, 1049572543650844673, NULL, NULL, '2019-07-24 16:39:51', NULL, '2019-07-24 16:39:51', 0);
INSERT INTO `sys_user_role` VALUES (1153947891140014082, 1153946436538609665, 1152129637249613826, NULL, NULL, '2019-07-24 16:39:51', NULL, '2019-07-24 16:39:51', 0);
INSERT INTO `sys_user_role` VALUES (1154631591748636673, 1153945747628371969, 1049572543650844673, NULL, NULL, '2019-07-26 13:56:38', NULL, '2019-07-26 13:56:38', 0);
INSERT INTO `sys_user_role` VALUES (1156037338583732225, 1126787286214901761, 1049572543650844673, NULL, NULL, '2019-07-30 11:02:34', NULL, '2019-07-30 11:02:34', 0);
INSERT INTO `sys_user_role` VALUES (1156852913048539137, 1153945562777006081, 1152129637249613826, NULL, NULL, '2019-08-01 17:03:22', NULL, '2019-08-01 17:03:22', 0);
INSERT INTO `sys_user_role` VALUES (1156853054673408002, 1156853048100933633, 1152129637249613826, NULL, NULL, '2019-08-01 17:03:56', NULL, '2019-08-01 17:03:56', 0);
INSERT INTO `sys_user_role` VALUES (1159379134849667073, 1159379024161984514, 1049572543650844673, NULL, NULL, '2019-08-08 16:21:40', NULL, '2019-08-08 16:21:40', 0);
INSERT INTO `sys_user_role` VALUES (1159382584010375169, 1159382437171986433, 1049572543650844673, NULL, NULL, '2019-08-08 16:35:22', NULL, '2019-08-08 16:35:22', 0);
INSERT INTO `sys_user_role` VALUES (1169189277602963458, 1169188402696314882, 1152129637249613826, NULL, NULL, '2019-09-04 18:03:40', NULL, '2019-09-04 18:03:40', 0);
INSERT INTO `sys_user_role` VALUES (1197763599052009473, 1197763096083656705, 1049572543650844673, NULL, NULL, '2019-11-22 14:27:50', NULL, '2019-11-22 14:27:50', 0);
INSERT INTO `sys_user_role` VALUES (1197763871111344129, 1197763721898979330, 1049572543650844673, NULL, NULL, '2019-11-22 14:28:54', NULL, '2019-11-22 14:28:54', 0);
INSERT INTO `sys_user_role` VALUES (1204937493596475393, 1204937493567115266, 1049572543650844673, NULL, NULL, '2019-12-12 09:34:19', NULL, '2019-12-12 09:34:19', 0);
INSERT INTO `sys_user_role` VALUES (1211542234302443521, 1209303564290019329, 1211542090513313793, NULL, NULL, '2019-12-30 14:59:12', NULL, '2019-12-30 14:59:12', 0);
INSERT INTO `sys_user_role` VALUES (1224196859611635713, 1224196858588225537, 1049572543650844673, NULL, NULL, '2020-02-03 13:04:10', NULL, '2020-02-03 13:04:10', 0);
INSERT INTO `sys_user_role` VALUES (1225697255403597826, 1225697253923008513, 1049572543650844673, NULL, NULL, '2020-02-07 16:26:12', NULL, '2020-02-07 16:26:12', 0);
INSERT INTO `sys_user_role` VALUES (1227538614729957378, 1227538614629294082, 1049572543650844673, NULL, NULL, '2020-02-12 18:23:06', NULL, '2020-02-12 18:23:06', 0);

-- ----------------------------
-- Table structure for t_paper
-- ----------------------------
DROP TABLE IF EXISTS `t_paper`;
CREATE TABLE `t_paper`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `ref_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联类型ID',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
  `sort_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '排序编号',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标记【 0：未删除，1：已删除】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_paper
-- ----------------------------
INSERT INTO `t_paper` VALUES ('1252527586428743682', '如何计算体积', '1', 'sys', '<div class=\"ql-container ql-snow\"><div class=\"ql-editor\"><h1 class=\"ql-align-center\"><strong>如何计算体积</strong></h1><h3 class=\"ql-align-center\"><br></h3><p class=\"ql-align-center\">我司海运计算体积很简单，一件货物长X 宽 X 高进行测量，多件货物测量后进行相加即可. 如果一票货物实际重量大过它的体积，则按照重量体积计算，不同国家对体积的重量有一定限制，例如：</p><p class=\"ql-align-center\"><br></p><p class=\"ql-align-center\">1立方体积货物其重量是800公斤，则计费体积是1.6立方</p><p class=\"ql-align-center\"><br></p><p class=\"ql-align-center\">2立方体积货物其重量是800公斤，则计费体积是2立方</p><p class=\"ql-align-center\"><br></p><p class=\"ql-align-center\">1立方体积货物其重量是2000公斤，则计费体积是4立方… 以此类推</p><p class=\"ql-align-center\"><br></p><p class=\"ql-align-center\">测量方法： 一箱货物长是35厘米 宽是50厘米 高是50厘米，其体积应是 0.35 x 0.5 x 0.5 = 0.087 立方</p><p class=\"ql-align-center\"><br></p><p class=\"ql-align-center\"><img src=\"data:image/jpeg;base64,/9j/4QEHRXhpZgAASUkqAAgAAAAHABIBAwABAAAAAQAAABoBBQABAAAAYgAAABsBBQABAAAAagAAADEBAgAVAAAAcgAAADIBAgAUAAAAhwAAABMCAwABAAAAAQAAAGmHBAABAAAAmwAAAAAAAABgAAAAAQAAAGAAAAABAAAAQUNEIFN5c3RlbXMgyv3C67PJz/EAMjAxMjowNDoxMCAxNzo1MzoyOAAFAACQBwAEAAAAMDIyMJCSAgAEAAAAOTUzAAKgBAABAAAAFgIAAAOgBAABAAAAcgEAAAWgBAABAAAA3QAAAAAAAAACAAEAAgAEAAAAUjk4AAIABwAEAAAAMDEwMAAAAAAAAAAB/8AAEQgBcgIWAwEhAAIRAQMRAf/bAIQAAwICAgIBAwICAgMDAwMEBwQEBAQECQYGBQcKCQsLCgkKCgwNEQ4MDBAMCgoPFA8QERITExMLDhUWFRIWERITEgEEBQUGBQYNBwcNGxIPEhsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsb/8QAuQABAAEFAQEBAAAAAAAAAAAAAAYCBAUHCAMJARAAAQMDAgMDBwUKBgoMDwAAAgADBAEFBgcSCBETFCIyCRUWISNCUhcxM0FhGCRDU2JygpKisgolUWNzwjQ2OVh2lrTS0/AmNURWZHR1gYOho+IaKDc4RlRVZZGVtbbB8fIBAQADAQEBAAAAAAAAAAAAAAACAwUEAQYRAQACAgEEAgMBAQEAAAAAAAACEQEDBBITITMxMhQiQSNhUf/aAAwDAQACEQMRAD8A+qaICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICIITrfc8ysvBfn1405bluZZAxe5SbCESJSU+U4IrhR6Ns1EqOH1aByColurypyrz5LgvANN+FOV/Bxsj15tEy1XrPm8flZJdM8uc6tMhgZiLQvCAzi2PsOjO6AttiQ9WhNl7Xrkbodq6O6qw5OiemWJarZpaoOrGR4fbrpPx+4yGYV4kSCi0OSdIPdOnJxuRuoLdKDVs6cqba0pKtWJVihcLGZTMpym64z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Hg+jTpoUmqPwS9op4n3N66ixxno260Mc/AEf9wFo8DFdWHBzc3GLD41/dTdQ/wDADFf8vv62uthliICICICICICICICICICICICICICICICICICICICICjGTAYXdl/3dmwEHLGrTPR14uf8AOdE/2AUPXze72N3V9Tp/anr/AJVT8Lr6RPZn7NSu3lU8tnsk/C+NSHl6vwfjVB/HzQUB0/PsT+mD99bD8DKDmvLMg1wzLyll60lwXWD0QtdssEe7B/sfiT9xEQAfj73v/H9SkPD3qlqDetV8u0c1cO2zMnwsmTrc4I7AnR3Q3iZhy8fg8G3x+Hud/tzDHZty3nuMjwx59luoWnOWzsxu3b5FrzCdbIx9maZ6MZnZsDuAH6/jXnxBarZni2UYnphphGgUy3O5D0aHNuIGcaC0Ad93Zy759/8AK8HhNV51dfIp7HZ/nbBZSHEvo7oLlWYSNTGNTJLFtA4MQscahnGe6wb3tjX0oizvPx+4onodqxl2Y4deskrxJRswdh4xIlSLC7jMe1S7dL2CQnTaHtgCtDD4O8KtuE9csoyjLDWmlPEvq5k+o2AxA10t2RXW83fst8xi4WCPbm4kfvBv7XsDqntruAA727l3D8NZjlWuOu9zxXPdasQyi3W7F8ByHzSGOOWwDpdGWnQF105B03h4w7gf/wB3T0xtT3csllWr2tGououdOaT5bDxiz6dWSPczZdtjUs7u68z1dhm79EOwCHufyfqe+ruseqzvk4rFrtg2XwscI4cR2fBas4yDdeePonsddMxAP0N/5Srxpj48Lu7l0xG3yYjTbn4QAXVVq/20iB8Bh/UUuJitksKOTm4xYHGv7qbqH/gBiv8Al9/W11qM8RARARARARARARARARARARARARARARARARARARARAUfyhsqwmHqeECIa/wDP/wDpBzDrSzs1zkOfjIzJ/wCv6i1+vnd/sk3dXrirAN7SoNVrVYfQqgw2OoCtzQUnvVCCl4N90ibPxwLYQdxBzzmWkuu8Hj1u2sOk0/AundLOzaSj5AcvfsDYZ9xoPyKe+rrENFdYdOMSvuaY7kuMZDqjmExl+8z7226zAaaAD5tMi1Td3T9/u7vV3B5Lt7se3Tm7ee5a90/4XMVicHw6basgxk8ibdDyC6m067GZOcfwGBgewPB+X8AK21F4X4kjTzEG9FbqziF80/kvSbAcgnZUb21d5tO7957TP8/3+4ofkf6f8I6v82SiQOLqTjN1fvV80wh3JtlnzOzbGZZw3j6wG92jq9/ZsDZ3PjUQx/h91PuvEfkerOpFwwiFfLljj1jjRMXakBGkG8NQ6sg3e9v+YPf9z4FLuwhdPO1lELFwua9TNJsO0lzPI8BiYbjF0G5FItVJb1zLaZlt74CHiPxdz9PwHlcg4X9VDfy/T3GswxyJpznF+89XLqsu+do28wN1prb7LZ3A8Z/V+vbLkxV9lf5jw7ap23VDLpujOQ4zAsef2qPbLvHvDT3Whgyz0d0faHj2b/F8f/wr1e0D1NvnCZZdDdNbhiTeMw4EdmZKu5yAmE80YHvDpAYd7Z3+4oY3xvFrO1lu7TJnPfQ+2xdS/R/z52nYfmHrdj2b+5s6vf37F1za3d+Qsd//AHSH9RdHHzjMpZw5d+KjTB4jSsnyl2o1wi168aNiOM2l18O+DctuReHzjEXzUdFmVEdIPFQJDJ17pjz26tBwiICICICICICICICICICICICICICICICICICICICICwWT/wBrg/03/wCCQcya6hs1ViP/AI+AH75rW5r53f7JN3V64vUPoU/pFWtUIgpPvqg2/tQUGHsl5IGz+OYnT8HWBbA3+x8CChujdHA7iuFH/jy/68jRR6f6tj/483PVTnRU+rl60+48j59T+VUH9lVGWHlKN1P5FSdadVLy9XFnPfm8L/jIfvrozEhKfl7HMaEDPtj9f+vv7FscLNxlll8vFSpZaN/+cdrp/h9G/wDtqxra61GaIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgKwu7VHsdkB+RuQcw66h/sjtT5+/DP99aup4Vgcn2NvR6xFzLwPpk3oCpNBQf0Ko/Q/61IUmGye1/TAp6H0QoK+59iKKQf0Ko2ey99RHkHTB3voaC3/C+NUGaiKN+/wCjVq9MbZaNx9wAFv3zSEbk8l4SLALJLuuRNXV+CYRWO+Bu+/8AmLpHA4Ai4/NLfyAegFfc5+/+4C3+Nq7bH3bLYTS21XK1666zTLjAmxY90zWNKhOvNGASWaWC0tVcarXxh1WnQ3D74GPuLaC7XIIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgIgLxeYbfgkw+G8DHaQ/yoOXddQ6N4ggbm84r0lnf+otTUPurA5Psbej1q0XMvUh3/AqkBEFPj9mqe59ikPM/7JaP8tTsOn2UfjQV9z7E7n4tRSUGm/eoig15Ggt15Ge/8IoixOQ9LvAW60xDny/gDwApnjGmJnOC65O/2yX4wZ9xlaujTjMnFu20lVwy2zY1krWIWS3Scjyx6N2mNi1mOP284+8wOTsddaBqN3D9s8YBv2NAfVMAPeeNRZ8PCYTNwgR4k+jAnMZjSyksg/UeZgLpNgTgUKteRbA50p4R8K18eWVLFs8iIiICICICICICICICICICICICICICICICICICICICICIOa+IK2P3640uNqp2gGHvwXv8AcBaF7Y2c82PA6HjA/Gsfk6+qVtXjyuNLgT9p31VuD5uos34aP1WF4PZVo239n5i8o14fD2b4bwViMmVjSW5LXUbXqiKnf7VUH9IgoPpn0vz1Pgb+1AM2wVBqKQqFEee/v/MvF4zUZZpK6WMqY3Gi9R9wAV7ZMRvmWu9QwO22z4z+me/7i7uPozJy8jZiLa2PYfbbJABiDFAPjM/Gax1jmZfq1EozpY3W14297ORmc2ObJ7T7++0x3Wds3ePLbLMhj+2aMO17HWltUx+tuPB8Es+n2HHarYEqTKlvdtuNzlVE5lzkbREpD5CI7i2iA0AREAAQaaEGgABmKsViICICICICICICICICICICICICICICICICICICICICIC1vXWXF7VqmGH5tBu2GXCVP832h6+sg1CvZGexmkWW2ZxydeLfUIpmEraBFVgR7yCVXjGrVeo/OYxQHeXqda50P/vLUeoOgkO7Rge7Ecs/H2iIPRfA1XKGJYW659Emick00zDFJTrkWL54ggfgANkln88Pf/Y/TUch3iJJkmDZ7JDHceZPuPM/ngsXZqpqa9nW8rwfsmljANc7rkzFkPfAdWTRFQq/wSB7Pudz31OvwRm376Cn9D/rVHgUUlBmvLeojyN5v6NtY4JMq5XrzbY4vbJHgPZ4A/PV2nX3FOzNJ3iumrcWeF1vn35L+D3AUov2U2DD3Lfa5T3ab1d97dksME2jud1MNm8I7JmG/ZvAzPuA0HfeNoAMw3tccQZOzZ1snaNE66h7bxrfC7Zan+ZxcAlNR5FtjBT6I53dPtUvxGYdUooHUNgOnHCWe8FdTnpWiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAsXd7Rar7is6x3m2RLjbbkwUaZEksC6zJaMdpgYF3SAh7tRrTlXmg1uGlOWYCZydDss7Bbm67qYdfzcl2Qhp+CiOf2Rbu6LTQUaJ2KwG/ZCMldWvWy2Q8qhYtqpZJmn+QTZLcKINzdE7VdZBnsAINwH2Lxunv6Uc+lLMAqZRwFBObnjlmutS7dbGHjKneLp7TL9JarzzhrxzL6UeEwGSG3ZIr7GSP/AEof5ir2YtZrnTQGfaE6l4VHGhgF4ttDIGpAfTf6/qH+eta7zZlG2+wbLodwwNZO7RmLU17sSZuwn96u/nrMfglwy8O6Pk/CqtEQ/ow/PU1372kA/oVQaDyM1ZTJ7EaLvfPZ8AfGvIRzN5KeML2yYTf8qd6l260C2fid/tnltXHsSttqgswLbC2H4O4HfNbnH1dtk7NlrNp/NcyyqVjeldvjR2Ibpxp2XXaN2i0xpLNeTsZqOEhqRKki53C2mDTRdYSd6sc457NwLS3HNO4so7a/cLjd7nVqt0u91mnLmziDfs3EXdaaoRumMdkAjtE670mw31Xa5LT1EREQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQFi7vaLVfcVnWO82yJcbbcmCjTIklgXWZLRjtMDAu6QEPdqNacq80Gth0MLDA6+guVV0/2erzF2Ht2NOV/wCTt7XZ/G6f3k7F3unve63Laha5Vwwehr1itdP6D6/Pvb+3Y05X/lHY12fxtB9+tRd7p7Gety3JVlU2q60280bbwiYOd0hL61qbUHh8xnMKuyYbTMR4/WIUDZsL8gh8CjKOJpwzmLnLJNIspwqbKrEhHcGQPvxwc2PB/nqKwL9AnuvNtv7JDH00cw2GH54LG3aqa2nbbIA9+EXrv3ridasPcU1Z78AHG0FBmreS97JBj2fOt7n9hxyL2kvfdP6EFsPDtN4trdCbcfv+4fjj9z8xa+jRiLO3TpLLve8cw6xxZ2SuzBjvyQhMs263SJ0mSewz6MePHA3nT2A8ewAPYDJmfcAzV/bdPsr1Gji7qdCHHMW29P0OiSGpZ3iOfe5XiR0efwCUSOfS7roOvTGndgaPyzfltO0Wm1WLFYNjstti26221gY0SJGYFpmM0A7QABHuiAj3aDSnKnJZPnVELVIiQiAiAiAiAiAiAiAiAiAiAiAiAiAiDlXj1fvEP5B/MuXZVYvPusFlxa5+Ysjm2rtlumdXtDLnZnQ3bui3yOvfDvbCHcXPqpBorV7jJ0c0e1kc0+ul0iXXIYcduTcoLOR2a2nbhdpzZo5W4zookZjSpbG6mQjsI6ALrVXLSw8bOj+V8OOfag4nHut9e0xt4XTKLJa34MyVDjnRw6GEluSUF/2LLrtaMyjrSgVCtKO+zQWk/jYxK0cMFt10vWkOpVt0zuEe3yzyqXDgNMRGZjjTYuORay+2kAOPUGpNxzodBqbXVbIDPYGq+u+PaVamYZghYzkGT5Zn8iYzYbHZAji/IGIxV+S6Tsl5lgAbDbzoTtCKpjtEuRbQj+mnE6xqXxY5Lo5G0X1Asd6wvsvpI/dHLT2W19qjm/F3kxPdJzqAHKnRFzbWtN+ynOtIVrFxQ6kYXx+6U6SWXQvNax8ovF6J4wm2Wp3+JBgPV2RBOXyAKuux5FSfOMdBZoNBOpmAhH/KMXy+3XyB+b5a5asqwi6s9jGtuduwMSgB6e1EdakVhSHGXmnY77nNonHArRwakNDGm3at24o7NjGd4lAzjSnUDF7LnWQMYzYMgukaEMWVMkg4UUTjBKKax1enypR6M2TdSpR2jVaFtDdaIOStUNL9aMA4YKXa337UrVLVs477lcwtuRyrJZLa8DlOhLkWJiWTToMskJdljQpNZRRiE26k/wB6a5LqDMynyNrOUae5/dc6l5Hj8KzUzHFrU/27qSjahSruzCijV4XY1XHpRRm6CYlHJrm3WlSEIrZJumeIcR2H2TUPDNf9PJeQ3CjOO3DLdT58+zz7i3UTbguVj3mUzR12m6oMyBEH6NmA769yvVSC0u12tVgxWZfb7c4luttujuS5kyW8LLEZkBqRuOGVaCICNK1qVa0pSlK1quf7nxsYlZeHgNarzpDqVA0vckM0DMpMOA1EKI9KGMzOpEKXSfWOdTBwfvXeTZidArStEF3qrxjY1pNxHZHplc9JdQLzPxfD3M9my7XS11i1szVdr0oKvTWzrscoQVbqFHK1CtREhqJVu9HeLnEtY9fbXpzD07zXG7lfMHZ1Dtjt7CBViXaHnm2mnRrGlvEJkTnOgGIlSg150p6qVDeqINRV0Hh4tuLRTMrtpy23XqBZLYyzIx10x+jAre6BBHZ31MnRgnEN3qmRnv2GBzVXLMCMI2uOJ9gtzfdrmNhByXZCGn4WWH9kW7ui66dXRdisBs3zTJBLnrZjOoWGQL9bJ0WfEmRxlQJ8ZwXWpLJjuAxIe6YFuEv5KrTuo+hePXx3qXizmDoH7G4x+49+QYGH7ir2YtZrnTR9+0w1Fwl0jg7MntQfiu5cA/qH++sFZ8gtt13hHf2SA+mjmGx5n88Fj7tGYtjXuxNmGT31U1ZP+Jgb599cN/x2VjpWcy5NxtjZmZun4Gg8ZrNWHA7jftsq/uHGiH4I7XjP89d3H0Zk4d+zEW0LNj0G2wAi26KDLQe4ALBhl1xynLn8O0ggx8guEaSdvul+3g9ZsfkiXeCWYOgbsgNh/ejPf3dIXjjg6Dq2qZMstk4BpNZMOvB5deQjXvOZkco1xyV2ILUhxoiE6xGPEUeGJAOyOJlTmO8ydeN109jKSsXzA8qRi+K6TcWMXXS96c2rILLqVp/esDuLjmNNyCtl4COR224DIIqCMupkyFDKnUBiG9s31pSgA8lJg3pzxHZLqlnOjVqxC9aTY/A06tnYcd83tvzBq/26TK6g1IrrQBZbdcEgKgSCEw5HRdK6e3vKsf8ALt3TTTNcd0quV6u2l/pZJzDHsLcs94kcrg1DGK885LkE41QGRryqVPWLdOVKB6wlWqevmVMeUcxTha00uGK2LJLzj72WTbzlMdyXHKGBOtNxYURp9k5EsnGzcKlXQFtllw6UcrzoGgNAsx+STjT459Q3HcVyiXh9vtF9njjEPzTb50yNbpz0lqgdWTVp2rwOA6RG4XW6pFTnWo0C6tPGNxc3rg9hcQ0PS7Cm8TnyG5zUeXNtUW2jBObRqjDl7dv41bkdKuypHbx2yObdWqcqrvVBicsymxYNpZec1ymd2Ky4/b37pcZPSNzoR2WycdPaFKkXIBKvIaVrXl6qVqvlXC01mcI/kbcU1jzrTzhV1Kso3CG/FC4Yk+d5yO1zz7QBNXCVQCrLoDpbWjiVo2wzU61KrdW6h0XxLQsNz7y9ulGlPERdpY6XuYuV5xayzhq1ZL7lYzDaow+WzY+YxyCtGjPluNsOVaSSbelWlFptWnHl7cz0r0atkSzadFpvDv2S2OyMiNqt+RlMo0wRNBTpw5DsABKrYdPrAAuEJ1Gh0DrVc/6p6+ZUx5RzFOFrTS4YrYskvOPvZZNvOUx3JccoYE603FhRGn2TkSycbNwqVdAW2WXDpRyvOgBaZ3qzrRphoFqw3k2b6P3DNsPxd7NcaZgRpTT822x2dz9ZdpOVV1oOs24yEhuUYVq4NaiNQqDmi/uxOKb/ANp6Vf8AkA+XP+0+f/8ALf8AbT/t/wDs0HZOiGa3XUrgvwHUa+x4jFyyrF7be5jUQCFht6RFbdMW6ERFQKEdaUpUq15cuda/OpsgIgIgIgIgIgIgIg0pxL6B5Vrz8nno5qFasW9AMwiZq323H3Ln2yZE59nCu2Uxsa9o7vp6yLmG0g213XcnTniFyPKrCOX6+Y/EsVrvEa7T4uJYa/aZt0FguoEQ5L1wk0CObot1dEG6E4Ak3uETLmFpeNA8psfGTkOtmimoVqxe65vb40LLbffcfcvUG5uxBoEOU3QJUd1h1tqrjVaC5VsxIa1boY763eY6T6uah8I+e6eZhq7j53LNbO5YGZUDEDj262xnQNt5wYxTDeckGDrlN5SemOxmtGqbXesEK1F4Scq1C8jbZ+EmRqtaoXYbfbbNMyAMYcPtEOAYExRuNWZTpu16EehnVwxrtc5AO8enH+J/R/VXVTWTBLfmGDS82wmwWdye69h7dsCXTIeXRcJ+23uQdvchHHdcJrcD8hlwK8nAoVauBleFbT3VrTXX3ILExg8vFdJH7OzLZh3+2Y5Burl+q8QuOMBYRGPWPWMDVDJ+nV3i1QO7Q1sDWjQe66j8Q+muruH5rEx3LNMJFyK21uVoK526SzPi9nfB1gH2HN9KC2QGL1KU5FSonuptCP698OWo/EH5Pu+6K5ZrJao8/KLhHkXK6x8TrSLHjsONuhHhxu10Nvm4w0ZG8+/WtTepTaJNizd8QPD9n2ueK6YMs6nY/Yrlp7lEDM5EgsVelsXG5Qx9lQGu3ATMepG9Um6m4daEFKOU21qYb1RBoq2aNcQuF6NHp7p9xLxDtrMd5mDe8yxZ/IckaJ2pHV12ZWe0y8YGZdOhRtogLYELm2tSurZwvWHCODbCdHdI87yrDYmntwau9rkxrgZ+cZAE64TdyAahWTEeeeJx6OBM0P1CFWqUHaFreuH7PtT9ZMKvuu2p2P3+xYDeBye12LHMVesoP3dqnKLJkPuzpLhgxQnai03VsSI6VcqYjsrvVBicsxaxZzpZecKymD22y5Bb37XcY3VNvrx3mybdDeFaEPMCKnMa0rTnzpWlVqrE9I9cdMdLLNp1pvrTip4tjVvYtdo9KcHduN0CO02IAD0iLcIjTm2lNo1GOFdgjQquHQnDCFahcHmZZ/xP5LqBM1siFHyPSeXpM6zLxajk2sZ9sirNceaktNFI7UXVrQGG26hzboAV5OUu9IOEnKtLeKfAdTJGq1qu3odpexpbMhBjDkbt8OPIJ5iQ25WYfRdpyj0PmLglscrSgdQaNB0qiAiDVl20UtkTKp2U6V3uXgGQTZJzJZWxsTtV1kGe8zm28vYvG6ezqyA6UswCgDIAVjZGoWeYmw5a9W9JLrcIDVK9bJMMZO6wng8ImcAS84NPGdPoWWZYNCYc5BiLpgqyqSKwW/TjUTCY+U4XkEG9WWbvpGm2ia3LhvVEyA9jg7gPvCQV/wCeigWfcNVkzF3rTrW09Lb39G4R3ejKD9P+T8lQlHE1muWYtIz9HtRsVlfxdLZyS3h7h7GZoB+4akdqxTKbwyzBg2d6H+OkSA2AH+es38fHcp3/AJGelsLGNKoNk++zDtMv35D3fP8AQUjvUmx4hh79+yCWTEWNsbPa0bpmZmAAy00PfdeMzAAaADMzMAADM1pQjiDinPrY23YJlWr0YJ+TTLliuFP13xrLDcl2u+T6D9G9IlA6D0Jo91T7KABI7jXWdDc9EDcNos9qsGKQrJY7bHttut0cI0SHFZFpiM0A0EAAR7ogI/MKtpTLLLoogsTf8TxXK/NvpTjNqvPme4NXa3ecIbcjscxrn0pDW+ldjobi2mPIqc68q+tAsGJ4rinnL0Wxm1WbzzcHbtcfN8NuP22Y7y6sh3ZSm909o7jLmVeVOda8lAPuTuFj+9p0q/xNgf6JBNc109wDUrFWrFqNg+P5VbWJFJbUO92xmcw29QSGjgg6JDQ6CZ0oVKc+RVp9dVicK0Q0X01yp2+6c6Q4Vityfj1iOzLJYIsF9xmpCVWyNoBKoVIArUa15cxGv1UQWv3PWgXyp+nXyHaf+knnDzt559Gonbu2dTqdo6/T39Xqd/fz3bvXz5+tbAQFr/FuHrQLBs7i5ThOh2n+P3qFv7NcbXjUSJKY3hUD2Ott0IeYEQ15V9dCrT5qoJBmunuAalYq1YtRsHx/KraxIpLah3u2MzmG3qCQ0cEHRIaHQTOlCpTnyKtPrqmFae4Bprirti05wfH8Vtr8ist2HZLYzBYceqIjVwgaERqdRAKVKtOfIRp9VEEgUfzXT3ANSsVasWo2D4/lVtYkUltQ73bGZzDb1BIaOCDokNDoJnShUpz5FWn11QR+3cPWgVnwS74tadDtP4VlyDoed7dHxqI3FuHROps9doW6C7sOtSHdSu2tedOVVivuTuFj+9p0q/xNgf6JBP8AFsTxXBsEi4thOM2rH7LC39mt1rhtxIrG86mexpulBHmZEVeVPXUq1+eqyyAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiAiDW+TaMYtf89l5lj0u64Tl8yo9oyHGXgjyZmwRAKy2jA483a1SoB2pp7o0MulsLvLFhnuqenVOjqthp5JaW6/2z4PbpEnb/AMYtO52W13yBoOzHN3bTdd7MHzBL7JdsN1N06jZPiWRWy+2uXU+yXW1S2pcZ6oGQHsdDuHQTEh/RXsOGhT/dv/YoIRf8trBzmVhWnmODl2VxajSUzIdegWe3DsA6dquAMug09sJqoxwB2QXaGT6QMmbwZHE9H7dCzpnNc7ufpnmESpUgXWZb2WGrJQwqLrNtaAfvVot5jUt7sgw2A8+9RoNgbNRARARARARARARARARARARARARARARARARARARARARARARARARARARARARARARARBgbdiGMWjPLvlVrxm1wrzf6sedrjHhgEqf0Q2NdZ0aUJ3YHdDdXu0+ZR7NMbzzLsqascHJ41hxB2MHnR2FR2l5nbjLeyxIEgGEOwQEnh6rpC8fS7IbQPGEgxXGLLhuERcex2F2SBF3mAG6brhuGROOvOvHUjddMyMjdMiMzMjMiIq1WfQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQEQa/yniF0CwbO5WLZrrhp/j96hbO0266ZNEiSmN4UMd7TjlCHmBCVOdPXQqV+aqfdC6BfJZ6dfLhp/6N+cPNPnj0midh7Z0+p2fr9TZ1en39nPdt9fLl60GwFH8k1CwDDcqstiy/OMfsdyySR2SzQ7lc2Yz9ye3APTjgZUJ09zjdNoUrXmY0+uiCQIgKKZ1qxpZpf2H5TNS8VxLzn1OxefbzHgdq6e3qdPqmO/bvDny58tw8/nogtMK1v0X1Kyp2xac6vYVlVyYj1luw7Jf4s59tmhCNXCBoyKgUIwpUq05cyGn10U2QEQafm8S2PQPKWwOFx/T/NSyG42et/Zu7MOO9aRg0E/buOA/V1oOs2THtGh5u1ClO6YFXcCAiAiAo/muoWAaa4q1fdRs4x/Fba/IpEamXu5swWHHqiRUbE3SEanUQOtBpXnyGtfqqgkCj+SahYBhuVWWxZfnGP2O5ZJI7JZodyubMZ+5PbgHpxwMqE6e5xum0KVrzMafXRBIEQEQEQYnKcsxXBsElZTmuS2rH7LC2dpuN0mtxIrG86AO91ytBHmZCNOdfXUqU+eqtcK1CwDUrFXb7pznGP5VbWJFYjsyyXNmcw29QRKrZG0RDQ6CYVqNa8+RDX66IJAiAiAiCP5JqFgGG5VZbFl+cY/Y7lkkjslmh3K5sxn7k9uAenHAyoTp7nG6bQpWvMxp9dFIEBEFpdrtarBisy+325xLdbbdHclzJkt4WWIzIDUjccMq0EQEaVrUq1pSlKVrVRTCtb9F9SsqdsWnOr2FZVcmI9ZbsOyX+LOfbZoQjVwgaMioFCMKVKtOXMhp9dEE2VpdrtarBisy+325xLdbbdHclzJkt4WWIzIDUjccMq0EQEaVrUq1pSlKVrVBFMK1v0X1Kyp2xac6vYVlVyYj1luw7Jf4s59tmhCNXCBoyKgUIwpUq05cyGn10VrlPELoFg2dysWzXXDT/H71C2dpt10yaJElMbwoY72nHKEPMCEqc6euhUr81UGV+VjSz5CflS+UvFfQz/fH55j+a/pej/ZO/pfS+z8Xj7vz+pR+08TfDbf8qhWKxcQemtxuVxkNxIcOJlkJ5+S8ZUEG2wF2pEZFWlKDSla1rWlKINlog1paeJvhtv+VQrFYuIPTW43K4yG4kOHEyyE8/JeMqCDbYC7UiMirSlBpSta1rSlFa/dY8LH98tpV/jlA/0qDP5JrfovhuK2W+5fq9hVjtuSR+12aZcr/FjMXJnaBdSOZnQXQ2uN13BWtORjX66K7zrVjSzS/sPymal4riXnPqdi8+3mPA7V09vU6fVMd+3eHPlz5bh5/PRBK0QEQEQEQEQEQEQcVcevyWaX/IP0fRXEvOev9lyy67ezwO1dPq9tuL/h37d7PVfLny3BuL10V35TS04BgXkOdT4dmtmP47Iy28W+WbUZlmId3uTlxiuvOVoNKVekE0wZkXrOotEVfUNa0BXWrK9IfKK8Pumd64pImpNo1Ys9wO9PXRi0RWN1IzR26ZB7Gy0bYSpFHGmqOOPi5QqgFTMaHRxc8QldGOJ/T+mM8VUSA/c9SLFZstwuXJshsWmyvN1OS+4JRu2MAQi2dXXHuQ0frWlRpUNobf1+1tsNm8mBlmsWm+suK2iINvJu0Zh2Y75a2JBv0jAW2LRyp+2Lp7hFyjZ94m3KATZaL0B1zzzM/KK2DT24cRcvIo7Ee5T5cKyTMfy+yXqM3GYo2RXC3Qocq2G2/ILn2mMLbxMiAOVqVBIO31xr5SvIMetmK6BW+755ExaQ5rRY7hSeUqO0/CjMC/R+aFHxJvYxV5oiMwJsamG+laFyqEUyrL8Sd8pboPl+nOu0viQvtsvE21SLTGrAnHjVtuAsxpV5q/Z4zTbAM1JkCGWLgOC+WzpEFXBmsrW67XDjJ1HxjW/iY+58bxu4Mw8Wx3fZo/ni11E6t3jttyjPDJ7QdDHpMbaRuz7HKE5WpVDFfdA53O8jb8p2qWv/AMjkmPkHm7HNRPRVqV6ZW4T2xp/mV5snW+1NVNzot7THpdca0YrUFqDhf4zdSNYuNrCMf1X4nomBx59nspxsXjNWW8hmE0mXhfpWaxGpS1G66wyRQnq1f3SCbbq3UmqAH0et2J4rZ87u+U2nGbVCvWQdDzvcY8NtuVcOiFQZ67o0oTuwK1Ed1a7aV5U5UUA4p9QZmlvk+82zm2Z/asInwLfRuFf7pan7lFgyHnAZZI2GRI683HAGhUBygVKhk26IkBBzroDrnnmZ+UVsGntw4i5eRR2I9yny4VkmY/l9kvUZuMxRsiuFuhQ5VsNt+QXPtMYW3iZEAcrUqCXb6DRXGTq3n2jvCOzfdPrfLbful4YtV0yNmzPXYMPtpg4cq8uRGhrV4I7TRV2lUQ3EFS3UpsOKYjrHgtt4n8St9h48Imp0fIJD1mcxSkG03ubKfcb6jL7J2eOy5EBqjLpOuvg4xsKu6rVaCdAj7PEc7pjx18RWA8Quvvoni1ot9qumBSbszb4k4I8mJJclnbg7PSs7ovDRsKVbkV3MiBUcOp79F6i60ZNr1/A2Miz/ADvL4mRZY5IYiXyQyDDRtPN5Cz0m3GmREGz7PWOW3bStRMC9e7nUNlXHWzUCw8R2i9h0X4wvl8n3jII0DPbDbrFarjFatZ1aZlXWrlsYE7e0y48NRF506Vq6HMjFp2hyDi54hK6McT+n9MZ4qokB+56kWKzZbhcuTZDYtNlebqcl9wSjdsYAhFs6uuPcho/WtKjSobQn/FBxD3XFuAy36jaEXWJfLbkN4btUvNbJDLIIWLW323bbyTMehjICMLDlKjUqBQ9u7dtq2eKxHWPBbbxP4lb7Dx4RNTo+QSHrM5ilINpvc2U+431GX2Ts8dlyIDVGXSddfBxjYVd1Wq0E6B1AtacRVi1hv3CPfR0EzmXjGdwI5z7MbMGFKC4PNgVRhODLGrYg7XkPU5hUC2FUqiJAYcq8G+reuXFBqZYMjx/X/NX8Jw+z257OpNzx20tMX2/PsA69a7fULc2TUeOW6j7pOE7WjjYt0GhDIrNZWt12uHGTqPjGt/Ex9z43jdwZh4tju+zR/PFrqJ1bvHbblGeGT2g6GPSY20jdn2OUJytSqGwOGvXS+3ryfddUNfbzarLarfcHoduzS6UCyRcmtdHBbh3g47tR7F2rePJoq0pWtaEFKA62NNVeT71Y0sm53rli0LUvFX71k2uGTXayW5q8xzlXOGQMmMiO1Q9zrVQbcKhhSo1oBV58qVQZVniOd0x46+IrAeIXX30Txa0W+1XTApN2Zt8ScEeTEkuSztwdnpWd0Xho2FKtyK7mRAqOHU9+i7dxm6kXryV/Dnn924nolhyzL9SDxXN5DLVlAxtpTHuvIcaejGEc48fsRb6CICMoCcE+o3VBibTxrZ5bsqhXCZxjxLrHi8Q7eDuwJdMfFiVholStbm5VqIDlAKlNtZQGLXKteVBryrTYF64k+Iq5caGpGNZxrDhWj9txm8O27GrPe77GxJ+620JUkGboLlzs1wGYDwhQKHHcbAasVrVunUEqh1rwsZfm2e+T7wnL9RMgtV+v1zt9XZF3tdtlQItyCjhizJBmSyy6PUao2da9IAKpVJunTIFtVBxrxc8QldGOJ/T+mM8VUSA/c9SLFZstwuXJshsWmyvN1OS+4JRu2MAQi2dXXHuQ0frWlRpUNu39ftbbDZvJgZZrFpvrLitoiDbybtGYdmO+WtiQb9IwFti0cqfti6e4Rco2feJtygE2QaL0B1zzzM/KK2DT24cRcvIo7Ee5T5cKyTMfy+yXqM3GYo2RXC3Qocq2G2/ILn2mMLbxMiAOVqVBLt9ByV5TCx3W78BmPz3sbyDIcJsGcWu96h2qyPkD8vHGOqUsSAXGycChdEq0oVNlQF2tQo3Uwiuruf6R63amcMtk4Tchx+/33HM4tt8FnFSBt/H8UaYqFwB/ZQawY5NORWijO9OrtdrVGzIdlA6f04160n1b1TzTCtPcr863rTy4UteSRuwyGOwyKuPN7NzrYi53471ObdSp3OfPlWnPRXlMLHdbvwGY/PexvIMhwmwZxa73qHarI+QPy8cY6pSxIBcbJwKF0SrShU2VAXa1CjdTAIrq7n+ket2pnDLZOE3Icfv99xzOLbfBZxUgbfx/FGmKhcAf2UGsGOTTkVoozvTq7Xa1RsyHZSP4FmVi0n8o5r23jmo+n+g0S/ZAxcXIOqtkNydkEjdJCRdILpSoVa2914XekHXlUoVHTEYtHaNkEg4LNAtEtW/Jc2qzagaMy348TOHcvkFdIj0K1ZFc+mPSu0GN0owFbHo7rYtRyjg1QKVbJs6hVw5rwef+Mrjlq43NSPvu/XrzhBw6x170HDLcEl2M43G5/SS3+huellQTMai0AttjUSDqpcq+UaxaZlPBtiY3KDdZmA2rUCz3TUhm3uv0/wBjTROVmG62xWjzrQVq04VG6EQ9OjlKU6e4Qusoy/gy1V1k0j0/xSy4/qffcXvDVxxWPgcuPIDEWYlG+cyQ6w8DcaE3WkcatEVaOlRkQZdIKUHQHC1qNpbTKtd7hM434mmse6a0ZFcIECJe8cBi4xnCZq3NbrOivOGB07tDA+nWjdOVOe6tQ0rqk3jOS+Qr0b0FvE2Jgdia1oasdiyO5xn4IX6wk1IOuTBEmGLgRzpcQccp1KtCRcqE2JCAYnO9U9Q9V+NDRPWTiCu0TDZGkupFg01udvn36K6xcLnDlFIvt3oIVBuMDdaQBdqDZNcnmeTtaN0og+z6ICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICICIP/9k=\"></p><p class=\"ql-align-center\"><br></p><p><br></p></div></div>', NULL, NULL, NULL, '2020-04-21 17:20:22', NULL, '2020-04-21 17:20:22', 0);

-- ----------------------------
-- Table structure for t_paper_type
-- ----------------------------
DROP TABLE IF EXISTS `t_paper_type`;
CREATE TABLE `t_paper_type`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名',
  `p_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父ID',
  `sort_no` int(32) NULL DEFAULT NULL COMMENT '排序编号',
  `paper_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标记【 0：未删除，1：已删除】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_paper_type
-- ----------------------------
INSERT INTO `t_paper_type` VALUES ('1', '关于我们', '0', 1, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('1252527586642653185', '如何计算体积', '1', 0, '1252527586428743682', NULL, NULL, '2020-04-21 17:20:22', NULL, '2020-04-21 17:20:22', 0);
INSERT INTO `t_paper_type` VALUES ('2', '国际运输', '0', 2, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('3', '门到门运输专线', '0', 3, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('4', '运输案例', '0', 4, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('5', '货运知识', '0', 5, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('6', '服务范围', '0', 6, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('61', '东南亚', '6', 1, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('611', '新加坡', '61', 1, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('62', '中东', '6', 2, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('63', '澳洲', '6', 3, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('7', '递接招聘', '0', 7, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('8', '联系我们', '0', 8, NULL, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);

SET FOREIGN_KEY_CHECKS = 1;
