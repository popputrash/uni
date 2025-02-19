import se.mau.DA343A.VT25.assignment2.Buffer;
import se.mau.DA343A.VT25.assignment2.Logger;

import java.io.*;

public class MyLogger extends Logger implements Runnable , Closeable {
    private Buffer<String> buffer = new Buffer<>();
    private Writer writer;
    private File logFile;
    private boolean closing = false;


    public MyLogger(Buffer<String> buf) throws IOException {
        this.buffer = buf;
        initalizeLogger();
    }

    @Override
    protected void writeMessage(String s) {
        try {
            writer.write(s + "\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        closing = true;
    }

    @Override
    public void run() {
        if(!this.closing && writer != null) {
            while(!this.closing) {
                String s = null;
                try {
                    s = buffer.get();
                    if(s != null) {
                        this.log(s);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void initalizeLogger() throws IOException {
        logFile = new File("log.txt");
        logFile.createNewFile();
        writer = new FileWriter(logFile, true);
    }
}
