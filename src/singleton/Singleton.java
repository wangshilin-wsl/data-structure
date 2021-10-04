package singleton;
//双重校验锁实现对象单例（线程安全）
public class Singleton {
    public static volatile Singleton singleton;
    private Singleton(){}
    public static Singleton getSingleton(){
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if(singleton==null) {
            //类对象加锁
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
