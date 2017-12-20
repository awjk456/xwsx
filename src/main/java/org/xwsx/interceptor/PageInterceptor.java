package org.xwsx.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.xwsx.bean.BaseBean;
import org.xwsx.bean.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 *mybatis拦截器的标签，
 * @Signature------>类的数字签名，
 *          type是所要拦截的接口，
 *          method是所要拦截的方法(这个方法是mybatis源码里面用来获取sql语句的方法),
 *          args是传递的参数
 */
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class})})
public class PageInterceptor implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //首先先获取mybatis源码中获取jdbc的statment是在接口StatmentHandle,这个类在参数当中能获取到
        //invocation这个参数中有被我们拦截下来的对象,因为我们知道这个对象是StatmentHandle所以直接强转
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //mybatis提供的反射方法，反射StatmentHandle类
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
        //这个地方通过反射回来的对象来获取StatmentHandle中它的一个实现类--BaseStatmentHandle的一个属性MappedStatementHandle
        //我们之所以要获取到appedStatementHandle是因为，这里面保存的是我们在xml中定义的一条sql语句的信息
        //这里要注意，我们访问StatmentHandle是通过它的实现类RoutingStatementHandle中的delegate对象来的
        //所以后面ognl表达式要先写delegate
        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
        //这里是获取的id，这个id就是我们在xml一条sql语句中写的那个id
        String id = mappedStatement.getId();
        //判断这个id是不是以Bypage结尾的
        if(id.matches(".+ByPage$")){
            //通过statementHandle来获取这个sql对象
            BoundSql boundSql = statementHandler.getBoundSql();
            //获取原始的sql语句。。。。
            String sql = boundSql.getSql();

            //------------->这里插入的是获取总条数的部分
            //查询总条数
            String countSql = "select count(*) from ("+sql+")a";
            //获取Connection
            Connection connection = (Connection)invocation.getArgs()[0];
            //jdbc基本语法
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            //因为是原始sql语句，参数没有赋值，在这里赋值一下,通过反射获取参数
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            //执行获取总条数的sql

            ResultSet rs = countStatement.executeQuery();

            //这里是获取分页的参数，这个参数是在dao层传递过来的参数这个我们把Page对象封装了一下
            BaseBean baseBean = (BaseBean) boundSql.getParameterObject();
            Page page = baseBean.getPage();
            if(page.getCurrentPage() ==0){
                page.setCurrentPage(1);

            }
            if(rs.next()){
                page.setTotalNumbers(rs.getInt(1));
            }

            String pageSql = sql+" limit "+page.getDbIndex()+","+page.getDbNumber();
            System.out.println("查看sql语句"+pageSql);
            metaObject.setValue("delegate.boundSql.sql",pageSql);

        }

        //用反射调用被拦截的方法返回就是Statement
        return invocation.proceed();
    }

    /*
    被拦截的对象确定是否需要代理
    方法里面写的this是拦截器实例
    返回的是有代理权的实例
    如果经过plugin方法被代理了的会进入interceptor方法
     这个方法里面调用的wrap方法可以取到注解，通过注解可以获取到我们拦截的类型
     */
    @Override
    public Object plugin(Object target) {

        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
