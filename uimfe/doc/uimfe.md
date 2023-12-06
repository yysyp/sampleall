### MEF micro front-end options:
1. Iframe:
    Cons:
       url 不同步。浏览器刷新 iframe url 状态丢失、后退前进按钮无法使用。
       UI 不同步，DOM 结构不共享。
       全局上下文完全隔离，内存变量不共享。
       慢。每次子应用进入都是一次浏览器上下文重建、资源重新加载的过程。

2. webpack module federation plugins

3. Single-spa: 

4. Qiankun:
   limit & solution: https://zhuanlan.zhihu.com/p/355419817?utm_id=0
   load multiple sub app: https://www.jianshu.com/p/6485de20eb70
   路由与应用绑定的方式简单直观，是微前端中最为常见的使用场景，通常我们会用这种方式将一堆独立域名访问的 MPA 应用，整合成一个一体化的 SPA 应用。

    但这类场景也有自己的局限性：
    由于URL/路由的 唯一性/排他性 的特点，这种方式只适用单实例场景需求
    微应用的调度都是由路由系统来自动处理的，虽然省事但是碰到更复杂的需求，如同一个路由下，根据不同的用户权限展示不同的微应用这类个性化诉求，需要写一些中间层代码来曲线救国
    应用挂载的容器节点等需提前准备好，不然碰到 动态/嵌套 路由这类情况，可能会因为路由 listener 监听触发的时序不确定，导致微应用无法完成挂载的问题

    qiankun 2.0 的发布带来一个全新的 API loadMicroApp，通过这个 API 我们可以自己去控制一个微应用加载/卸载，这个方式也是 qiankun 2.0 的重磅特性.
    import { MicroApp } from 'umi';    
    局限：比如我们会要求这类微应用必须是不带路由系统的 widget 类型，不然也会出现多实例时路由冲突的问题。
    若想要 loadMicroApp API 能符合预期的运行，我们需要确保被加载的微应用是不含自己的路由系统，否则会出现多个应用间路由系统互相 抢占/冲突 的情况。
    这种场景下，我们其实只需要确保微应用的路由系统不会干扰到全局的 URL 系统即可。幸运的是 react-router 的 memory history 模式很好的解决了这一问题。如果你是一个 umi 应用，只需要直接使用我们封装好的组件即可完成 memory history 的运行时切换
    import { MicroAppWithMemoHistory } from 'umi';

npm 包的更新下发需要依赖产品重新部署才会生效: 
1, 时间一长就容易出现依赖产品版本割裂导致的体验不一致
2, 无法灰度
3, 技术栈耦合   

5. Wujie:
web components + iframe
