# Loveta Spring

Loveta Spring 是 Loveta 项目的后端服务，面向爱心救助、动物档案、捐赠管理、财务流水、物资库存和社区内容等业务场景，提供一套基于
RESTful 风格的管理端 API。

项目使用 Spring Boot 3 构建，围绕 MyBatis-Plus、Sa-Token、MySQL、Redis 与阿里云 OSS 组织后端能力，适合作为救助组织、公益团队或动物救助平台的业务中台后端。

- API 文档：[Apifox - loveta-dev](https://loveta-dev.apifox.cn)
- 权限编码说明：[doc/Permission.md](doc/Permission.md#权限编码)

## 项目亮点

- 业务覆盖完整：围绕动物救助实际流程设计，覆盖动物档案、区域、分类、捐赠、财务账户、财务流水、物资、库存、采购、出入库、论坛内容、用户、角色、菜单、权限等核心模块。
- 权限模型清晰：使用 Sa-Token 完成登录态校验和接口权限控制，权限码采用 `模块:资源:动作` 的层级命名方式，并支持通过反射扫描
  `@SaCheckPermission` 注解同步权限。
- 前后端类型联动：集成 `typescript-generator`，可从后端 DTO、Query、VO、Result、PageVO 等模型生成 TypeScript 类型声明，减少前后端字段漂移。
- 数据访问效率高：使用 MyBatis-Plus 提供通用 CRUD、分页插件和条件构造器，并通过 XML Mapper 承载复杂联表查询。
- 安全边界明确：除 `/auth/*` 外，接口默认需要登录；MyBatis-Plus 配置分页最大 100 条并启用全表更新/删除拦截器。
- 文件上传友好：封装阿里云 OSS 临时访问 URL 与表单直传参数生成能力，避免后端转发大文件。
- 统一响应模型：接口统一返回 `Result<T>`，分页接口统一返回 `PageVO<T>`，便于前端拦截、错误处理和类型推导。
- 多环境配置：内置 `dev`、`test`、`prod` 配置文件，支持本地开发、测试部署和生产环境按 profile 切换。
- Docker 可交付：提供多阶段 Dockerfile，使用 Maven 构建 Jar，再基于 Amazon Corretto 17 运行。

## 技术栈

| 类别        | 技术                                    |
|-----------|---------------------------------------|
| 基础框架      | Spring Boot 3.1.6                     |
| 运行环境      | Java 17                               |
| Web       | Spring Web                            |
| 数据访问      | MyBatis-Plus 3.5.5、MyBatis XML Mapper |
| 数据库       | MySQL 8.x                             |
| 缓存/会话     | Redis、Sa-Token Redis                  |
| 鉴权授权      | Sa-Token 1.37.0                       |
| 对象存储      | 阿里云 OSS SDK 3.17.2                    |
| 参数校验      | Spring Validation、Jakarta Validation  |
| 工具库       | Hutool、Lombok                         |
| 类型生成      | typescript-generator                  |
| 代码分析/辅助生成 | Reflections、QDox、JavaParser           |
| 构建工具      | Maven Wrapper                         |
| 容器化       | Docker、Amazon Corretto 17             |

## 业务模块

### 用户与权限

- 用户管理：用户基本信息、手机号登录查询、状态、性别、头像等资料维护。
- 角色管理：角色编码、角色名称、描述维护。
- 用户角色关系：支持批量维护用户与角色的多对多关系。
- 权限管理：维护权限编码、名称、描述，并支持从代码注解同步权限。
- 角色权限关系：支持批量维护角色与权限的多对多关系。
- 菜单管理：菜单树、菜单类型、排序、角色菜单授权和当前用户菜单查询。
- 当前运行数据：`/aggregate/runtime-data` 一次性返回当前用户、菜单与权限编码，方便前端启动时初始化上下文。

### 动物救助业务

- 动物档案：维护动物名称、性别、状态、分类、所在区域、图片与描述等信息。
- 动物分类：维护动物类型，例如猫、狗等救助对象分类。
- 区域管理：维护区域/地点维度，用于归类动物或业务数据。
- 地理位置：维护经纬度、地址等位置信息，支持救助地点、仓库或其他场景复用。

### 捐赠与财务

- 捐赠管理：记录捐赠人、捐赠金额/物品、联系方式、时间和备注等信息。
- 财务账户：维护现金、银行卡、平台账户等资金账户。
- 财务交易分类：维护收入、支出等交易分类。
- 财务流水：记录账户、分类、金额、发生时间、描述等财务明细。

### 物资与库存

- 物资分类：维护物资类别，例如粮食、药品、日用品等。
- 物资管理：维护物资基础资料、图片、规格、单位等。
- 仓库管理：维护物资存储仓库。
- 库存管理：维护物资在不同仓库中的库存数量。
- 采购记录：记录物资采购来源、数量、金额和采购时间。
- 出入库记录：记录物资入库、出库等库存变动流水。

### 社区内容

- 板块管理：维护论坛/社区板块，支持排序批量更新。
- 帖子管理：支持帖子发布、列表、分页、编辑和删除；普通用户只能编辑自己的帖子，拥有 `post:update` 等权限的用户可执行管理操作。

### 对象存储

- 获取文件访问 URL：生成带时效的 OSS 文件访问地址。
- 表单直传：生成前端直传 OSS 所需的临时表单参数，适用于图片、附件等上传场景。
- 图片处理：内置 OSS 图片处理样式枚举，便于统一缩略图、裁剪或压缩策略。

## 技术路径

项目整体采用经典的分层后端架构：

```text
HTTP Request
  -> Controller
  -> DTO 参数校验
  -> Service 业务编排
  -> Mapper / XML SQL
  -> MySQL / Redis / OSS
  -> VO 视图对象
  -> Result<T> 统一响应
```

### 请求处理路径

1. Controller 暴露 RESTful API，负责接收请求、触发权限校验、转换 DTO/VO。
2. DTO 使用 Jakarta Validation 注解做入参校验。
3. Service 层承载业务逻辑，简单 CRUD 复用 MyBatis-Plus，复杂业务通过自定义方法封装。
4. Mapper 接口与 XML Mapper 执行数据库访问，联表查询直接返回 BasicVO 或 VO。
5. Controller 返回 `Result<T>`、`Result<PageVO<T>>` 等统一响应结构。

### 鉴权授权路径

1. `/auth/otp-login` 使用手机号和验证码登录，当前开发实现中验证码为 `888888`。
2. 登录成功后通过 Sa-Token 创建登录态并返回 Token 信息。
3. `SaTokenConfig` 对 `/**` 注册登录拦截器，并排除 `/auth/*`。
4. 需要细粒度授权的接口使用 `@SaCheckPermission("permission:code")`。
5. `StpInterfaceImpl` 根据当前用户查询角色编码和权限编码。
6. `/permissions/sync` 可扫描 Controller 中的 `@SaCheckPermission` 注解并同步权限表。

### 数据访问路径

- PO：数据库持久化对象，位于 `model/po`。
- DTO：新增/修改/登录等请求对象，位于 `model/dto`。
- Query：列表和分页查询条件对象，位于 `model/query`。
- VO：接口返回视图对象，位于 `model/vo`。
- Mapper：MyBatis-Plus Mapper 接口，位于 `mapper`。
- XML：复杂 SQL 和联表查询，位于 `src/main/resources/mapper`。

### 前后端类型路径

`pom.xml` 中配置了 `typescript-generator-maven-plugin`：

- `generate-query`：生成 DTO、Query、`PageDTO` 相关 TypeScript 类型。
- `generate-vo`：生成 VO、`Result`、`PageVO` 相关 TypeScript 类型。
- 时间类型映射为 `number`，便于前端统一处理时间戳。

默认输出路径为作者本机前端项目目录：

```text
C:/Users/lingk/WebstormProjects/link-admin/src/types/api/query.d.ts
C:/Users/lingk/WebstormProjects/link-admin/src/types/api/vo.d.ts
```

如果在其他机器构建，建议先按本地前端项目位置调整 `pom.xml` 中的 `outputFile`，或在临时构建时跳过该插件执行。

## 项目结构

```text
loveta-spring
├── doc/                                  # 项目文档
│   └── Permission.md                     # 权限编码说明
├── src/main/java/com/github/lingkai5wu/loveta
│   ├── config/                           # Spring、Sa-Token、MyBatis-Plus、OSS、Jackson 配置
│   ├── controller/                       # REST API 控制器
│   ├── enums/                            # 业务枚举
│   ├── exception/                        # 全局异常处理
│   ├── mapper/                           # MyBatis Mapper 接口
│   ├── model/
│   │   ├── dto/                          # 请求 DTO
│   │   ├── po/                           # 数据库实体
│   │   ├── query/                        # 查询对象
│   │   └── vo/                           # 响应 VO
│   ├── satoken/                          # Sa-Token 权限与角色查询实现
│   ├── service/                          # Service 接口
│   └── service/impl/                     # Service 实现
├── src/main/resources
│   ├── mapper/                           # MyBatis XML
│   ├── application.yml                   # 默认配置，默认激活 dev
│   ├── application-dev.yml               # 开发环境配置
│   ├── application-test.yml              # 测试环境配置
│   └── application-prod.yml              # 生产环境配置
├── src/test/java                         # 测试与辅助生成工具
├── init.sql                              # MySQL 表结构初始化脚本
├── Dockerfile                            # 容器构建文件
├── pom.xml                               # Maven 配置
├── mvnw / mvnw.cmd                       # Maven Wrapper
└── README.md
```

## 环境要求

- JDK 17+
- Maven 3.8+，也可以直接使用仓库内 Maven Wrapper
- MySQL 8.x
- Redis 6+
- Docker，可选
- 阿里云 OSS Bucket 与 AccessKey，可选但文件上传相关接口需要

## 快速开始

### 1. 准备数据库

创建数据库：

```sql
CREATE DATABASE loveta DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
```

导入表结构：

```bash
mysql -u root -p loveta < init.sql
```

本地开发配置默认连接：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost/loveta
    username: root
    password: root
```

如果本地账号、密码或库名不同，请修改 `src/main/resources/application-dev.yml`。

### 2. 启动 Redis

项目引入了 Sa-Token Redis 与 Spring Data Redis。开发环境未显式配置 Redis 时，Spring Boot 会使用默认地址：

```text
localhost:6379
```

如果 Redis 不在本机，请在对应 profile 中补充：

```yaml
spring:
  data:
    redis:
      host: your-redis-host
      port: 6379
```

### 3. 配置 OSS 环境变量

`application.yml` 通过环境变量读取阿里云 OSS 密钥：

```bash
LOVETA_ALIYUN_OSS_ACCESS_KEY_ID=your-access-key-id
LOVETA_ALIYUN_OSS_ACCESS_KEY_SECRET=your-access-key-secret
```

开发环境默认 bucket：

```yaml
aliyun:
  oss:
    bucket: loveta-dev
```

如果暂时不调试 OSS 接口，可以先不访问 `/oss/*` 相关接口。

### 4. 启动项目

Windows：

```powershell
.\mvnw.cmd spring-boot:run
```

Linux/macOS：

```bash
./mvnw spring-boot:run
```

默认激活 `dev` profile，服务端口使用 Spring Boot 默认端口：

```text
http://localhost:8080
```

### 5. 登录

登录接口：

```http
POST /auth/otp-login
Content-Type: application/json

{
  "phone": "用户手机号",
  "otp": "888888"
}
```

说明：

- 当前验证码校验为开发实现，固定验证码为 `888888`。
- 手机号必须已存在于 `user` 表。
- 用户状态必须为 `CONFIRMED`。
- 登录成功后返回 Sa-Token Token 信息，后续接口需要携带 Token。

## 常用命令

### 运行测试

```bash
./mvnw test
```

Windows：

```powershell
.\mvnw.cmd test
```

### 打包

```bash
./mvnw clean package
```

Windows：

```powershell
.\mvnw.cmd clean package
```

### 指定环境启动

```bash
java -jar target/loveta-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
```

也可以通过 Maven 启动时指定 profile：

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=test
```

### Docker 构建与运行

构建镜像：

```bash
docker build -t loveta-spring .
```

运行容器：

```bash
docker run --rm -p 8080:8080 \
  -e LOVETA_SQLPUB_USERNAME=your-username \
  -e LOVETA_SQLPUB_PASSWORD=your-password \
  -e LOVETA_REDIS_HOST=your-redis-host \
  -e LOVETA_ALIYUN_OSS_ACCESS_KEY_ID=your-access-key-id \
  -e LOVETA_ALIYUN_OSS_ACCESS_KEY_SECRET=your-access-key-secret \
  loveta-spring
```

当前 Dockerfile 默认以 `test` profile 启动：

```text
java -jar /loveta-spring.jar --spring.profiles.active=test
```

如需生产环境运行，请按部署目标调整 Dockerfile 或启动命令。

## 配置说明

### 默认配置

`application.yml`：

```yaml
spring:
  profiles:
    active: dev

sa-token:
  is-read-body: false
  is-read-cookie: false

aliyun:
  oss:
    access-key-id: ${LOVETA_ALIYUN_OSS_ACCESS_KEY_ID}
    access-key-secret: ${LOVETA_ALIYUN_OSS_ACCESS_KEY_SECRET}
    endpoint: oss-cn-hangzhou.aliyuncs.com
```

### 开发环境

`application-dev.yml`：

- MySQL：`jdbc:mysql://localhost/loveta`
- 用户名/密码：`root/root`
- OSS Bucket：`loveta-dev`
- 日志级别：项目包 debug

### 测试环境

`application-test.yml`：

- MySQL：`jdbc:mysql://mysql.sqlpub.com/loveta_test`
- 数据库账号：环境变量 `LOVETA_SQLPUB_USERNAME`
- 数据库密码：环境变量 `LOVETA_SQLPUB_PASSWORD`
- Redis Host：环境变量 `LOVETA_REDIS_HOST`
- OSS Bucket：`loveta-test`

### 生产环境

`application-prod.yml`：

- Sa-Token 禁止并发登录：`is-concurrent: false`
- OSS Bucket：`loveta`

生产环境还需要通过外部配置、环境变量或配置中心补充数据库、Redis、OSS 密钥等敏感配置。

## API 设计约定

项目接口整体采用 RESTful 风格：

| 操作      | HTTP 方法 | 示例                          |
|---------|---------|-----------------------------|
| 获取单个资源  | GET     | `/animals/{id}`             |
| 获取资源列表  | GET     | `/animals`                  |
| 获取分页列表  | GET     | `/animals/page`             |
| 创建资源    | POST    | `/animals`                  |
| 更新资源    | PUT     | `/animals`                  |
| 删除资源    | DELETE  | `/animals/{id}`             |
| 批量排序    | POST    | `/menus/batch-update-order` |
| 当前用户上下文 | GET     | `/aggregate/runtime-data`   |

统一返回结构：

```json
{
  "code": 200,
  "msg": "OK",
  "data": {}
}
```

分页返回结构封装在 `PageVO<T>` 中，包含分页元数据和 `records` 列表。

## 主要接口分组

| 模块   | 路径前缀                                | 说明               |
|------|-------------------------------------|------------------|
| 认证   | `/auth`                             | 发送验证码、验证码登录、退出登录 |
| 聚合数据 | `/aggregate`                        | 当前用户运行时数据        |
| 用户   | `/users`                            | 用户、用户角色、用户权限     |
| 角色   | `/roles`                            | 角色、角色菜单、角色权限     |
| 权限   | `/permissions`                      | 权限列表、当前权限、权限同步   |
| 菜单   | `/menus`                            | 菜单树、当前菜单、排序      |
| 动物   | `/animals`                          | 动物档案、列表、分页       |
| 动物分类 | `/animal-categories`                | 动物分类维护           |
| 区域   | `/areas`                            | 区域维护             |
| 捐赠   | `/donations`                        | 捐赠记录             |
| 财务账户 | `/financial-accounts`               | 财务账户             |
| 财务分类 | `/financial-transaction-categories` | 财务交易分类           |
| 财务流水 | `/financial-transactions`           | 财务交易明细           |
| 物资   | `/materials`                        | 物资资料、物资库存查询      |
| 物资分类 | `/material-categories`              | 物资分类             |
| 仓库   | `/material-warehouses`              | 物资仓库             |
| 库存   | `/material-stocks`                  | 物资库存             |
| 采购   | `/material-purchases`               | 采购记录             |
| 出入库  | `/material-movements`               | 出入库流水            |
| 论坛板块 | `/forums`                           | 板块管理、排序          |
| 帖子   | `/posts`                            | 帖子发布、编辑、分页       |
| OSS  | `/oss`                              | 文件访问 URL、表单直传参数  |

## 权限设计

权限码使用冒号分隔，体现模块、资源与动作层级：

```text
animal:get
animal:list
animal:page
animal:save
animal:update
animal:remove
financial:transaction:list
material:warehouse:update
permission:sync
```

常见动作：

| 动作       | 含义       |
|----------|----------|
| `get`    | 查询单个资源   |
| `list`   | 查询资源列表   |
| `page`   | 分页查询     |
| `save`   | 新增       |
| `update` | 修改       |
| `remove` | 删除       |
| `sync`   | 同步       |
| `post`   | 上传或提交类动作 |

权限同步逻辑位于 `PermissionServiceImpl#getPermissionCodeSetFromReflection`：

- 扫描 `com.github.lingkai5wu.loveta.controller` 包下带 `@SaCheckPermission` 的方法。
- 收集注解中的权限码。
- 自动加入 `*` 超级权限。
- 对存在多个叶子权限的父级自动补充通配权限，例如 `animal:*`。
- `/permissions/sync` 会删除数据库中已不存在的权限码，并新增代码中出现但数据库不存在的权限码。

## 数据库说明

初始化脚本位于 `init.sql`，包含以下主要表：

| 表                                | 说明     |
|----------------------------------|--------|
| `user`                           | 用户     |
| `role`                           | 角色     |
| `permission`                     | 权限     |
| `menu`                           | 菜单     |
| `user_role`                      | 用户角色关系 |
| `role_permission`                | 角色权限关系 |
| `role_menu`                      | 角色菜单关系 |
| `animal`                         | 动物档案   |
| `animal_category`                | 动物分类   |
| `area`                           | 区域     |
| `location`                       | 位置     |
| `donation`                       | 捐赠     |
| `financial_account`              | 财务账户   |
| `financial_transaction`          | 财务流水   |
| `financial_transaction_category` | 财务流水分类 |
| `material`                       | 物资     |
| `material_category`              | 物资分类   |
| `material_warehouse`             | 仓库     |
| `material_stock`                 | 库存     |
| `material_purchase`              | 采购记录   |
| `material_movement`              | 出入库记录  |
| `forum`                          | 论坛板块   |
| `post`                           | 帖子     |
| `report`                         | 举报     |

脚本主要用于初始化表结构。导入后仍需要根据实际环境准备基础用户、角色、菜单和权限数据。

## 开发约定

### 新增业务模块建议流程

1. 在 `model/po` 新增数据库实体，并使用 `@TableName` 标注表名。
2. 在 `model/dto` 新增保存、更新 DTO，并添加必要的 Validation 注解。
3. 在 `model/vo` 新增返回对象，避免直接暴露 PO。
4. 在 `mapper` 新增 Mapper 接口。
5. 如需复杂查询，在 `resources/mapper` 新增 XML SQL。
6. 在 `service` 与 `service/impl` 新增业务接口和实现。
7. 在 `controller` 暴露 RESTful API，并添加 `@SaCheckPermission`。
8. 调用 `/permissions/sync` 同步权限码。
9. 如前端依赖类型声明，执行 Maven 构建生成 TypeScript 类型。

### DTO / VO 使用

- 新增、修改、登录等请求使用 DTO。
- 列表、详情、聚合数据返回使用 VO。
- 简单枚举状态通过 `enums` 包统一维护。
- 分页请求使用 `PageDTO`，分页返回使用 `PageVO`。

### Mapper XML 使用

建议将以下查询放入 XML：

- 多表关联查询。
- 返回 BasicVO、VO 的查询。
- 包含动态条件、排序或复杂筛选的查询。
- 需要和前端页面结构强绑定的聚合查询。

### 异常处理

`exception` 包中包含：

- 通用异常处理。
- 数据访问异常处理。
- 参数校验异常处理。
- Sa-Token 异常处理。

接口应尽量通过统一异常处理和 `Result` 返回结构对前端保持稳定响应。

## 部署建议

- 生产环境不要在配置文件中提交数据库密码、Redis 密码、OSS 密钥等敏感信息。
- 使用环境变量、Docker Secret、Kubernetes Secret 或配置中心管理敏感配置。
- 为 MySQL、Redis、OSS 分别准备生产级账号和最小权限策略。
- 根据实际业务量调整 Hikari 连接池、Redis 连接池和 JVM 参数。
- Dockerfile 当前默认 `test` profile，生产镜像应切换为 `prod` 或通过启动命令覆盖。
- 建议在 CI 中执行 `mvn test`、`mvn package` 和必要的接口回归测试。

## 常见问题

### 启动时报数据库连接失败

检查以下项：

- MySQL 是否启动。
- 数据库 `loveta` 是否已创建。
- `init.sql` 是否已导入。
- `application-dev.yml` 中的用户名、密码、端口和库名是否正确。

### 登录后访问接口仍提示未登录

检查以下项：

- 前端或 API 调试工具是否携带 Sa-Token 返回的 Token。
- Token 名称是否与 Sa-Token 返回字段一致。
- Redis 是否正常运行，测试/生产环境尤其需要确认 Redis Host 配置。

### `/permissions/sync` 后权限不符合预期

检查以下项：

- Controller 方法是否添加了 `@SaCheckPermission`。
- 权限码是否符合层级命名。
- 当前用户是否拥有 `permission:sync` 权限。
- 同步逻辑会删除数据库中存在但代码注解中已不存在的权限码。

### Maven 构建时 TypeScript 生成失败

检查 `pom.xml` 中 `typescript-generator-maven-plugin` 的 `outputFile`
。默认路径是作者本机前端项目目录，在其他机器上可能不存在或不可写，需要修改为本机前端项目路径，或在临时构建时跳过该插件执行。

## 相关文档

- [RESTful API 设计参考](https://restfulapi.cn/)
- [Apifox API 文档](https://loveta-dev.apifox.cn)
- [权限编码设计](doc/Permission.md#权限编码)
