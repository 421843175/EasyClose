package mvc.hou;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class EasyClose {
//    public static void main(String[] args) {
//        new AnGet();
//    }
    Class<EasyClose> t = EasyClose.class;
    String gc;
    public EasyClose(String gc){
        this.gc=gc;
        ArrayList<String> ar=saomiao();
        try {
            fs(ar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public EasyClose(){
        ArrayList<String> ar=saomiao();
        try {
            fs(ar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  String packPath(){
        return t.getPackage().getName();
    }

    public ArrayList<String> saomiao(){
        ArrayList<String> ar=new ArrayList<>();
        String s =  gc+"\\"+t.getPackage().getName().replace(".", "\\");
        //        System.out.println(s);
        File f=new File(s);
        for (String s1 : f.list()) {
            if(s1.indexOf("java")!=-1)
            {
                s1= s1.replace(".java","");
                ar.add(packPath()+"."+s1);
            }



        }
        return ar;

    }
    public void fs(ArrayList<String> ar) throws Exception {
        HashMap<Field,Class> ars=new HashMap();
        for (String s : ar) {
            Class<?> c = Class.forName(s);

            Field[] f = c.getDeclaredFields();
            for (Field field : f) {
                if(field.isAnnotationPresent(Close.class))
                {
                    ars.put(field,c);
                }
            }
        }
        for (Field f : ars.keySet()) {
//            Close p = f.getDeclaredAnnotation(Close.class);
            Class fgc = f.getType();
           // Object o = fgc.newInstance();

            Object o1 = f.get(ars.get(f).newInstance());
            if(o1!=null) {
                Method fc = fgc.getDeclaredMethod("close");
                fc.setAccessible(true);
                fc.invoke(o1);
                System.out.println(fc + "已经成功关闭");
            }
        }

    }
}
