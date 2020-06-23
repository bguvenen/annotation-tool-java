package anntool.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import anntool.datamodels.Tweet;

public class DatabaseIO
{
    protected static String readFromFile(String filePath)
    {
        String content = "";

        
        File fileDir = new File(filePath);
		BufferedReader in;
		try {

			in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));

			String line;

            while ((line = in.readLine()) != null)
            {
                content = content + line;
            }

			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return content;
    }

    protected static boolean writeToFile(String filePath, String content)
    {
        try
        {
            List<String> lines = Arrays.asList(content);
            Path textFile = Paths.get(filePath);
            Files.write(textFile, lines, StandardCharsets.UTF_8);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    // protected static boolean writeToFile(String filePath, String content)
    // {
    // FileWriter fileWriter = null;
    // BufferedWriter bufferedWriter = null;
    //
    // try
    // {
    // fileWriter = new FileWriter(filePath);
    // bufferedWriter = new BufferedWriter(fileWriter);
    //
    // bufferedWriter.write(content);
    // bufferedWriter.close();
    // fileWriter.close();
    //
    // return true;
    // }
    // catch (Exception e)
    // {
    // e.printStackTrace();
    // }
    //
    // return false;
    // }

    // returns false, if the directory already exists
    protected static boolean createDirectory(String directory)
    {
        return new File(directory).mkdirs();
    }

    protected static boolean dirExists(String path)
    {
        File directory = new File(path);
        return directory.exists();
    }

    protected static boolean fileExists(String path)
    {
        File file = new File(path);
        return file.canWrite();
    }

    protected static int getSubDirectoryCount(String directory)
    {
        if (!fileExists(directory))
            return 0;

        File folder = new File(directory);
        File[] directories = folder.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isDirectory();
            }
        });

        return directories.length;
    }

    protected static int getSubFileCount(String directory)
    {
        if (!fileExists(directory))
            return 0;

        File folder = new File(directory);
        File[] files = folder.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isFile();
            }
        });

        return files.length;
    }

    protected static String[] getDirectoryNames(String directory)
    {
        if (!fileExists(directory))
            return new String[0];

        File folder = new File(directory);
        File[] directories = folder.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isDirectory();
            }
        });

        String[] dirNames = new String[directories.length];
        for (int i = 0; i < directories.length; i++)
        {
            dirNames[i] = directories[i].getName();
        }
        return dirNames;
    }

    protected static String[] getFileNames(String directory)
    {
        if (!fileExists(directory))
            return new String[0];

        File folder = new File(directory);
        File[] files = folder.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isFile();
            }
        });

        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; i++)
        {
            fileNames[i] = files[i].getName();
        }
        return fileNames;
    }

    // cleans only files
    protected static boolean cleanDirectory(String directory)
    {
        File folder = new File(directory);

        for (File file : folder.listFiles())
        {
            if (!file.isDirectory())
            {
                file.delete();
            }
        }

        return true;
    }

    protected static boolean delete(String directoryOrFilePath)
    {
        File file = new File(directoryOrFilePath);

        return delete(file);
    }

    private static boolean delete(File file)
    {
        if (file.isDirectory())
        {
            if (file.list().length == 0)
            {
                file.delete();
                return true;
            }

            String[] files = file.list();

            for (String temp : files)
            {
                File fileDelete = new File(file, temp);
                delete(fileDelete);
            }

            if (file.list().length != 0)
            {
                return false;
            }
        }

        file.delete();
        return true;
    }
}
