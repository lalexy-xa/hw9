package org.example;

import java.util.Scanner;

public class HttpImageStatusCli {
    void askStatus(){
        String code;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter HTTP status code");

        while (true){
            code = scanner.nextLine().trim();
            try{
                HttpStatusImageDownloader download = new HttpStatusImageDownloader();
                download.downloadStatusImage(Integer.parseInt(code));
                break;
            }catch (NumberFormatException nfe){
                System.out.println("Please enter valid number");
            }catch (Exception e){
                System.out.println("There is not image for HTTP status");
                break;
            }
        }

        scanner.close();

    }
}
