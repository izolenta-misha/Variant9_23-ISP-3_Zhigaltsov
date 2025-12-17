package logging;

public class Logger {
private String loglevel;
public String message;
public int level;
 public Logger(String Message,int level){
     if(level>0 && level<=4) {
         switch (level) {
             case 1:
                 this.loglevel = "DEBUG";
                 break;
             case 2:
                 loglevel = "WARNING";
                 break;
             case 3:
                 loglevel = "ERROR";
                 break;
             default:
                 loglevel = "INFO";
         }
         log(Message);
     }
     else{
         this.loglevel = "INFO";
         System.out.println("неправильно значение левела");
     }
 }
public void log (String message)
{
    System.out.println("Log:" + message);
}
        public void log(String message, int level) {
        if(level>0 && level<=4) {
            switch (level) {
               case 1:
                    loglevel = "DEBUG";
                    break;
                case 2:
                    loglevel = "WARNING";
                    break;
                case 3:
                    loglevel = "ERROR";
                    break;
               default:
                    loglevel = "INFO";
            }
            log(message);
        }
         }
public void log(String message , boolean error){
    if(error)
    {System.out.println("Ошибка: " + message);}
    else log (message);
}
@Override
public String toString(){
    return "Logger [level=" + loglevel + "]";
}
public String getLoglevel()
{
    return loglevel;
}

}
