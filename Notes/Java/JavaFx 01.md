## JavaFX

JavaFX 是一个开源的下一代客户端应用平台，适用于基于Java构建的桌面、移动端和嵌入式系统。 它是许多个人和公司的共同努力的成果，目的是为开发丰富的客户端应用提供一个现代、高效、功能齐全的工具包。

---

## 开发环境

- JDK 17
- JavaFx SDK 18.0

> 注：在Java 8-11中内置

## JavaFx应用结构

> 窗口Stage
>
> > 场景scene
> >
> > > 根节点parent
> > >
> > > > 节点note

- 一次只能显示一个场景
- 一个场景可以显示多个节点

## 构建项目

- 添加JavaFx SDK ：文件夹中的lib

![image-20220609000116912](image-20220609000116912.png)

- 添加运行参数

> --module-path "自己的sdk-lib路径" --add-modules javafx.controls,javafx.fxml 

![image-20220609002905713](image-20220609002905713.png)

## 项目建设

### 必要代码

> - 启动代码` Application.launch(args);;`//启动程序
> - 在实现的方法中`stage.show();`//显示程序窗口