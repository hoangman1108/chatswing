package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileCSV {
    public static List<Account> readCSV() {
        BufferedReader reader = null;
        List<Account> list = new ArrayList<Account>();
        try {
            String line = "";

            reader = new BufferedReader(new FileReader("src\\account\\account.csv"));

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    Account account = new Account();
                    account.setUsername(fields[0]);
                    account.setPassword(fields[1]);
                    account.setFullname(fields[2]);
                    list.add(account);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }

    public static void writeCSV(String account) {
        List<Account> list = readCSV();
        FileWriter fileWriter = null;
        File file = new File("src\\account\\acount.csv");

        try {
            //d:\cmd\hocsinh.csv
            fileWriter = new FileWriter("src\\account\\account.csv");
            for (Account ac : list) {
                fileWriter.append(ac.getUsername());
                fileWriter.append(",");
                fileWriter.append(ac.getPassword());
                fileWriter.append(",");
                fileWriter.append(ac.getFullname());
                fileWriter.append("\n");
            }
            fileWriter.append(account);

            fileWriter.append("\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static String findAccount(String account) {
        BufferedReader reader = null;
        List<Account> list = new ArrayList<Account>();
        try {
            String line = "";

            reader = new BufferedReader(new FileReader("src\\account\\account.csv"));
            int temp;
            while ((line = reader.readLine()) != null) {
                if ((temp = line.indexOf(account)) != -1){
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");
                    tokenizer.nextToken();
                    tokenizer.nextToken();
                    return  tokenizer.nextToken();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return "";
    }
}
