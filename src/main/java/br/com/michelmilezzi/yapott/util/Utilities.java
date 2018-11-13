package br.com.michelmilezzi.yapott.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.michelmilezzi.yapott.exception.YapottException;
import br.com.michelmilezzi.yapott.model.Config;

public final class Utilities {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utilities.class);
    
    private Utilities() {
    }

    public static void writeToFile(final File mirrorFile, final String fileToWrite, final List<Config> configs) throws YapottException {

        if (!mirrorFile.exists()) {
            throw new YapottException("Config file doesn't exists");
        }

        File finalFile = new File(fileToWrite);

        FileWriter fileWriter = null;
        FileReader fileReader = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {

            final Map<String, String> configMap = listToMap(configs);

            fileWriter = new FileWriter(finalFile);
            fileReader = new FileReader(mirrorFile);
            out = new PrintWriter(fileWriter);
            in = new BufferedReader(fileReader);

            String line;
            while ((line = in.readLine()) != null) {
                writeLine(out, configMap, line);
            }

        } catch (IOException e) {
            throw new YapottException("Error writing to file", e);
        } finally {

            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    LOGGER.error("| Unexpected error. ", e);
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    LOGGER.error("| Unexpected error. ", e);
                }
            }
            
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Exception e) {
                    LOGGER.error("| Unexpected error. ", e);
                }
            }
            
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    LOGGER.error("| Unexpected error. ", e);
                }
            }            

        }

    }
    
    private static void writeLine(PrintWriter out, final Map<String, String> configMap, String line) {
        boolean hasConfig = false;
        
        Iterator<Entry<String, String>> it = configMap.entrySet().iterator();
        while (it.hasNext()) {

            Entry<String, String> pair = it.next();
            final String key = String.valueOf(pair.getKey());
            
            Pattern p = Pattern.compile("^#?" + key + "(.*)");
            Matcher m = p.matcher(line);
            
            if (m.matches()) {
                hasConfig = true;
                out.println(outputConfig(key, String.valueOf(pair.getValue())));
                it.remove(); 
                break;
            }

        }                 
        
        if (!hasConfig) {
            out.println(line);
        }
    }

    public static File createBackupFile(final String originalFilename) throws YapottException {

        InputStream inStream = null;
        OutputStream outStream = null;

        try{

            final String backupFilename = createBackupFilename(originalFilename);

            File originalFile = new File(originalFilename);
            File backupFile = new File(backupFilename);

            inStream = new FileInputStream(originalFile);
            outStream = new FileOutputStream(backupFile);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = inStream.read(buffer)) > 0){
                outStream.write(buffer, 0, length);
            }

            inStream.close();
            outStream.close();

            return backupFile;
            
        }catch(IOException e){
            throw new YapottException("Error creating backup file", e);
        }        
        
    }
    
    private static Map<String, String> listToMap(final List<Config> configList) {
        Map<String, String> configMap = new HashMap<String, String>();
        for (Config config : configList) {
            configMap.put(config.getName(), config.getFormatedSetting());
        }
        return configMap;
    }
    
    private static String createBackupFilename(final String fileToWrite) {
        return MessageFormat.format("{0}.{1}", fileToWrite, new SimpleDateFormat("yyyyMMddHms").format(new Date()));
    }
    
    private static String outputConfig(String key, String value) {
        return MessageFormat.format("{0} = {1}\t\t\t# changed by yapott", key, value);
    }
    
}
