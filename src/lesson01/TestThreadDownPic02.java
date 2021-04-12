package lesson01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习：多线程下载图片
public class TestThreadDownPic02 implements Runnable{
    private String url;
    private  String name;
    public TestThreadDownPic02(String url,String name){
        this.url = url;
        this.name = name;
    }
    //下载图片线程的执行体
    @Override
    public void run() {
        WebDownloader02 webDownloader = new WebDownloader02();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名为："+name);
    }

    public static void main(String[] args) {
        TestThreadDownPic thread01 = new TestThreadDownPic("http://hbimg.huabanimg.com/9fe54937f9d6be58319d6c0e2da469174f9c8908afba-8JiOjt_fw658/format/webp", "1.jpg");
        TestThreadDownPic thread02 = new TestThreadDownPic("http://hbimg.huabanimg.com/c4c38b69c665d99c6a8a4fce9f099d1190d626391de3ab-s0722P_fw658/format/webp", "2.jpg");
        TestThreadDownPic thread03 = new TestThreadDownPic("http://hbimg.huabanimg.com/40a88f68e39cd8be58b0b5b8a1b78b4f56da1dad18069-ZBWGEz_fw658/format/webp", "3.jpg");

        new Thread(thread01).start();
        new Thread(thread02).start();
        new Thread(thread03).start();
    }
}

//下载器
class WebDownloader02{
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

