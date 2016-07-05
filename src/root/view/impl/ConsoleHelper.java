package root.view.impl;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConsoleHelper {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final String EXIT = "EXIT";

    public static void writeMessage(String message){
        if (message != null) {
            System.out.println(message);
        }
    }

    public static String readString(){
        String result = "";
        while (true){
            try{
                result = reader.readLine();
                if (result == null||result.length() == 0) throw new Exception();
                break;
            }catch (Exception e){
                final String ERROR = "Произошла ошибка при попытке ввода текста. Попробуйте еще раз.";
                writeMessage(ERROR);
            }
            finally {
                if (result.equalsIgnoreCase(EXIT)){
                    System.exit(0);
                }
            }
        }
        return result;
    }

    public static int readInt(){
        int result = 0;
        while (true){
            try{
                String line = readString();
                if ( (line == null) || (line.length() == 0))throw new NumberFormatException();
                result = Integer.parseInt(line);
            } catch (NumberFormatException e){
                String notNumber = "You enter is not a number. Try again!!!";
                writeMessage(notNumber);
            }
            return result;
        }
    }

    public static int[] readIntsLineWithDelimiter(String s, int countCreteria) {
        int result[] = new int[countCreteria];
        while (true){
            try{
                String line = readString();
                if ( (line == null) || (line.length() == 0))throw new NumberFormatException();
                final String[] splittedString = line.split(" ");

                if (splittedString.length != countCreteria) throw new UnsupportedOperationException();
                for (int i = 0; i < countCreteria; i++) {
                    int temp = Integer.parseInt(splittedString[i]);
                    result[i] = temp;
                }
            } catch (NumberFormatException e){
                String notNumber = "One of your enter is not a number. Try again!!!";
                writeMessage(notNumber);
            } catch (UnsupportedOperationException e){
                String notNumber = "The width of matrix row is less when degree. Enter the line again";
                writeMessage(notNumber);
            }
            return result;
        }
    }
}

