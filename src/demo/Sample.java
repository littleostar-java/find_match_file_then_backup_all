package demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import util.StaticUtilTool;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sample {
    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            try {
                FileUtils.deleteDirectory(new File(StaticUtilTool.dest_root_path_str));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                do_delete_and_do_copy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static String path_parent = System.getProperty("user.dir") + File.separator + "json_";
    static File file = new File(path_parent, "array_.json");

    private static void do_delete_and_do_copy() throws IOException {
        StaticUtilTool staticUtilTool = new StaticUtilTool();

        String root_path = "C:" + File.separator + "WebstormProjects";
//        staticUtilTool.do_delete_by_path(root_path, "node_modules");

        LinkedList<String> linkedList = new LinkedList<String>();

        String readFileToString = FileUtils.readFileToString(file, "UTF-8");
        linkedList = gson.fromJson(readFileToString, linkedList.getClass());
//        use_list_get_json_write_to_file(linkedList);

        for (String str : linkedList) {
            staticUtilTool.do_copy_source_to_d_disk(str);
        }
        System.out.println("finish copy ............................................................");
    }

    private static void use_list_get_json_write_to_file(LinkedList<String> linkedList) throws IOException {
        String json = gson.toJson(linkedList);
        System.out.println(json);
        FileUtils.writeStringToFile(file, json, "UTF-8");
    }
}
