-- --------------------------------------------------------
-- 主机:                           192.168.0.110
-- 服务器版本:                        8.0.34 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 loveta 的数据库结构
CREATE DATABASE IF NOT EXISTS `loveta` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `loveta`;

-- 导出  表 loveta.animal 结构
CREATE TABLE IF NOT EXISTS `animal` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `status` tinyint NOT NULL COMMENT '状态',
  `category_id` int unsigned NOT NULL COMMENT '分类ID',
  `breed` varchar(50) DEFAULT NULL COMMENT '品种',
  `area_id` int unsigned NOT NULL COMMENT '区域ID',
  `sex` tinyint unsigned NOT NULL COMMENT '性别',
  `date_of_birth` date DEFAULT NULL COMMENT '出生日期',
  `color` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '花色',
  `feature` text COMMENT '特征',
  `health` varchar(50) DEFAULT NULL COMMENT '健康状况',
  `weight` int DEFAULT NULL COMMENT '体重',
  `rfid` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电子标签',
  `avatar` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
  `banner` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '横幅',
  `attachment` json DEFAULT NULL COMMENT '附件',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `rfid` (`rfid`),
  KEY `FK_animal_area` (`area_id`),
  KEY `FK_animal_animal_category` (`category_id`),
  CONSTRAINT `FK_animal_animal_category` FOREIGN KEY (`category_id`) REFERENCES `animal_category` (`id`),
  CONSTRAINT `FK_animal_area` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='动物';

-- 数据导出被取消选择。

-- 导出  表 loveta.animal_category 结构
CREATE TABLE IF NOT EXISTS `animal_category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='动物分类';

-- 数据导出被取消选择。

-- 导出  表 loveta.area 结构
CREATE TABLE IF NOT EXISTS `area` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='区域';

-- 数据导出被取消选择。

-- 导出  表 loveta.donation 结构
CREATE TABLE IF NOT EXISTS `donation` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `donor_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '捐赠方名称',
  `donor_user_id` int unsigned DEFAULT NULL COMMENT '捐赠用户ID',
  `contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系方式',
  `date` datetime DEFAULT NULL COMMENT '捐赠时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `financial_transaction_id` int unsigned DEFAULT NULL COMMENT '收支明细ID',
  `material_movement_ID` int unsigned DEFAULT NULL COMMENT '物资出入库记录ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_donation_user` (`donor_user_id`),
  CONSTRAINT `FK_donation_user` FOREIGN KEY (`donor_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='捐赠';

-- 数据导出被取消选择。

-- 导出  表 loveta.financial_account 结构
CREATE TABLE IF NOT EXISTS `financial_account` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户名',
  `status` tinyint unsigned NOT NULL COMMENT '状态',
  `user_id` int unsigned NOT NULL COMMENT '用户ID',
  `balance` int NOT NULL COMMENT '当前余额',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_financial_account_user` (`user_id`),
  CONSTRAINT `FK_financial_account_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资金账户';

-- 数据导出被取消选择。

-- 导出  表 loveta.financial_transaction 结构
CREATE TABLE IF NOT EXISTS `financial_transaction` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `date` datetime NOT NULL COMMENT '交易日期',
  `operator_user_id` int unsigned NOT NULL COMMENT '操作用户ID',
  `account_id` int unsigned NOT NULL COMMENT '账户ID',
  `category_id` int unsigned NOT NULL COMMENT '费用类别ID',
  `amount` int NOT NULL COMMENT '交易金额',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `attachment` json DEFAULT NULL COMMENT '附件',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_financial_transaction_user` (`operator_user_id`),
  KEY `FK_financial_transaction_financial_account` (`account_id`),
  KEY `FK_financial_transaction_financial_transaction_category` (`category_id`),
  CONSTRAINT `FK_financial_transaction_financial_account` FOREIGN KEY (`account_id`) REFERENCES `financial_account` (`id`),
  CONSTRAINT `FK_financial_transaction_financial_transaction_category` FOREIGN KEY (`category_id`) REFERENCES `financial_transaction_category` (`id`),
  CONSTRAINT `FK_financial_transaction_user` FOREIGN KEY (`operator_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收支明细';

-- 数据导出被取消选择。

-- 导出  表 loveta.financial_transaction_category 结构
CREATE TABLE IF NOT EXISTS `financial_transaction_category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='收支分类';

-- 数据导出被取消选择。

-- 导出  表 loveta.forum 结构
CREATE TABLE IF NOT EXISTS `forum` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签',
  `status` tinyint unsigned NOT NULL COMMENT '状态',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `notice` varchar(255) DEFAULT NULL COMMENT '公告',
  `header` text COMMENT '版头',
  `sort_order` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='板块';

-- 数据导出被取消选择。

-- 导出  表 loveta.location 结构
CREATE TABLE IF NOT EXISTS `location` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `area_id` int unsigned DEFAULT NULL COMMENT '区域ID',
  `lng` decimal(9,6) NOT NULL COMMENT '经度',
  `lat` decimal(9,6) NOT NULL COMMENT '纬度',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_location_area` (`area_id`),
  CONSTRAINT `FK_location_area` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='位置';

-- 数据导出被取消选择。

-- 导出  表 loveta.material 结构
CREATE TABLE IF NOT EXISTS `material` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_id` int unsigned DEFAULT NULL COMMENT '分类ID',
  `status` tinyint unsigned NOT NULL COMMENT '状态',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `specification` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '规格',
  `unit` varchar(50) NOT NULL COMMENT '单位',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '内容',
  `attachment` json DEFAULT NULL COMMENT '附件',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_material_material_category` (`category_id`),
  CONSTRAINT `FK_material_material_category` FOREIGN KEY (`category_id`) REFERENCES `material_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物资';

-- 数据导出被取消选择。

-- 导出  表 loveta.material_category 结构
CREATE TABLE IF NOT EXISTS `material_category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='物资分类';

-- 数据导出被取消选择。

-- 导出  表 loveta.material_movement 结构
CREATE TABLE IF NOT EXISTS `material_movement` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `stock_id` int unsigned NOT NULL COMMENT '库存ID',
  `movement_type` tinyint NOT NULL COMMENT '变动类型',
  `operator_user_id` int unsigned NOT NULL COMMENT '操作用户ID',
  `reference_user_id` int unsigned DEFAULT NULL COMMENT '接收用户ID',
  `quantity` int NOT NULL COMMENT '变动数量',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `attachment` json DEFAULT NULL COMMENT '附件',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_material_movement_user` (`operator_user_id`),
  KEY `FK_material_movement_user_2` (`reference_user_id`),
  KEY `FK_material_movement_material_stock` (`stock_id`),
  CONSTRAINT `FK_material_movement_material_stock` FOREIGN KEY (`stock_id`) REFERENCES `material_stock` (`id`),
  CONSTRAINT `FK_material_movement_user` FOREIGN KEY (`operator_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_material_movement_user_2` FOREIGN KEY (`reference_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='物资出入库记录';

-- 数据导出被取消选择。

-- 导出  表 loveta.material_purchase 结构
CREATE TABLE IF NOT EXISTS `material_purchase` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `movement_id` int unsigned NOT NULL COMMENT '出入库记录ID',
  `financial_transaction_id` int unsigned DEFAULT NULL COMMENT '收支明细ID',
  `procurement_user_id` int unsigned NOT NULL COMMENT '采购用户ID',
  `status` tinyint unsigned NOT NULL COMMENT '状态',
  `total_amount` int unsigned NOT NULL COMMENT '总价',
  `supplier` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '供应商',
  `order_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '订单号',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `attachment` json DEFAULT NULL COMMENT '附件',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_material_purchase_material_movement` (`movement_id`),
  KEY `FK_material_purchase_financial_transaction` (`financial_transaction_id`),
  KEY `FK_material_purchase_user` (`procurement_user_id`),
  CONSTRAINT `FK_material_purchase_financial_transaction` FOREIGN KEY (`financial_transaction_id`) REFERENCES `financial_transaction` (`id`),
  CONSTRAINT `FK_material_purchase_material_movement` FOREIGN KEY (`movement_id`) REFERENCES `material_movement` (`id`),
  CONSTRAINT `FK_material_purchase_user` FOREIGN KEY (`procurement_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='物资采购记录';

-- 数据导出被取消选择。

-- 导出  表 loveta.material_stock 结构
CREATE TABLE IF NOT EXISTS `material_stock` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `material_id` int unsigned NOT NULL COMMENT '物资ID',
  `warehouse_id` int unsigned NOT NULL COMMENT '仓库ID',
  `quantity` int unsigned NOT NULL DEFAULT '0' COMMENT '当前数量',
  `min` int unsigned DEFAULT NULL COMMENT '最低预警',
  `max` int unsigned DEFAULT NULL COMMENT '最高上限',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_material_stock_material` (`material_id`),
  KEY `FK_material_stock_material_warehouse` (`warehouse_id`),
  CONSTRAINT `FK_material_stock_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  CONSTRAINT `FK_material_stock_material_warehouse` FOREIGN KEY (`warehouse_id`) REFERENCES `material_warehouse` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='物资库存';

-- 数据导出被取消选择。

-- 导出  表 loveta.material_warehouse 结构
CREATE TABLE IF NOT EXISTS `material_warehouse` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `location_id` int unsigned DEFAULT NULL COMMENT '位置ID',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `keeper_user_id` int unsigned DEFAULT NULL COMMENT '保管用户ID',
  `contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系方式',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_material_warehouse_user` (`keeper_user_id`),
  KEY `FK_material_warehouse_location` (`location_id`),
  CONSTRAINT `FK_material_warehouse_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_material_warehouse_user` FOREIGN KEY (`keeper_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='物资仓库';

-- 数据导出被取消选择。

-- 导出  表 loveta.menu 结构
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int unsigned NOT NULL COMMENT '父菜单ID',
  `type` tinyint NOT NULL COMMENT '类型',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路径',
  `sort_order` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单';

-- 数据导出被取消选择。

-- 导出  表 loveta.permission 结构
CREATE TABLE IF NOT EXISTS `permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限编码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限';

-- 数据导出被取消选择。

-- 导出  表 loveta.post 结构
CREATE TABLE IF NOT EXISTS `post` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `forum_id` int unsigned NOT NULL COMMENT '板块ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `status` tinyint NOT NULL COMMENT '状态',
  `user_id` int unsigned NOT NULL COMMENT '用户ID',
  `contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系方式',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `attachment` json DEFAULT NULL COMMENT '附件',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_post_forum` (`forum_id`),
  KEY `FK_post_user` (`user_id`),
  CONSTRAINT `FK_post_forum` FOREIGN KEY (`forum_id`) REFERENCES `forum` (`id`),
  CONSTRAINT `FK_post_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='帖子';

-- 数据导出被取消选择。

-- 导出  表 loveta.report 结构
CREATE TABLE IF NOT EXISTS `report` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int unsigned NOT NULL COMMENT '用户ID',
  `status` tinyint unsigned NOT NULL COMMENT '状态',
  `object_type` tinyint unsigned NOT NULL COMMENT '对象类型',
  `object_id` int unsigned NOT NULL COMMENT '对象ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `attachment_group_id` int unsigned DEFAULT NULL COMMENT '附件组ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_reports_attachment_group` (`attachment_group_id`),
  CONSTRAINT `FK_reports_attachment_group` FOREIGN KEY (`attachment_group_id`) REFERENCES `attachment_group` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='举报';

-- 数据导出被取消选择。

-- 导出  表 loveta.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色';

-- 数据导出被取消选择。

-- 导出  表 loveta.role_menu 结构
CREATE TABLE IF NOT EXISTS `role_menu` (
  `role_id` int unsigned NOT NULL COMMENT '角色ID',
  `menu_id` int unsigned NOT NULL COMMENT '菜单ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_role_menu_menu` (`menu_id`),
  CONSTRAINT `FK_role_menu_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_role_menu_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单关系表';

-- 数据导出被取消选择。

-- 导出  表 loveta.role_permission 结构
CREATE TABLE IF NOT EXISTS `role_permission` (
  `role_id` int unsigned NOT NULL COMMENT '角色ID',
  `permission_id` int unsigned NOT NULL COMMENT '权限ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK_role_permission_permission` (`permission_id`),
  CONSTRAINT `FK_role_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_role_permission_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限关系表';

-- 数据导出被取消选择。

-- 导出  表 loveta.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `wx_openid` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信唯一标识',
  `wx_unionid` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信统一标识',
  `status` tinyint unsigned NOT NULL COMMENT '状态',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
  `realname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
  `sex` tinyint unsigned DEFAULT NULL COMMENT '性别',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';

-- 数据导出被取消选择。

-- 导出  表 loveta.user_role 结构
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int unsigned NOT NULL COMMENT '用户ID',
  `role_id` int unsigned NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_user_role_role` (`role_id`),
  CONSTRAINT `FK_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关系表';

-- 数据导出被取消选择。

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
