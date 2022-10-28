import java.io.*;
import java.util.ArrayList;

public class ImgCopy {
    static String rootPath = System.getProperty("user.dir");
    static String parentPath = new File(rootPath).getParent();
    static File files = new File(rootPath + "/posts");

    public static void main(String[] args) {
        printFile(files, "..");

    }

    public static void printFile(File file, String path) {

        if (file.getName().contains(".png")) {

           // System.out.println(file.getParent() + File.separator + file.getParentFile().getName() + File.separator + file.getName());
            String desPath = files.getParentFile().getParent() + File.separator + file.getParentFile().getName() + File.separator + file.getName();
            System.out.println(desPath);
            toCopy(file.getPath(), desPath);
            return;
        }
        //如果文件路径是个目录,就有子集

        if (file.isDirectory()) {

            File[] files = file.listFiles();
            ArrayList<File> arr = new ArrayList<>();
            for (File f : files) {
                arr.add(f);
            }

            for (File temp : arr) {
                printFile(temp, path + "/" + file.getName());
            }

        }
    }

    public static boolean toCopy(String res, String des) {

        boolean flag = false;
//输入源文件

        File file = new File(res);

        FileInputStream fr = null;

//复制目标文件

        File desFile = new File(des);
        File desParent = desFile.getParentFile();
        System.out.println(desParent);
        if (!desParent.exists()) {
            desParent.mkdirs();
        }

        FileOutputStream bw = null;

        try {

            fr = new FileInputStream(file);

            bw = new FileOutputStream(desFile);

//buffer

            byte[] b = new byte[512];

            while (fr.read(b) != -1) {

                bw.write(b);

            }

            bw.flush();

            flag = true;

        } catch (FileNotFoundException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        } catch (IOException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        } finally {

            if (fr != null)

                try {

                    fr.close();

                } catch (IOException e) {

// TODO Auto-generated catch block

                    e.printStackTrace();

                }

            if (bw != null) {

                try {

                    bw.close();

                } catch (IOException e) {

// TODO Auto-generated catch block

                    e.printStackTrace();

                }

            }

        }

        return flag;

    }

}

