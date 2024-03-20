# 权限

## 权限编码

权限编码按照层级结构进行组织，用`:`分割，反映系统中的模块、资源和操作，形如`menu:update`，具体的：

- 资源：需操作的对象。如`menus`表示对数据库的菜单进行操作。
- 操作：具体操作内容。如`menu:update`表示对数据库的菜单进行更新操作。

操作与函数名、MyBatis-Plus 的 [Service CRUD 接口](https://baomidou.com/pages/49cc81/#service-crud-%E6%8E%A5%E5%8F%A3)
操作命名相同。示例如下：

```java

@PutMapping
@SaCheckPermission("menu:update")
public SaResult updateMenu(@RequestBody Menu menu) {
    boolean updated = menuService.updateById(menu);
    if (!updated) {
        return SaResult.error("菜单不存在");
    }
    return SaResult.ok();
}
```