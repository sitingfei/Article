package com.example;
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by huyf on 2015/11/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
        //Schema schema = new Schema(1, "cc");
//      当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
        Schema schema = new Schema(1, "cc.wudoumi.article.bean");
        schema.setDefaultJavaPackageDao("cc.wudoumi.article.dao");

        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
        addArticleType(schema);
        addArticleList(schema);
        addDefaultImage(schema);


        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
        // 其实，输出目录的路径可以在 build.gradle 中设置，有兴趣的朋友可以自行搜索，这里就不再详解。
        new DaoGenerator().generateAll(schema, "C:/qianjujun/code/Article/app/src/main/java/");
    }




    private static void addArticleType(Schema schema){
        Entity articleType = schema.addEntity("ArticleType");
        articleType.addLongProperty("_id").columnName("_id").primaryKey();
        articleType.addIntProperty("id");
        articleType.addStringProperty("name");
        articleType.addIntProperty("parentId").columnName("parent_id");
        articleType.addStringProperty("color");

    }


    private static void addArticleList(Schema schema){
        Entity articleType = schema.addEntity("Article");
        articleType.addLongProperty("_id").columnName("_id").primaryKey();
        articleType.addIntProperty("id");
        articleType.addLongProperty("time");
        articleType.addIntProperty("idFrom").columnName("id_from");
        articleType.addStringProperty("author");
        articleType.addStringProperty("title");
        articleType.addStringProperty("summary");
        articleType.addIntProperty("praiseNum").columnName("praise_num");
        articleType.addStringProperty("themeImage").columnName("theme_image");
        articleType.addIntProperty("idType").columnName("id_type");

    }


    private static void addDefaultImage(Schema schema){

        Entity articleType = schema.addEntity("DefaultImage");
        articleType.addLongProperty("_id").columnName("_id").primaryKey();
        articleType.addIntProperty("id");
        articleType.addIntProperty("typeId").columnName("type_id");
        articleType.addStringProperty("url");
    }
}
