package logging;

public class FileLogger extends Logger {
    public FileLogger(String Message,int level){
        super(Message,level);
    }
    private String filepath;
    @Override
    public void log(String message)
    {
        System.out.println("Запись в файл [" + filepath + "]: " + message);
    }

    public void setfilepath(String path)
    {
        this.filepath = path;
    }
    @Override
    public String toString() {
        return "FileLogger [file=" + filepath + ", level=" + getLoglevel() + "]";
    }


}
