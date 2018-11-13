package br.com.michelmilezzi.yapott.converter;

public class ByteToMegaBytesConverter implements Converter<Long> {

    public static final int MEGABYTE_UNITY = 1048576;
    
    private static ByteToMegaBytesConverter instance;

    private ByteToMegaBytesConverter() {
        super();
    }

    public Long convert(Long byteValue) {
        return byteValue / MEGABYTE_UNITY;
    }

    public static ByteToMegaBytesConverter getInstance() {
        if (instance == null) {
            instance = new ByteToMegaBytesConverter();
        }
        return instance;
    }
    
}
