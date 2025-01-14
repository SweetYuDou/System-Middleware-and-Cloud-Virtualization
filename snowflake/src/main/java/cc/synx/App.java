package cc.synx;

import cc.synx.SnowflakeUtil;

public class App {
    public static void main(String[] args) {
        SnowflakeUtil snowflakeUtil = new SnowflakeUtil(1L);
        System.out.println("first snowflake id is:" + snowflakeUtil.nextId());
    }
}
