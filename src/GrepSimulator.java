import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class GrepSimulator {
    private static AtomicInteger totalLines = new AtomicInteger(0);
    private static AtomicInteger totalFiles = new AtomicInteger(0);

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please send the filepath and the search string");
            return;
        }
        String inputTextSearch = args[0];
        String filePath = args[1];

        File fileOrDirectory = new File(filePath);
        if (!fileOrDirectory.exists()) {
            System.out.println("The provided path does not exist");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        searchFiles(fileOrDirectory, inputTextSearch, executor);


    }

    private static void searchFiles(File fileOrDirectory, String inputTextSearch, ExecutorService executor) {
        if (fileOrDirectory.isFile()) {
            executor.execute(new FileSearchTask(fileOrDirectory, inputTextSearch));
        } else if (fileOrDirectory.isDirectory()) {
            for (File file : fileOrDirectory.listFiles()) {
                searchFiles(fileOrDirectory, inputTextSearch, executor);
            }
        }
    }

    static class FileSearchTask implements Runnable {

        File file;
        String searchString;

        public FileSearchTask(File file, String searchString) {
            this.file = file;
            this.searchString = searchString;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNumber = 0;
                boolean fileHasMatch = false;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    if (line.contains(searchString)) {
                        System.out.println("File:" + file.getName() + " Line " + lineNumber + " : " + line);
                        totalLines.incrementAndGet();
                        fileHasMatch = true;
                    }
                }
                if (fileHasMatch) {
                    totalFiles.incrementAndGet();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
