package lesson01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

//创建线程方式三：实现Callable接口
//1.实现Callable接口，需要返回值类型
public class TestThreadDownPic03 implements Callable<Boolean> {
    private String url;
    private  String name;
    public TestThreadDownPic03(String url,String name){
        this.url = url;
        this.name = name;
    }
    //2.重写call方法，需要抛出异常,下载图片线程的执行体
    @Override
    public Boolean call() throws Exception {
        WebDownloader03 webDownloader = new WebDownloader03();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名为："+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThreadDownPic03 thread01 = new TestThreadDownPic03("http://hbimg.huabanimg.com/9fe54937f9d6be58319d6c0e2da469174f9c8908afba-8JiOjt_fw658/format/webp", "11.jpg");
        TestThreadDownPic03 thread02 = new TestThreadDownPic03("http://hbimg.huabanimg.com/c4c38b69c665d99c6a8a4fce9f099d1190d626391de3ab-s0722P_fw658/format/webp", "12.jpg");
        TestThreadDownPic03 thread03 = new TestThreadDownPic03("http://hbimg.huabanimg.com/40a88f68e39cd8be58b0b5b8a1b78b4f56da1dad18069-ZBWGEz_fw658/format/webp", "13.jpg");
        //3.创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        //4.提交执行--提交线程
        Future<Boolean> result1 = ser.submit(thread01);
        Future<Boolean> result2 = ser.submit(thread02);
        Future<Boolean> result3 = ser.submit(thread03);
        //打印结果
        Boolean res1 = result1.get();
        Boolean res2 = result2.get();
        Boolean res3 = result3.get();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        //5.关闭服务
        ser.shutdownNow();
    }


}

//下载器
class WebDownloader03{
    //下载方法
    public void downloader(String url,String name){
        //把一个url--->file
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}