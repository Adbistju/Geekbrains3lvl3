import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int count = 10;
    public static void main(String[] args) {
        System.out.println("Hi! Enter your text here. Write a command:  -print , to see last "+count+" lines of file content.");
        while (true){
            doWriterToFile(bufferReader());
        }

    }

    public static String bufferReader(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String content = null;
        try {
            content = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    static void doWriterToFile(String content) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(
                            new File("src/test.txt"),
                            true
                    )
            );
            String printS="-print";
            if(printS.equals(content)){
                printLastLines(count);
                return;
            }
            String printE="-exit";
            if(printE.equals(content)){
                System.exit(1);
                return;
            }
            bw.newLine();
            bw.write(
                    String.format(
                            (doInLine()+1)+". "+
                            content
                    )
            );
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static int doInLine() {
        int lineNumber = 0;
        try{
            File myFile =new File("src/test.txt");
            FileReader fileReader = new FileReader(myFile);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            while (lineNumberReader.readLine() != null){
                lineNumber++;
            }

            lineNumberReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return lineNumber;
    }

    private static void printLastLines(int count){
        int a = doInLine()-count;
        try {
            FileInputStream fin = new FileInputStream("src/test.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String line;
            List<String> tempLines = new ArrayList<>();
            while ((line = br.readLine()) != null){
                tempLines.add(line);
            }
            int y=0;
            for (int i=0; i<tempLines.size(); i++){
                if(y>=a){
                    System.out.println(tempLines.get(i));
                }
                y++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
