package com.freelance.ascstb.sdfilesinfo.model.data;

import android.os.Build;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileResult {
    List<MyFile> fileList;
    int fileSize;
    List<String> fileExtensionList;

    public FileResult() {
    }

    public List<MyFile> getBiggestFiles(int top) {
        Collections.sort(fileList, new Comparator<MyFile>(){
            public int compare(MyFile obj1, MyFile obj2) {
                // ## Ascending order
                //return obj1.firstName.compareToIgnoreCase(obj2.firstName); // To compare string values
                return Long.valueOf(obj1.size).compareTo(obj2.size); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(obj1.empId); // To compare integer values
            }
        });

        return fileList.subList(fileList.size() - top, fileList.size());
    }

    public int getAverageFileSize(){
        int average = 0;
        int sum = 0;
        for (int i = 0; i < fileList.size(); i++) {
            sum += fileList.size();
        }

        average = sum / fileList.size();

        return average;
    }

    public String getMostFrequestFileExtensions() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for(int i = 0; i < fileList.size(); i++){
            if(map.get(fileList.get(i).extension) == null){
                map.put(fileList.get(i).extension,1);
            }else{
                map.put(fileList.get(i).extension, map.get(fileList.get(i).extension) + 1);
            }
        }


        int largest = 0;
        String stringOfLargest;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if( value > largest){
                largest = value;
                stringOfLargest = key;
            }
        }

        return "";
    }

    @Override
    public String toString() {
        return "FileResult{" +
                "fileList=" + fileList +
                ", fileSize=" + fileSize +
                ", fileExtensionList=" + fileExtensionList +
                '}';
    }

    public List<MyFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<MyFile> fileList) {
        this.fileList = fileList;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public List<String> getFileExtensionList() {
        return fileExtensionList;
    }

    public void setFileExtensionList(List<String> fileExtensionList) {
        this.fileExtensionList = fileExtensionList;
    }
}
