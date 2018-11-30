package InternetWorm;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Homework {

    static final String IMGURL_REG = "<img class=\"BDE_Image\" src=\"(.*?)\"";

    static String html="https://tieba.baidu.com/p/2256306796?red_tag=1781367364";



    //获得image标签
    private static List<String> getImageURL(String html){
        List<String> listImagheURL=new ArrayList<>();
        Matcher matcher=Pattern.compile(IMGURL_REG).matcher(html);
        while(matcher.find()){
            listImagheURL.add(matcher.group(1));
        }
        System.out.println(listImagheURL);
        return listImagheURL;
    }


    //解析HTML路径
    private static String getHtml(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        StringBuilder str=new StringBuilder();
        String len = null;
        while ((len=reader.readLine())!=null) {
            str.append(len,0,len.length());
            str.append("\n");
        }
        reader.close();
        stream.close();

        return str.toString();
    }
    //下载
    private static void download(List<String> imageURL){
        URL url=null;
        try {
            for(String ele:imageURL){
                url=new URL(ele);
                URLConnection connection=url.openConnection();
                String path = "h:\\EE\\" + ele.substring(ele.lastIndexOf("/") + 1);
                System.out.println(ele.substring(ele.lastIndexOf("/")+1));
                InputStream stream=connection.getInputStream();
                BufferedInputStream in=new BufferedInputStream(stream);
                FileOutputStream outputStream=new FileOutputStream(path);
                BufferedOutputStream out=new BufferedOutputStream(outputStream);

                int len=0;
                byte[] bytes=new byte[1024*8];
                while((len=in.read(bytes))!=-1){
                    out.write(bytes,0,len);
                    out.flush();
                }
                out.close();
                in.close();
            }
        } catch (IOException e) {
            System.out.println("下载失败");
        }

    }
    public static void main(String[] args) {
        String url= null;
        try {
            url = getHtml(html);
            List<String> imageURL = getImageURL(url);
            download(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
