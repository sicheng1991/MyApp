package com.chimu.mylib.db.dbmanager;


import android.text.TextUtils;
import android.util.Log;

import com.chimu.mylib.db.OrmliteHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luorucai on 2017/5/4.
 * BaseDao实现类
 */
public class BaseDaoImp<T> implements BaseDao<T> {

    private final String TAG = this.getClass().getName();
    public Dao<T, Integer> dao;
    private Class<T> mClass;
    private String databaseName;
    private static ExecutorService executorService;

    public BaseDaoImp(OrmliteHelper helper, Class<T> clazz) {
        mClass = clazz != null ? clazz : initClazz();
        dao = helper.fetchDao(mClass);
        databaseName = helper.getDatabaseName();
        if (executorService == null)
            executorService = Executors.newCachedThreadPool();
    }

    private Class<T> initClazz() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (params[0] instanceof Class) {
            return (Class<T>) params[0];
        } else {
            return null;
        }
    }

    @Override
    public int create(T model) {
        int line = 0;
        try {
            long start = getTime();
            prepareDeal();
            line = dao.create(model);
            doLog("create[" + (getTime() - start) + "ms] 影响行数：" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public int create(List<T> list) {
        int line = 0;
        try {
            long start = getTime();
            prepareDeal();
            line = dao.create((T) list);
            doLog("create[" + (getTime() - start) + "ms] 影响行数：" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public int delete(T model) {
        int line = 0;
        try {
            long start = getTime();
            prepareDeal();
            line = dao.delete(model);
            doLog("delete[" + (getTime() - start) + "ms] 影响行数：" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public int delete(List<T> list) {
        int line = 0;
        try {
            long start = getTime();
            prepareDeal();
            line = dao.delete(list);
            doLog("delete[" + (getTime() - start) + "ms] 影响行数：" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public int delete(WhereInfo whereInfo) {
        int line = 0;
        try {
            long start = getTime();
            prepareDeal();
            DeleteBuilder<T, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder = (DeleteBuilder<T, Integer>) fetchQueryBuilder(deleteBuilder, whereInfo);
            line = dao.delete(deleteBuilder.prepare());
            doLog("update[" + (getTime() - start) + "ms] 影响行数：" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public int update(T model) {
        int line = 0;
        try {
            long start = getTime();
            prepareDeal();
            line = dao.update(model);
            doLog("update[" + (getTime() - start) + "ms] 影响行数：" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public int update(T model, WhereInfo whereInfo) {
        int line = 0;
        try {
            long start = getTime();
            prepareDeal();
            QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
            //获取第一条数据
            queryBuilder = (QueryBuilder<T, Integer>) fetchQueryBuilder(queryBuilder, whereInfo);
            T data = dao.queryForFirst(queryBuilder.prepare());
            //若不存在则添加
            if (data == null) {
                line = dao.create(model);
            } else {
                //存在则更新
                model = CastUtil.castModel(model, data);
                line = dao.update(model);
            }
            doLog("update[" + (getTime() - start) + "ms] 影响行数：" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public List<T> queryForAll() {
        List<T> all = new ArrayList<T>();
        try {
            long start = getTime();
            prepareDeal();
            all = dao.queryForAll();
            doLog("queryForAll[" + (getTime() - start) + "ms] 影响行数：" + all.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public List<T> queryForAll(WhereInfo whereInfo) {
        List<T> all = new ArrayList<T>();
        try {
            long start = getTime();
            prepareDeal();
            QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
            orderBy(queryBuilder, whereInfo.orders);
            all = dao.query(queryBuilder.prepare());
            doLog("queryForAll[" + (getTime() - start) + "ms] 影响行数：" + all.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public List<T> query(WhereInfo whereInfo) {
        List<T> all = new ArrayList<T>();
        try {
            long start = getTime();
            prepareDeal();
            QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
            orderBy(queryBuilder, whereInfo.orders);
            queryBuilder = (QueryBuilder<T, Integer>) fetchQueryBuilder(queryBuilder, whereInfo);
            all = dao.query(queryBuilder.prepare());
            doLog("query[" + (getTime() - start) + "ms] 影响行数：" + all.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public List<T> queryLimit(WhereInfo whereInfo) {
        List<T> all = new ArrayList<T>();
        try {
            long start = getTime();
            prepareDeal();
            QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
            orderBy(queryBuilder, whereInfo.orders);
            int offset = whereInfo.currentPage;
            if (offset != 0) {
                offset = (whereInfo.currentPage - 1) * whereInfo.limit + whereInfo.size;
            }
            queryBuilder.offset((long) offset);
            queryBuilder.limit((long) whereInfo.limit);
            queryBuilder = (QueryBuilder<T, Integer>) fetchQueryBuilder(queryBuilder, whereInfo);
            all = dao.query(queryBuilder.prepare());
            whereInfo.currentPage = ++whereInfo.currentPage;
            whereInfo.size = all.size();
            doLog("queryLimit[" + (getTime() - start) + "ms] 影响行数：" + all.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public List<T> query(QueryBuilder<T, Integer> queryBuilder) {
        List<T> all = new ArrayList<T>();
        try {
            long start = getTime();
            prepareDeal();
            all = dao.query(queryBuilder.prepare());
            doLog("query[" + (getTime() - start) + "ms] 影响行数：" + all.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public long countOf() {
        return countOf(null);
    }

    @Override
    public long countOf(WhereInfo whereInfo) {
        long line = 0;
        try {
            long start = getTime();
            prepareDeal();
            QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.setCountOf(true);
            if (null != whereInfo) {
                queryBuilder = (QueryBuilder<T, Integer>) fetchQueryBuilder(queryBuilder, whereInfo);
                line = dao.countOf(queryBuilder.prepare());
            } else {
                line = dao.countOf();
            }
            doLog("countOf[" + (getTime() - start) + "ms] 影响行数：" + line);
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public boolean isExist(WhereInfo whereInfo) {
        List<T> all = new ArrayList<T>();
        try {
            long start = getTime();
            prepareDeal();
            QueryBuilder<T, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder = (QueryBuilder<T, Integer>) fetchQueryBuilder(queryBuilder, whereInfo);
            all = dao.query(queryBuilder.prepare());
            doLog("isExist[" + (getTime() - start) + "ms] 影响行数：" + all.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !all.isEmpty();
    }

    @Override
    public int executeRaw(String statement, String... arguments) {
        int line = 0;
        try {
            long start = getTime();
            prepareDeal();
            line = dao.executeRaw(statement, arguments);
            doLog("executeRaw[" + (getTime() - start) + "ms] 影响行数：" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public Dao<T, Integer> fetchDao() {
        return dao;
    }

    @Override
    public String getTableName() {
        prepareDeal();
        return dao.toString();
    }

    @Override
    public int clearTable() {
        int line = 0;
        try {
            long start = getTime();
            prepareDeal();
            line = TableUtils.clearTable(dao.getConnectionSource(), mClass);
            doLog("clearTable[" + (getTime() - start) + "ms] 影响行数：" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public int dropTable() {
        int line = 0;
        try {
            if (dao.isTableExists()) {
                long start = getTime();
                line = TableUtils.dropTable(dao.getConnectionSource(), mClass, false);
                doLog("dropTable[" + (getTime() - start) + "ms] 影响行数：" + line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public void callInTransaction(Callable<T> callable) {
        TransactionManager transactionManager = new TransactionManager(dao.getConnectionSource());
        try {
            long start = getTime();
            prepareDeal();
            transactionManager.callInTransaction(callable);
            doLog("callInTransaction[" + (getTime() - start) + "ms] ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <CT> CT callBatchTasks(Callable<CT> callable) {
        try {
            long start = getTime();
            prepareDeal();
            CT ct = dao.callBatchTasks(callable);
            doLog("callBatchTasks[" + (getTime() - start) + "ms] ");
            return ct;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> void asyncTask(final DbRun<T> dbRun) {
        if (null != dbRun) {
            prepareDeal();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        long start = getTime();
                        T data = dbRun.run();
                        doLog("asyncTask[" + (getTime() - start) + "ms] ");
                        MessageInfo<T> info = new MessageInfo<T>();
                        info.what = HandlerHelper.WHAT_CALLBACK;
                        info.model = data;
                        info.dbRun = dbRun;
                        HandlerHelper.get().sendMessage(info.build());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 预处理
     */
    private void prepareDeal() {
        checkTable();
    }

    /**
     * 检查数据表
     */
    private void checkTable() {
        try {
            if (!dao.isTableExists()) {
                TableUtils.createTable(dao.getConnectionSource(), mClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建查询条件
     */
    private StatementBuilder<T, Integer> fetchQueryBuilder(StatementBuilder<T, Integer> queryBuilder, WhereInfo whereInfo) throws SQLException {
        List<com.chimu.mylib.db.dbmanager.Where> wheres = whereInfo.wheres;
        if (!wheres.isEmpty()) {
            Where<T, Integer> whereBuilder = queryBuilder.where();
            boolean isFirst = true;
            for (int i = 0; i < wheres.size(); i++) {
                com.chimu.mylib.db.dbmanager.Where where = wheres.get(i);
                if (TextUtils.isEmpty(where.name))
                    continue;
                // 处理and-or
                isFirst = appendAnd(whereBuilder, where, isFirst);
                // 等于
                if (com.chimu.mylib.db.dbmanager.Where.EQ.equals(where.op)) {
                    whereBuilder.eq(where.name, where.value);
                }
                // 模糊查询
                if (com.chimu.mylib.db.dbmanager.Where.LIKE.equals(where.op)) {
                    whereBuilder.like(where.name, where.value);
                }
                // between
                if (com.chimu.mylib.db.dbmanager.Where.BETWEEN.equals(where.op)) {
                    whereBuilder.between(where.name, where.low, where.high);
                }
                // lt 小于
                if (com.chimu.mylib.db.dbmanager.Where.LT.endsWith(where.op)) {
                    whereBuilder.lt(where.name, where.value);
                }
                // gt 大于
                if (com.chimu.mylib.db.dbmanager.Where.GT.endsWith(where.op)) {
                    whereBuilder.gt(where.name, where.value);
                }
                // ge 大于等于
                if (com.chimu.mylib.db.dbmanager.Where.GE.endsWith(where.op)) {
                    whereBuilder.ge(where.name, where.value);
                }
                // le 小于等于
                if (com.chimu.mylib.db.dbmanager.Where.LE.endsWith(where.op)) {
                    whereBuilder.le(where.name, where.value);
                }
                // ne 不等于
                if (com.chimu.mylib.db.dbmanager.Where.NE.endsWith(where.op)) {
                    whereBuilder.ne(where.name, where.value);
                }
                // in 包含
                if (com.chimu.mylib.db.dbmanager.Where.IN.endsWith(where.op)) {
                    whereBuilder.in(where.name, where.values);
                }
                // notIn 不包含
                if (com.chimu.mylib.db.dbmanager.Where.NOT_IN.endsWith(where.op)) {
                    whereBuilder.notIn(where.name, where.values);
                }
            }
        }
        return queryBuilder;
    }

    /**
     * 追加连接符
     */
    private boolean appendAnd(Where<T, Integer> whereBuilder, com.chimu.mylib.db.dbmanager.Where where, boolean isFirst) {
        if (!isFirst) {
            if (com.chimu.mylib.db.dbmanager.Where.AND.equals(where.andOr)) {
                whereBuilder.and();
            }
            if (com.chimu.mylib.db.dbmanager.Where.OR.endsWith(where.andOr)) {
                whereBuilder.or();
            }
        }
        return false;
    }

    /**
     * 排序
     */
    private void orderBy(QueryBuilder<T, Integer> queryBuilder, Map<String, Boolean> orders) {
        if (!orders.isEmpty()) {
            for (Map.Entry<String, Boolean> order : orders.entrySet()) {
                queryBuilder.orderBy(order.getKey(), order.getValue());
            }
        }
    }

    long getTime() {
        return System.currentTimeMillis();
    }

    private void doLog(String msg) {
            Log.d(TAG, msg + " | " + mClass.getSimpleName() + " | " + databaseName);
    }

}
