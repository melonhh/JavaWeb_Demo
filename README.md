# JavaWeb_Demo
JavaWeb知识点补充
## EL标签 （expression language）
${}
1. . 与 [] 运算符
    * 同：`${sessionScope.user.username}` 同 `${sessionScope.user["username"]}`
    * 有时非要[]不可：比如变量名为user.demo, 则必须`${sessionScope["user.demo"]`
    * 动态：`[param.user]` --- 动态获取请求参数user
2. EL变量
    * 取变量数据 ：${username} --- 不规定域（但取不到局部/全局变量，即域之外的值）
    * 确定域：pageScope、requestScope、sessionScope、applicationScope
3. EL自动类型转换
    * ${param.count+10} 若count值为int型，则结果为30
    * request.getParameter()接收到的值统一为String
    * 但是感觉不太靠谱
4. EL隐藏变量
   1. pageContext --- ServletContext
        * 获取有关用户要求或页面的有关信息
        * pageContext.request.queryString  --- 获取请求的参数字符串
        * pageContext.request.requestURL  --- 获取请求的URL
        * pageContext.request.contextPath --- 获取web application的名称
        * 等等
   2. pageScope --- map
   3. requestScope --- map
   4. sessionScope --- map
   5. applicationScope --- map
   6. param --- map
   7. paramValues --- map
   8. header --- map
   9. headerValues --- map
   10. cookie
   11. initParam
5. EL运算符
    * 支持Java所有的运算符
    * <code>+ -  * / mod ?(三目) < > == </code>
6. EL逻辑运算符
    * &&、 ||、 ！
    * and、 or、 not
7. empty运算符
    * ${empty user} --- 检测user是否为空
8. 条件运算符
    * ${ A ? B: C}
## 自定义标签
SimpleTagSupport
1. 编写标签功能的Java类，继承SimpleTagSupport类
2. 编写标签库描述文件
3. 在jsp中导入和使用自定义标签库
4. 但是很遗憾，自己编写的不知道啥回事不能用，只能用jstl
> 实现一个简单的自定义标签
    
    1. 编写Java类
    public class SayHelloTag extends SimpleTagSupport {
        @Override
        public void doTag() throws JspException, IOException {
            getJspContext().getOut().println("hello world");
        }
    }
    2. 编写tld文件
    <uri>http://melon.cn/mytag</uri>
    <tag>
        <description>Outputs Hello, World</description>
        <name>helloWorld</name>
        <tag-class>cn.melon.myTag.SayHelloTag</tag-class>
        <body-content>empty</body-content>
    </tag>
    3.导入和使用
    <%@ taglib prefix="myTag" uri="http://melon.cn/mytag" %>
    <myTag:helloWorld/>
    
> 定义一个带属性的标签

例
1. 类中定义参数map（String）
2. 设置参数的getter、setter
3. 在doTag() 中使用 getJspContext().getAttribute(map)获取实际的对象
4. 在tld文件中设置<attribute>标签

> 定义一个带标签体的标签

例
1.      <mytag:hello map="map">
        <tr>
            <td>${name}</td>
            <td>${age}</td>
        </tr>
        </mytag:hello>
        
2.      <tag>
            <description>Outputs a colored tile</description>
            <name>hello</name>
            <tag-class>Test.MyTag</tag-class>
            <body-content>scriptless</body-content>
            <attribute>
                <name>map</name>
                <required>false</required>
                <fragment>true</fragment>
            </attribute>
        </tag>
    * scriptless 表示可以有标签体（静态的html标签，不能是jsp脚本）
    
3.      public void doTag() throws JspException, IOException {
            HashMap<String,Integer> maps = (HashMap<String,Integer>)(getJspContext().getAttribute(map));
            for (String str : maps.keySet()){
                getJspContext().setAttribute("name",str);
                getJspContext().setAttribute("age",maps.get(str));
                getJspBody().invoke(null);
            }
        }
    * `getJspBody().invoke(null);`表示立即输出整个内容到jsp页面
## JSTL标签（核心标签）
1. out   value="<<java>>"
    * value
    * defaultValue
    * escapeXML --- 是否转换特殊字符

2. set