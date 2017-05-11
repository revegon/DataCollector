/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacollector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Xenon
 */
public class Crawler {
    
    private static final String USER_AGENT
            = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    
    private static final String[] fields = {"", //just me
                    "posts", "groups", "likes"};
    
    private int page_no=1;
    
    private String name;
    private String reg;

    public Crawler(String name, String reg) {
        this.name = name;
        this.reg = reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public void crawl(String token)
    {
        try {
//            String field = "posts";
            //        String token = "EAACEdEose0cBAJUZBtjZAdD70Kx8CLJrstf3TqroZBPB44v80utYCd4Pgo4FJCInKq5ZBZAzxWlZCQaZAE9c7VaTgnDnMRcK8yCdCtZAD8RWl6R7KkZCtg39yM5Mbhw6HdBX6JLWBkTwqj4hPy4mUfrGLREjhXszbm8ARr7Wy7FShaAuoym9OQPgoI8UhMi4axAUZD";
            for(String field : fields)
            {
                String url;
            if(field.equals("likes")) url = "https://graph.facebook.com/v2.3/me/"+field+"?summary=total_count&access_token=" + token;
            else url = "https://graph.facebook.com/v2.3/me/"+field+"?access_token=" + token;
            
            URL urlobj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlobj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            String link;
               if(field.isEmpty()) field ="me";
                System.out.println(field);

            do{
            link = read(con.getInputStream(), field);
            if(link == null)  break;
            
            URL openurl = new URL(link);
            con = (HttpURLConnection) openurl.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            }while(true);
            page_no=1;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String read(InputStream is, String dtname) throws IOException{
        
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String in;
            while ((in = br.readLine()) != null) {
                response.append(in+"\n");
            }
//            System.out.println(response.toString());
            write(response.toString(), dtname);
        try {    
            JSONObject obj = new JSONObject(response.toString());
            JSONObject jo = obj.getJSONObject("paging");
            String res =(String) jo.get("next");
            System.out.println(res);
//            page_no++;
            return res;
        } catch (JSONException ex) {
            return null;
        }
    }

    private void write(String string, String dtname) throws IOException {
        File file = new File("F:/res/data/"+name+"_"+reg+"/"+dtname+"_"+ page_no++ +".txt");
        System.out.println(file.exists());
        if(!file.exists())
        {
            System.out.println("making dirs");
            file.getParentFile().mkdirs();
        }
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(string);
        bw.close();
//        PrintWriter pw = new PrintWriter(file);
//        pw.append(string);
//        pw.close();
    }
    
}