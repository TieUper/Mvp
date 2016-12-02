package com.example;



/**
 * 数据库实体生成generator
 */
public class MyClass {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.example.administrator.mvp.model.greendao");

        addCategory(schema);
        addNews(schema);
        new DaoGenerator().generateAll(schema, "F:/StudioWorkSpace/MvpEx/app/src/main/java/");


    }
    //添加栏目
    private static void addCategory(Schema schema) {
        Entity category = schema.addEntity("Category");
        category.addIdProperty();
        category.addLongProperty("ID").unique().notNull();
        category.addStringProperty("Category").notNull();
    }

    private static void  addNews(Schema schema ){

        Entity news = schema.addEntity("News");

        news.addIdProperty();
        news.addStringProperty("NewsID").unique().notNull();
        news.addLongProperty("CategoryId");
        news.addStringProperty("Title");
        news.addStringProperty("ReleseDate");
        news.addStringProperty("TopicID");
        news.addBooleanProperty("isClick").notNull();
        Entity preview = schema.addEntity("Preview");
        preview.addIdProperty();
        preview.addStringProperty("NewsID");
        preview.addIntProperty("Type");
        preview.addStringProperty("Body");
        preview.addStringProperty("Body1");
        preview.addStringProperty("Body2");

    }
//
//    private static void addCustomerOrder(Schema schema) {
//        Entity customer = schema.addEntity("Customer");
//        customer.addIdProperty();
//        customer.addStringProperty("name").notNull();
//
//        Entity order = schema.addEntity("Order");
//        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
//        order.addIdProperty();
//        Property orderDate = order.addDateProperty("date").getProperty();
//        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
//        order.addToOne(customer, customerId);
//
//        ToMany customerToOrders = customer.addToMany(order, customerId);
//        customerToOrders.setName("orders");
//        customerToOrders.orderAsc(orderDate);
//    }

//    public void testAddArticle()
//    {
//        User u = new User();
//        u.setName("张鸿洋");
//        new UserDao(getContext()).add(u);
//        Article article = new Article();
//        article.setTitle("ORMLite的使用");
//        article.setUser(u);
//        new ArticleDao(getContext()).add(article);
//
//    }
//
//    public void testGetArticleById()
//    {
//        Article article = new ArticleDao(getContext()).get(1);
//        L.e(article.getUser() + " , " + article.getTitle());
//    }
//
//    public void testGetArticleWithUser()
//    {
//
//        Article article = new ArticleDao(getContext()).getArticleWithUser(1);
//        L.e(article.getUser() + " , " + article.getTitle());
//    }
//
//    public void testListArticlesByUserId()
//    {
//
//        List<Article> articles = new ArticleDao(getContext()).listByUserId(1);
//        L.e(articles.toString());
//    }
//
//    public void testGetUserById()
//    {
//        User user = new UserDao(getContext()).get(1);
//        L.e(user.getName());
//        if (user.getArticles() != null)
//            for (Article article : user.getArticles())
//            {
//                L.e(article.toString());
//            }
//    }
//
//    public void testAddStudent() throws SQLException
//    {
//        Dao dao = DatabaseHelper.getHelper(getContext()).getDao(Student.class);
//        Student student = new Student();
//        student.setDao(dao);
//        student.setName("张鸿洋");
//        student.create();
//    }
}
