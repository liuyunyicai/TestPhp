package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        // com.xxx.bean:自动生成的Bean对象会放到/java-gen/com/xxx/bean中
        Schema schema = new Schema(1, "bean");
        // DaoMaster.java、DaoSession.java、BeanDao.java会放到/java-gen/com/xxx/dao中
//        schema.setDefaultJavaPackageDao("com.hust.dao");
        // 创建表
        initDb(schema);
        // 自动创建表
        new DaoGenerator().generateAll(schema, args[0]);
    }

    // 初始化表信息
    private static void initDb(Schema schema) {
        Entity box = schema.addEntity("SelfInfo");
        box.addIdProperty();
        box.addStringProperty("name");
        box.addIntProperty("slots");
        box.addStringProperty("description");
    }

}
