package com.digisold.web.mybatis.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }) })
public class QueryInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		Object result = null;
		if (target instanceof Executor) {
			MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
			long start = System.currentTimeMillis();
			/** 执行方法 */
			Object parameter = null;
			if (invocation.getArgs().length > 1) {
				parameter = invocation.getArgs()[1];
			}
			String sql = mappedStatement.getBoundSql(parameter).getSql();
			// System.out.println("======================Mybatis
			// SQLPrint====================");
			// System.out.println(sql);
			result = invocation.proceed();
			// long end = System.currentTimeMillis();
			// System.out.println("查询消耗" + ((end - start) / 1000.0) + "秒！");
		}
		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
