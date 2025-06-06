package hsy.com.mysql.datasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 记录当前数据库
 * 提供切换功能
 */
public class DynamicDataSourceContextHolder {
    public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源的变量
     */
    public static void setDataSourceType(String dataSourceType) {
        log.info("切换到{}数据源", dataSourceType);
        CONTEXT_HOLDER.set(dataSourceType);
    }

    /**
     * 获取数据源的变量
     */
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置主库
     */
    public static void setMaster() {
        setDataSourceType(DataSourceType.MASTER.name());
    }

    /**
     * 设置从库
     * 采用随机的方式选择
     */
    public static void setSlave() {
        Random random=new Random();
        if (random.nextInt(2)<1){
            setDataSourceType(DataSourceType.SLAVE1.name());
        }else {
            setDataSourceType(DataSourceType.SLAVE2.name());
        }
    }

    /**
     * 清空数据源变量
     */
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}

